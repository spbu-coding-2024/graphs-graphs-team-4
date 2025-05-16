package org.graphApp.model.graph.algorithms

import org.graphApp.model.graph.Edge
import org.graphApp.model.graph.Graph
import org.graphApp.model.graph.Vertex
import org.graphApp.model.graph.WeightedEdge




class MinimalSpanningTree<V, E>(
    private val graph: Graph<V,E>,
) {
    private val _edges = graph.edges
    private val _vertices = graph.vertices
    private var _used: MutableMap<Long, Boolean> = mutableMapOf()

    private val _sortedEdges: List<WeightedEdge<E, V>> =
        _edges
            .filterIsInstance<WeightedEdge<E, V>>()
            .sortedBy { it.weight.toDouble() }

    private fun resetUsedFlags() {
        _used = graph.vertices.associate {
                vertex -> vertex.id to false
        }.toMutableMap()
    }
    private val parent = mutableMapOf<Long, Long>()

    private fun dsuInit() {
        _vertices.forEach { vertex ->
            parent[vertex.id] = vertex.id
        }
    }

    private fun dsuGet(v: Long): Long {
        if (parent[v] != v) {
            parent[v] = dsuGet(parent[v]!!)
        }
        return parent[v]!!
    }

    private fun dsuUnit(a: Vertex<V>, b: Vertex<V>) {
        val pa = dsuGet(a.id)
        val pb = dsuGet(b.id)
        if (pa != pb) {
            parent[pa] = pb
        }
    }


    fun bfsSpanningTree (
    ) : List<Edge<E,V>>? {
        resetUsedFlags()
        val result = mutableListOf<Edge<E, V>>()
        val queue: ArrayDeque<Vertex<V>> = ArrayDeque()
        queue.add(_vertices.first())
        _used[_vertices.first().id] = true
        while(queue.isNotEmpty()) {
            val currentVertex = queue.first()
            queue.removeFirst()
            graph.edges
                .filter { it.vertices.first.id == currentVertex.id || it.vertices.second.id == currentVertex.id }
                .forEach { edge ->
                    val neightboor = if(edge.vertices.first.id == currentVertex.id) {edge.vertices.second} else {edge.vertices.first}
                    if(_used[neightboor.id] == false) {
                        _used[neightboor.id] = true
                        queue.add(neightboor)
                        result.add(edge)
                    }
                }
        }
        return result
    }

    fun kraskalSpanningTree () : List<WeightedEdge<E,V>>? {
        dsuInit()
        var cost = 0
        val res = mutableListOf<WeightedEdge<E,V>>()

        _sortedEdges.forEach { edge ->
            val startV: Vertex<V> = edge.vertices.first
            val endV: Vertex<V> = edge.vertices.second
            val weight: Double = edge.weight.toDouble()
            if(dsuGet(startV.id) != dsuGet(endV.id)) {
                cost += weight.toInt()
                res.add(edge)
                dsuUnit(startV, endV)
            }
        }
        return res
    }
}
