package org.graphApp.viewmodel.graph

interface RepresentationStrategy {
    fun <V> place(width: Double, height: Double, vertices: Collection<VertexViewModel<V>>)
    fun <V> highlight(vertices: Collection<VertexViewModel<V>>)
}
