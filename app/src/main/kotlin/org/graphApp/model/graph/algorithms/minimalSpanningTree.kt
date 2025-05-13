package org.graphApp.model.graph.algorithms

import org.graphApp.model.graph.Graph
import org.graphApp.model.graph.Vertex

fun<V,E> MinimalSpanningTree (
    graph: Graph<V,E>,
    isDirectedGraph: Boolean,
    isWeightedGraph: Boolean,
) : List<Vertex<V>>? {
    if(isDirectedGraph) {
        return null
    }
    
    if(isWeightedGraph) {

    } else {

    }

    val resultStrongCommunities = mutableListOf<MutableList<Vertex<V>>>()
    TODO()
}