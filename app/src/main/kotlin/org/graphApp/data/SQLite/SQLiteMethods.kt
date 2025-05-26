package data.SQLiteMainLogic

import org.graphApp.viewmodel.graph.*
import org.jetbrains.exposed.exceptions.ExposedSQLException
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.select

class SQLiteExposed(dbName: String) {
    val dataBaseConnection = Database.connect(
        "jdbc:sqlite:$dbName",
        driver = "org.sqlite.JDBC",
        setupConnection = { it.createStatement().execute("PRAGMA foreign_keys=ON") }
    )

    init {
        transaction(db = dataBaseConnection) {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(Edges, Vertices, Graphs)
        }
    }

    fun addGraph(graphName: String, isWeightedVal: Boolean, isDirected: Boolean = false): Int {
        var id = -1
        transaction(db = dataBaseConnection) {
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
        transaction(db = dataBaseConnection) {
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
        transaction(db = dataBaseConnection) {
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
        transaction(db = dataBaseConnection) {
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

    fun addAllEdges(graphID: Int, edges: Collection<EdgeViewModel<*, *>>) {
        transaction(db = dataBaseConnection) {
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

    fun getGraph(name: String): GraphInfo? {
        return transaction(db = dataBaseConnection) {
            Graphs.select { Graphs.graphName eq name }
                .limit(n = 1).firstNotNullOfOrNull {
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
        return transaction(db = dataBaseConnection) {
            Vertices.select { Vertices.graph_id eq graphID }
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
        return transaction(db = dataBaseConnection) {
            Edges.select { Edges.graph_id eq graphID }
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

data class GraphInfo(val id: Int, val isDirected: Boolean, val isWeighted: Boolean, val label: String)
data class EdgeInfo(val label: String, val vertexFrom: Long, val vertexTo: Long, val weight: String)
data class VertexInfo(val id: Long, val x: Float, val y: Float, val label: String)