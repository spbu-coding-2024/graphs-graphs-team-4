package integration

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.runBlocking
import org.graphApp.data.Neo4j.Neo4jDataBase
import org.graphApp.model.graph.DirectGraph
import org.graphApp.model.graph.algorithms.FindStrongCommunities
import org.graphApp.viewmodel.MainScreenViewModel
import org.graphApp.viewmodel.graph.GraphViewModel
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.neo4j.driver.AuthTokens
import org.neo4j.driver.Driver
import org.neo4j.driver.GraphDatabase

fun clean(driver: Driver) {
    driver.session().use { session ->
        session.executeWrite { tx ->
            tx.run("MATCH (n) DETACH DELETE n;")
        }
    }
}

class Neo4jWithStrongConnectivity {
    private lateinit var graph: DirectGraph<String, Long>
    private lateinit var graphViewModel: GraphViewModel<String, Long>
    private lateinit var mainScreenViewModel: MainScreenViewModel<Long>
    private lateinit var finder: FindStrongCommunities<String, Long>
    private lateinit var driver: Driver
    private lateinit var database: Neo4jDataBase<String, Long>

    @BeforeEach
    fun setUp() {
        graph = DirectGraph()
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
    @DisplayName("Integration test for Strong connectivity")
    fun testStrongConnectivity() = runBlocking {
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
        finder = FindStrongCommunities(graph = graphViewModel.graph)
        Assertions.assertEquals(5, graphViewModel.vertices.size)
        Assertions.assertEquals(5, graphViewModel.edges.size)
        val actualResult = finder.findStrongCommunitiesInGraph()
        Assertions.assertEquals(3, actualResult!!.size)
    }

    @Test
    @DisplayName("Integration test for Strong connectivity")
    fun testOneStrongConnectivity() = runBlocking {
        for(id in 0..<3) {
            graphViewModel.addVertex(id.toString(), 0.dp, 0.dp)
        }
        graphViewModel.createEdge(0, 1)
        graphViewModel.createEdge(1, 2)
        graphViewModel.createEdge(2, 0)

        database.storeGraph()
        database.uploadGraph()
        finder = FindStrongCommunities(graph = graphViewModel.graph)
        Assertions.assertEquals(3, graphViewModel.vertices.size)
        Assertions.assertEquals(3, graphViewModel.edges.size)
        val actualResult = finder.findStrongCommunitiesInGraph()
        Assertions.assertEquals(1, actualResult!!.size)
    }


}