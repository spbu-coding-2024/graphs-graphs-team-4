package org.graphApp.viewmodel.graph

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.Dp
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

    private val _showEdgeWeightPopup = mutableStateOf(false)
    val showEdgeWeightPopup: Boolean
        get() = _showEdgeWeightPopup.value

    private val _selectFirstVertex = mutableStateOf<VertexViewModel<V>?>(null)
    val selectFirstVertex: VertexViewModel<V>?
        get() = _selectFirstVertex.value


    fun addVertex(label: V, x: Dp , y: Dp): VertexViewModel<V> {
        val vertex = graph.addVertex(label)
        return _vertices.getOrPut(vertex.id) {
            VertexViewModel(
                x = x,
                y = y,
                v = vertex,
                _labelVisible = showVerticesLabels,
            )
        }
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

    fun clear() {
        _vertices.clear()
        _edges.clear()
    }


    fun onVertexSelected(vertex: VertexViewModel<V>) {
        when {
            _selectFirstVertex.value == null -> {
                _selectFirstVertex.value = vertex
                vertex.selected = true
            }
            _selectFirstVertex.value?.vertexID == vertex.vertexID -> {
                _selectFirstVertex.value?.selected = false
                _selectFirstVertex.value = null
            }
            else -> {
                val edgeExists = _edges.any {
                    (it.v.vertexID == vertex.vertexID && it.u.vertexID == _selectFirstVertex.value?.vertexID) ||
                        (it.u.vertexID == vertex.vertexID && it.v.vertexID == _selectFirstVertex.value?.vertexID )
                }

                if(!edgeExists) {
                    createEdge(_selectFirstVertex.value!!.vertexID, vertex.vertexID, null as E)
                }
                _selectFirstVertex.value?.selected = false
                _selectFirstVertex.value = null
                vertex.selected = false
            }
        }
    }


    fun createEdge (fromV: Long, toV: Long, edgeV: E, weight: String? = null): EdgeViewModel<E,V>? {
        return addEdge(fromV, toV,edgeV, weight)
    }

}
