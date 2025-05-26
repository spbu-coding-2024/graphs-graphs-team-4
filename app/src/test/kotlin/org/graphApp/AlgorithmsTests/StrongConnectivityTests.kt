package org.graphApp.AlgorithmsTests

import org.graphApp.model.graph.DirectGraph
import org.graphApp.model.graph.Vertex
import org.graphApp.model.graph.algorithms.FindStrongCommunities
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


internal class StrongConnectivityTests {

    private lateinit var graph: DirectGraph<String, Long>
    private lateinit var finder: FindStrongCommunities<String, Long>

    @BeforeEach
    fun setUp() {
        graph = DirectGraph()
    }

    @Test
    @DisplayName("TestCase with only one strong connectivity")
    fun testCaseWithOneStrongConnectivity() {
        for (vertexID in 0..2) {
            graph.addVertex(vertexID.toString())
        }
        graph.addEdge("0", "1", 0)
        graph.addEdge("1", "2", 1)
        graph.addEdge("2", "0", 2)
        val result: MutableList<MutableList<Vertex<String>>>? = mutableListOf(mutableListOf())
        finder = FindStrongCommunities(graph)
        for (vertex in graph.vertices) {
            if (vertex.id == 0L || vertex.id == 1L || vertex.id == 2L) {
                result!![0].add(vertex)
            }
        }
        val result2: MutableList<MutableList<Vertex<String>>>? =
            finder.findStrongCommunitiesInGraph() as MutableList<MutableList<Vertex<String>>>?
        result2!![0].sortBy { it.id }
        Assertions.assertEquals(result, result2)
    }
}