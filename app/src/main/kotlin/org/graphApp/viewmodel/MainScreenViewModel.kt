package org.graphApp.viewmodel

import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import org.graphApp.model.graph.DirectGraph
import org.graphApp.model.graph.DirectWeightedGraph
import org.graphApp.model.graph.DirectedWeightedGraph
import org.graphApp.model.graph.Graph
import org.graphApp.model.graph.UndirectedGraph
import org.graphApp.model.graph.UndirectedWeightedGraph
import org.graphApp.model.graph.WeightedGraph
import org.graphApp.view.algorithms.AlgorithmsView
import org.graphApp.viewmodel.graph.GraphViewModel
import sun.awt.X11.XUtilConstants.ZoomState
import kotlin.math.exp


class MainScreenViewModel<E>(graph: Graph<String, E>) {


    var scale by mutableStateOf(1f)
    var rotation by mutableStateOf(0f)
    var offset by  mutableStateOf(Offset.Zero)


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

    var graphViewModel by mutableStateOf(GraphViewModel(
        graph = graph,
        showVerticesLabels = _showVertexLabels,
        showEdgesWeights = _showEdgesWeights,
        isWeightedGraph = _isWeightedGraphState,
        isDirectedGraph = _isDirectedGraphState,
    ))

    @Suppress("UNCHECKED_CAST")
    fun loadGraphFromDatabase(newGraphViewModel : GraphViewModel<*,*>, graphName : String) {
        try {
            graphViewModel.clear()

            val loadedGraph = newGraphViewModel.graph

            isWeightedGraph = loadedGraph is WeightedGraph<*, *> || loadedGraph is DirectedWeightedGraph<*, *>
            isDirectedGraph = loadedGraph is DirectGraph<*,*> || loadedGraph is DirectedWeightedGraph<*,*>

            graphViewModel = GraphViewModel(
                graph = loadedGraph as Graph<String, E>,
                showVerticesLabels = _showVertexLabels,
                showEdgesWeights = _showEdgesWeights,
                isWeightedGraph = _isWeightedGraphState,
                isDirectedGraph = _isDirectedGraphState
            )

            restoreVisualization(newGraphViewModel)


        } catch(e : Exception) {
            println("Error loading graph : ${e.message}")
            e.printStackTrace()
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun restoreVisualization(loadedViewModel: GraphViewModel<*,*>) {
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
}
