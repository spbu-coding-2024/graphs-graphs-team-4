package dataTest

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import data.SQLiteMainLogic.SQLiteExposed
import data.SQLiteMainLogic.SQLiteMainLogic
import org.graphApp.model.graph.DirectGraph
import org.graphApp.model.graph.DirectWeightedGraph
import org.graphApp.model.graph.UndirectedGraph
import org.graphApp.model.graph.WeightedGraph
import org.graphApp.viewmodel.graph.GraphViewModel
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.random.Random
import kotlin.system.measureTimeMillis

class SQLiteTest {
    private lateinit var sqliteExposed: SQLiteExposed
    private lateinit var sqLiteMainLogic: SQLiteMainLogic<String, String>
    private lateinit var directGraph: DirectGraph<String, String>
    private lateinit var undirectedGraph: UndirectedGraph<String, String>
    private lateinit var weightedGraph: WeightedGraph<String, String>
    private lateinit var directWeightedGraph: DirectWeightedGraph<String, String>

    private val testDBname = "testSQLite.db"

    @BeforeEach
    fun init() {
        File(testDBname).delete()

        sqliteExposed = SQLiteExposed(testDBname)
        sqLiteMainLogic = SQLiteMainLogic(sqliteExposed)

        undirectedGraph = UndirectedGraph()
        weightedGraph = WeightedGraph()
        directGraph = DirectGraph()
        directWeightedGraph = DirectWeightedGraph()
    }

    @AfterEach
    fun cleanup() {
        File(testDBname).delete()
    }


    @Test
    @Tag("SQLite - saving and loading direct graph")
    fun `SQLite - saving and loading direct graph`() {

        val viewModel = GraphViewModel(
            graph = directGraph,
            showVerticesLabels = mutableStateOf(false),
            showEdgesWeights = mutableStateOf(false),
            isWeightedGraph = mutableStateOf(false),
            isDirectedGraph = mutableStateOf(true)
        )

        val vertex1 = viewModel.addVertex("1", 100.dp, 100.dp)
        val vertex2 = viewModel.addVertex("2", 200.dp, 200.dp)
        val vertex3 = viewModel.addVertex("3", 300.dp, 300.dp)


        viewModel.addEdge(vertex1.vertexID, vertex2.vertexID, "rebro1")
        viewModel.addEdge(vertex2.vertexID, vertex3.vertexID, "rebro2")
        viewModel.addEdge(vertex3.vertexID, vertex1.vertexID, "rebro3")


        assertTrue(sqLiteMainLogic.saveToSQLiteDataBase(viewModel, "test_findCycles"))
        val loadedViewModel = sqLiteMainLogic.readFromSQLiteDataBase<String, String>("test_findCycles")

        assertNotNull(loadedViewModel)
    }

    @Test
    @Tag("SQLite - saving and loading undirect graph")
    fun `SQLite - saving and loading undirect graph`() {

        val viewModel = GraphViewModel(
            graph = undirectedGraph,
            showVerticesLabels = mutableStateOf(false),
            showEdgesWeights = mutableStateOf(false),
            isWeightedGraph = mutableStateOf(false),
            isDirectedGraph = mutableStateOf(true)
        )

        val vertex1 = viewModel.addVertex("1", 100.dp, 100.dp)
        val vertex2 = viewModel.addVertex("2", 200.dp, 200.dp)
        val vertex3 = viewModel.addVertex("3", 300.dp, 300.dp)


        viewModel.addEdge(vertex1.vertexID, vertex2.vertexID, "rebro1")
        viewModel.addEdge(vertex2.vertexID, vertex3.vertexID, "rebro2")
        viewModel.addEdge(vertex3.vertexID, vertex1.vertexID, "rebro3")


        assertTrue(sqLiteMainLogic.saveToSQLiteDataBase(viewModel, "test_findCycles"))
        val loadedViewModel = sqLiteMainLogic.readFromSQLiteDataBase<String, String>("test_findCycles")

        assertNotNull(loadedViewModel)
    }

    @Test
    @Tag("SQLite - saving and loading weighted graph")
    fun `SQLite - saving and loading weighted graph`() {

        val viewModel = GraphViewModel(
            graph = weightedGraph,
            showVerticesLabels = mutableStateOf(false),
            showEdgesWeights = mutableStateOf(false),
            isWeightedGraph = mutableStateOf(false),
            isDirectedGraph = mutableStateOf(true)
        )

        val vertex1 = viewModel.addVertex("1", 100.dp, 100.dp)
        val vertex2 = viewModel.addVertex("2", 200.dp, 200.dp)
        val vertex3 = viewModel.addVertex("3", 300.dp, 300.dp)


        viewModel.addEdge(vertex1.vertexID, vertex2.vertexID, "rebro1", "100")
        viewModel.addEdge(vertex2.vertexID, vertex3.vertexID, "rebro2", "100")
        viewModel.addEdge(vertex3.vertexID, vertex1.vertexID, "rebro3", "100")


        assertTrue(sqLiteMainLogic.saveToSQLiteDataBase(viewModel, "test_findCycles"))
        val loadedViewModel = sqLiteMainLogic.readFromSQLiteDataBase<String, String>("test_findCycles")

        assertNotNull(loadedViewModel)
    }

    @Test
    @Tag("SQLite - saving and loading directed weighted graph")
    fun `SQLite - saving and loading directed weighted graph`() {

        val viewModel = GraphViewModel(
            graph = directWeightedGraph,
            showVerticesLabels = mutableStateOf(false),
            showEdgesWeights = mutableStateOf(false),
            isWeightedGraph = mutableStateOf(false),
            isDirectedGraph = mutableStateOf(true)
        )

        val vertex1 = viewModel.addVertex("1", 100.dp, 100.dp)
        val vertex2 = viewModel.addVertex("2", 200.dp, 200.dp)
        val vertex3 = viewModel.addVertex("3", 300.dp, 300.dp)


        viewModel.addEdge(vertex1.vertexID, vertex2.vertexID, "rebro1", "100")
        viewModel.addEdge(vertex2.vertexID, vertex3.vertexID, "rebro2", "100")
        viewModel.addEdge(vertex3.vertexID, vertex1.vertexID, "rebro3", "100")


        assertTrue(sqLiteMainLogic.saveToSQLiteDataBase(viewModel, "test_findCycles"))
        val loadedViewModel = sqLiteMainLogic.readFromSQLiteDataBase<String, String>("test_findCycles")

        assertNotNull(loadedViewModel)
    }

    @Test
    @Tag("SQLite - Weighted")
    fun `SQLite - saving and loading weighted graph with various weights`() {
        val viewModel = GraphViewModel(
            graph = weightedGraph,
            showVerticesLabels = mutableStateOf(true),
            showEdgesWeights = mutableStateOf(true),
            isWeightedGraph = mutableStateOf(true),
            isDirectedGraph = mutableStateOf(false)
        )

        val vertex1 = viewModel.addVertex("A", 100.dp, 100.dp)
        val vertex2 = viewModel.addVertex("B", 200.dp, 200.dp)
        val vertex3 = viewModel.addVertex("C", 300.dp, 300.dp)
        val vertex4 = viewModel.addVertex("D", 400.dp, 400.dp)

        viewModel.addEdge(vertex1.vertexID, vertex2.vertexID, "rebro1", "15.5")
        viewModel.addEdge(vertex2.vertexID, vertex3.vertexID, "rebro2", "22.3")
        viewModel.addEdge(vertex3.vertexID, vertex4.vertexID, "rebro3", "8.7")
        viewModel.addEdge(vertex4.vertexID, vertex1.vertexID, "rebro4", "30.0")

        assertTrue(sqLiteMainLogic.saveToSQLiteDataBase(viewModel, "test_weighted_graph"))
        val loadedViewModel = sqLiteMainLogic.readFromSQLiteDataBase<String, String>("test_weighted_graph")

        assertNotNull(loadedViewModel)
        assertEquals(4, loadedViewModel!!.vertices.size)
        assertEquals(4, loadedViewModel.edges.size)

        val weights = loadedViewModel.edges.mapNotNull { it.weight }
        assertTrue(weights.contains("15.5"))
        assertTrue(weights.contains("22.3"))
        assertTrue(weights.contains("8.7"))
        assertTrue(weights.contains("30.0"))
    }

    @Test
    @Tag("SQLite - Empty")
    fun `SQLite - saving and loading empty graph`() {
        val viewModel = GraphViewModel(
            graph = directGraph,
            showVerticesLabels = mutableStateOf(false),
            showEdgesWeights = mutableStateOf(false),
            isWeightedGraph = mutableStateOf(false),
            isDirectedGraph = mutableStateOf(true)
        )

        assertTrue(sqLiteMainLogic.saveToSQLiteDataBase(viewModel, "test_empty_graph"))
        val loadedViewModel = sqLiteMainLogic.readFromSQLiteDataBase<String, String>("test_empty_graph")

        assertNotNull(loadedViewModel)
        assertEquals(0, loadedViewModel!!.vertices.size)
        assertEquals(0, loadedViewModel.edges.size)
    }

    @Test
    @Tag("SQLite - Stress")
    fun `SQLite - stress test - large graph performance`() {
        val viewModel = GraphViewModel(
            graph = directWeightedGraph,
            showVerticesLabels = mutableStateOf(false),
            showEdgesWeights = mutableStateOf(true),
            isWeightedGraph = mutableStateOf(true),
            isDirectedGraph = mutableStateOf(true)
        )

        val vertexCount = 1000
        val edgeCount = 2000
        val vertices = mutableListOf<org.graphApp.viewmodel.graph.VertexViewModel<String>>()

        val vertexCreationTime = measureTimeMillis {
            repeat(vertexCount) { i ->
                val vertex = viewModel.addVertex(
                    "Node_$i",
                    Random.nextInt(50, 1000).dp,
                    Random.nextInt(50, 1000).dp
                )
                vertices.add(vertex)
            }
        }

        val edgeCreationTime = measureTimeMillis {
            repeat(edgeCount) { i ->
                val fromVertex = vertices[Random.nextInt(vertices.size)]
                val toVertex = vertices[Random.nextInt(vertices.size)]
                val weight = Random.nextDouble(1.0, 100.0).toString()

                viewModel.addEdge(fromVertex.vertexID, toVertex.vertexID, "rebro_$i", weight)
            }
        }

        assertEquals(vertexCount, viewModel.vertices.size)
        assertTrue(viewModel.edges.size >= edgeCount)

        val saveTime = measureTimeMillis {
            assertTrue(sqLiteMainLogic.saveToSQLiteDataBase(viewModel, "stress_test_large_graph"))
        }

        val loadTime = measureTimeMillis {
            val loadedViewModel = sqLiteMainLogic.readFromSQLiteDataBase<String, String>("stress_test_large_graph")
            assertNotNull(loadedViewModel)
            assertEquals(vertexCount, loadedViewModel!!.vertices.size)
            assertTrue(loadedViewModel.edges.size >= edgeCount)
        }

        assertTrue(saveTime < 10000)
        assertTrue(loadTime < 10000)
    }
}
