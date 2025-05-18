package data.SQLiteMainLogic

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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
import org.jetbrains.exposed.sql.select

class SQLiteMainLogic<E, V>(val connection: SQLiteExposed) {

    fun saveToSQLiteDataBase(viewModel: GraphViewModel<V, E>, name: String): Boolean {
        return try {
            val id = connection.addGraph(
                name,
                viewModel.showEdgesWeights.value,
                isDirected = viewModel.graph.edges.any { it is DirectedEdge<*, *> }
            )
            if (id == -1) return false
            connection.addAllVertices(id, viewModel.vertices)
            connection.addAllEdges(id, viewModel.edges)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    fun readFromSQLiteDataBase(name: String): Pair<Graph<String, String>, Map<VertexViewModel<String>, Pair<Dp?, Dp?>?>>? {
        val gi = connection.getGraph(name) ?: return null
        val vertices = connection.getVertices(gi.id)
        val edges = connection.getEdges(gi.id)
        val placement = mutableMapOf<VertexViewModel<String>, Pair<Dp, Dp>>()

        val graph: Graph<String, String> = when {
            gi.isDirected && gi.isWeighted -> DirectWeightedGraph()
            gi.isDirected && !gi.isWeighted -> DirectGraph()
            !gi.isDirected && gi.isWeighted -> WeightedGraph()
            else -> UndirectedGraph()
        }

        val vertexMap = mutableMapOf<Long, VertexViewModel<String>>()
        vertices.forEach { vertexInfo ->
            val vertex = graph.addVertex(vertexInfo.label)
            val vertexViewModel = VertexViewModel(
                x = vertexInfo.x.dp,
                y = vertexInfo.y.dp,
                v = vertex,
                _labelVisible = androidx.compose.runtime.mutableStateOf(true)
            )
            vertexMap[vertexInfo.id] = vertexViewModel
            placement[vertexViewModel] = Pair(vertexInfo.x.dp, vertexInfo.y.dp)
        }

        edges.forEach { edgeInfo ->
            val fromVertex = vertexMap[edgeInfo.vertexFrom]
            val toVertex = vertexMap[edgeInfo.vertexTo]

            if (fromVertex != null && toVertex != null) {
                when {
                    gi.isDirected && gi.isWeighted -> {
                        (graph as DirectedWeightedGraph<String, String>).addEdge(
                            fromVertex.value,
                            toVertex.value,
                            edgeInfo.label,
                            edgeInfo.weight
                        )
                    }
                    gi.isDirected && !gi.isWeighted -> {
                        (graph as DirectedUnWeightedGraph<String, String>).addEdge(
                            fromVertex.value,
                            toVertex.value,
                            edgeInfo.label
                        )
                    }
                    !gi.isDirected && gi.isWeighted -> {
                        (graph as UndirectedWeightedGraph<String, String>).addEdge(
                            fromVertex.value,
                            toVertex.value,
                            edgeInfo.label,
                            edgeInfo.weight
                        )
                    }
                    else -> {
                        graph.addEdge(
                            fromVertex.value,
                            toVertex.value,
                            edgeInfo.label
                        )
                    }
                }
            }
        }

        return graph to placement
    }
}

object Graphs : IntIdTable() {
    val graphName = varchar("name", 255).uniqueIndex()
    val isDirected = bool("directed")
    val label = text("label")
    val isWeighted = bool("weighted")
}

object Vertices : IntIdTable() {
    val vertex_id = long("vid")
    val x = float("x_coord")
    val y = float("y_coord")
    val label = text("label")
    val graph_id = integer("graph_id").references(Graphs.id, onDelete = ReferenceOption.CASCADE)

    init {
        uniqueIndex(vertex_id, graph_id)
    }
}

object Edges : IntIdTable() {
    val fromVertex = long("from_id")
    val toVertex = long("to_id")
    val weight = text("weight")
    val label = text("label")
    val graph_id = integer("graph_id").references(Graphs.id, onDelete = ReferenceOption.CASCADE)
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

    fun addGraph(graphName: String, isWeightedVal: Boolean, isDirected: Boolean = false): Int {
        var id = -1
        transaction(dataBaseConnection) {
            try {
                val t = Graphs.insertAndGetId {
                    it[Graphs.graphName] = graphName
                    it[Graphs.isWeighted] = isWeightedVal
                    it[Graphs.isDirected] = isDirected
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
        transaction(dataBaseConnection) {
            Graphs.deleteWhere { Graphs.id eq graphID }
        }
    }

    fun deleteAll() {
        transaction(dataBaseConnection) {
            Graphs.deleteAll()
        }
    }

    fun getGraph(name: String): GraphInfo? {
        return transaction(dataBaseConnection) {
            Graphs.select { Graphs.graphName eq name }
                .limit(1).firstNotNullOfOrNull {
                    GraphInfo(
                        id = it[Graphs.id].value,
                        isDirected = it[Graphs.isDirected],
                        isWeighted = it[Graphs.isWeighted],
                        label = it[Graphs.label]
                    )
                }
        }
    }

    fun getVertices(graphID: Int): List<VertexInfo> {
        return transaction(dataBaseConnection) {
            Vertices.select { Vertices.graph_id eq graphID}
                .map {
                    VertexInfo(
                        id = it[Vertices.vertex_id],
                        x = it[Vertices.x],
                        y = it[Vertices.y],
                        label = it[Vertices.label]
                    )
                }
        }
    }

    fun getEdges(graphID: Int): List<EdgeInfo> {
        return transaction(dataBaseConnection) {
            Edges.select { Edges.graph_id eq graphID}
                .map {
                    EdgeInfo(
                        label = it[Edges.label],
                        vertexFrom = it[Edges.fromVertex],
                        vertexTo = it[Edges.toVertex],
                        weight = it[Edges.weight]
                    )
                }
        }
    }


}

data class GraphInfo(val id : Int, val isDirected: Boolean, val isWeighted: Boolean, val label : String)
data class EdgeInfo(val label : String, val vertexFrom : Long, val vertexTo : Long, val weight : String)
data class VertexInfo(val id : Long, val x : Float, val y : Float, val label : String)