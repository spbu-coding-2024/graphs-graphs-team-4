package org.graphApp.model.graph

internal class DirectWeightedGraph<V, E> : DirectedWeightedGraph<V, E> {
    private val _vertices = hashMapOf<Long, Vertex<V>>()
    private val elementToVertex = hashMapOf<V, Vertex<V>>()
    private val _edges = hashMapOf<E, WeightedDirectedEdge<E, V>>()
    companion object {
        private var vertexIdCounter: Long = 0
    }
    override val vertices: Collection<Vertex<V>> get() = _vertices.values
    override val edges: Collection<Edge<E, V>> get() = _edges.values

    override fun addVertex(v: V): Vertex<V> =
        elementToVertex.getOrPut(v) {
            val newVertex = DVertex(vertexIdCounter++, v)
            _vertices[newVertex.id] = newVertex
            newVertex
        }

    override fun addEdge(u: V, v: V, e: E): Edge<E, V> =
        throw IllegalArgumentException("You must set weight for your edge")

    override fun addEdge(u: V, v: V, e: E, weight: String): WeightedDirectedEdge<E, V> {
        val from = addVertex(u)
        val to = addVertex(v)
        return _edges.getOrPut(e) { DataWeightedDirectedEdge(e, from, to, weight) }
    }

    private data class DataWeightedDirectedEdge<E, V>(
        override val element: E,
        override var from: Vertex<V>,
        override var to: Vertex<V>,
        override var weight: String
    ) : WeightedDirectedEdge<E, V>
}
