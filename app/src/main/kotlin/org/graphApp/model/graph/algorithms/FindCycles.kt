package org.graphApp.model.graph.algorithms

import org.graphApp.model.graph.*

fun <V, E> findCycleUndirected(
    graph: Graph<V,E>,
    vertex: Vertex<V>
) : List<Vertex<V>>? {
    val visited = hashSetOf<Vertex<V>>()
    val parent = hashMapOf<Vertex<V>, Vertex<V>?>()
    val vertices = graph.vertices.toList()

    vertices.forEach { vertex ->
        parent[vertex] = null
    }

    return dfsUndirected(graph, vertex, visited, parent)
}

tailrec fun <V, E> dfsUndirected(
    graph : Graph<V, E>,
    current : Vertex<V>,
    visited : HashSet<Vertex<V>>,
    parent : HashMap<Vertex<V>, Vertex<V>?>
) : List<Vertex<V>>? {
    visited.add(current)

    for (edge in graph.edges) {
        if (!edge.incident(current)) continue

        val neighbor = if (edge.vertices.first == current) edge.vertices.second else edge.vertices.first

        if (neighbor !in visited) {
            parent[neighbor] = current

            val cycle = dfsUndirected(graph, neighbor, visited, parent)
            if (cycle != null) {
                return cycle
            }
        } else if (neighbor != parent[current]) {
            return reconstructCycleUndirected(current, neighbor, parent)
        }
    }

    return null
}

private fun <V> reconstructCycleUndirected(
    current : Vertex<V>,
    target : Vertex<V>,
    parent : HashMap<Vertex<V>, Vertex<V>?>
) : List<Vertex<V>>? {
    val pathFromCurrent = mutableListOf<Vertex<V>>()
    val pathFromTarget = mutableListOf<Vertex<V>>()

    var v : Vertex<V>? = current
    while (v != null) {
        pathFromCurrent.add(v)
        v = parent[v]
    }

    v = target

    while (v != null) {
        pathFromTarget.add(v)
        v = parent[v]

    }
    val setTargetPath = pathFromTarget.toSet()
    // LCA - наименьший общий предок
    val LCA = pathFromCurrent.first { it in setTargetPath }
    val cycle = mutableListOf<Vertex<V>>()

    for (u in pathFromCurrent) {
        cycle.add(u)
        if (u == LCA) {
            break
        }
    }

    val LCAindex = pathFromTarget.indexOf(LCA)
    for (i in LCAindex - 1 downTo 0) {
        cycle.add(pathFromTarget[i])
    }
    return cycle
}
