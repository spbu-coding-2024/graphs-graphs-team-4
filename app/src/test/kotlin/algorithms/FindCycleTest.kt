package algorithms

import org.graphApp.model.graph.*
import org.graphApp.model.graph.algorithms.FindCycles
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Tag
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream


class FindCycleTest {

    private lateinit var directGraph: DirectGraph<String, String>
    private lateinit var undirectedGraph: UndirectedGraph<String, String>

    @BeforeEach
    fun init() {
        directGraph = DirectGraph()
        undirectedGraph = UndirectedGraph()
    }

    @Test
    @Tag("3 vertices cycle dir")
    fun `3 vertices cycle dir`() {

        val vertex1 = directGraph.addVertex("A")
        val vertex2 = directGraph.addVertex("B")
        val vertex3 = directGraph.addVertex("C")

        directGraph.addEdge("A", "B", "МКАД1")
        directGraph.addEdge("B", "C", "МКАД2")
        directGraph.addEdge("C", "A", "МКАД3")

        val findCycles = FindCycles(directGraph)
        val result = findCycles.findCycleDirected(vertex1)

        assertNotNull(result)
        val (vertices, edges) = result!!
        assertEquals(3, vertices!!.size)
        assertEquals(3, edges.size)
        assertTrue(vertices.contains(vertex1.id))
        assertTrue(vertices.contains(vertex2.id))
        assertTrue(vertices.contains(vertex3.id))
    }

    @Test
    @Tag("No cycles in dir graph")
    fun `no cycle in directed graph`() {
        val vertex1 = directGraph.addVertex("A")
        directGraph.addVertex("B")
        directGraph.addVertex("C")

        directGraph.addEdge("A", "B", "МКАД1")
        directGraph.addEdge("B", "C", "МКАД2")

        val findCycles = FindCycles(directGraph)
        val result = findCycles.findCycleDirected(vertex1)

        assertNull(result)
    }

    @Test
    @Tag("3 vertices cycle undir")
    fun `3 vertices cycle undir`() {

        val vertex1 = undirectedGraph.addVertex("A")
        val vertex2 = undirectedGraph.addVertex("B")
        val vertex3 = undirectedGraph.addVertex("C")

        undirectedGraph.addEdge("A", "B", "МКАД1")
        undirectedGraph.addEdge("B", "C", "МКАД2")
        undirectedGraph.addEdge("C", "A", "МКАД3")

        val findCycles = FindCycles(undirectedGraph)
        val result = findCycles.findCycleDirected(vertex1)

        assertNotNull(result)
        val (vertices, edges) = result!!
        assertEquals(3, vertices!!.size)
        assertEquals(3, edges.size)
        assertTrue(vertices.contains(vertex1.id))
        assertTrue(vertices.contains(vertex2.id))
        assertTrue(vertices.contains(vertex3.id))
    }

    @Test
    @Tag("No cycle in undirected tree")
    fun `no cycle in undirected tree`() {
        val vertex1 = undirectedGraph.addVertex("A")
        val vertex2 = undirectedGraph.addVertex("B")
        val vertex3 = undirectedGraph.addVertex("C")
        val vertex4 = undirectedGraph.addVertex("D")

        undirectedGraph.addEdge("A", "B", "МКАД1")
        undirectedGraph.addEdge("B", "C", "МКАД2")
        undirectedGraph.addEdge("B", "D", "МКАД3")

        val findCycles = FindCycles(undirectedGraph)
        val result = findCycles.findCycleUndirected(vertex1)
        val (vertices, _) = result!!
        assertNull(vertices)
    }

    @Test
    @Tag("Square cycle undir")
    fun `square cycle undirected`() {
        val vertex1 = undirectedGraph.addVertex("A")
        undirectedGraph.addVertex("B")
        undirectedGraph.addVertex("C")
        undirectedGraph.addVertex("D")

        undirectedGraph.addEdge("A", "B", "МКАД1")
        undirectedGraph.addEdge("B", "C", "МКАД2")
        undirectedGraph.addEdge("C", "D", "МКАД3")
        undirectedGraph.addEdge("D", "A", "МКАД4")

        val findCycles = FindCycles(undirectedGraph)
        val result = findCycles.findCycleUndirected(vertex1)

        assertNotNull(result)
        val (vertices, edges) = result!!
        assertEquals(4, vertices!!.size)
        assertEquals(4, edges.size)
    }
    @Test
    @Tag("Multiple cycles")
    fun `multiple cycles`() {
        val vertex1 = undirectedGraph.addVertex("A")
        undirectedGraph.addVertex("B")
        undirectedGraph.addVertex("C")
        undirectedGraph.addVertex("D")
        undirectedGraph.addVertex("E")

        undirectedGraph.addEdge("A", "B", "МКАД1")
        undirectedGraph.addEdge("B", "C", "МКАД2")
        undirectedGraph.addEdge("C", "A", "МКАД3")
        undirectedGraph.addEdge("C", "D", "МКАД4")
        undirectedGraph.addEdge("D", "E", "МКАД5")
        undirectedGraph.addEdge("E", "C", "МКАД6")

        val findCycles = FindCycles(undirectedGraph)
        val result = findCycles.findCycleUndirected(vertex1)

        assertNotNull(result)
        val (vertices, edges) = result!!
        assertTrue(vertices!!.size >= 3)
        assertTrue(edges.size >= 3)
    }

    @Test
    @Tag("Single vertex no cycle undir")
    fun `single vertex no cycle undir`() {
        val vertex1 = undirectedGraph.addVertex("A")

        val findCycles = FindCycles(undirectedGraph)
        val result = findCycles.findCycleUndirected(vertex1)
        val (vertices, _) = result!!
        assertNull(vertices)
    }

    @Test
    @Tag("Two vertices no cycle undir")
    fun `two vertices no cycle undir`() {
        val vertex1 = undirectedGraph.addVertex("A")
        undirectedGraph.addVertex("B")

        undirectedGraph.addEdge("A", "B", "МКАД1")

        val findCycles = FindCycles(undirectedGraph)
        val result = findCycles.findCycleUndirected(vertex1)
        val (vertices, _) = result!!
        assertNull(vertices)
    }

    @Test
    @Tag("Empty graph undir")
    fun `empty graph undir`() {
        val findCycles = FindCycles(graph = undirectedGraph)
        assertTrue(undirectedGraph.vertices.isEmpty())
    }

    @Test
    @Tag("Empty graph dir")
    fun `empty graph dir`() {
        val findCycles = FindCycles(graph = directGraph)
        assertTrue(directGraph.vertices.isEmpty())
    }

    @Test
    @Tag("Disconnected components dir")
    fun `disconnected components dir`() {

        val vertex1 = directGraph.addVertex("A")
        directGraph.addVertex("B")
        directGraph.addEdge("A", "B", "edge1")

        val vertex3 = directGraph.addVertex("C")
        directGraph.addVertex("D")
        directGraph.addEdge("C", "D", "edge2")
        directGraph.addEdge("D", "C", "edge3")

        val findCycles = FindCycles(directGraph)

        val result1 = findCycles.findCycleDirected(vertex1)
        assertNull(result1)

        val result2 = findCycles.findCycleDirected(vertex3)
        assertNotNull(result2)
        val (vertices, edges) = result2!!
        assertEquals(2, vertices!!.size)
        assertEquals(2, edges.size)
    }

    @Test
    @Tag("Disconnected components undir")
    fun `disconnected components undir`() {

        val vertex1 = undirectedGraph.addVertex("A")
        undirectedGraph.addVertex("B")
        undirectedGraph.addEdge("A", "B", "edge1")

        val vertex3 = undirectedGraph.addVertex("C")
        undirectedGraph.addVertex("D")
        undirectedGraph.addEdge("C", "D", "edge2")
        undirectedGraph.addEdge("D", "C", "edge3")

        val findCycles = FindCycles(undirectedGraph)

        val result1 = findCycles.findCycleDirected(vertex1)
        assertNull(result1)

        val result2 = findCycles.findCycleDirected(vertex3)
        assertNotNull(result2)
        val (vertices, edges) = result2!!
        assertEquals(2, vertices!!.size)
        assertEquals(2, edges.size)
    }

    companion object {
        @JvmStatic
        fun cycleTestData(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(3, "Small triangle"),
                Arguments.of(4, "Square"),
                Arguments.of(5, "Pentagon"),
                Arguments.of(6, "Hexagon")
            )
        }
    }
    @ParameterizedTest(name = "Directed cycle")
    @MethodSource("cycleTestData")
    @Tag("Parameterized directed cycle test")
    fun `parameterized directed cycle test`(size: Int) {
        val vertices = mutableListOf<Vertex<String>>()

        for (i in 1..size) {
            vertices.add(directGraph.addVertex("$i"))
        }

        for (i in 0 until size - 1) {
            directGraph.addEdge("${i+1}", "${i+2}", "rebro${i+1}")
        }
        directGraph.addEdge("$size", "1", "rebro$size")

        val findCycles = FindCycles(directGraph)
        val result = findCycles.findCycleDirected(vertices[0])

        assertNotNull(result)
        val (vertexPath, edges) = result!!
        assertEquals(size, vertexPath!!.size)
        assertEquals(size, edges.size)
    }

    @ParameterizedTest(name = "Undirected cycle")
    @MethodSource("cycleTestData")
    @Tag("Parameterized undirected cycle test")
    fun `parameterized undirected cycle test`(size: Int) {
        val vertices = mutableListOf<Vertex<String>>()

        for (i in 1..size) {
            vertices.add(undirectedGraph.addVertex("$i"))
        }

        for (i in 0 until size - 1) {
            undirectedGraph.addEdge("${i+1}", "${i+2}", "rebro${i+1}")
        }
        undirectedGraph.addEdge("$size", "1", "rebro$size")

        val findCycles = FindCycles(undirectedGraph)
        val result = findCycles.findCycleUndirected(vertices[0])

        assertNotNull(result)
        val (vertexPath, edges) = result!!
        assertEquals(size, vertexPath!!.size)
        assertEquals(size, edges.size)
    }
}
