package org.graphApp.viewmodel.graph

import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.graphApp.model.graph.Edge
import org.graphApp.model.graph.Graph
import org.graphApp.model.graph.Vertex

class GraphViewModel<V, E>(
    private val graph: Graph<V, E>,
    showVerticesLabels: State<Boolean>,
    showEdgesLabels: State<Boolean>,
) {
    private val _vertices = graph.vertices.associateWith { v ->
        VertexViewModel(0.dp, 0.dp, Color.Gray, v, showVerticesLabels)
    }
    private val _edges = graph.edges.associateWith { e ->
        val fst = _vertices[e.vertices.first]
            ?: throw IllegalStateException("VertexView for ${e.vertices.first} not found")
        val snd = _vertices[e.vertices.second]
            ?: throw IllegalStateException("VertexView for ${e.vertices.second} not found")
        EdgeViewModel(fst, snd, e, showEdgesLabels)
    }

    val vertices: Collection<VertexViewModel<V>>
        get() = _vertices.values

    val edges: Collection<EdgeViewModel<E, V>>
        get() = _edges.values

}
