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
    fun `two path with different length directed graph`() {

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

    @Test
    fun `little path directedWeighted graph (pos weights)`() {

        val vertex1 = directWeightedGraph.addVertex("1")
        val vertex2 = directWeightedGraph.addVertex("2")
        val vertex3 = directWeightedGraph.addVertex("3")

        directWeightedGraph.addEdge("1", "2", "КАД1", "50")
        directWeightedGraph.addEdge("2", "3", "КАД2", "100")

        val fordBellman = FordBellman(graph = directWeightedGraph)
        val result = fordBellman.fordBellman(vertex1.id, vertex3.id)

        assertNotNull(result)
        val (path, edges) = result!!
        assertEquals(2, edges.size)
        assertEquals(listOf(vertex1, vertex2, vertex3), path)
        assertEquals(0.0, fordBellman.d[vertex1.id])
        assertEquals(50.0, fordBellman.d[vertex2.id])
        assertEquals(150.0, fordBellman.d[vertex3.id])
    }

    @Test
    fun `two path with different length directedWeighted graph (pos weights)`() {

        val vertex1 = directWeightedGraph.addVertex("1")
        val vertex2 = directWeightedGraph.addVertex("2")
        val vertex3 = directWeightedGraph.addVertex("3")
        val vertex4 = directWeightedGraph.addVertex("4")
        val vertex5 = directWeightedGraph.addVertex("5")
        val vertex6 = directWeightedGraph.addVertex("6")

        directWeightedGraph.addEdge("1", "3", "ЗСД1", "10")
        directWeightedGraph.addEdge("3", "6", "ЗСД2", "20")
        directWeightedGraph.addEdge("1", "2", "КАД1", "30")
        directWeightedGraph.addEdge("2", "3", "КАД2", "30")
        directWeightedGraph.addEdge("3", "4", "КАД3", "40")
        directWeightedGraph.addEdge("4", "5", "КАД4", "50")
        directWeightedGraph.addEdge("5", "6", "КАД5", "60")

        val fordBellman = FordBellman(graph = directWeightedGraph)
        val result = fordBellman.fordBellman(vertex1.id, vertex6.id)

        assertNotNull(result)
        val (path, edges) = result!!
        assertEquals(7, edges)
        assertEquals(0.0, fordBellman.d[vertex1.id])
        assertEquals(30.0, fordBellman.d[vertex2.id])
        assertEquals(10.0, fordBellman.d[vertex3.id])
        assertEquals(50.0, fordBellman.d[vertex4.id])
        assertEquals(90.0, fordBellman.d[vertex5.id])
        assertEquals(30.0, fordBellman.d[vertex6.id])
    }

    @Test
    fun `test no path in directedWeighted graph (pos weights)`() {

        val vertex1 = directWeightedGraph.addVertex("1")
        val vertex2 = directWeightedGraph.addVertex("2")
        val vertex3 = directWeightedGraph.addVertex("3")

        directWeightedGraph.addEdge("1", "2", "Ботаническая", "10")

        val fordBellman = FordBellman(graph = directWeightedGraph)
        val result = fordBellman.fordBellman(vertex1.id, vertex3.id)

        assertNull(result)
    }

    @Test
    fun `little path directedWeighted graph (neg weights)`() {

        val vertex1 = directWeightedGraph.addVertex("1")
        val vertex2 = directWeightedGraph.addVertex("2")
        val vertex3 = directWeightedGraph.addVertex("3")

        directWeightedGraph.addEdge("1", "2", "КАД1", "50")
        directWeightedGraph.addEdge("2", "3", "КАД2", "-100")

        val fordBellman = FordBellman(graph = directWeightedGraph)
        val result = fordBellman.fordBellman(vertex1.id, vertex3.id)

        assertNotNull(result)
        val (path, edges) = result!!
        assertEquals(2, edges.size)
        assertEquals(listOf(vertex1, vertex2, vertex3), path)
        assertEquals(0.0, fordBellman.d[vertex1.id])
        assertEquals(50.0, fordBellman.d[vertex2.id])
        assertEquals(50.0, fordBellman.d[vertex3.id])
    }

    @Test
    fun `two path with different length directedWeighted graph (neg weights)`() {

        val vertex1 = directWeightedGraph.addVertex("1")
        val vertex2 = directWeightedGraph.addVertex("2")
        val vertex3 = directWeightedGraph.addVertex("3")
        val vertex4 = directWeightedGraph.addVertex("4")
        val vertex5 = directWeightedGraph.addVertex("5")
        val vertex6 = directWeightedGraph.addVertex("6")

        directWeightedGraph.addEdge("1", "3", "ЗСД1", "-10")
        directWeightedGraph.addEdge("3", "6", "ЗСД2", "-20")
        directWeightedGraph.addEdge("1", "2", "КАД1", "-30")
        directWeightedGraph.addEdge("2", "3", "КАД2", "-30")
        directWeightedGraph.addEdge("3", "4", "КАД3", "-40")
        directWeightedGraph.addEdge("4", "5", "КАД4", "-50")
        directWeightedGraph.addEdge("5", "6", "КАД5", "-60")

        val fordBellman = FordBellman(graph = directWeightedGraph)
        val result = fordBellman.fordBellman(vertex1.id, vertex6.id)

        assertNotNull(result)
        val (path, edges) = result!!
        assertEquals(7, edges)
        assertEquals(0.0, fordBellman.d[vertex1.id])
        assertEquals(-30.0, fordBellman.d[vertex2.id])
        assertEquals(-60.0, fordBellman.d[vertex3.id])
        assertEquals(-100.0, fordBellman.d[vertex4.id])
        assertEquals(-150.0, fordBellman.d[vertex5.id])
        assertEquals(-210.0, fordBellman.d[vertex6.id])
    }

    @Test
    fun `test no path in directedWeighted graph (neg weights)`() {

        val vertex1 = directWeightedGraph.addVertex("1")
        val vertex2 = directWeightedGraph.addVertex("2")
        val vertex3 = directWeightedGraph.addVertex("3")

        directWeightedGraph.addEdge("1", "2", "Ботаническая", "-10")

        val fordBellman = FordBellman(graph = directWeightedGraph)
        val result = fordBellman.fordBellman(vertex1.id, vertex3.id)

        assertNull(result)
    }

    @Test
    fun `test negative cycle weighted dir graph`() {
        val vertexA = directWeightedGraph.addVertex("A")
        val vertexB = directWeightedGraph.addVertex("B")
        val vertexC = directWeightedGraph.addVertex("C")
        val vertexD = directWeightedGraph.addVertex("D")

        directWeightedGraph.addEdge("A", "B", "Невский", "2")
        directWeightedGraph.addEdge("B", "C", "Литейный", "3")
        directWeightedGraph.addEdge("C", "D", "Садовая", "-7")
        directWeightedGraph.addEdge("D", "B", "Гороховая", "-1")

        val fordBellman = FordBellman(directWeightedGraph)
        val result = fordBellman.fordBellman(vertexA.id, vertexC.id)

        assertNull(result)
        assertTrue(fordBellman.hasNegativeCycle[vertexB.id] == true)
        assertTrue(fordBellman.hasNegativeCycle[vertexC.id] == true)
        assertTrue(fordBellman.hasNegativeCycle[vertexD.id] == true)
    }

    @Test
    fun `little path undirected graph`() {

        val vertex1 = undirectedGraph.addVertex("1")
        val vertex2 = undirectedGraph.addVertex("2")
        val vertex3 = undirectedGraph.addVertex("3")

        undirectedGraph.addEdge("1", "2", "КАД1")
        undirectedGraph.addEdge("2", "3", "КАД2")

        val fordBellman = FordBellman(graph = undirectedGraph)
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
    fun `two path with different length undirected graph`() {

        val vertex1 = undirectedGraph.addVertex("1")
        val vertex2 = undirectedGraph.addVertex("2")
        val vertex3 = undirectedGraph.addVertex("3")
        val vertex4 = undirectedGraph.addVertex("4")
        val vertex5 = undirectedGraph.addVertex("5")
        val vertex6 = undirectedGraph.addVertex("6")

        undirectedGraph.addEdge("1", "3", "ЗСД1")
        undirectedGraph.addEdge("3", "6", "ЗСД2")
        undirectedGraph.addEdge("1", "2", "КАД1")
        undirectedGraph.addEdge("2", "3", "КАД2")
        undirectedGraph.addEdge("3", "4", "КАД3")
        undirectedGraph.addEdge("4", "5", "КАД4")
        undirectedGraph.addEdge("5", "6", "КАД5")

        val fordBellman = FordBellman(graph = undirectedGraph)
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
    fun `test no path in undirected graph`() {

        val vertex1 = undirectedGraph.addVertex("1")
        val vertex2 = undirectedGraph.addVertex("2")
        val vertex3 = undirectedGraph.addVertex("3")

        undirectedGraph.addEdge("1", "2", "Ботаническая")

        val fordBellman = FordBellman(graph = undirectedGraph)
        val result = fordBellman.fordBellman(vertex1.id, vertex3.id)

        assertNull(result)
    }

    @Test
    fun `little path undirectedWeighted graph (pos weights)`() {

        val vertex1 = weightedGraph.addVertex("1")
        val vertex2 = weightedGraph.addVertex("2")
        val vertex3 = weightedGraph.addVertex("3")

        weightedGraph.addEdge("1", "2", "КАД1", "50")
        weightedGraph.addEdge("2", "3", "КАД2", "100")

        val fordBellman = FordBellman(graph = weightedGraph)
        val result = fordBellman.fordBellman(vertex1.id, vertex3.id)

        assertNotNull(result)
        val (path, edges) = result!!
        assertEquals(2, edges.size)
        assertEquals(listOf(vertex1, vertex2, vertex3), path)
        assertEquals(0.0, fordBellman.d[vertex1.id])
        assertEquals(50.0, fordBellman.d[vertex2.id])
        assertEquals(150.0, fordBellman.d[vertex3.id])
    }

    @Test
    fun `two path with different length undirectedWeighted graph (pos weights)`() {

        val vertex1 = weightedGraph.addVertex("1")
        val vertex2 = weightedGraph.addVertex("2")
        val vertex3 = weightedGraph.addVertex("3")
        val vertex4 = weightedGraph.addVertex("4")
        val vertex5 = weightedGraph.addVertex("5")
        val vertex6 = weightedGraph.addVertex("6")

        weightedGraph.addEdge("1", "3", "ЗСД1", "10")
        weightedGraph.addEdge("3", "6", "ЗСД2", "20")
        weightedGraph.addEdge("1", "2", "КАД1", "30")
        weightedGraph.addEdge("2", "3", "КАД2", "30")
        weightedGraph.addEdge("3", "4", "КАД3", "40")
        weightedGraph.addEdge("4", "5", "КАД4", "50")
        weightedGraph.addEdge("5", "6", "КАД5", "60")

        val fordBellman = FordBellman(graph = weightedGraph)
        val result = fordBellman.fordBellman(vertex1.id, vertex6.id)

        assertNotNull(result)
        val (path, edges) = result!!
        assertEquals(7, edges)
        assertEquals(0.0, fordBellman.d[vertex1.id])
        assertEquals(30.0, fordBellman.d[vertex2.id])
        assertEquals(10.0, fordBellman.d[vertex3.id])
        assertEquals(50.0, fordBellman.d[vertex4.id])
        assertEquals(90.0, fordBellman.d[vertex5.id])
        assertEquals(30.0, fordBellman.d[vertex6.id])
    }

    @Test
    fun `test no path in undirectedWeighted graph (pos weights)`() {

        val vertex1 = weightedGraph.addVertex("1")
        val vertex2 = weightedGraph.addVertex("2")
        val vertex3 = weightedGraph.addVertex("3")

        weightedGraph.addEdge("1", "2", "Ботаническая", "10")

        val fordBellman = FordBellman(graph = weightedGraph)
        val result = fordBellman.fordBellman(vertex1.id, vertex3.id)

        assertNull(result)
    }

    @Test
    fun `little path undirectedWeighted graph (neg weights)`() {

        val vertex1 = weightedGraph.addVertex("1")
        val vertex2 = weightedGraph.addVertex("2")
        val vertex3 = weightedGraph.addVertex("3")

        weightedGraph.addEdge("1", "2", "КАД1", "50")
        weightedGraph.addEdge("2", "3", "КАД2", "-100")

        val fordBellman = FordBellman(graph = weightedGraph)
        val result = fordBellman.fordBellman(vertex1.id, vertex3.id)

        assertNotNull(result)
        val (path, edges) = result!!
        assertEquals(2, edges.size)
        assertEquals(listOf(vertex1, vertex2, vertex3), path)
        assertEquals(0.0, fordBellman.d[vertex1.id])
        assertEquals(50.0, fordBellman.d[vertex2.id])
        assertEquals(50.0, fordBellman.d[vertex3.id])
    }

    @Test
    fun `two path with different length undirectedWeighted graph (neg weights)`() {

        val vertex1 = weightedGraph.addVertex("1")
        val vertex2 = weightedGraph.addVertex("2")
        val vertex3 = weightedGraph.addVertex("3")
        val vertex4 = weightedGraph.addVertex("4")
        val vertex5 = weightedGraph.addVertex("5")
        val vertex6 = weightedGraph.addVertex("6")

        weightedGraph.addEdge("1", "3", "ЗСД1", "-10")
        weightedGraph.addEdge("3", "6", "ЗСД2", "-20")
        weightedGraph.addEdge("1", "2", "КАД1", "-30")
        weightedGraph.addEdge("2", "3", "КАД2", "-30")
        weightedGraph.addEdge("3", "4", "КАД3", "-40")
        weightedGraph.addEdge("4", "5", "КАД4", "-50")
        weightedGraph.addEdge("5", "6", "КАД5", "-60")

        val fordBellman = FordBellman(graph = weightedGraph)
        val result = fordBellman.fordBellman(vertex1.id, vertex6.id)

        assertNotNull(result)
        val (path, edges) = result!!
        assertEquals(7, edges)
        assertEquals(0.0, fordBellman.d[vertex1.id])
        assertEquals(-30.0, fordBellman.d[vertex2.id])
        assertEquals(-60.0, fordBellman.d[vertex3.id])
        assertEquals(-100.0, fordBellman.d[vertex4.id])
        assertEquals(-150.0, fordBellman.d[vertex5.id])
        assertEquals(-210.0, fordBellman.d[vertex6.id])
    }

    @Test
    fun `test no path in undirectedWeighted graph (neg weights)`() {

        val vertex1 = weightedGraph.addVertex("1")
        val vertex2 = weightedGraph.addVertex("2")
        val vertex3 = weightedGraph.addVertex("3")

        weightedGraph.addEdge("1", "2", "Ботаническая", "-10")

        val fordBellman = FordBellman(graph = weightedGraph)
        val result = fordBellman.fordBellman(vertex1.id, vertex3.id)

        assertNull(result)
    }

}