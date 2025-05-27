import org.graphApp.model.graph.UndirectedGraph
import org.graphApp.model.graph.WeightedGraph
import org.graphApp.model.graph.algorithms.MinimalSpanningTree
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.provider.Arguments
import java.util.stream.Stream

class KraskalSpanningTreeTests {
    private lateinit var minimalSpanningTree: MinimalSpanningTree<String, Long>
    private lateinit var weightedGraph: WeightedGraph<String, Long>
    @BeforeEach
    fun setUp() {
        weightedGraph = WeightedGraph()
        minimalSpanningTree = MinimalSpanningTree(weightedGraph)
    }

    companion object {
        @JvmStatic
        fun graphCases() : Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    2,
                    listOf(
                        listOf("0", "1", 0L, 5L)
                    ),
                    listOf(
                        listOf("0", "1", 0L, 5L)
                    )
                ),
                Arguments.of(
                    3,
                    listOf(
                        listOf("0", "1", 0L, 1L),
                        listOf("1", "2", 1L, 2L),
                        listOf("0", "2", 2L, 3L)
                    ),
                    listOf(
                        listOf("0", "1", 0L, 1L),
                        listOf("1", "2", 1L, 2L)
                    )
                ),
                Arguments.of(
                    4,
                    listOf(
                        listOf("0", "1", 0L, 1L),
                        listOf("0", "2", 1L, 2L),
                        listOf("0", "3", 2L, 3L),
                        listOf("1", "2", 3L, 4L),
                        listOf("2", "3", 4L, 5L),
                        listOf("1", "3", 5L, 6L)
                    ),
                    listOf(
                        listOf("0", "1", 0L, 1L),
                        listOf("0", "2", 1L, 2L),
                        listOf("0", "3", 2L, 3L)
                    )
                ),
                Arguments.of(
                    4,
                    listOf(
                        listOf("0", "1", 0L, 1L),
                        listOf("1", "2", 1L, 2L),
                        listOf("2", "3", 2L, 3L),
                        listOf("3", "0", 3L, 4L),
                        listOf("0", "2", 4L, 5L),
                        listOf("1", "3", 5L, 6L)
                    ),
                    listOf(
                        listOf("0", "1", 0L, 1L),
                        listOf("1", "2", 1L, 2L),
                        listOf("2", "3", 2L, 3L)
                    )
                ),
                Arguments.of(
                    5,
                    listOf(
                        listOf("0", "1", 0L, 1L),
                        listOf("1", "2", 1L, 2L),
                        listOf("2", "3", 2L, 3L),
                        listOf("3", "4", 3L, 4L),
                        listOf("0", "4", 4L, 5L),
                        listOf("0", "2", 5L, 6L),
                        listOf("1", "3", 6L, 7L)
                    ),
                    listOf(
                        listOf("0", "1", 0L, 1L),
                        listOf("1", "2", 1L, 2L),
                        listOf("2", "3", 2L, 3L),
                        listOf("3", "4", 3L, 4L)
                    )
                ),
                Arguments.of(
                    5,
                    listOf(
                        listOf("0", "1", 0L, 10L),
                        listOf("0", "2", 1L, 20L),
                        listOf("0", "3", 2L, 30L),
                        listOf("0", "4", 3L, 40L),
                        listOf("1", "2", 4L, 15L),
                        listOf("1", "3", 5L, 25L),
                        listOf("1", "4", 6L, 35L),
                        listOf("2", "3", 7L, 22L),
                        listOf("2", "4", 8L, 32L),
                        listOf("3", "4", 9L, 42L)
                    ),
                    listOf(
                        listOf("0", "1", 0L, 10L),
                        listOf("1", "2", 4L, 15L),
                        listOf("2", "3", 7L, 22L),
                        listOf("2", "4", 8L, 32L)
                    )
                ),
                Arguments.of(
                    6,
                    listOf(
                        listOf("0", "1", 0L, 1L),
                        listOf("1", "2", 1L, 2L),
                        listOf("0", "2", 2L, 3L),
                        listOf("3", "4", 3L, 4L),
                        listOf("4", "5", 4L, 5L),
                        listOf("3", "5", 5L, 6L),
                        listOf("2", "3", 6L, 7L),
                        listOf("0", "4", 7L, 8L)
                    ),
                    listOf(
                        listOf("0", "1", 0L, 1L),
                        listOf("1", "2", 1L, 2L),
                        listOf("3", "4", 3L, 4L),
                        listOf("4", "5", 4L, 5L),
                        listOf("2", "3", 6L, 7L)
                    )
                ),
                Arguments.of(
                    6,
                    listOf(
                        listOf("0", "1", 0L, 1L),
                        listOf("1", "2", 1L, 2L),
                        listOf("2", "3", 2L, 3L),
                        listOf("3", "4", 3L, 4L),
                        listOf("4", "5", 4L, 5L),
                        listOf("5", "0", 5L, 6L),
                        listOf("0", "3", 6L, 7L)
                    ),
                    listOf(
                        listOf("0", "1", 0L, 1L),
                        listOf("1", "2", 1L, 2L),
                        listOf("2", "3", 2L, 3L),
                        listOf("3", "4", 3L, 4L),
                        listOf("4", "5", 4L, 5L)
                    )
                ),
                Arguments.of(
                    7,
                    listOf(
                        listOf("6", "0", 0L, 1L),
                        listOf("6", "1", 1L, 2L),
                        listOf("6", "2", 2L, 3L),
                        listOf("6", "3", 3L, 4L),
                        listOf("6", "4", 4L, 5L),
                        listOf("6", "5", 5L, 6L),
                        listOf("0", "1", 6L, 7L),
                        listOf("1", "2", 7L, 8L)
                    ),
                    listOf(
                        listOf("6", "0", 0L, 1L),
                        listOf("6", "1", 1L, 2L),
                        listOf("6", "2", 2L, 3L),
                        listOf("6", "3", 3L, 4L),
                        listOf("6", "4", 4L, 5L),
                        listOf("6", "5", 5L, 6L)
                    )
                ),
                Arguments.of(
                    8,
                    listOf(
                        listOf("0", "7", 0L, 1L),
                        listOf("0", "1", 1L, 2L),
                        listOf("1", "2", 2L, 3L),
                        listOf("2", "3", 3L, 4L),
                        listOf("3", "4", 4L, 5L),
                        listOf("4", "5", 5L, 6L),
                        listOf("5", "6", 6L, 7L),
                        listOf("6", "7", 7L, 8L),
                        listOf("2", "5", 8L, 9L),
                        listOf("1", "6", 9L, 10L)
                    ),
                    listOf(
                        listOf("0", "7", 0L, 1L),
                        listOf("0", "1", 1L, 2L),
                        listOf("1", "2", 2L, 3L),
                        listOf("2", "3", 3L, 4L),
                        listOf("3", "4", 4L, 5L),
                        listOf("4", "5", 5L, 6L),
                        listOf("5", "6", 6L, 7L)
                    )
                ),
                Arguments.of(
                    9,
                    listOf(
                        listOf("0", "1", 0L, 1L),
                        listOf("1", "2", 1L, 2L),
                        listOf("2", "3", 2L, 3L),
                        listOf("3", "4", 3L, 4L),
                        listOf("4", "5", 4L, 5L),
                        listOf("5", "6", 5L, 6L),
                        listOf("6", "7", 6L, 7L),
                        listOf("7", "8", 7L, 8L),
                        listOf("0", "4", 8L, 20L),
                        listOf("2", "6", 9L, 21L)
                    ),
                    listOf(
                        listOf("0", "1", 0L, 1L),
                        listOf("1", "2", 1L, 2L),
                        listOf("2", "3", 2L, 3L),
                        listOf("3", "4", 3L, 4L),
                        listOf("4", "5", 4L, 5L),
                        listOf("5", "6", 5L, 6L),
                        listOf("6", "7", 6L, 7L),
                        listOf("7", "8", 7L, 8L)
                    )
                ),
                Arguments.of(
                    10,
                    listOf(
                        listOf("0", "1", 0L, 1L),
                        listOf("0", "2", 1L, 2L),
                        listOf("0", "3", 2L, 3L),
                        listOf("0", "4", 3L, 4L),
                        listOf("0", "5", 4L, 5L),
                        listOf("0", "6", 5L, 6L),
                        listOf("0", "7", 6L, 7L),
                        listOf("0", "8", 7L, 8L),
                        listOf("0", "9", 8L, 9L),
                        listOf("1", "2", 9L, 20L),
                        listOf("3", "4", 10L, 21L)
                    ),
                    listOf(
                        listOf("0", "1", 0L, 1L),
                        listOf("0", "2", 1L, 2L),
                        listOf("0", "3", 2L, 3L),
                        listOf("0", "4", 3L, 4L),
                        listOf("0", "5", 4L, 5L),
                        listOf("0", "6", 5L, 6L),
                        listOf("0", "7", 6L, 7L),
                        listOf("0", "8", 7L, 8L),
                        listOf("0", "9", 8L, 9L)
                    )
                ),
                Arguments.of(
                    11,
                    listOf(
                        listOf("0", "1", 0L, 1L),
                        listOf("1", "2", 1L, 2L),
                        listOf("2", "3", 2L, 3L),
                        listOf("3", "4", 3L, 4L),
                        listOf("4", "5", 4L, 5L),
                        listOf("5", "6", 5L, 6L),
                        listOf("6", "7", 6L, 7L),
                        listOf("7", "8", 7L, 8L),
                        listOf("8", "9", 8L, 9L),
                        listOf("9", "10", 9L, 10L),
                        listOf("0", "10", 10L, 20L)
                    ),
                    listOf(
                        listOf("0", "1", 0L, 1L),
                        listOf("1", "2", 1L, 2L),
                        listOf("2", "3", 2L, 3L),
                        listOf("3", "4", 3L, 4L),
                        listOf("4", "5", 4L, 5L),
                        listOf("5", "6", 5L, 6L),
                        listOf("6", "7", 6L, 7L),
                        listOf("7", "8", 7L, 8L),
                        listOf("8", "9", 8L, 9L),
                        listOf("9", "10", 9L, 10L)
                    )
                ),
                Arguments.of(
                    4,
                    listOf(
                        listOf("0", "1", 0L, 1L),
                        listOf("1", "2", 1L, 2L),
                        listOf("2", "3", 2L, 3L),
                        listOf("3", "0", 3L, 4L),
                        listOf("0", "2", 4L, 5L),
                        listOf("1", "3", 5L, 6L)
                    ),
                    listOf(
                        listOf("0", "1", 0L, 1L),
                        listOf("1", "2", 1L, 2L),
                        listOf("2", "3", 2L, 3L)
                    )
                ),
                Arguments.of(
                    3,
                    listOf(
                        listOf("0", "1", 0L, 2L),
                        listOf("1", "2", 1L, 3L),
                        listOf("0", "2", 2L, 1L)
                    ),
                    listOf(
                        listOf("0", "2", 2L, 1L),
                        listOf("0", "1", 0L, 2L)
                    )
                )
            )

        }
    }






}