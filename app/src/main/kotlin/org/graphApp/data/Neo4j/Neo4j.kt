package org.graphApp.data.Neo4j

import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.graphApp.model.graph.DirectedEdge
import org.graphApp.model.graph.Edge
import org.graphApp.model.graph.Vertex
import org.graphApp.model.graph.WeightedDirectedEdge
import org.graphApp.model.graph.WeightedEdge
import org.graphApp.viewmodel.MainScreenViewModel
import org.graphApp.viewmodel.graph.EdgeViewModel
import org.graphApp.viewmodel.graph.GraphViewModel
import org.graphApp.viewmodel.graph.VertexViewModel
import org.neo4j.driver.AuthTokens
import org.neo4j.driver.GraphDatabase

const val STUB = 0


class Neo4jDataBase<V, E>(
    val graphViewModel: GraphViewModel<V, E>,
    val mainViewModel: MainScreenViewModel<E>,
    uri: String,
    username: String,
    password: String,
) {


    private val driver = GraphDatabase.driver(uri.ifBlank { "bolt://localhost:7687" }, AuthTokens.basic(username, password))
    private val session = driver.session()


    enum class EdgeTypeNeo4j(val typeString: String) {
        DIRECTED("directedunweighted"),
        DIRECTED_WEIGHTED("directedweighted"),
        UNDIRECTED("undirectedunweighted"),
        WEIGHTED("undirectedweighted")
    }


    private fun storeVertex(vertex: VertexViewModel<V>, onComplete: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val params = mapOf(
                "id" to vertex.vertexID,
                "element" to vertex.vertex.element
            )
            session.executeWrite { tx ->
                tx.run(
                    "CREATE (node: Vertex {id: \$id, element: \$element})", params
                )

            }
        }
    }

    private fun storeEdge(edge: EdgeViewModel<E, V>, onComplete: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            if (graphViewModel.isWeightedGraph.value && graphViewModel.isDirectedGraph.value) {
                val paramsWeightedDirectedEdge = mapOf(
                    "fromID" to edge.u.vertexID,
                    "toID" to edge.v.vertexID,
                    "weight" to edge.weight,
                    "type" to EdgeTypeNeo4j.DIRECTED_WEIGHTED.typeString
                )
                driver.session().executeWrite { tx ->
                    tx.run(
                        "MATCH (from: Vertex {id: \$fromID}), (to: Vertex {id: \$toID})" +
                                "CREATE (from)-[:CONNECTED_TO {weight: \$weight, type: \$type}]->(to)",
                        paramsWeightedDirectedEdge
                    )
                }

            }
            if (!graphViewModel.isWeightedGraph.value && graphViewModel.isDirectedGraph.value) {
                val directedEdge = mapOf(
                    "fromID" to edge.u.vertexID,
                    "toID" to edge.v.vertexID,
                    "type" to EdgeTypeNeo4j.DIRECTED.typeString
                )
                driver.session().executeWrite { tx ->
                    tx.run(
                        "MATCH (from: Vertex {id: \$fromID}), (to: Vertex {id: \$toID})" +
                                "CREATE (from)-[:CONNECTED_TO {type: \$type}]->(to)", directedEdge
                    )
                }

            }
            if (graphViewModel.isWeightedGraph.value && !graphViewModel.isDirectedGraph.value) {
                val paramsWeightedEdge = mapOf(
                    "V1" to edge.u.vertexID,
                    "V2" to edge.v.vertexID,
                    "weight" to edge.weight,
                    "type" to EdgeTypeNeo4j.WEIGHTED.typeString
                )
                driver.session().executeWrite { tx ->
                    tx.run(
                        "MATCH (Vertex1: Vertex {id: \$V1}), (Vertex2: Vertex {id: \$V2})" +
                                "CREATE (Vertex1)-[:CONNECTED_TO {weight: \$weight, type: \$type}]-(Vertex2)",
                        paramsWeightedEdge
                    )
                }
            }

            if (!graphViewModel.isWeightedGraph.value && !graphViewModel.isDirectedGraph.value) {
                val paramsEdge = mapOf(
                    "Vertex1ID" to edge.u.vertexID,
                    "Vertex2ID" to edge.v.vertexID,
                    "type" to EdgeTypeNeo4j.UNDIRECTED.typeString
                )
                driver.session().executeWrite { tx ->
                    tx.run(
                        "MATCH(V1: Vertex {id: \$Vertex1ID}), (V2: Vertex {id: \$Vertex2ID})" +
                                "CREATE (V1)-[:CONNECTED_TO {type: \$type}]-(V2)", paramsEdge
                    )
                }
            }
        }
    }

    fun storeGraph() {
        val vertices = graphViewModel.vertices
        val edges = graphViewModel.edges
        vertices.forEach { vertex ->
            storeVertex(vertex, onComplete = {
                edges.forEach { edge ->
                    storeEdge(edge, {})
                }
            })
        }


    }


    private fun uploadVertex(onComplete: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            driver.session().executeRead { tx ->
                val unloadVertices = tx.run(
                    "MATCH (node:Vertex) RETURN node.id as id, node.element as element"
                )
                unloadVertices.forEach { vertex ->
                    val element = vertex["element"].asObject()
                    graphViewModel.addVertex(element as V, 0.dp, 0.dp)
                }
            }
        }
    }

    private fun uploadEdge(onComplete: () -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            driver.session().executeRead { tx ->
                val unloadEdges = tx.run(
                    "MATCH (a)-[edge:CONNECTED_TO]-(b)" + "RETURN edge.type AS type"
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
                            mainViewModel.isWeightedGraph = true
                            mainViewModel.isDirectedGraph = true
                            val unloadAllEdges = tx.run(
                                "MATCH (a:Vertex)-[edge:CONNECTED_TO]->(b:Vertex)" + "RETURN edge.type AS type, edge.weight AS weight, a.id AS idFrom, b.id AS idTo)",
                            )
                            unloadAllEdges.forEach { edge ->
                                val IDFrom = edge["idFrom"].toString().toLong()
                                val IDTo = edge["idTo"].toString().toLong()
                                val edgeWeight = edge["weight"].asString()
                                graphViewModel.createEdge(IDFrom, IDTo, edgeWeight)
                            }
                        }

                        EdgeTypeNeo4j.WEIGHTED.typeString -> {
                            mainViewModel.isWeightedGraph = true
                            mainViewModel.isDirectedGraph = false
                            val unloadAllEdges = tx.run(
                                "MATCH (a:Vertex)-[edge:CONNECTED_TO]-(b:Vertex)" + "RETURN edge.type AS type, edge.weight AS weight, a.id AS idFrom, b.id AS idTo)",
                            )
                            unloadAllEdges.forEach { edge ->
                                val IDFrom = edge["idFrom"].toString().toLong()
                                val IDTo = edge["idTo"].toString().toLong()
                                val edgeWeight = edge["weight"].asString()
                                graphViewModel.createEdge(IDFrom, IDTo, edgeWeight)
                            }
                        }

                        EdgeTypeNeo4j.DIRECTED.typeString -> {
                            mainViewModel.isWeightedGraph = false
                            mainViewModel.isDirectedGraph = true
                            val unloadAllEdges = tx.run(
                                "MATCH (a:Vertex)-[edge:CONNECTED_TO]->(b:Vertex)" + "RETURN edge.type AS type, a.id AS idFrom, b.id AS idTo)",
                            )
                            unloadAllEdges.forEach { edge ->
                                val IDFrom = edge["idFrom"].toString().toLong()
                                val IDTo = edge["idTo"].toString().toLong()
                                graphViewModel.createEdge(IDFrom, IDTo, null)
                            }
                        }

                        EdgeTypeNeo4j.UNDIRECTED.typeString -> {
                            mainViewModel.isWeightedGraph = false
                            mainViewModel.isDirectedGraph = false
                            val unloadAllEdges = tx.run(
                                "MATCH (a:Vertex)-[edge:CONNECTED_TO]-(b:Vertex)" + "RETURN edge.type AS type, a.id AS idFrom, b.id AS idTo)",
                            )
                            unloadAllEdges.forEach { edge ->
                                val IDFrom = edge["idFrom"].toString().toLong()
                                val IDTo = edge["idTo"].toString().toLong()
                                graphViewModel.createEdge(IDFrom, IDTo, null)
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

    fun uploadGraph() {
        graphViewModel.clear()
        uploadVertex {
            uploadEdge {
            }
        }
    }

}