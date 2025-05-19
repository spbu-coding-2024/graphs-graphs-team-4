package org.graphApp.viewmodel.graph

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import org.graphApp.model.graph.*

class EdgeViewModel<E, V>(
    val u: VertexViewModel<V>,
    val v: VertexViewModel<V>,
    private var edge: Edge<E, V>,
    private val _weightVisible: State<Boolean>,
    private val _directVisible: State<Boolean>
) {
    val label: String
        get() = edge.element.toString()


    private val weightedEdge get() = edge as? WeightedEdge<*, *>
    private val directedEdge get() = edge as? DirectedEdge<*, *>


    val weightVisible: Boolean
        get() = _weightVisible.value && weightedEdge != null

    val directVisible: Boolean
        get() = _directVisible.value && directedEdge != null

    val weight: String?
        get() = weightedEdge?.weight

    private var _color = mutableStateOf(Color.Magenta)
    var color: Color
        get() = _color.value
        set(value) {
            _color.value = value
        }
}
