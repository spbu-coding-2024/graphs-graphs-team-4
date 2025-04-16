package org.graphApp.model.graph

internal class WeightedGraph<V, E> : UndirectedWeightedGraph<V, E> {
    private val _vertices = hashMapOf<V, Vertex<V>>()
    private val _edges = hashMapOf<E, WeightedEdge<E, V>>()

    override val vertices: Collection<Vertex<V>> get() = _vertices.values
    override val edges: Collection<Edge<E, V>> get() = _edges.values

    override fun addVertex(v: V, id: String) =
        _vertices.getOrPut(v) { DVertex(id, v) }

    override fun addEdge(u: Pair<String, V>, v: Pair<String, V>, e: E): Edge<E, V> =
        throw IllegalArgumentException("You must set weight for your edge")

    override fun addEdge(u: Pair<String, V>, v: Pair<String, V>, e: E, weight: String): WeightedEdge<E, V> {
        val first = addVertex(u.second, u.first)
        val second = addVertex(v.second, v.first)
        return _edges.getOrPut(e) { DataWeightedEdge(e, first to second, weight) }
    }
    private data class DataWeightedEdge<E, V>(
        override val element: E,
        override val vertices: Pair<Vertex<V>, Vertex<V>>,
        override var weight: String
    ) : WeightedEdge<E, V>
}