package org.graphApp.model.graph

internal class UndirectedGraph<V, E> : Graph<V, E> {
    private val _vertices = hashMapOf<Long, Vertex<V>>()
    private val elementToVertex = hashMapOf<V, Vertex<V>>()
    private val _edges = hashMapOf<E, Edge<E, V>>()
    private var vertexIdCounter: Long = 0

    override val vertices: Collection<Vertex<V>> get() = _vertices.values
    override val edges: Collection<Edge<E, V>> get() = _edges.values


    override fun addVertex(v: V): Vertex<V> =
        elementToVertex.getOrPut(v) {
            val newVertex = DVertex(vertexIdCounter++, v)
            _vertices[newVertex.id] = newVertex
            newVertex
        }

    override fun addEdge(u: V, v: V, e: E): Edge<E, V> {
        val from = addVertex(u)
        val to = addVertex(v)
        return _edges.getOrPut(e) { DataEdge(e, from to to) }
    }

    private data class DataEdge<E, V>(
        override val element: E,
        override val vertices: Pair<Vertex<V>, Vertex<V>>
    ) : Edge<E, V>
}
