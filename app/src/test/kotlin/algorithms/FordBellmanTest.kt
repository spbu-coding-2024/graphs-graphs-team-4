package test.algorithms

import androidx.compose.material.contentColorFor
import org.graphApp.model.graph.*
import org.graphApp.model.graph.algorithms.FordBellman
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.random.Random

class FordBellmanTest {

    private lateinit var directGraph: DirectGraph<String,String>
    private lateinit var undirectedGraph: UndirectedGraph<String, String>
    private lateinit var weightedGraph: WeightedGraph<String, String>
    private lateinit var directWeightedGraph: DirectWeightedGraph<String, String>

    val fordBellman = FordBellman(graph = directGraph)

    @BeforeEach
    fun init() {
        directGraph = DirectGraph()
        undirectedGraph = UndirectedGraph()
        weightedGraph = WeightedGraph()
        directWeightedGraph = DirectWeightedGraph()
    }

    @Test
    fun `little path directed graph`() {

        val vertex1 = directGraph.addVertex("1")
        val vertex2 = directGraph.addVertex("2")
        val vertex3 = directGraph.addVertex("3")

        directGraph.addEdge("1", "2", "КАД1")
        directGraph.addEdge("2", "3", "КАД2")

        val fordBellman = FordBellman(graph = directGraph)
        val result = fordBellman.fordBellman(vertex1.id, vertex3.id)

        assertNotNull(result)
        val (path, edges) = result!!
        assertEquals(2, edges.size)
        assertEquals(listOf(vertex1, vertex2, vertex3), path)
        assertEquals(0.0, fordBellman.d[vertex1.id])
        assertEquals(1.0, fordBellman.d[vertex2.id])
        assertEquals(2.0, fordBellman.d[vertex3.id])
    }

    @Test
    fun `two path with diffirent length directed graph`() {

        val vertex1 = directGraph.addVertex("1")
        val vertex2 = directGraph.addVertex("2")
        val vertex3 = directGraph.addVertex("3")
        val vertex4 = directGraph.addVertex("4")
        val vertex5 = directGraph.addVertex("5")
        val vertex6 = directGraph.addVertex("6")

        directGraph.addEdge("1", "3", "ЗСД1")
        directGraph.addEdge("3", "6", "ЗСД2")
        directGraph.addEdge("1", "2", "КАД1")
        directGraph.addEdge("2", "3", "КАД2")
        directGraph.addEdge("3", "4", "КАД3")
        directGraph.addEdge("4", "5", "КАД4")
        directGraph.addEdge("5", "6", "КАД5")

        val fordBellman = FordBellman(graph = directGraph)
        val result = fordBellman.fordBellman(vertex1.id, vertex6.id)

        assertNotNull(result)
        val (path, edges) = result!!
        assertEquals(7, edges)
        assertEquals(0.0, fordBellman.d[vertex1.id])
        assertEquals(1.0, fordBellman.d[vertex2.id])
        assertEquals(1.0, fordBellman.d[vertex3.id])
        assertEquals(2.0, fordBellman.d[vertex4.id])
        assertEquals(3.0, fordBellman.d[vertex5.id])
        assertEquals(2.0, fordBellman.d[vertex6.id])
    }

    @Test
    fun `test no path in directed graph`() {

        val vertex1 = directGraph.addVertex("1")
        val vertex2 = directGraph.addVertex("2")
        val vertex3 = directGraph.addVertex("3")

        directGraph.addEdge("1", "2", "Ботаническая")

        val fordBellman = FordBellman(graph = directGraph)
        val result = fordBellman.fordBellman(vertex1.id, vertex3.id)

        assertNull(result)
    }

}