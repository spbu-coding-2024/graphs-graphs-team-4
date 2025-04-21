package org.graphApp.model.graph

import org.graphApp.model.graph.WeightedGraph.DataWeightedEdge

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

    private data class DataDirectedEdge<E, V>(
        override val element: E,
        override var from: Vertex<V>,
        override var to: Vertex<V>
    ) : DirectedEdge<E, V>
}
