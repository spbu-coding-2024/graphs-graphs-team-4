package org.graphApp.model.graph.algorithms

import org.graphApp.model.graph.Graph
import org.graphApp.model.graph.Vertex
import kotlin.collections.forEach

internal class FindStrongCommunities<V,E>(
    private val graph: Graph<V,E>,
    private val isDirectedGraph: Boolean,
) {

    private var _used: MutableMap<Long, Boolean> = mutableMapOf()

    private fun resetUsedFlags() {
        _used = graph.vertices.associate {
                vertex -> vertex.id to false
        }.toMutableMap()
    }

    private val _edges = graph.edges
    private val _vertices = graph.vertices
    private val _graphInfo = mutableMapOf<Long, MutableList<Vertex<V>>>()
    private val _graphInfoT = mutableMapOf<Long, MutableList<Vertex<V>>>()
    private val _component = mutableListOf<Vertex<V>>()
    private val _order = mutableListOf<Vertex<V>>()

    private fun convertToList(
    ) {
        _edges.forEach { edge ->
            val fromId = edge.vertices.first
            val toVertex = edge.vertices.second
            _graphInfo.computeIfAbsent(fromId.id) { mutableListOf() }.add(toVertex)
            _graphInfoT.computeIfAbsent(toVertex.id) { mutableListOf() }.add(fromId)
        }
    }


    private fun dfs1(v: Vertex<V>) {
        _used[v.id] = true
        _graphInfo[v.id]?.forEach { vertex ->
            if(_used[vertex.id] == false) {
                dfs1(vertex)
            }
        }
        _order.add(v)
    }

    private fun dfs2(v: Vertex<V>) {
        _used[v.id] = true
        _component.add(v)
        _graphInfoT[v.id]?.forEach { vertex ->
            if(_used[vertex.id] == false) {
                dfs2(vertex)
            }
        }
    }


    fun findStrongCommunitiesInGraph(

    ) : List<List<Vertex<V>>>? {
        val resultStrongCommunities = mutableListOf<List<Vertex<V>>>()

        if (!isDirectedGraph) {
            return null
        }
        resetUsedFlags()
        convertToList()
        _vertices.forEach { vertex ->
            if(_used[vertex.id] == false) {
                    dfs1(vertex)
            }
        }
        resetUsedFlags()
        _order.asReversed().forEach { vertex ->
            if (_used[vertex.id] == false) {
                dfs2(vertex)
                resultStrongCommunities.add(_component.toMutableList())
                _component.clear()
            }
        }
        return resultStrongCommunities
    }
}

