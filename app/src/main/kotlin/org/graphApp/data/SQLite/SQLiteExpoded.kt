package data.SQLiteMainLogic

import androidx.compose.ui.unit.Dp
import org.graphApp.viewmodel.graph.*
import org.graphApp.model.graph.*
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.graphApp.data.SQLite.EdgeTable
import org.graphApp.data.SQLite.VertexTable
import org.graphApp.data.SQLite.GraphTable
import org.jetbrains.exposed.sql.select

class SQLiteMainLogic<E, V>(val connection: SQLiteExposed) {

    fun saveToSQLiteDataBase(viewModel: GraphViewModel<V, E>, name: String) {
        val id = connection.addGraph(
            name,
            viewModel.showEdgesWeights.value,
        )
        if (id == -1) return
        connection.addAllVertices(id, viewModel.vertices)
        connection.addAllEdges(id, viewModel.edges)
    }

    fun readFromSQLiteDataBase(name: String): Pair<Graph<String, String>, Map<VertexViewModel<String>, Pair<Dp?, Dp?>?>>? {
        val gi = connection.getGraph(name) ?: return null
        val vertices = connection.getVertices(gi.id)
        val edges = connection.getEdges(gi.id)
        val placement = mutableMapOf<VertexViewModel<String>, Pair<Dp, Dp>>()

        // Выбираем подходящий граф
        val graph: Graph<String, String> = when {
            gi.isDirected && gi.isWeighted -> DirectWeightedGraph()
            gi.isDirected && !gi.isWeighted -> DirectGraph()
            !gi.isDirected && gi.isWeighted -> WeightedGraph()
            else -> UndirectedGraph()
        }

        // TODO

        return graph to placement
    }


}

object Graphs : IntIdTable() {
    val GraphName = varchar("graphName", 255).uniqueIndex()
    val isDirected = bool("is_directed")
    val label = text("label")
    val isWeighted = bool("is_weighted")
}

object Vertices : IntIdTable() {
    val vertex_id = long("vertexID")
    val x = float("x")
    val y = float("y")
    val label = text("label")
    val graph_id = integer("graphID").references(Graphs.id, onDelete = ReferenceOption.CASCADE)

    init {
        uniqueIndex(vertex_id, graph_id)
    }
}

object Edges : IntIdTable() {
    val fromVertex = long("from_vertexID")
    val toVertex = long("to_vertexID")
    val weight = text("weight")
    val label = text("label")
    val graph_id = integer("graphID").references(Graphs.id, onDelete = ReferenceOption.CASCADE)
}

class SQLiteExposed(dbName: String) {
    val dataBaseConnection = Database.connect(
        "jdbc:sqlite:$dbName",
        driver = "org.sqlite.JDBC",
        setupConnection = { it.createStatement().execute("PRAGMA foreign_keys=ON") }
    )

    init {
        transaction(dataBaseConnection) {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(Edges, Vertices, Graphs)
        }
    }

    fun addGraph(graphName: String, isWeightedVal: Boolean): Int {
        var id = -1
        transaction(dataBaseConnection) {
            try {
                val t = Graphs.insertAndGetId {
                    it[Graphs.GraphName] = graphName
                    it[Graphs.isWeighted] = isWeightedVal
                    it[Graphs.label] = ""
                }
                id = t.value
            } catch (e: ExposedSQLException) {
                throw e
            }
        }
        return id
    }

    fun addEdge(graphID: Int, vertexFrom: Long, vertexTo: Long, weight: String, label: String) {
        transaction(dataBaseConnection) {
            Edges.insert {
                it[Edges.graph_id] = graphID
                it[Edges.weight] = weight
                it[Edges.fromVertex] = vertexFrom
                it[Edges.toVertex] = vertexTo
                it[Edges.label] = label
            }
        }
    }

    fun addVertex(graphID: Int, vertexID: Long, label: String, x: Float, y: Float) {
        transaction(dataBaseConnection) {
            Vertices.insert {
                it[Vertices.graph_id] = graphID
                it[Vertices.vertex_id] = vertexID
                it[Vertices.x] = x
                it[Vertices.y] = y
                it[Vertices.label] = label
            }
        }
    }

    fun addAllVertices(graphID: Int, vertices: Collection<VertexViewModel<*>>) {
        transaction(dataBaseConnection) {
            vertices.forEach { vertex ->
                try {
                    Vertices.insert {
                        it[Vertices.graph_id] = graphID
                        it[Vertices.vertex_id] = vertex.vertexID
                        it[Vertices.label] = vertex.value
                        it[Vertices.x] = vertex.x.value
                        it[Vertices.y] = vertex.y.value
                    }
                } catch (e: ExposedSQLException) {
                    throw e
                }
            }
        }
    }

    fun addAllEdges(graphID: Int, edges: Collection<EdgeViewModel<*,*>>) {
        transaction(dataBaseConnection) {
            edges.forEach { edge ->
                try {
                    Edges.insert {
                        it[Edges.graph_id] = graphID
                        it[Edges.label] = edge.label
                        it[Edges.weight] = edge.weight?.toString() ?: "0.0"
                        it[Edges.fromVertex] = edge.u.vertexID
                        it[Edges.toVertex] = edge.v.vertexID
                    }
                } catch (e: ExposedSQLException) {
                    throw e
                }
            }
        }
    }

    fun deleteGraph(graphID: Int) {
        transaction {
            Graphs.deleteWhere { Graphs.id eq graphID }
        }
    }

    fun deleteAll() {
        transaction {
            Graphs.deleteAll()
        }
    }

    fun getGraph(name: String): GraphInfo? {
        return transaction(dataBaseConnection) {
            GraphTable.select { GraphTable.graphName eq name }
                .limit(1).firstNotNullOfOrNull {
                    GraphInfo(
                        id = it[GraphTable.id].value,
                        isDirected = it[GraphTable.isDirected],
                        isWeighted = it[GraphTable.isWeighted],
                        label = it[GraphTable.label]
                    )
                }
        }
    }

    
    fun getVertices(graphID: Int): List<VertexInfo> {
        return transaction(dataBaseConnection) {
            VertexTable.select { VertexTable.graph_id eq graphID}
                .map {
                    VertexInfo(
                        id = it[VertexTable.vertex_id],
                        x = it[VertexTable.x],
                        y = it[VertexTable.y],
                        label = it[VertexTable.label]
                    )
                }
        }
    }
    fun getEdges(graphID: Int): List<EdgeInfo> {
        return transaction(dataBaseConnection) {
            EdgeTable.select { EdgeTable.graph_id eq graphID}
                .map {
                    EdgeInfo(
                        label = it[EdgeTable.label],
                        vertexFrom = it[EdgeTable.from],
                        vertexTo = it[EdgeTable.to],
                        weight = it[EdgeTable.weight]
                    )
                }
        }
    }
}
data class GraphInfo(val id : Int, val isDirected: Boolean, val isWeighted: Boolean, val label : String)
data class EdgeInfo(val label : String, val vertexFrom : Long, val vertexTo : Long, val weight : String)
data class VertexInfo(val id : Long, val x : Float, val y : Float, val label : String)
