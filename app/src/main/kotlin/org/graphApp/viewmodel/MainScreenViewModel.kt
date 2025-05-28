package org.graphApp.viewmodel

import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import org.graphApp.model.graph.DirectGraph
import org.graphApp.model.graph.DirectWeightedGraph
import org.graphApp.model.graph.DirectedWeightedGraph
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.graphApp.currentGraph
import org.graphApp.model.graph.Edge
import org.graphApp.model.graph.Graph
import org.graphApp.model.graph.UndirectedGraph
import org.graphApp.model.graph.WeightedGraph
import org.graphApp.viewmodel.graph.GraphViewModel
import kotlin.math.exp
import kotlin.random.Random


class MainScreenViewModel<E>(graph: Graph<String, E>) {


    var scale by mutableStateOf(1f)
    var rotation by mutableStateOf(0f)
    var offset by mutableStateOf(Offset.Zero)


    private var _isWeightedGraphState = mutableStateOf(false)
    var isWeightedGraph: Boolean
        get() = _isWeightedGraphState.value
        set(value) {
            _isWeightedGraphState.value = value
        }


    private var _isDirectedGraphState = mutableStateOf(false)
    var isDirectedGraph: Boolean
        get() = _isDirectedGraphState.value
        set(value) {
            _isDirectedGraphState.value = value
        }


    private var _showVertexLabels = mutableStateOf(false)
    var showVertexLabels: Boolean
        get() = _showVertexLabels.value
        set(value) {
            _showVertexLabels.value = value
        }


    private var _showEdgesWeights = mutableStateOf(false)
    var showEdgesWeight: Boolean
        get() = _showEdgesWeights.value
        set(value) {
            _showEdgesWeights.value = value
        }

    fun scale(delta: Int) {
        scale = (scale * exp(delta * 0.2f)).coerceIn(-0.25f, 5f)
    }

    var graphViewModel by mutableStateOf(
        GraphViewModel(
            graph = graph,
            showVerticesLabels = _showVertexLabels,
            showEdgesWeights = _showEdgesWeights,
            isWeightedGraph = _isWeightedGraphState,
            isDirectedGraph = _isDirectedGraphState,
        )
    )

    fun scaleAt(delta: Int, cursorPosition: Offset) {
        val scaleFactor = if (delta > 0) 1.1f else 0.9f
        val oldScale = scale
        val newScale = (scale * scaleFactor)

        val offsetToCursor = (cursorPosition - offset) / oldScale

        offset = cursorPosition - offsetToCursor * newScale
        scale = newScale
    }


    @Suppress("UNCHECKED_CAST")
    fun generateLargeGraph(vertexCount: Int = 10000, edgeCount: Int = 20000) {
        createNewGraph(isWeightedGraph, isDirectedGraph)

        val vertices = mutableListOf<Long>()

        for (i in 0 until vertexCount) {
            val vertex = graphViewModel.addVertex(
                label = "V$i",
                x = ((i % 50) * 20f).dp,
                y = ((i / 50) * 20f).dp
            )
            vertices.add(vertex.vertexID)
        }

        repeat(edgeCount) {
            val from = vertices.random()
            val to = vertices.random()
            if (from != to) {
                val weight = if (isWeightedGraph) Random.nextDouble(0.0, 10000.0) else null
                val _weight: String = weight.toString()
                graphViewModel.addEdge(
                    fromVertedID = from,
                    toVertexID = to,
                    edgeValue = "" as E,
                    weight = _weight
                )
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun loadGraphFromDatabase(newGraphViewModel: GraphViewModel<*, *>, graphName: String) {
        try {
            graphViewModel.clear()

            val loadedGraph = newGraphViewModel.graph

            isWeightedGraph = loadedGraph is WeightedGraph<*, *> || loadedGraph is DirectedWeightedGraph<*, *>
            isDirectedGraph = loadedGraph is DirectGraph<*, *> || loadedGraph is DirectedWeightedGraph<*, *>

            graphViewModel = GraphViewModel(
                graph = loadedGraph as Graph<String, E>,
                showVerticesLabels = _showVertexLabels,
                showEdgesWeights = _showEdgesWeights,
                isWeightedGraph = _isWeightedGraphState,
                isDirectedGraph = _isDirectedGraphState
            )

            restoreVisualization(newGraphViewModel)


        } catch (e: Exception) {
            println("Error loading graph : ${e.message}")
            e.printStackTrace()
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun restoreVisualization(loadedViewModel: GraphViewModel<*, *>) {
        val vertexMap = mutableMapOf<Long, Long>()

        loadedViewModel.vertices.forEach { oldVertexVM ->
            val newVertexVM = graphViewModel.addVertex(
                label = oldVertexVM.vertex.element as String,
                x = oldVertexVM.x,
                y = oldVertexVM.y
            )
            vertexMap[oldVertexVM.vertexID] = newVertexVM.vertexID
        }

        loadedViewModel.edges.forEach { oldEdgeVM ->
            val fromVertexId = vertexMap[oldEdgeVM.u.vertexID]
            val toVertexId = vertexMap[oldEdgeVM.v.vertexID]

            if (fromVertexId != null && toVertexId != null) {
                graphViewModel.addEdge(
                    fromVertedID = fromVertexId,
                    toVertexID = toVertexId,
                    edgeValue = oldEdgeVM.edge.element as E,
                    weight = oldEdgeVM.weight
                )
            }
        }
    }

    fun createNewGraph(isWeighted: Boolean, isDirected: Boolean) {

        graphViewModel.clear()
        val newGraph: Graph<String, E> = when {
            isDirected && isWeighted -> {
                DirectWeightedGraph<String, E>()
            }

            !isDirected && isWeighted -> {
                WeightedGraph<String, E>()
            }

            isDirected && !isWeighted -> {
                DirectGraph<String, E>()
            }

            else -> {
                UndirectedGraph<String, E>()
            }
        }

        isWeightedGraph = isWeighted
        isDirectedGraph = isDirected

        graphViewModel = GraphViewModel(
            graph = newGraph,
            showVerticesLabels = _showVertexLabels,
            showEdgesWeights = _showEdgesWeights,
            isWeightedGraph = _isWeightedGraphState,
            isDirectedGraph = _isDirectedGraphState
        )
    }

    fun randomLongExcluding(min: Long, max: Long, exclude: Long): Long {
        require(max > min) { "Invalid diapason" }
        var value: Long
        do {
            value = Random.nextLong(min, max + 1)
        } while (value == exclude)
        return value
    }


    fun createRandomGraph(isWeighted: Boolean): Job = CoroutineScope(Dispatchers.Default).launch {
        val randomVertices = Random.nextLong(50L, 800L)
        val randomEdges = (4..8).random()
        for (vertexID in 0..randomVertices) {
            graphViewModel.addVertex(vertexID.toString(), 0.dp, 0.dp)

        }
        for (vertexIDFrom in 0..randomVertices) {
            val vertexIDTo = randomLongExcluding(0, randomVertices, vertexIDFrom)
            if (isWeighted) {
                repeat(randomEdges) {
                    graphViewModel.addEdge(vertexIDFrom, vertexIDTo, vertexIDFrom.toString() as E)
                }
            } else {
                repeat(randomEdges) {
                    graphViewModel.addEdge(
                        vertexIDFrom, vertexIDTo, vertexIDFrom.toString() as E,
                        Random.nextLong(1L, 100L).toString() as String
                    )
                }
            }
        }

    }
}
