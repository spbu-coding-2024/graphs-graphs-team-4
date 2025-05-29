package integration

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.runBlocking
import org.graphApp.data.Neo4j.Neo4jDataBase
import org.graphApp.model.graph.UndirectedGraph
import org.graphApp.model.graph.algorithms.MinimalSpanningTree
import org.graphApp.viewmodel.MainScreenViewModel
import org.graphApp.viewmodel.graph.GraphViewModel
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.neo4j.driver.AuthTokens
import org.neo4j.driver.Driver
import org.neo4j.driver.GraphDatabase
import org.junit.jupiter.api.Assertions

class Neo4jWithMinimalSpanningTreeBfs {
    private lateinit var graph: UndirectedGraph<String, Long>
    private lateinit var graphViewModel: GraphViewModel<String, Long>
    private lateinit var mainScreenViewModel: MainScreenViewModel<Long>
    private lateinit var finder: MinimalSpanningTree<String, Long>
    private lateinit var driver: Driver
    private lateinit var database: Neo4jDataBase<String, Long>
    @BeforeEach
    fun setUp() {
        graph = UndirectedGraph()
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

    @AfterEach
    fun delete() {
        driver.close()
    }

    @Test
    @DisplayName("Integration test for Finding Minimal SpanningTree with BFS")
    fun testBfsMinimalSpanningTree() = runBlocking {
        for(id in 0..<5) {
            graphViewModel.addVertex(id.toString(), 0.dp, 0.dp)
        }
        graphViewModel.createEdge(0, 1)
        graphViewModel.createEdge(1, 2)
        graphViewModel.createEdge(2, 0)
        graphViewModel.createEdge(3, 4)
        graphViewModel.createEdge(3, 0)

        database.storeGraph()
        database.uploadGraph()
        Assertions.assertEquals(5, graphViewModel.vertices.size)
        Assertions.assertEquals(5, graphViewModel.edges.size)
        finder = MinimalSpanningTree(graph)
        val actualResult = finder.bfsSpanningTree()
        Assertions.assertEquals(4, actualResult!!.size)
    }

    @Test
    @DisplayName("Integration test for Finding Minimal SpanningTree with BFS")
    fun testBfsMinimalSpanningTree2() = runBlocking {
        for(id in 0..<3) {
            graphViewModel.addVertex(id.toString(), 0.dp, 0.dp)
        }
        graphViewModel.createEdge(0, 1)
        graphViewModel.createEdge(1, 2)
        graphViewModel.createEdge(2, 0)

        database.storeGraph()
        database.uploadGraph()
        finder = MinimalSpanningTree(graph)
        val actualResult = finder.bfsSpanningTree()
        Assertions.assertEquals(3, graphViewModel.vertices.size)
        Assertions.assertEquals(3, graphViewModel.edges.size)
        Assertions.assertEquals(2, actualResult!!.size)
    }
}