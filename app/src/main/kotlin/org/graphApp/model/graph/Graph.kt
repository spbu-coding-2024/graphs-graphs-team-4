package org.graphApp.model.graph

interface Graph<V, E> {
    val vertices: Collection<Vertex<V>>
    val edges: Collection<Edge<E, V>>

    fun addVertex(v: V): Vertex<V>
    fun addEdge(u: V, v: V, e: E): Edge<E, V>

    fun removeVertex(vertex: Vertex<V>)
    fun removeEdge(edge: Edge<E, V>)
    fun clear()
}

interface UndirectedWeightedGraph<V, E> : Graph<V, E> {
    fun addEdge(u: V, v: V, e: E, weight: String): WeightedEdge<E, V>
}

interface DirectedWeightedGraph<V, E> : Graph<V, E> {
    fun addEdge(
        u: V,
        v: V,
        e: E,
        weight: String
    ): WeightedDirectedEdge<E, V>
}

interface DirectedUnWeightedGraph<V, E> : Graph<V, E> {
    override fun addEdge(u: V, v: V, e: E): DirectedEdge<E, V>
}
