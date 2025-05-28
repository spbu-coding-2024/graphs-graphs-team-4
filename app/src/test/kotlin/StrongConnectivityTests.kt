import org.graphApp.model.graph.DirectGraph
import org.graphApp.model.graph.Vertex
import org.graphApp.model.graph.algorithms.FindStrongCommunities
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
class StrongConnectivityTests {

    private lateinit var graph: DirectGraph<String, Long>
    private lateinit var finder: FindStrongCommunities<String, Long>

    @BeforeEach
    fun setUp() {
        graph = DirectGraph()
        finder = FindStrongCommunities(graph)
    }

    companion object {
        @JvmStatic
        fun graphCases(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    3,
                    listOf(
                        Triple("0", "1", 0L), Triple("1", "2", 1L), Triple("2", "0", 2L)
                    ),
                    listOf(listOf(0L, 1L, 2L))
                ),
                Arguments.of(
                    4,
                    listOf(
                        Triple("0", "1", 0L), Triple("1", "0", 1L), Triple("2", "3", 2L), Triple("3", "2", 3L)
                    ),
                    listOf(listOf(0L, 1L), listOf(2L, 3L))
                ),
                Arguments.of(
                    3,
                    listOf(
                        Triple("0", "1", 0L), Triple("1", "2", 1L), Triple("2", "0", 2L)
                    ),
                    listOf(listOf(0L, 1L, 2L))
                ),
                Arguments.of(
                    2,
                    listOf(
                        Triple("0", "1", 0L), Triple("1", "2", 1L)
                    ),
                    listOf(listOf(2L), listOf(1L), listOf(0L))
                ),
                Arguments.of(
                    4,
                    listOf(
                        Triple("0", "1", 0L), Triple("1", "2", 1L), Triple("2", "3", 2L), Triple("3", "0", 3L)
                    ),
                    listOf(listOf(0L, 1L, 2L, 3L))
                ),
                Arguments.of(
                    3,
                    listOf(
                        Triple("0", "1", 1L), Triple("1", "2", 3L)
                    ),
                    listOf(listOf(2L), listOf(1L), listOf(0L))
                ),
                Arguments.of(
                    2,
                    listOf<Triple<String, String, Long>>(),
                    listOf(listOf(0L), listOf(1L))
                ),
                Arguments.of(
                    4,
                    listOf(
                        Triple("0", "1", 0L), Triple("1", "2", 1L), Triple("2", "0", 2L), Triple("2", "3", 3L)
                    ),
                    listOf(listOf(0L, 1L, 2L), listOf(3L))
                ),
                Arguments.of(
                    5,
                    listOf(
                        Triple("0", "1", 0L), Triple("1", "2", 1L), Triple("2", "0", 2L),
                        Triple("2", "3", 3L), Triple("3", "4", 4L), Triple("4", "2", 5L)
                    ),
                    listOf(listOf(0L, 1L, 2L, 3L, 4L))
                ),
                Arguments.of(
                    0,
                    listOf<Triple<String, String, Long>>(),
                    listOf<List<Long>>()
                ),
                Arguments.of(
                    3,
                    listOf<Triple<String, String, Long>>(),
                    listOf(listOf(0L), listOf(1L), listOf(2L))
                ),
                Arguments.of(
                    3,
                    listOf(
                        Triple("0", "1", 0L), Triple("0", "2", 1L), Triple("0", "3", 2L)
                    ),
                    listOf(listOf(3L), listOf(2L), listOf(1L), listOf(0L))
                ),
                Arguments.of(
                    3,
                    listOf(
                        Triple("0", "1", 0L), Triple("1", "2", 1L), Triple("1", "3", 2L)
                    ),
                    listOf(listOf(3L), listOf(2L), listOf(1L), listOf(0L))
                ),
                Arguments.of(
                    3,
                    listOf(
                        Triple("0", "1", 0L), Triple("1", "2", 1L), Triple("2", "0", 2L), Triple("2", "1", 3L)
                    ),
                    listOf(listOf(0L, 1L, 2L))
                )
            )
        }
    }

    @ParameterizedTest
    @MethodSource("graphCases")
    @DisplayName("TestCase with only one strong connectivity")
    fun testCaseWithOneStrongConnectivity(
        vertices: Int,
        edges: List<Triple<String, String, Long>>,
        expectedResults: List<List<Long>>
    ) {
        for (vertexID in 0..<vertices) {
            graph.addVertex(vertexID.toString())
        }
        for (edge in edges) {
            graph.addEdge(edge.first, edge.second, edge.third)
        }

        val result: List<List<Vertex<String>>> = finder.findStrongCommunitiesInGraph()!!
        val resultsID: List<List<Long>> = result.map { component ->
            component.map { it.id }.sorted()
        }
        val sortedAlgoResult = resultsID
            .map { it.sorted() }
            .sortedBy { it.firstOrNull() ?: Long.MAX_VALUE }
        val sortedExpectedResult = expectedResults
            .map { it.sorted() }
            .sortedBy { it.firstOrNull() ?: Long.MAX_VALUE }

        Assertions.assertEquals(sortedAlgoResult, sortedExpectedResult)
    }

}