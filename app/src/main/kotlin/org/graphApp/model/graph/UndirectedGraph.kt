package org.graphApp.model.graph

internal class UndirectedGraph<V, E> : Graph<V, E> {
    private val _vertices = hashMapOf<V, Vertex<V>>()
    private val _edges = hashMapOf<E, Edge<E, V>>()

    override val vertices: Collection<Vertex<V>> get() = _vertices.values
    override val edges: Collection<Edge<E, V>> get() = _edges.values

    override fun addVertex(v: V, id: String) =
        _vertices.getOrPut(v) { DVertex(id, v) }

    override fun addEdge(u: Vertex<V>, v: Vertex<V>, e: E): Edge<E, V> {
        val first = addVertex(u.element, u.id)
        val second = addVertex(v.element, v.id)
        return _edges.getOrPut(e) { DataEdge(e, first to second) }
    }

    private data class DataEdge<E, V>(
        override val element: E,
        override val vertices: Pair<Vertex<V>, Vertex<V>>
    ) : Edge<E, V>

}


