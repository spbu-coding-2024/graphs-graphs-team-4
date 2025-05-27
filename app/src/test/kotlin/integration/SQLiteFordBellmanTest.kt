package integration

import androidx.compose.runtime.mutableStateOf
import data.SQLiteMainLogic.SQLiteExposed
import data.SQLiteMainLogic.SQLiteMainLogic
import org.graphApp.model.graph.*
import org.graphApp.model.graph.algorithms.FordBellman
import org.graphApp.viewmodel.graph.GraphViewModel
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.AfterEach
import androidx.compose.ui.unit.dp
import org.graphApp.viewmodel.graph.VertexViewModel
import org.junit.jupiter.api.Tag
import java.io.File

class SQLiteFordBellmanTest {

    private lateinit var sqliteExposed: SQLiteExposed
    private lateinit var sqLiteMainLogic: SQLiteMainLogic<String, String>
    private lateinit var directGraph: DirectGraph<String, String>
    private lateinit var undirectedGraph: UndirectedGraph<String, String>
    private lateinit var weightedGraph: WeightedGraph<String, String>
    private lateinit var directWeightedGraph: DirectWeightedGraph<String, String>
    private val testDBname = "testFBintegration.db"

    @BeforeEach
    fun init() {
        File(testDBname).delete()

        sqliteExposed = SQLiteExposed(testDBname)
        sqLiteMainLogic = SQLiteMainLogic(sqliteExposed)

        directGraph = DirectGraph()
        undirectedGraph = UndirectedGraph()
        weightedGraph = WeightedGraph()
        directWeightedGraph = DirectWeightedGraph()
    }

    @AfterEach
    fun cleanup() {
        File(testDBname).delete()
    }

    @Test
    @Tag("Integration test - save dir weighted graph - FordBellman")
    fun `integration test - save dir weighted graph - FordBellman`() {
        val viewModel = GraphViewModel(
            graph = directWeightedGraph,
            showVerticesLabels = mutableStateOf(true),
            showEdgesWeights = mutableStateOf(true),
            isWeightedGraph = mutableStateOf(true),
            isDirectedGraph = mutableStateOf(true)
        )

        val vertices = mutableListOf<VertexViewModel<String>>()
        for (i in 0..100) {
            vertices.add(viewModel.addVertex("$i", (i * 10).dp, (10 * i).dp))
        }

        for (i in 0..99) {
            viewModel.addEdge(vertices[i].vertexID, vertices[i + 1].vertexID, "rebro$i", "100.0")
        }

        val saveResult = sqLiteMainLogic.saveToSQLiteDataBase(viewModel, "test_weighted_graph")
        assertTrue(saveResult)

        val loadedViewModel = sqLiteMainLogic.readFromSQLiteDataBase<String, String>("test_weighted_graph")
        assertNotNull(loadedViewModel)

        assertEquals(101, loadedViewModel!!.vertices.size)
        assertEquals(100, loadedViewModel.edges.size)
        assertTrue(loadedViewModel.isWeightedGraph.value)
        assertTrue(loadedViewModel.isDirectedGraph.value)

        val fordBellman = FordBellman(graph = loadedViewModel.graph)

        val startVertex = loadedViewModel.vertices.find { it.value == "1" }
        val endVertex = loadedViewModel.vertices.find { it.value == "100" }

        assertNotNull(startVertex)
        assertNotNull(endVertex)

        val result = fordBellman.fordBellman(startVertex!!.vertexID, endVertex!!.vertexID)

        assertNotNull(result)

        val (vertexPath, edges) = result!!

        assertNotNull(vertexPath)
        assertNotNull(edges)

        assertTrue(vertexPath!!.isNotEmpty())

        assertEquals(startVertex.vertexID, vertexPath.first())
        assertEquals(endVertex.vertexID, vertexPath.last())

        assertFalse(fordBellman.hasNegativeCycle[endVertex.vertexID] == true)

        assertEquals(100, vertexPath.size)
        assertEquals(99, edges.size)

        assertEquals(9900.0, fordBellman.d[endVertex.vertexID]!!, 0.001)
    }

    @Test
    @Tag("Integration test - save empty graph - FordBellman")
    fun `integration test - save empty graph - FordBellman`() {
        val viewModel = GraphViewModel(
            graph = undirectedGraph,
            showVerticesLabels = mutableStateOf(false),
            showEdgesWeights = mutableStateOf(false),
            isWeightedGraph = mutableStateOf(false),
            isDirectedGraph = mutableStateOf(false)
        )

        val vertex1 = viewModel.addVertex("1", 100.dp, 100.dp)
        val vertex2 = viewModel.addVertex("2", 200.dp, 200.dp)
        val vertex3 = viewModel.addVertex("3", 300.dp, 300.dp)
        val vertex4 = viewModel.addVertex("4", 400.dp, 400.dp)

        viewModel.addEdge(vertex1.vertexID, vertex2.vertexID, "rebro1")
        viewModel.addEdge(vertex3.vertexID, vertex4.vertexID, "rebro2")

        assertTrue(sqLiteMainLogic.saveToSQLiteDataBase(viewModel, "test_empty"))
        val loadedViewModel = sqLiteMainLogic.readFromSQLiteDataBase<String, String>("test_empty")
        assertNotNull(loadedViewModel)

        val fordBellman = FordBellman(graph = loadedViewModel!!.graph)
        val startVertex = loadedViewModel.vertices.find { it.value == "1" }
        val endVertex = loadedViewModel.vertices.find { it.value == "4" }

        val result = fordBellman.fordBellman(startVertex!!.vertexID, endVertex!!.vertexID)
        assertNull(result)

    }



}