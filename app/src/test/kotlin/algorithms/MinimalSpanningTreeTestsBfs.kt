package algorithms

import org.graphApp.model.graph.UndirectedGraph
import org.graphApp.model.graph.algorithms.MinimalSpanningTree
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream


class BfsSpanningTreeTests {
    private lateinit var minimalSpanningTree: MinimalSpanningTree<String, Long>
    private lateinit var graph: UndirectedGraph<String, Long>

    @BeforeEach
    fun setUp() {
        graph = UndirectedGraph()
    }


    companion object {
        @JvmStatic
        fun graphCases(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    2,
                    listOf(
                        listOf("0", "1")
                    ),
                    listOf(
                        listOf("0", "1")
                    )
                ),
                Arguments.of(
                    3,
                    listOf(
                        listOf("0", "1"),
                        listOf("1", "2"),
                        listOf("0", "2")
                    ),
                    listOf(
                        listOf("0", "1"),
                        listOf("0", "2")
                    )
                ),
                Arguments.of(
                    4,
                    listOf(
                        listOf("0", "1"),
                        listOf("0", "2"),
                        listOf("1", "3"),
                        listOf("2", "3")
                    ),
                    listOf(
                        listOf("0", "1"),
                        listOf("0", "2"),
                        listOf("1", "3")
                    )
                ),
                Arguments.of(
                    5,
                    listOf(
                        listOf("0", "1"),
                        listOf("0", "2"),
                        listOf("1", "3"),
                        listOf("1", "4"),
                        listOf("3", "4")
                    ),
                    listOf(
                        listOf("0", "1"),
                        listOf("0", "2"),
                        listOf("1", "3"),
                        listOf("1", "4")
                    )
                ),
                Arguments.of(
                    4,
                    listOf(
                        listOf("0", "1"),
                        listOf("1", "2"),
                        listOf("2", "3"),
                        listOf("3", "0")
                    ),
                    listOf(
                        listOf("0", "1"),
                        listOf("0", "3"),
                        listOf("1", "2")
                    )
                ),
                Arguments.of(
                    6,
                    listOf(
                        listOf("0", "1"),
                        listOf("0", "2"),
                        listOf("1", "3"),
                        listOf("2", "4"),
                        listOf("4", "5"),
                        listOf("3", "5")
                    ),
                    listOf(
                        listOf("0", "1"),
                        listOf("0", "2"),
                        listOf("1", "3"),
                        listOf("2", "4"),
                        listOf("3", "5")
                    )
                ),
                Arguments.of(
                    7,
                    listOf(
                        listOf("0", "1"),
                        listOf("0", "2"),
                        listOf("1", "3"),
                        listOf("2", "4"),
                        listOf("3", "5"),
                        listOf("4", "6"),
                        listOf("5", "6")
                    ),
                    listOf(
                        listOf("0", "1"),
                        listOf("0", "2"),
                        listOf("1", "3"),
                        listOf("2", "4"),
                        listOf("3", "5"),
                        listOf("4", "6")
                    )
                )
            )

        }
    }

    @ParameterizedTest
    @MethodSource("graphCases")
    @DisplayName("Testcases for algorithm Kraskal")
    fun kraskalSpanningTreeTest(
        vertices: Long,
        edges: List<List<Any>>,
        expectedResult: List<List<Any>>
    ) {
        for (vertex in 0..<vertices) {
            graph.addVertex(vertex.toString())
        }
        var edgeID = 0L
        for (edge in edges) {
            graph.addEdge(
                edge[0].toString(),
                edge[1].toString(),
                edgeID++
            )
        }
        minimalSpanningTree = MinimalSpanningTree(graph)
        val result = minimalSpanningTree.bfsSpanningTree()
        val mappedResult: List<List<Any>> = result!!.map { edge ->
            listOf(
                edge.vertices.first.id,
                edge.vertices.second.id,
            )
        }
        val sortedExpected = expectedResult
            .map { it.map { e -> e.toString() }.sorted() }
            .sortedBy { it.joinToString() }

        val sortedActual = mappedResult
            .map { it.map { e -> e.toString() }.sorted() }
            .sortedBy { it.joinToString() }
        Assertions.assertEquals(sortedExpected, sortedActual)
    }
}