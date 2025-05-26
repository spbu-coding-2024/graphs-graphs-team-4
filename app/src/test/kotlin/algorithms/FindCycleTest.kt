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


class FindCycleTest {

    private lateinit var directGraph: DirectGraph<String, String>
    private lateinit var undirectedGraph: UndirectedGraph<String, String>

    @BeforeEach
    fun init() {
        directGraph = DirectGraph<String, String>()
        undirectedGraph = UndirectedGraph<String, String>()
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


}