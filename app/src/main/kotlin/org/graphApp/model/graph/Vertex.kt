package org.graphApp.model.graph

interface Vertex<V> {
    val id: String
    var element: V
}

internal data class DVertex<V>(
    override val id: String,
    override var element: V
) : Vertex<V>