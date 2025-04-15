package org.graphApp.model.graph

interface Edge<E, V> {
    var element: E
    val vertices: Pair<Vertex<V>, Vertex<V>>

    fun incident(v: Vertex<V>) = v == vertices.first || v == vertices.second
}