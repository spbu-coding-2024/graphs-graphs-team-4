package org.graphApp.model.graph.algorithms

import org.graphApp.model.graph.*

fun <V, E> fordBellman(
    graph: Graph<V, E>,
    from: Vertex<V>,
    to: Vertex<V>
): MutableList<Vertex<V>>? {
    val parent = hashMapOf<Vertex<V>, Vertex<V>?>()
    val d = hashMapOf<Vertex<V>, Double>()
    val vertices = graph.vertices.toList()

    vertices.forEach { vertex ->
        d[vertex] = Double.POSITIVE_INFINITY
        parent[vertex] = null
    }
    val n = vertices.size

    for (i in 0..n-1) {
        for (edge in graph.edges) {
            relax(edge, d, parent)
        }
    }
    return if (to != null) {
        pathReconsctract(from, to, parent)
    } else {
        null
    }
}


private fun <V, E> getEdgeWeight(edge : Edge<E, V>) : Double {
    return when (edge) {
        is WeightedEdge<*, *> -> {
            try {
                (edge as WeightedEdge<E,V>).weight.toDouble()
            } catch (e : NumberFormatException) {
                1.0
            }
        }
        else -> 1.0
    }
}

private fun <V, E> relax(
    edge : Edge<E, V>,
    d : HashMap<Vertex<V>, Double>,
    parent : HashMap<Vertex<V>, Vertex<V>?>
) {
    val weight : Double = getEdgeWeight(edge)
    val from : Vertex<V>
    val to : Vertex<V>

    if (edge is DirectedEdge<*, *>) {
        from = edge.from as Vertex<V>
        to = edge.to as Vertex<V>
    } else {
        from = edge.vertices.first
        to = edge.vertices.second
        tryRelax(to, from, weight, d, parent)
    }
    tryRelax(from, to, weight, d, parent)
}

private fun <V> tryRelax(
    from : Vertex<V>,
    to : Vertex<V>,
    weight : Double,
    d : HashMap<Vertex<V>, Double>,
    parent : HashMap<Vertex<V>, Vertex<V>?>
) {
    val fromDist  = d[from] ?: return
    if (fromDist == Double.POSITIVE_INFINITY) {
        return
    }

    val newDist = fromDist + weight
    if (newDist < (d[to] ?: Double.POSITIVE_INFINITY)) {
        d[to] = newDist
        parent[to] = from
    }
}
private fun <V> pathReconsctract(
    from : Vertex<V>,
    to : Vertex<V>,
    parent : HashMap<Vertex<V>, Vertex<V>?>
) : MutableList<Vertex<V>>? {
    if (to == from) return mutableListOf(from)

    val path = mutableListOf<Vertex<V>>()
    var current : Vertex<V>? = to

    if (parent[to] == null) return null

    while (current != null) {
        path.add(0, current)
        if (current == from) {
            break
        }
        current = parent[current]
    }


    return if (path.firstOrNull() == from) path else null
}
