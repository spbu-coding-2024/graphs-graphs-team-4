package org.graphApp.view.graph

import org.graphApp.viewmodel.graph.GraphViewModel
import org.graphApp.viewmodel.graph.VertexViewModel



interface RepresentationStrategy<V, E> {
    fun place(width: Double, height: Double, originalGraph: GraphViewModel<V,E>)
    fun move(old: Pair<Int, Int>, new: Pair<Int, Int>, vertices: Collection<VertexViewModel<V>>)
}