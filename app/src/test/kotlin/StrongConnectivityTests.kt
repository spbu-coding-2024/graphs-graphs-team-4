import org.graphApp.model.graph.DVertex
import org.graphApp.model.graph.DirectGraph
import org.graphApp.model.graph.Vertex
import org.graphApp.model.graph.algorithms.FindStrongCommunities
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class StrongConnectivityTests {

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

        val expected: List<Vertex<String>> = listOf(
            DVertex(
                0L,
                "0"
            ),
            DVertex(
                1L,
                "1"
            ),
            DVertex(
                2L,
                "2"
            ),
        )
        finder = FindStrongCommunities(graph)
        val result: List<List<Vertex<String>>> = finder.findStrongCommunitiesInGraph()!!
        val expectedIds = expected.map { it.id }.sorted()
        val actualIds = result[0].map { it.id }.sorted()
        for(i in 0..2) {
            Assertions.assertEquals(expectedIds[i], actualIds[i])
        }
    }

}