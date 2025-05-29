package org.graphApp.viewmodel.graph

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import org.graphApp.model.graph.*

class GraphViewModel<V, E>(
    val graph: Graph<V, E>,
    val showVerticesLabels: State<Boolean>,
    val showEdgesWeights: State<Boolean>,
    val isWeightedGraph: State<Boolean>,
    val isDirectedGraph: State<Boolean>,
    val highlight: State<Boolean> = mutableStateOf(false)
) {
    private var edgeId = 0L
    private val _vertices = mutableStateMapOf<Long, VertexViewModel<V>>()
    val vertices: Collection<VertexViewModel<V>>
        get() = _vertices.values

    private val _edges = mutableStateListOf<EdgeViewModel<E, V>>()
    val edges: Collection<EdgeViewModel<E, V>>
        get() = _edges

    private val _showEdgeWeight = mutableStateOf(false)
    val showEdgeWeight: Boolean
        get() = _showEdgeWeight.value

    private val _selectFirstVertex = mutableStateOf<VertexViewModel<V>?>(null)

    fun addVertex(label: V, x: Dp, y: Dp): VertexViewModel<V> {
        val vertex = graph.addVertex(label)
        return _vertices.getOrPut(vertex.id) {
            VertexViewModel(
                x = x,
                y = y,
                v = vertex,
                _labelVisible = showVerticesLabels,
                highlight = highlight,
            )
        }
    }

    fun removeVertex(vertex: VertexViewModel<V>) {
        vertex.selected = false
        _selectFirstVertex.value = null
        val vertexID = vertex.vertexID
        val vm = _vertices.remove(vertexID)
        vm?.let { removeVertex ->
            _edges.removeAll { edgevm ->
                edgevm.u.vertexID == vertexID || edgevm.v.vertexID == vertexID
            }
        }
    }

    private val _edgeWeightPosition = mutableStateOf(DpOffset.Zero)
    val edgeWeightPopupPosition: DpOffset
        get() = _edgeWeightPosition.value

    private var pendingFromId: Long? = null
    private var pendingToId: Long? = null

    private fun showEdgeWeight(from: VertexViewModel<V>, to: VertexViewModel<V>) {
        val cx: Dp = ((from.x + to.x) / 2)
        val cy: Dp = ((from.y + to.y) / 2)
        _edgeWeightPosition.value = DpOffset(cx, cy)

        pendingFromId = from.vertexID
        pendingToId = to.vertexID
        _showEdgeWeight.value = true
    }

    fun dismissEdgeWeight() {
        _showEdgeWeight.value = false
        pendingFromId = null
        pendingToId = null
    }

    fun confirmEdgeWeight(weight: String) {
        if (pendingFromId != null && pendingToId != null) {
            createEdge(pendingFromId!!, pendingToId!!, weight)
        }
        dismissEdgeWeight()
    }

    fun addEdge(fromVertedID: Long, toVertexID: Long, edgeValue: E, weight: String? = null): EdgeViewModel<E, V>? {
        val from = _vertices[fromVertedID]
        val to = _vertices[toVertexID]

        if (from == null || to == null) return null

        val edge = when (graph) {
            is WeightedGraph -> (graph).addEdge(from.vertex.element, to.vertex.element, edgeValue, weight ?: "0")
            is DirectedWeightedGraph -> (graph).addEdge(
                from.vertex.element,
                to.vertex.element,
                edgeValue,
                weight ?: "0"
            )


            else -> graph.addEdge(from.vertex.element, to.vertex.element, edgeValue)
        }

        val edgevm = EdgeViewModel(
            u = from,
            v = to,
            edge = edge,
            _weightVisible = showEdgesWeights,
            _directVisible = isDirectedGraph
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
                            (it.u.vertexID == vertex.vertexID && it.v.vertexID == _selectFirstVertex.value?.vertexID)
                }

                if (!edgeExists) {
                    if (isWeightedGraph.value) {
                        showEdgeWeight(_selectFirstVertex.value!!, vertex)
                    } else {
                        createEdge(
                            _selectFirstVertex.value!!.vertexID,
                            vertex.vertexID, null
                        )
                    }
                }
                _selectFirstVertex.value?.selected = false
                _selectFirstVertex.value = null
                vertex.selected = false
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun createEdge(from: Long, to: Long, weight: String? = null) {
        val id = edgeId++
        addEdge(from, to, id as E, weight)
    }

}
