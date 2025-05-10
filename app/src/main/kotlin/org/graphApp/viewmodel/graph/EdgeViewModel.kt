package org.graphApp.viewmodel.graph

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.graphApp.model.graph.*

class EdgeViewModel<E, V>(
    val u: VertexViewModel<V>,
    val v: VertexViewModel<V>,
    private var edge: Edge<E, V>,
    private val _weightVisible: State<Boolean>,
    private val _directionVisible: State<Boolean>,
) {
    val label: String
        get() = edge.element.toString()


    private val weightedEdge get() = edge as? WeightedEdge<*, *>
    private val directedEdge get() = edge as? DirectedEdge<*, *>

    val weightVisible: Boolean
        get() = _weightVisible.value && weightedEdge != null





    val weight: String?
        get() = weightedEdge?.weight

    val directionVisible: Boolean
        get() = _directionVisible.value && directedEdge != null

    val isDirected: Boolean?
        get() = directedEdge?.let { it.from.id == u.vertexID && it.to.id == v.vertexID }

}
