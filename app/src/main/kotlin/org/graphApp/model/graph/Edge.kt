// edge.kt
package org.graphApp.model.graph

interface Edge<E, V> {
    val element: E
    val vertices: Pair<Vertex<V>, Vertex<V>>

    fun incident(v: Vertex<V>) = v == vertices.first || v == vertices.second
}

interface WeightedEdge<E, V> : Edge<E, V> {
    var weight: String
}

interface DirectedEdge<E, V> : Edge<E, V> {
    var from: Vertex<V>
    var to: Vertex<V>
    override val vertices: Pair<Vertex<V>, Vertex<V>>
        get() = from to to
}

interface WeightedDirectedEdge<E, V> : DirectedEdge<E, V>, WeightedEdge<E, V>
