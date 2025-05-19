package org.graphApp.data.Neo4j

import org.graphApp.model.graph.DirectedEdge
import org.graphApp.model.graph.Edge
import org.graphApp.model.graph.Vertex
import org.graphApp.model.graph.WeightedDirectedEdge
import org.graphApp.model.graph.WeightedEdge
import org.graphApp.viewmodel.graph.GraphViewModel
import org.neo4j.driver.AuthTokens
import org.neo4j.driver.GraphDatabase

class Neo4jDataBase<V,E> (
    val viewModel : GraphViewModel<V,E>,
    uri: String,
    username: String,
    password: String,
) {
    private val driver = GraphDatabase.driver(uri, AuthTokens.basic(username, password))
    private val session = driver.session()

    fun addVertex (vertex: Vertex<V>) {
        val params = mapOf(
            "id" to vertex.id,
            "element" to vertex.element
        )
        session.executeWrite { tx ->
            tx.run("CREATE (node: Vertex {id: \$id, element: \$element})" +
                    "RETURN node", params)

        }
    }

    fun addEdge (edge: Edge<E, V>) {
        val edgeType = when(edge) {
            is WeightedDirectedEdge<E, V> -> {
                val paramsWeightedDirectedEdge = mapOf(
                    "fromID" to edge.from.id,
                    "toID" to edge.to.id,
                    "element" to edge.element,
                    "weight" to edge.weight
                )
                session.executeWrite { tx ->
                    tx.run("MATCH(from: Vertex {id: \$fromID}), (to: Vertex {id: \$toID})" +
                            "CREATE (from)-[:CONNECTED_TO {element: \$element, weight: \$weight}]->(to)", paramsWeightedDirectedEdge)
                }

            }
            is DirectedEdge<E,V> -> {
                val directedEdge = mapOf(
                    "fromID" to edge.from.id,
                    "toID" to edge.to.id,
                    "element" to edge.element,
                )
                session.executeWrite { tx ->
                    tx.run("MATCH(from: Vertex {id: \$fromID}), (to: Vertex {id: \$toID})" +
                            "CREATE (from)-[:CONNECTED_TO {element: \$element}]->(to)", directedEdge)
                }

            }
            is WeightedEdge<E, V> -> {
                val paramsWeightedEdge = mapOf(
                    "V1" to edge.vertices.first,
                    "V2" to edge.vertices.second,
                    "element" to edge.element,
                    "weight" to edge.weight
                )
                session.executeWrite { tx ->
                    tx.run("MATCH(V1: Vertex {id: \$Vertex1ID}), (V2: Vertex {id: \$Vertex2ID})" +
                    "CREATE (V1)-[:CONNECTED_TO {element: \$element, weight: \$weight}]-(V2)", paramsWeightedEdge)
                }
            }

            else -> {
                val paramsEdge = mapOf(
                    "Vertex1ID" to edge.vertices.first.id,
                    "Vertex2ID" to edge.vertices.second.id,
                    "element" to edge.element
                )
                session.executeWrite { tx ->
                    tx.run("MATCH(V1: Vertex {id: \$Vertex1ID}), (V2: Vertex {id: \$Vertex2ID})" +
                            "CREATE (V1)-[:CONNECTED_TO {element: \$element}]-(V2)", paramsEdge)
                }
            }
        }

    }
}