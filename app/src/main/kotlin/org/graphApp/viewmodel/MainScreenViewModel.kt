package org.graphApp.viewmodel

import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Offset
import org.graphApp.model.graph.DirectGraph
import org.graphApp.model.graph.DirectWeightedGraph
import org.graphApp.model.graph.Graph
import org.graphApp.model.graph.UndirectedGraph
import org.graphApp.model.graph.WeightedGraph
import org.graphApp.viewmodel.graph.GraphViewModel
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
