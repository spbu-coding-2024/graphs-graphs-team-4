package org.graphApp.model.graph

interface Vertex<V> {
    val id: Long
    var element: V
}

internal data class DVertex<V>(
    override val id: Long,
    override var element: V
) : Vertex<V>
