import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.runBlocking
import org.graphApp.data.Neo4j.Neo4jDataBase
import org.graphApp.model.graph.DirectGraph
import org.graphApp.model.graph.UndirectedGraph
import org.graphApp.model.graph.UndirectedWeightedGraph
import org.graphApp.model.graph.WeightedGraph
import org.graphApp.model.graph.algorithms.FindStrongCommunities
import org.graphApp.model.graph.algorithms.MinimalSpanningTree
import org.graphApp.viewmodel.MainScreenViewModel
import org.graphApp.viewmodel.graph.GraphViewModel
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.neo4j.driver.AuthTokens
import org.neo4j.driver.Driver
import org.neo4j.driver.GraphDatabase
import kotlin.test.assertEquals

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
        graphViewModel.clear()
        database.uploadGraph()
        assertEquals(5, graphViewModel.vertices.size)
        assertEquals(5, graphViewModel.edges.size)
        finder = MinimalSpanningTree(graph)
        val actualResult = finder.bfsSpanningTree()
        assertEquals(4, actualResult!!.size)
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
        graphViewModel.clear()
        database.uploadGraph()
        finder = MinimalSpanningTree(graph)
        val actualResult = finder.bfsSpanningTree()
        assertEquals(3, graphViewModel.vertices.size)
        assertEquals(3, graphViewModel.edges.size)
        assertEquals(2, actualResult!!.size)
    }
}