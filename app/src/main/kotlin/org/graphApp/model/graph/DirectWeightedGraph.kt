package org.graphApp.model.graph

internal class DirectWeightedGraph<V, E> : DirectedWeightedGraph<V, E> {
    private val _vertices = hashMapOf<V, Vertex<V>>()
    private val _edges = hashMapOf<E, WeightedDirectedEdge<E, V>>()

    override val vertices: Collection<Vertex<V>> get() = _vertices.values
    override val edges: Collection<Edge<E, V>> get() = _edges.values

    override fun addVertex(v: V, id: String) =
        _vertices.getOrPut(v) { DVertex(id, v) }

    override fun addEdge(u: Pair<String, V>, v: Pair<String, V>, e: E): Edge<E, V> =
        throw IllegalArgumentException("You must set weight for your edge")

    override fun addEdge(u: Pair<String, V>, v: Pair<String, V>, e: E, weight: String): WeightedDirectedEdge<E, V> {
        val first = addVertex(u.second, u.first)
        val second = addVertex(v.second, v.first)
        return _edges.getOrPut(e) { DataWeightedDirectedEdge(e, first, second, weight) }
    }

    private data class DataWeightedDirectedEdge<E, V>(
        override val element: E,
        override var from: Vertex<V>,
        override var to: Vertex<V>,
        override var weight: String
    ) : WeightedDirectedEdge<E, V>
}