package org.graphApp.model.graph.algorithms

import org.graphApp.model.graph.*

fun <V, E> findCycleUndirected(
    graph: Graph<V,E>,
    startVertex: Vertex<V>
) : List<Vertex<V>>? {
    val visited = hashSetOf<Vertex<V>>()
    val parent = hashMapOf<Vertex<V>, Vertex<V>?>()
    val vertices = graph.vertices.toList()

    vertices.forEach { vertex ->
        parent[vertex] = null
    }

    return dfsUndirected(graph, startVertex, visited, parent)
}

fun <V, E> dfsUndirected(
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
    current: Vertex<V>,
    target: Vertex<V>,
    parent: HashMap<Vertex<V>, Vertex<V>?>
): List<Vertex<V>> {
    val cycle = mutableListOf<Vertex<V>>()

    cycle.add(current)
    cycle.add(target)

    var vertex = current
    while (vertex != target) {
        vertex = parent[vertex] ?: break
        cycle.add(0, vertex)
    }

    return cycle
}

fun <V, E> findCycleDirected(
    graph : Graph<V, E>,
    startVertex : Vertex<V>
) : List<Vertex<V>>? {
    val visited = hashSetOf<Vertex<V>>()
    val stack = hashSetOf<Vertex<V>>()
    val parent = hashMapOf<Vertex<V>, Vertex<V>?>()
    val vertices = graph.vertices
    vertices.forEach { vertex ->
        parent[vertex] = null
    }

    return dfsDirected(graph, startVertex, visited, stack, parent)
}

private fun <V, E> dfsDirected(
    graph : Graph<V, E>,
    current : Vertex<V>,
    visited : HashSet<Vertex<V>>,
    stack : HashSet<Vertex<V>>,
    parent : HashMap<Vertex<V>, Vertex<V>?>
) : List<Vertex<V>>? {
    visited.add(current)
    stack.add(current)
    for (edge in graph.edges) {
        if (edge !is DirectedEdge<*, *> || (edge.from as Vertex<V>) != current) {
            continue
        }
        val neighbor = (edge.to as Vertex<V>)
        if (neighbor !in visited) {
            parent[neighbor] = current

            val cycle = dfsDirected(graph, neighbor, visited, stack, parent)
            if (cycle != null) {
                return cycle
            }
        } else if (neighbor in stack) {
            return reconstructCycleDirected(current, neighbor, parent)
        }
    }
    stack.remove(current)
    return null
}

private fun <V> reconstructCycleDirected(
    current : Vertex<V>,
    target : Vertex<V>,
    parent : HashMap<Vertex<V>, Vertex<V>?>
) : List<Vertex<V>> {
    val cycle = mutableListOf<Vertex<V>>()

    cycle.add(current)

    var vertex = current
    while (vertex != target) {
        vertex = parent[vertex] ?: break
        cycle.add(0, vertex)
    }

    cycle.add(0, target)

    return cycle
}
