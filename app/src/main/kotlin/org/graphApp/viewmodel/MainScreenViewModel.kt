package org.graphApp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import org.graphApp.model.graph.Graph
import org.graphApp.viewmodel.graph.GraphViewModel
import org.graphApp.viewmodel.graph.RepresentationStrategy

class MainScreenViewModel<V, E>(graph: Graph<V, E>, private val representationStrategy: RepresentationStrategy) {
    private var _showVerticesLabels = mutableStateOf(false)
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


    val graphViewModel = GraphViewModel(graph, _showVerticesLabels, _showEdgesWeights, _showDirections)

    init {
        representationStrategy.place(800.0, 600.0, graphViewModel.vertices)
    }

    fun resetGraphView() {
        representationStrategy.place(800.0, 600.0, graphViewModel.vertices)
        graphViewModel.vertices.forEach { v -> v.color = Color.Gray }
    }

    fun setVerticesColor() {
        representationStrategy.highlight(graphViewModel.vertices)
    }
}
