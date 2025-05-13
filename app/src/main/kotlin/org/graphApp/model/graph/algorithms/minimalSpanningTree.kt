package org.graphApp.model.graph.algorithms

import org.graphApp.model.graph.Graph
import org.graphApp.model.graph.Vertex
import org.graphApp.model.graph.WeightedEdge
import org.jetbrains.exposed.sql.Query




class MinimalSpanningTree<V, E>(
    private val graph: Graph<V,E>,
    private val isDirectedGraph: Boolean,
    private val isWeightedGraph: Boolean
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


    private fun bfsSpanningTree (
    ) : List<WeightedEdge<E,V>>? {
        resetUsedFlags()
        val result = mutableListOf<WeightedEdge<E,V>>()
        val queue: ArrayDeque<WeightedEdge<E,V>> = ArrayDeque()
        queue.add(_sortedEdges[0])
        _used[_sortedEdges[0].vertices.first.id] = true
        while(queue.isNotEmpty()) {
            val currentEdge = queue.first()
            queue.removeFirst()
            _sortedEdges
                .filter { it.vertices.first.id == currentEdge.vertices.first.id }
                .forEach { edge ->
                    result.add(edge)
                    if(_used[currentEdge.vertices.second.id] != false) {
                        queue.add(edge)
                        _used[edge.vertices.first.id] = true
                    }
                }
        }
        return result
    }

    private fun kraskalSpanningTree () : List<WeightedEdge<E,V>>? {
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

fun minimalSpanningTree (

) : List<WeightedEdge<E,V>>? {
    if (isDirectedGraph) {
        return null
    }

    if (isWeightedGraph) {
        return kraskalSpanningTree()
    } else {
        return bfsSpanningTree()
    }

    }
}
