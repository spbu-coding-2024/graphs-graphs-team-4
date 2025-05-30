package org.graphApp.viewmodel

import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import org.graphApp.model.graph.DirectGraph
import org.graphApp.model.graph.DirectWeightedGraph
import org.graphApp.model.graph.DirectedWeightedGraph
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.graphApp.model.graph.Graph
import org.graphApp.model.graph.UndirectedGraph
import org.graphApp.model.graph.WeightedGraph
import org.graphApp.viewmodel.graph.GraphViewModel
import kotlin.random.Random


class MainScreenViewModel<E>(graph: Graph<String, E>) {


    var scale by mutableStateOf(1f)
    var rotation by mutableStateOf(0f)
    var offset by mutableStateOf(Offset.Zero)
    private var showHighlightOfVertex = mutableStateOf(true)
    var highlight: Boolean
        get() = showHighlightOfVertex.value
        set(value) {
            showHighlightOfVertex.value = value
        }
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

    var graphViewModel by mutableStateOf(
        GraphViewModel(
            graph = graph,
            showVerticesLabels = _showVertexLabels,
            showEdgesWeights = _showEdgesWeights,
            isWeightedGraph = _isWeightedGraphState,
            isDirectedGraph = _isDirectedGraphState,
            highlight = showHighlightOfVertex
        )
    )

    fun scaleAt(delta: Int, screenCenter: Offset) {
        val scaleFactor = if (delta > 0) 1.1f else 0.9f
        val oldScale = scale
        val newScale = (scale * scaleFactor)

        val offsetToCenter = (screenCenter - offset) / oldScale

        offset = screenCenter - offsetToCenter * newScale
        scale = newScale
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
                isDirectedGraph = _isDirectedGraphState,
                highlight = showHighlightOfVertex
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
                label = oldVertexVM.vertex.element as String, x = oldVertexVM.x, y = oldVertexVM.y
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
            isDirectedGraph = _isDirectedGraphState,
            highlight = showHighlightOfVertex
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
        val isExistEdge: (Long, Long) -> Boolean = { from, to ->
            graphViewModel.edges.any { edge ->
                (edge.v.vertexID == from && edge.u.vertexID == to) || (edge.u.vertexID == from && edge.v.vertexID == to)
            }
        }

        val randomVertices = Random.nextLong(10L, 49L)
        val randomEdges = (1..3).random()
        for (vertexID in 0..randomVertices) {
            graphViewModel.addVertex(vertexID.toString(), 0.dp, 0.dp)

        }
        for (i in 0 until randomVertices) {
            val next = (i + 1) % (randomVertices + 1)
            if (!isExistEdge(i, next)) {
                addRandomEdge(i, next, isWeighted)
            }
        }
        for (vertexIDFrom in 0..randomVertices) {
            var vertexIDTo = randomLongExcluding(0, randomVertices, vertexIDFrom)
            repeat(randomEdges) {
                if (isDirectedGraph) {
                    if(isExistEdge(vertexIDFrom, vertexIDTo)) {
                        println(isExistEdge(vertexIDFrom, vertexIDTo))
                        vertexIDTo = Random.nextLong(0, randomVertices-1)
                    } else {
                        addRandomEdge(vertexIDFrom, vertexIDTo, isWeighted)
                    }
                } else {
                    addRandomEdge(vertexIDFrom, vertexIDTo, isWeighted)
                }
            }
        }
    }

    private fun addRandomEdge(vertexIDFrom: Long, vertexIDTo: Long, isWeighted: Boolean) {
        if (!isWeighted) {
            graphViewModel.addEdge(
                vertexIDFrom, vertexIDTo,
                Random.nextLong(1L, 1000L).toString() as E
            )
        } else {
            graphViewModel.addEdge(
                vertexIDFrom,
                vertexIDTo,
                Random.nextLong(1L, 1000L).toString() as E,
                Random.nextLong(1L, 100L).toString() as String
            )
        }
    }
}
