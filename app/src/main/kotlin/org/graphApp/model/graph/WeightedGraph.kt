package org.graphApp.model.graph

internal class WeightedGraph<V, E> : UndirectedWeightedGraph<V, E> {
    private val _vertices = hashMapOf<Long, Vertex<V>>()
    private val elementToVertex = hashMapOf<V, Vertex<V>>()
    private val _edges = hashMapOf<E, WeightedEdge<E, V>>()

    private var vertexIdCounter: Long = 0

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

    override fun addEdge(u: V, v: V, e: E, weight: String): WeightedEdge<E, V> {
        val from = addVertex(u)
        val to = addVertex(v)
        return _edges.getOrPut(e) { DataWeightedEdge(e, from to to, weight) }
    }

    private data class DataWeightedEdge<E, V>(
        override val element: E,
        override val vertices: Pair<Vertex<V>, Vertex<V>>,
        override var weight: String
    ) : WeightedEdge<E, V>
}
