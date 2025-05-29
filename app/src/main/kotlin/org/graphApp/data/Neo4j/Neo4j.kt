package org.graphApp.data.Neo4j

import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.graphApp.viewmodel.MainScreenViewModel
import org.graphApp.viewmodel.graph.EdgeViewModel
import org.graphApp.viewmodel.graph.GraphViewModel
import org.graphApp.viewmodel.graph.VertexViewModel
import org.neo4j.driver.Driver
import org.neo4j.driver.Session

const val STUB = 0


class Neo4jDataBase<V, E>(
    val mainViewModel: MainScreenViewModel<E>,
    val graph: GraphViewModel<V, E>,
    graphName: String,
    private val driver: Driver
) {
    private val currentGraphName = graphName
    private val gName = mapOf(
        "graphname" to currentGraphName
    )

    private fun <T> withSession(action: (Session) -> T): T {
        return driver.session().use { session -> action(session) }
    }

    enum class EdgeTypeNeo4j(val typeString: String) {
        DIRECTED("directedunweighted"),
        DIRECTED_WEIGHTED("directedweighted"),
        UNDIRECTED("undirectedunweighted"),
        WEIGHTED("undirectedweighted")
    }


    fun checkNeo4jConnection() {
        withSession { session ->
            val result = session.run("RETURN 1")
            if (result.single()[0].asInt() != 1) {
                throw IllegalStateException("The neo4j connection has been corrupted!")
            }
        }
    }

    private fun checkForExistingNameOfGraph() {
        withSession { session ->
            session.executeRead { tx ->
                val result = tx.run(
                    "MATCH (v:Vertex {graphname: \$graphname})\n" +
                            "RETURN COUNT(v) > 0 AS exists", gName
                )
                if (result.single().get("exists").asBoolean()) {
                    throw IllegalArgumentException("GraphName already exists in Neo4j Database!")
                }
            }

        }
    }


    private fun storeVertex(vertex: VertexViewModel<V>) {
        withSession { session ->

            val params = mapOf(
                "id" to vertex.vertexID,
                "element" to vertex.vertex.element,
                "graphname" to currentGraphName
            )
            session.executeWrite { tx ->
                tx.run(
                    "CREATE (:Vertex {id: \$id, element: \$element, graphname: \$graphname})", params
                )

            }
        }
    }

    private fun storeEdge(edge: EdgeViewModel<E, V>) {

        withSession { session ->
            if (mainViewModel.graphViewModel.isWeightedGraph.value && mainViewModel.graphViewModel.isDirectedGraph.value) {
                val paramsWeightedDirectedEdge = mapOf(
                    "fromID" to edge.u.vertexID,
                    "toID" to edge.v.vertexID,
                    "weight" to edge.weight,
                    "type" to EdgeTypeNeo4j.DIRECTED_WEIGHTED.typeString,
                    "graphname" to currentGraphName
                )
                session.executeWrite { tx ->
                    tx.run(
                        "MATCH (from:Vertex {id: \$fromID, graphname: \$graphname}), (to:Vertex {id: \$toID, graphname: \$graphname})" +
                                " CREATE (from)-[:CONNECTED_TO {weight: \$weight, type: \$type}]->(to)",
                        paramsWeightedDirectedEdge
                    )
                }
            }

            if (!mainViewModel.graphViewModel.isWeightedGraph.value && mainViewModel.graphViewModel.isDirectedGraph.value) {
                val directedEdge = mapOf(
                    "fromID" to edge.u.vertexID,
                    "toID" to edge.v.vertexID,
                    "type" to EdgeTypeNeo4j.DIRECTED.typeString,
                    "graphname" to currentGraphName
                )
                session.executeWrite { tx ->
                    tx.run(
                        "MATCH (from:Vertex {id: \$fromID, graphname: \$graphname}), (to:Vertex {id: \$toID, graphname: \$graphname})" +
                                " CREATE (from)-[:CONNECTED_TO {type: \$type}]->(to)", directedEdge
                    )
                }


            }
            if (mainViewModel.graphViewModel.isWeightedGraph.value && !mainViewModel.graphViewModel.isDirectedGraph.value) {
                val paramsWeightedEdge = mapOf(
                    "V1" to edge.u.vertexID,
                    "V2" to edge.v.vertexID,
                    "weight" to edge.weight,
                    "type" to EdgeTypeNeo4j.WEIGHTED.typeString,
                    "graphname" to currentGraphName
                )
                session.executeWrite { tx ->
                    tx.run(
                        "MATCH (Vertex1:Vertex {id: \$V1, graphname: \$graphname}), (Vertex2:Vertex {id: \$V2, graphname: \$graphname})" +
                                " CREATE (Vertex1)-[:CONNECTED_TO {weight: \$weight, type: \$type}]->(Vertex2)",
                        paramsWeightedEdge
                    )
                }
            }

            if (!mainViewModel.graphViewModel.isWeightedGraph.value && !mainViewModel.graphViewModel.isDirectedGraph.value) {
                val paramsEdge = mapOf(
                    "Vertex1ID" to edge.u.vertexID,
                    "Vertex2ID" to edge.v.vertexID,
                    "type" to EdgeTypeNeo4j.UNDIRECTED.typeString,
                    "graphname" to currentGraphName
                )
                session.executeWrite { tx ->
                    tx.run(
                        "MATCH (V1:Vertex {id: \$Vertex1ID, graphname: \$graphname}), (V2:Vertex {id: \$Vertex2ID, graphname: \$graphname})" +
                                " CREATE (V1)-[:CONNECTED_TO {type: \$type}]->(V2)", paramsEdge
                    )
                }
            }
        }
    }

    suspend fun storeGraph() {
        withContext(Dispatchers.IO) {
            checkNeo4jConnection()
            checkForExistingNameOfGraph()
            mainViewModel.graphViewModel.vertices.forEach { vertex ->
                storeVertex(vertex as VertexViewModel<V>)
            }
            mainViewModel.graphViewModel.edges.forEach { edge ->
                storeEdge(edge as EdgeViewModel<E, V>)
            }
        }
    }


    private fun uploadVertex() {
        withSession { session ->
            session.executeRead { tx ->
                val unloadVertices = tx.run(
                    "MATCH (node:Vertex {graphname: \$graphname}) RETURN node.element AS element, node.graphname AS graphname",
                    gName
                )
                unloadVertices.forEach { vertex ->
                    val element = vertex["element"].asObject()
                    if (element == null) {
                        throw IllegalStateException("Vertex element not found")
                    }
                    mainViewModel.graphViewModel.addVertex(element as V as String, 0.dp, 0.dp)
                }
            }
        }
    }

    private fun uploadEdge() {
        withSession { session ->
            session.executeRead { tx ->
                val unloadEdges = tx.run(
                    "MATCH (a:Vertex {graphname: \$graphname})-[edge:CONNECTED_TO]-(b:Vertex {graphname: \$graphname})" + "RETURN edge.type AS type",
                    gName
                )
                val mapForCorrectTypeEdge: MutableMap<String, Int> = mutableMapOf()
                unloadEdges.forEach { edge ->
                    val typeOfEdge = edge["type"].asString()
                    mapForCorrectTypeEdge.put(typeOfEdge, STUB)
                }
                if (mapForCorrectTypeEdge.size != 1) {
                    throw IllegalArgumentException("Incorrect type of edge")
                } else {
                    when (mapForCorrectTypeEdge.keys.first()) {

                        EdgeTypeNeo4j.DIRECTED_WEIGHTED.typeString -> {
                            val unloadAllEdges = tx.run(
                                "MATCH (a:Vertex {graphname: \$graphname})-[edge:CONNECTED_TO]->(b:Vertex {graphname: \$graphname})" +
                                        " RETURN edge.type AS type, edge.weight AS weight, a.id AS idFrom, b.id AS idTo",
                                gName
                            )
                            unloadAllEdges.forEach { edge ->
                                val IDFrom = edge["idFrom"].asLong()
                                val IDTo = edge["idTo"].asLong()
                                val edgeWeight = edge["weight"].asString()
                                mainViewModel.graphViewModel.createEdge(IDFrom, IDTo, edgeWeight)
                            }
                        }

                        EdgeTypeNeo4j.WEIGHTED.typeString -> {
                            val unloadAllEdges = tx.run(
                                "MATCH (a:Vertex {graphname: \$graphname})-[edge:CONNECTED_TO]-(b:Vertex {graphname: \$graphname})" +
                                        " RETURN edge.type AS type, edge.weight AS weight, a.id AS idFrom, b.id AS idTo",
                                gName
                            )
                            unloadAllEdges.forEach { edge ->
                                val IDFrom = edge["idFrom"].asLong()
                                val IDTo = edge["idTo"].asLong()
                                val edgeWeight = edge["weight"].asString()
                                mainViewModel.graphViewModel.createEdge(IDFrom, IDTo, edgeWeight)
                            }
                        }

                        EdgeTypeNeo4j.DIRECTED.typeString -> {
                            val unloadAllEdges = tx.run(
                                "MATCH (a:Vertex {graphname: \$graphname})-[edge:CONNECTED_TO]->(b:Vertex {graphname: \$graphname})" +
                                        " RETURN edge.type AS type, a.id AS idFrom, b.id AS idTo", gName
                            )
                            unloadAllEdges.forEach { edge ->
                                val IDFrom = edge["idFrom"].asLong()
                                val IDTo = edge["idTo"].asLong()
                                mainViewModel.graphViewModel.createEdge(IDFrom, IDTo, null)
                            }
                        }

                        EdgeTypeNeo4j.UNDIRECTED.typeString -> {
                            val unloadAllEdges = tx.run(
                                "MATCH (a:Vertex {graphname: \$graphname})-[edge:CONNECTED_TO]-(b:Vertex {graphname: \$graphname})" +
                                        " RETURN edge.type AS type, a.id AS idFrom, b.id AS idTo", gName
                            )
                            unloadAllEdges.forEach { edge ->
                                val IDFrom = edge["idFrom"].asLong()
                                val IDTo = edge["idTo"].asLong()
                                mainViewModel.graphViewModel.createEdge(IDFrom, IDTo, null)
                            }
                        }

                        else -> {
                            throw IllegalArgumentException("Incorrect type of edge")
                        }
                    }
                }
            }
        }
    }

    fun uploadGraph() = CoroutineScope(Dispatchers.IO).launch {
        checkNeo4jConnection()
        val type = withSession { session ->
            session.executeRead { tx ->
                val result = tx.run(
                    "MATCH (a:Vertex {graphname: \$graphname})-[edge:CONNECTED_TO]-(b:Vertex {graphname: \$graphname}) RETURN edge.type AS type LIMIT 1",
                    gName
                )
                if (result.hasNext()) {
                    result.next().get("type").asString()
                } else {
                    null
                }
            }
        }

        when (type) {
            EdgeTypeNeo4j.DIRECTED_WEIGHTED.typeString -> mainViewModel.createNewGraph(true, true)
            EdgeTypeNeo4j.WEIGHTED.typeString -> mainViewModel.createNewGraph(true, false)
            EdgeTypeNeo4j.DIRECTED.typeString -> mainViewModel.createNewGraph(false, true)
            EdgeTypeNeo4j.UNDIRECTED.typeString -> mainViewModel.createNewGraph(false, false)
            else -> throw IllegalArgumentException("Unknown edge type: $type")
        }

        uploadVertex()
        uploadEdge()
    }
}