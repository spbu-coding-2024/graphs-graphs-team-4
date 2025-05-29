package org.graphApp.model.graph

internal class DirectGraph<V, E> : DirectedUnWeightedGraph<V, E> {
    private val _vertices = hashMapOf<Long, Vertex<V>>()
    private val elementToVertex = hashMapOf<V, Vertex<V>>()
    private val _edges = hashMapOf<E, DirectedEdge<E, V>>()
    private var vertexIdCounter: Long = 0
    override val vertices: Collection<Vertex<V>> get() = _vertices.values
    override val edges: Collection<Edge<E, V>> get() = _edges.values

    override fun addVertex(v: V): Vertex<V> =
        elementToVertex.getOrPut(v) {
            val newVertex = DVertex(vertexIdCounter++, v)
            _vertices[newVertex.id] = newVertex
            newVertex
        }

    override fun addEdge(u: V, v: V, e: E): DirectedEdge<E, V> {
        val from = addVertex(u)
        val to = addVertex(v)
        return _edges.getOrPut(e) { DataDirectedEdge(e, from, to) }
    }
    override fun removeVertex(vertex: Vertex<V>) {
        val removed = _vertices.remove(vertex.id) != null
        if (removed) {
            elementToVertex.remove(vertex.element)
            val edgesToRemove = _edges.values.filter { edge ->
                edge.from.id == vertex.id || edge.to.id == vertex.id
            }
            edgesToRemove.forEach { edge ->
                _edges.remove(edge.element)
            }
        }
    }

    override fun removeEdge(edge: Edge<E, V>) {
        _edges.remove(edge.element) != null
    }

    override fun clear() {
        _vertices.clear()
        elementToVertex.clear()
        _edges.clear()
        vertexIdCounter = 0
    }


    private data class DataDirectedEdge<E, V>(
        override val element: E,
        override var from: Vertex<V>,
        override var to: Vertex<V>
    ) : DirectedEdge<E, V>
}
