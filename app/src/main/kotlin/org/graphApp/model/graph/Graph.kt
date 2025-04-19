package org.graphApp.model.graph

interface Graph<V, E> {
    val vertices: Collection<Vertex<V>>
    val edges: Collection<Edge<E, V>>

    fun addVertex(v: V, id: String): Vertex<V>
    fun addEdge(u: Vertex<V>, v: Vertex<V>, e: E): Edge<E, V>
}

interface UndirectedWeightedGraph<V, E> : Graph<V, E> {
    fun addEdge(u: Vertex<V>, v: Vertex<V>, e: E, weight: String): WeightedEdge<E, V>
}

interface DirectedWeightedGraph<V, E> : Graph<V, E> {
    fun addEdge(u: Vertex<V>, v: Vertex<V>, e: E, weight: String): WeightedDirectedEdge<E, V>
}

interface DirectedUnWeightedGraph<V, E> : Graph<V, E> {
    override fun addEdge(u: Vertex<V>, v: Vertex<V>, e: E): DirectedEdge<E, V>
}