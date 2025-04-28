package org.graphApp.viewmodel.graph

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.graphApp.model.graph.*

class GraphViewModel<V, E>(
    val graph: Graph<V, E>,
    val showVerticesLabels: State<Boolean>,
    val showEdgesWeights: State<Boolean>,
    val showDirections: State<Boolean>,
) {
    private val _vertices = mutableStateMapOf<Long, VertexViewModel<V>>()
    val vertices: Collection<VertexViewModel<V>>
        get() = _vertices.values

    private val _edges = mutableStateListOf<EdgeViewModel<E,V>>()
    val edges: Collection<EdgeViewModel<E,V>>
        get() = _edges

    fun addVertex(vertex: V, x: Dp , y: Dp): VertexViewModel<V> {
        val vertex = graph.addVertex(vertex)
        val vm = VertexViewModel (
            x = x,
            y = y,
            v = vertex,
            _labelVisible = showVerticesLabels,

        )
        _vertices[vertex.id] = vm
        return vm
    }

    fun removeVertex(vertexID: Long) {
        val vm = _vertices.remove(vertexID)
        vm?.let { removeVertex ->
            _edges.removeAll { edgevm ->
                edgevm.u.vertexID == vertexID || edgevm.v.vertexID == vertexID
            }
        }
    }


    fun addEdge(fromVertedID: Long, toVertexID: Long, edgeValue: E, weight: String? = null): EdgeViewModel<E,V>? {
        val from = _vertices[fromVertedID]
        val to = _vertices[toVertexID]

        if (from == null || to == null) return null

        val edge = when (graph) {
            is DirectedWeightedGraph -> graph.addEdge(from.vertex.element, to.vertex.element, edgeValue, weight ?: "")
            is UndirectedWeightedGraph -> graph.addEdge(from.vertex.element, to.vertex.element, edgeValue, weight ?: "")
            else -> graph.addEdge(from.vertex.element, to.vertex.element, edgeValue)
        }

        val edgevm = EdgeViewModel (
            u = from,
            v = to,
            edge = edge,
            _weightVisible = showEdgesWeights,
            _directionVisible = showDirections
        )
        _edges += edgevm
        return edgevm
    }
    fun removeEdge(edgeElement: E) {
        _edges.removeAll { edgeVM -> edgeVM.label == edgeElement.toString() }
    }

    fun clear() {
        _vertices.clear()
        _edges.clear()
    }


}
