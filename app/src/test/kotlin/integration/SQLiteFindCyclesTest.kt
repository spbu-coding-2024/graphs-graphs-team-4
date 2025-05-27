package integration

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import data.SQLiteMainLogic.SQLiteExposed
import data.SQLiteMainLogic.SQLiteMainLogic
import org.graphApp.model.graph.DirectGraph
import org.graphApp.model.graph.algorithms.FindCycles
import org.graphApp.viewmodel.graph.GraphViewModel
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import java.io.File

class SQLiteFindCyclesTest {
    private lateinit var sqliteExposed: SQLiteExposed
    private lateinit var sqLiteMainLogic: SQLiteMainLogic<String, String>
    private lateinit var directGraph: DirectGraph<String, String>
    private val testDBname = "testFBintegration.db"

    @BeforeEach
    fun init() {
        File(testDBname).delete()

        sqliteExposed = SQLiteExposed(testDBname)
        sqLiteMainLogic = SQLiteMainLogic(sqliteExposed)

        directGraph = DirectGraph()
    }

    @AfterEach
    fun cleanup() {
        File(testDBname).delete()
    }


    @Test
    @Tag("Integration test - sqlite and FindCycles")
    fun `integration test - sqlite and FindCycles`() {

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

        val findCycles = FindCycles(graph = loadedViewModel!!.graph)
        val startVertex = loadedViewModel.vertices.find { it.value == "1" }
        val result = findCycles.findCycleDirected(startVertex!!.vertex)

        assertNotNull(result)

        val (vertices, edges) = result!!
        assertEquals(3, vertices!!.size)
        assertEquals(3, edges.size)
    }
}