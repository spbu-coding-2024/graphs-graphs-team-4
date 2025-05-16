package org.graphApp.model.graph.algorithms

import org.graphApp.model.graph.*
import kotlin.collections.set

class FindCycles<V, E>(
    private val graph: Graph<V, E>
) {
    private val _edges = graph.edges
    private val _vertices = graph.vertices
    private var visited = mutableMapOf<Long, Boolean>()
    private val parent = hashMapOf<Long, Long>()
    private val stack = hashSetOf<Long>()

    private fun init() {
        visited.clear()
        parent.clear()
        stack.clear()
        _vertices.forEach { vertex ->
            parent[vertex.id] = vertex.id
        }
    }

    fun findCycleUndirected(
        startVertex: Vertex<V>
    ): Pair<MutableList<Long>?, List<Edge<E, V>>>? {
        init()
        val vertexPath = dfsUndirected(startVertex.id)
        val edgePath = reconstructEdges(vertexPath)
        return Pair(vertexPath, edgePath)
    }

    private fun dfsUndirected(
        current: Long
    ): MutableList<Long>? {
        visited[current] = true

        for (edge in _edges) {
            if (!edge.incident(current)) continue

            val neighbor = if (edge.vertices.first.id == current) edge.vertices.second.id else edge.vertices.first.id

            if (visited[neighbor] != true) {
                parent[neighbor] = current
                val cycle = dfsUndirected(neighbor)
                if (cycle != null) {
                    return cycle
                }
            } else if (neighbor != parent[current]) {
                return reconstructCycleUndirected(current, neighbor)
            }
        }

        return null
    }

    private fun reconstructCycleUndirected(
        current: Long,
        target: Long
    ): MutableList<Long> {
        val cycle = mutableListOf<Long>()

        var vertex = current
        cycle.add(vertex)

        while (vertex != target) {
            vertex = parent[vertex] ?: break
            cycle.add(vertex)
        }

        if (vertex != target) {
            val targetPath = mutableListOf<Long>()
            vertex = target
            while (!cycle.contains(vertex)) {
                targetPath.add(vertex)
                vertex = parent[vertex] ?: break
            }
            cycle.addAll(targetPath.reversed())
        }

        if (cycle.first() != current) {
            cycle.add(current)
        }

        return cycle
    }

    fun findCycleDirected(
        startVertex: Vertex<V>
    ): Pair<MutableList<Long>?, List<Edge<E, V>>>? {
        init()
        val vertexPath = dfsDirected(startVertex.id)
        val edgePath = reconstructEdges(vertexPath)
        return Pair(vertexPath, edgePath)
    }

    private fun dfsDirected(
        current: Long
    ): MutableList<Long>? {
        visited[current] = true
        stack.add(current)

        for (edge in _edges) {
            if (edge !is DirectedEdge<*, *> || edge.from.id != current) continue

            val neighbor = edge.to.id

            if (visited[neighbor] != true) {
                parent[neighbor] = current
                val cycle = dfsDirected(neighbor)
                if (cycle != null) {
                    return cycle
                }
            } else if (neighbor in stack) {
                return reconstructCycleDirected(current, neighbor)
            }
        }

        stack.remove(current)
        return null
    }

    private fun reconstructCycleDirected(
        current: Long,
        target: Long
    ): MutableList<Long> {
        val cycle = mutableListOf<Long>()

        var vertex = current
        cycle.add(vertex)

        while (vertex != target) {
            vertex = parent[vertex] ?: break
            cycle.add(vertex)
        }

        if (cycle.first() != current) {
            cycle.add(current)
        }

        return cycle
    }

    private fun findEdge(from: Long, to: Long): Edge<E, V>? {
        return _edges.find { edge ->
            if (edge is DirectedEdge<*, *>) {
                edge.from.id == from && edge.to.id == to
            } else {
                (edge.vertices.first.id == from && edge.vertices.second.id == to) ||
                        (edge.vertices.second.id == from && edge.vertices.first.id == to)
            }
        }
    }

    private fun reconstructEdges(vertexPath: List<Long>?): List<Edge<E, V>> {
        if (vertexPath == null || vertexPath.size <= 1) {
            return emptyList()
        }

        val edgePath = mutableListOf<Edge<E, V>>()
        for (i in 0 until vertexPath.size - 1) {
            val fromId = vertexPath[i]
            val toId = vertexPath[i + 1]

            val edge = findEdge(fromId, toId)
            if (edge != null) {
                edgePath.add(edge)
            }
        }

        val firstVertex = vertexPath.first()
        val lastVertex = vertexPath.last()
        if (firstVertex != lastVertex) {
            val closingEdge = findEdge(lastVertex, firstVertex)
            if (closingEdge != null) {
                edgePath.add(closingEdge)
            }
        }

        return edgePath
    }
}