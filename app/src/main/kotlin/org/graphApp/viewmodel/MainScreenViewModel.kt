package org.graphApp.viewmodel

import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import org.graphApp.model.graph.DirectGraph
import org.graphApp.model.graph.DirectWeightedGraph
import org.graphApp.model.graph.Graph
import org.graphApp.model.graph.UndirectedGraph
import org.graphApp.model.graph.UndirectedWeightedGraph
import org.graphApp.model.graph.WeightedGraph
import org.graphApp.viewmodel.graph.GraphViewModel
import sun.awt.X11.XUtilConstants.ZoomState
import kotlin.math.exp


class MainScreenViewModel<E>(graph: Graph<String, E>) {
    private var _showVerticesLabels = mutableStateOf(false)
    private var localGraph: Graph<String, E> = graph

    var showVerticesLabels: Boolean
        get() = _showVerticesLabels.value
        set(value) {
            _showVerticesLabels.value = value
        }

    private var _showEdgesLabels = mutableStateOf(false)
    var showEdgesLabels: Boolean
        get() = _showEdgesLabels.value
        set(value) {
            _showEdgesLabels.value = value
        }

    private var _showEdgesWeights = mutableStateOf(false)
    var showWeight: Boolean
        get() = _showEdgesWeights.value
        set(value) {
            _showEdgesWeights.value = value
        }
    private var _showDirections = mutableStateOf(false)
    var showDirections: Boolean
        get() = _showDirections.value
        set(v) { _showDirections.value = v }

    var scale by mutableStateOf(1f)
    var rotation by mutableStateOf(0f)
    var offset by  mutableStateOf(Offset.Zero)

    fun scale(delta: Int) {
        scale = (scale * exp(delta * 0.2f)).coerceIn(-0.25f, 5f)
    }

    var graphViewModel = GraphViewModel(graph, _showVerticesLabels, _showEdgesWeights, _showDirections)

    fun createNewGraph(showWeights: Boolean, showDirected: Boolean) {
        val newGraph: Graph<String, E> = when {
            showDirected && showWeights -> {
                DirectWeightedGraph<String, E>()
            }

                !showDirected && showWeights -> {
                    WeightedGraph<String, E>()
                }

            showDirected && !showWeights -> {
                DirectGraph<String, E>()
            }

            else -> {
                UndirectedGraph<String, E>()
            }
        }

        localGraph.vertices.forEach { vertex -> newGraph.addVertex(vertex.element)}
        localGraph.edges.forEach { edge -> newGraph.addEdge(edge.vertices.first.element, edge.vertices.second.element, edge.element)}

        showWeight = showWeights
        showDirections = showDirected

        graphViewModel = GraphViewModel(newGraph, _showVerticesLabels, _showEdgesWeights, _showDirections)
    }

}
