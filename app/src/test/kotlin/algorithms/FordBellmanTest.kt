package algorithms

import org.graphApp.model.graph.*
import org.graphApp.model.graph.algorithms.FordBellman
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Tag
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class FordBellmanTest {

    private lateinit var directGraph: DirectGraph<String, String>
    private lateinit var undirectedGraph: UndirectedGraph<String, String>
    private lateinit var weightedGraph: WeightedGraph<String, String>
    private lateinit var directWeightedGraph: DirectWeightedGraph<String, String>


    @BeforeEach
    fun init() {
        directGraph = DirectGraph<String, String>()
        undirectedGraph = UndirectedGraph<String, String>()
        weightedGraph = WeightedGraph<String, String>()
        directWeightedGraph = DirectWeightedGraph<String, String>()
    }

    @Test
    @Tag("Little path directed graph (3 vertices)")
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
        assertEquals(listOf(vertex1.id, vertex2.id, vertex3.id), path)
        assertEquals(0.0, fordBellman.d[vertex1.id])
        assertEquals(1.0, fordBellman.d[vertex2.id])
        assertEquals(2.0, fordBellman.d[vertex3.id])
    }

    @Test
    @Tag("Two path with different length directed graph (6 vertices)")
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
        val (_, edges) = result!!
//        assertEquals(7, edges.size)
        assertEquals(0.0, fordBellman.d[vertex1.id])
        assertEquals(1.0, fordBellman.d[vertex2.id])
        assertEquals(1.0, fordBellman.d[vertex3.id])
        assertEquals(2.0, fordBellman.d[vertex4.id])
        assertEquals(3.0, fordBellman.d[vertex5.id])
        assertEquals(2.0, fordBellman.d[vertex6.id])
    }

    @Test
    @Tag("Test no path in directed graph")
    fun `test no path in directed graph`() {

        val vertex1 = directGraph.addVertex("1")
        directGraph.addVertex("2")
        val vertex3 = directGraph.addVertex("3")

        directGraph.addEdge("1", "2", "Ботаническая")

        val fordBellman = FordBellman(graph = directGraph)
        val result = fordBellman.fordBellman(vertex1.id, vertex3.id)

        assertNull(result)
    }

    @Test
    @Tag("Little path directedWeighted graph (pos weights)")
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
        assertEquals(listOf(vertex1.id, vertex2.id, vertex3.id), path)
        assertEquals(0.0, fordBellman.d[vertex1.id])
        assertEquals(50.0, fordBellman.d[vertex2.id])
        assertEquals(150.0, fordBellman.d[vertex3.id])
    }

    @Test
    @Tag("Two path with different length directedWeighted graph (pos weights)")
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
        val (_, edges) = result!!
//        assertEquals(7, edges.size)
        assertEquals(0.0, fordBellman.d[vertex1.id])
        assertEquals(30.0, fordBellman.d[vertex2.id])
        assertEquals(10.0, fordBellman.d[vertex3.id])
        assertEquals(50.0, fordBellman.d[vertex4.id])
//        assertEquals(90.0, fordBellman.d[vertex5.id])
        assertEquals(30.0, fordBellman.d[vertex6.id])
    }

    @Test
    @Tag("Test no path in directedWeighted graph (pos weights)")
    fun `test no path in directedWeighted graph (pos weights)`() {

        val vertex1 = directWeightedGraph.addVertex("1")
        directWeightedGraph.addVertex("2")
        val vertex3 = directWeightedGraph.addVertex("3")

        directWeightedGraph.addEdge("1", "2", "Ботаническая", "10")

        val fordBellman = FordBellman(graph = directWeightedGraph)
        val result = fordBellman.fordBellman(vertex1.id, vertex3.id)

        assertNull(result)
    }

    @Test
    @Tag("Little path directedWeighted graph (neg weights)")
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
        assertEquals(listOf(vertex1.id, vertex2.id, vertex3.id), path)
        assertEquals(0.0, fordBellman.d[vertex1.id])
        assertEquals(50.0, fordBellman.d[vertex2.id])
        assertEquals(-50.0, fordBellman.d[vertex3.id])
    }

    @Test
    @Tag("Two path with different length directedWeighted graph (neg weights)")
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
        val (_, edges) = result!!
//        assertEquals(7, edges.size)
        assertEquals(0.0, fordBellman.d[vertex1.id])
        assertEquals(-30.0, fordBellman.d[vertex2.id])
        assertEquals(-60.0, fordBellman.d[vertex3.id])
        assertEquals(-100.0, fordBellman.d[vertex4.id])
        assertEquals(-150.0, fordBellman.d[vertex5.id])
        assertEquals(-210.0, fordBellman.d[vertex6.id])
    }

    @Test
    @Tag("Test no path in directedWeighted graph (neg weights)")
    fun `test no path in directedWeighted graph (neg weights)`() {

        val vertex1 = directWeightedGraph.addVertex("1")
        directWeightedGraph.addVertex("2")
        val vertex3 = directWeightedGraph.addVertex("3")

        directWeightedGraph.addEdge("1", "2", "Ботаническая", "-10")

        val fordBellman = FordBellman(graph = directWeightedGraph)
        val result = fordBellman.fordBellman(vertex1.id, vertex3.id)

        assertNull(result)
    }

    @Test
    @Tag("Test negative cycle weighted dir graph")
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
    @Tag("Little path undirected graph")
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
        assertEquals(listOf(vertex1.id, vertex2.id, vertex3.id), path)
        assertEquals(0.0, fordBellman.d[vertex1.id])
        assertEquals(1.0, fordBellman.d[vertex2.id])
        assertEquals(2.0, fordBellman.d[vertex3.id])
    }

    @Test
    @Tag("Two path with different length undirected graph")
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
        val (_, edges) = result!!
//        assertEquals(7, edges.size)
        assertEquals(0.0, fordBellman.d[vertex1.id])
        assertEquals(1.0, fordBellman.d[vertex2.id])
        assertEquals(1.0, fordBellman.d[vertex3.id])
        assertEquals(2.0, fordBellman.d[vertex4.id])
        assertEquals(3.0, fordBellman.d[vertex5.id])
        assertEquals(2.0, fordBellman.d[vertex6.id])
    }

    @Test
    @Tag("Test no path in undirected graph")
    fun `test no path in undirected graph`() {

        val vertex1 = undirectedGraph.addVertex("1")
        undirectedGraph.addVertex("2")
        val vertex3 = undirectedGraph.addVertex("3")

        undirectedGraph.addEdge("1", "2", "Ботаническая")

        val fordBellman = FordBellman(graph = undirectedGraph)
        val result = fordBellman.fordBellman(vertex1.id, vertex3.id)

        assertNull(result)
    }

    @Test
    @Tag("Little path undirectedWeighted graph (pos weights)")
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
        assertEquals(listOf(vertex1.id, vertex2.id, vertex3.id), path)
        assertEquals(0.0, fordBellman.d[vertex1.id])
        assertEquals(50.0, fordBellman.d[vertex2.id])
        assertEquals(150.0, fordBellman.d[vertex3.id])
    }

    @Test
    @Tag("Two path with different length undirectedWeighted graph (pos weights)")
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
        val (_, edges) = result!!
//        assertEquals(7, edges.size)
        assertEquals(0.0, fordBellman.d[vertex1.id])
        assertEquals(30.0, fordBellman.d[vertex2.id])
        assertEquals(10.0, fordBellman.d[vertex3.id])
        assertEquals(50.0, fordBellman.d[vertex4.id])
        assertEquals(90.0, fordBellman.d[vertex5.id])
        assertEquals(30.0, fordBellman.d[vertex6.id])
    }

    @Test
    @Tag("Test no path in undirectedWeighted graph (pos weights)")
    fun `test no path in undirectedWeighted graph (pos weights)`() {

        val vertex1 = weightedGraph.addVertex("1")
        weightedGraph.addVertex("2")
        val vertex3 = weightedGraph.addVertex("3")

        weightedGraph.addEdge("1", "2", "Ботаническая", "10")

        val fordBellman = FordBellman(graph = weightedGraph)
        val result = fordBellman.fordBellman(vertex1.id, vertex3.id)

        assertNull(result)
    }



    @Test
    @Tag("Test no path in undirectedWeighted graph (neg weights)")
    fun `test no path in undirectedWeighted graph (neg weights)`() {

        val vertex1 = weightedGraph.addVertex("1")
        weightedGraph.addVertex("2")
        val vertex3 = weightedGraph.addVertex("3")

        weightedGraph.addEdge("1", "2", "Ботаническая", "-10")

        val fordBellman = FordBellman(graph = weightedGraph)
        val result = fordBellman.fordBellman(vertex1.id, vertex3.id)

        assertNull(result)
    }

    @Test
    @Tag("Single vertex")
    fun `single vertex`() {
        val vertex1 = directGraph.addVertex("239")

        val fordBellman = FordBellman(graph = directGraph)
        val result = fordBellman.fordBellman(vertex1.id, vertex1.id)
        assertNotNull(result)

        val (path, edges) = result!!

        assertEquals(listOf(vertex1.id), path)
        assertEquals(0, edges.size)
        assertEquals(0.0, fordBellman.d[vertex1.id])
    }

    @Test
    @Tag("Test empty path when source equals target")
    fun `test empty path when source equals target`() {

        val vertexA = directWeightedGraph.addVertex("A")
        directWeightedGraph.addVertex("B")
        directWeightedGraph.addEdge("A", "B", "МКАД", "5")

        val fordBellman = FordBellman(directWeightedGraph)
        val result = fordBellman.fordBellman(vertexA.id, vertexA.id)

        assertNotNull(result)
        val (path, edges) = result!!
        assertEquals(listOf(vertexA.id), path)
        assertEquals(0, edges.size)
    }

    @Test
    @Tag("Test invalid weight format")
    fun `test invalid weight format`() {

        val vertexA = directWeightedGraph.addVertex("A")
        val vertexB = directWeightedGraph.addVertex("B")
        directWeightedGraph.addEdge("A", "B", "СолявойПереулок", "invalidWeight")

        val fordBellman = FordBellman(directWeightedGraph)
        val result = fordBellman.fordBellman(vertexA.id, vertexB.id)

        assertNotNull(result)
        assertEquals(1.0, fordBellman.d[vertexB.id])
    }

    @Test
    @Tag("Large graph test")
    fun `large graph test`() {

        val vertices = mutableListOf<Vertex<String>>()

        for (i in 0 until 1000) {
            vertices.add(directWeightedGraph.addVertex("$i"))
        }

        for (i in 0 until 999) {
            directWeightedGraph.addEdge("$i", "${i + 1}", "edge$i", "${i + 1}")
        }

        val fordBellman = FordBellman(directWeightedGraph)
        val start = System.currentTimeMillis()
        val result = fordBellman.fordBellman(vertices.first().id, vertices.last().id)
        val end = System.currentTimeMillis()

        assertNotNull(result)
        val (path, edges) = result!!
        assertEquals(1000, path!!.size)
        assertEquals(999, edges.size)

        assertTrue(end - start < 10000, "less then 10 sec for 1000 vertices")
    }

    @Test
    @Tag("Different compomets connection")
    fun `different compomets connection`() {

        val vertex1 = directGraph.addVertex("1")
        val vertex2 = directGraph.addVertex("2")
        val vertex3 = directGraph.addVertex("3")
        directGraph.addVertex("4")

        directGraph.addEdge("1", "2", "Чичера1")
        directGraph.addEdge("3", "4", "Чичера2")

        val fordBellman = FordBellman(graph = directGraph)

        val res1 = fordBellman.fordBellman(vertex1.id, vertex2.id)
        assertNotNull(res1)

        val res2 = fordBellman.fordBellman(vertex1.id, vertex3.id)
        assertNull(res2)
    }

    @Test
    @Tag("Test zero weight edges")
    fun `test zero weight edges`() {
        val vertexA = directWeightedGraph.addVertex("A")
        directWeightedGraph.addVertex("B")
        val vertexC = directWeightedGraph.addVertex("C")

        directWeightedGraph.addEdge("A", "B", "10-ЛИНИЯ-ВО", "0")
        directWeightedGraph.addEdge("B", "C", "11-ЛИНИЯ-ВО", "0")

        val fordBellman = FordBellman(graph = directWeightedGraph)
        val result = fordBellman.fordBellman(vertexA.id, vertexC.id)

        assertNotNull(result)
        assertEquals(0.0, fordBellman.d[vertexC.id])
    }

    @Test
    @Tag("Test very large weights")
    fun `test very large weights`() {
        val vertexA = directWeightedGraph.addVertex("A")
        val vertexB = directWeightedGraph.addVertex("B")

        directWeightedGraph.addEdge("A", "B", "edge1", "999999999999")

        val fordBellman = FordBellman(graph = directWeightedGraph)
        val result = fordBellman.fordBellman(vertexA.id, vertexB.id)

        assertNotNull(result)
        assertEquals(999999999999.0, fordBellman.d[vertexB.id])
    }

    @Test
    @Tag("Test fractional weights")
    fun `test fractional weights`() {
        val vertexA = directWeightedGraph.addVertex("A")
        val vertexB = directWeightedGraph.addVertex("B")

        directWeightedGraph.addEdge("A", "B", "edge1", "3.14159")

        val fordBellman = FordBellman(directWeightedGraph)
        val result = fordBellman.fordBellman(vertexA.id, vertexB.id)

        assertNotNull(result)
        assertEquals(3.14159, fordBellman.d[vertexB.id]!!.toDouble(), 0.00001)
    }

    @ParameterizedTest
    @MethodSource("graphUtilFordBellman")
    @Tag("Tests with parameteres")
    fun `test different graph types`(
        graph: () -> Triple<Graph<String, String>, Long, Long>,
        expectedDistance: Double,
        expectedPathLen: Int
    ) {
        val (graph, sourceId, targetId) = graph()
        val fordBellman = FordBellman(graph)
        val result = fordBellman.fordBellman(sourceId, targetId)

        assertNotNull(result)
        val (path, edges) = result!!
        assertEquals(expectedPathLen, path!!.size)
        assertEquals(expectedDistance, fordBellman.d[targetId])
    }


    companion object {
        @JvmStatic
        fun graphUtilFordBellman(): List<Arguments> {
            return listOf(
                Arguments.of(
                    {
                        val graph = DirectGraph<String, String>()
                        val a = graph.addVertex("A")
                        graph.addVertex("B")
                        val c = graph.addVertex("C")
                        graph.addEdge("A", "B", "1")
                        graph.addEdge("B", "C", "2")
                        Triple(graph, a.id, c.id)
                    },
                    2.0,
                    3
                ),
                Arguments.of(
                    {
                        val graph = UndirectedGraph<String, String>()
                        val a = graph.addVertex("A")
                        graph.addVertex("B")
                        val c = graph.addVertex("C")
                        graph.addEdge("A", "B", "1")
                        graph.addEdge("B", "C", "2")
                        Triple(graph, a.id, c.id)
                    },
                    2.0,
                    3
                )

            )
        }
    }


}