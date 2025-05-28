package integration

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.runBlocking
import org.graphApp.data.Neo4j.Neo4jDataBase
import org.graphApp.model.graph.WeightedGraph
import org.graphApp.model.graph.algorithms.MinimalSpanningTree
import org.graphApp.viewmodel.MainScreenViewModel
import org.graphApp.viewmodel.graph.GraphViewModel
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions
import org.neo4j.driver.AuthTokens
import org.neo4j.driver.Driver
import org.neo4j.driver.GraphDatabase

class Neo4jWithMinimalSpanningTreeKraskal {
    private lateinit var graph: WeightedGraph<String, Long>
    private lateinit var graphViewModel: GraphViewModel<String, Long>
    private lateinit var mainScreenViewModel: MainScreenViewModel<Long>
    private lateinit var finder: MinimalSpanningTree<String, Long>
    private lateinit var driver: Driver
    private lateinit var database: Neo4jDataBase<String, Long>
    @BeforeEach
    fun setUp() {
        graph = WeightedGraph()
        mainScreenViewModel = MainScreenViewModel(graph)
        graphViewModel = GraphViewModel(
            graph,
            mutableStateOf(false),
            mutableStateOf(false),
            mutableStateOf(false),
            mutableStateOf(true)
        )

        driver = GraphDatabase.driver(
            "bolt://localhost:7687",
            AuthTokens.basic("neo4j", "Sosiska1234554321"))
        database = Neo4jDataBase(
            mainViewModel = mainScreenViewModel,
            graph = graphViewModel,
            driver = driver,
            graphName = "RISCVHATERS"
        )
        clean(driver)
    }

    @Test
    @DisplayName("Integration test for Finding Minimal SpanningTree with Kraskal")
    fun testKraskalMinimalSpanningTree() = runBlocking {
        for(id in 0..<5) {
            graphViewModel.addVertex(id.toString(), 0.dp, 0.dp)
        }
        graphViewModel.createEdge(0, 1, "1")
        graphViewModel.createEdge(1, 2, "2")
        graphViewModel.createEdge(2, 0, "3")
        graphViewModel.createEdge(3, 4, "2")
        graphViewModel.createEdge(3, 0, "1")

        database.storeGraph()
        database.uploadGraph()
        Assertions.assertEquals(5, graphViewModel.vertices.size)
        Assertions.assertEquals(5, graphViewModel.edges.size)
        finder = MinimalSpanningTree(graph)
        val actualResult = finder.kraskalSpanningTree()
        Assertions.assertEquals(4, actualResult!!.size)
        Assertions.assertEquals(6L, actualResult.sumOf { it.weight.toLong() })

    }

    @Test
    @DisplayName("Integration test for Finding Minimal SpanningTree with Kraskal")
    fun testKraskalMinimalSpanningTree2() = runBlocking {
        for(id in 0..<3) {
            graphViewModel.addVertex(id.toString(), 0.dp, 0.dp)
        }
        graphViewModel.createEdge(0, 1, "1")
        graphViewModel.createEdge(1, 2, "5")
        graphViewModel.createEdge(2, 0, "3")

        database.storeGraph()
        database.uploadGraph()
        finder = MinimalSpanningTree(graph)
        val actualResult = finder.kraskalSpanningTree()
        Assertions.assertEquals(3, graphViewModel.vertices.size)
        Assertions.assertEquals(3, graphViewModel.edges.size)
        Assertions.assertEquals(2, actualResult!!.size)
        Assertions.assertEquals(4L, actualResult.sumOf { it.weight.toLong() })
    }
}