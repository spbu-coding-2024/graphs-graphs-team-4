package org.graphApp.model.graph.algorithms

import org.graphApp.model.graph.*

class FordBellman<V, E>(
    private val graph: Graph<V, E>
) {
    private val _edges = graph.edges
    private val _vertices = graph.vertices
    private var _used: MutableMap<Long, Boolean> = mutableMapOf()
    val parent = hashMapOf<Long, Long>()
    val d = hashMapOf<Long, Double>()
    val hasNegativeCycle = hashMapOf<Long, Boolean>()

    private fun init(source: Long) {
        _vertices.forEach { vertex ->
            d[vertex.id] = Double.POSITIVE_INFINITY
            parent[vertex.id] = vertex.id
            hasNegativeCycle[vertex.id] = false
        }
        d[source] = 0.0
    }

    fun fordBellman(
        from: Vertex<V>,
        to: Vertex<V>?
    ): Pair<MutableList<Long>?, List<Edge<E,V>>>? {
        init(from.id)

        val n = _vertices.size

        for (i in 0 until n-1) {
            var wasChanged = false
            for (edge in _edges) {
                if (relax(edge, d, parent)) {
                    wasChanged = true
                }
            }

            if (!wasChanged) break
        }

        detectNegativeCycles()

        if (to != null && (hasNegativeCycle[to.id] == true || d[to.id] == Double.POSITIVE_INFINITY)) {
            return null
        }

        return if (to != null) {
            val vertexPath = pathReconstruct(from.id, to.id, parent) ?: return null
            val edgePath = reconstructEdges(vertexPath)
            Pair(vertexPath, edgePath)
        } else {
            null
        }
    }


    private fun detectNegativeCycles() {
        val tempD = HashMap(d)

        for (i in 0 until _vertices.size) {
            for (edge in _edges) {
                val (fromId, toId, weight) = getEdgeInfo(edge)

                if (d[fromId] != Double.POSITIVE_INFINITY && d[fromId]!! + weight < d[toId]!!) {
                    markVertexInNegativeCycle(toId)
                }

                if (edge !is DirectedEdge<*, *>) {
                    if (d[toId] != Double.POSITIVE_INFINITY && d[toId]!! + weight < d[fromId]!!) {
                        markVertexInNegativeCycle(fromId)
                    }
                }
            }
        }
    }

    private fun markVertexInNegativeCycle(vertexId: Long) {
        if (hasNegativeCycle[vertexId] == true) return

        hasNegativeCycle[vertexId] = true

        for (edge in _edges) {
            val (fromId, toId, _) = getEdgeInfo(edge)

            if (toId == vertexId && !hasNegativeCycle[fromId]!!) {
                markVertexInNegativeCycle(fromId)
            }

            if (edge !is DirectedEdge<*, *> && fromId == vertexId && !hasNegativeCycle[toId]!!) {
                markVertexInNegativeCycle(toId)
            }
        }
    }

    private fun getEdgeInfo(edge: Edge<E, V>): Triple<Long, Long, Double> {
        val weight = getEdgeWeight(edge)
        val fromId: Long
        val toId: Long

        if (edge is DirectedEdge<*, *>) {
            fromId = edge.from.id
            toId = edge.to.id
        } else {
            fromId = edge.vertices.first.id
            toId = edge.vertices.second.id
        }

        return Triple(fromId, toId, weight)
    }

    private fun <E, V> getEdgeWeight(edge: Edge<E, V>): Double {
        return when (edge) {
            is WeightedEdge<*, *> -> {
                try {

                    (edge as WeightedEdge<E, V>).weight.toDouble()
                } catch (e: NumberFormatException) {
                    1.0
                }
            }
            else -> 1.0
        }
    }

    private  fun <E, V> relax(
        edge: Edge<E, V>,
        d: HashMap<Long, Double>,
        parent: HashMap<Long, Long>
    ): Boolean {
        var changed = false
        val weight = getEdgeWeight(edge)
        val fromId: Long
        val toId: Long

        if (edge is DirectedEdge<*, *>) {
            fromId = edge.from.id
            toId = edge.to.id
            changed = tryRelax(fromId, toId, weight, d, parent) || changed
        } else {
            fromId = edge.vertices.first.id
            toId = edge.vertices.second.id
            changed = tryRelax(fromId, toId, weight, d, parent) || changed
            changed = tryRelax(toId, fromId, weight, d, parent) || changed
        }

        return changed
    }

    private fun tryRelax(
        from: Long,
        to: Long,
        weight: Double,
        d: HashMap<Long, Double>,
        parent: HashMap<Long, Long>
    ): Boolean {
        val fromDist = d[from] ?: return false
        if (fromDist == Double.POSITIVE_INFINITY) {
            return false
        }

        val newDist = fromDist + weight
        if (newDist < (d[to] ?: Double.POSITIVE_INFINITY)) {
            d[to] = newDist
            parent[to] = from
            return true
        }
        return false
    }

    private fun pathReconstruct(
        from: Long,
        to: Long,
        parent: HashMap<Long, Long>
    ): MutableList<Long>? {

        if (to == from) {
            return mutableListOf(from)
        }

        val path = mutableListOf<Long>()
        var current: Long? = to

        if (parent[to] == null) {
            return null
        }

        while (current != null) {

            path.add(0, current)

            if (current == from) {
                break
            }

            current = parent[current]

            if (path.contains(current)) {
                return null
            }
        }

        return if (path.firstOrNull() == from) path else null
    }
    private fun findEdge(from : Long, to : Long) : Edge<E, V>? {
        return _edges.find { edge ->
            if (edge is DirectedEdge<*,*>) {
                edge.from.id == from && edge.to.id == to
            } else {
                (edge.vertices.first.id == from && edge.vertices.second.id == to) ||
                        (edge.vertices.second.id  == from && edge.vertices.first.id == to)
            }
        }
    }

    private fun reconstructEdges(vertexPath : List<Long>) : List<Edge<E, V>> {
        if (vertexPath.size <= 1) {
            return emptyList()
        }

        val edgePath = mutableListOf<Edge<E, V>>()
        for (i in 0 until vertexPath.size - 1) {
            val fromId = vertexPath[i]
            val toId = vertexPath[i + 1]

            val edge = findEdge(fromId, toId) ?: continue
            edgePath.add(edge)
        }
        return edgePath
    }
}
