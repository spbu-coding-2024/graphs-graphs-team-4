package org.graphApp.view.algorithms

import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.graphApp.model.graph.Vertex
import org.graphApp.model.graph.*
import org.graphApp.model.graph.Edge
import org.graphApp.model.graph.algorithms.FindStrongCommunities
import org.graphApp.model.graph.algorithms.FordBellman
import org.graphApp.model.graph.algorithms.MinimalSpanningTree
import org.graphApp.model.graph.algorithms.FindCycles
import org.graphApp.model.graph.algorithms.FindCommunities
import org.graphApp.viewmodel.ErrorViewModel
import org.graphApp.viewmodel.graph.GraphViewModel
import org.graphApp.viewmodel.graph.VertexViewModel
import kotlin.Long
import kotlin.collections.List

class AlgorithmsView<V, E>(
    val viewModel: GraphViewModel<V, E>,
    val errorViewModel: ErrorViewModel
) {

    private val highlightColor = Color(0xFF419FE7)
    private val communitiesColors = listOf(
        Color(0xFFEF5350),
        Color(0xFFAB47BC),
        Color(0xFF5C6BC0),
        Color(0xFF29B6F6),
        Color(0xFF26A69A),
        Color(0xFF66BB6A),
        Color(0xFFD4E157),
        Color(0xFFFFCA28),
        Color(0xFFFF7043),
        Color(0xFFA1887F),
        Color(0xFF8D6E63),
        Color(0xFF789262),
        Color(0xFFEC407A),
        Color(0xFF7E57C2),
        Color(0xFF26C6DA)
    )


    fun findStrongCommunities() {
        CoroutineScope(Dispatchers.Default).launch {
            if (!viewModel.isDirectedGraph.value) {
                errorViewModel.showError("Warning: Graph should be directed")
                return@launch
            }
            resetAllColorsToDefaults()
            val strongCommunitiesFinder = FindStrongCommunities(graph = viewModel.graph)
            val communities = strongCommunitiesFinder.findStrongCommunitiesInGraph()

            communities?.forEachIndexed { communityIndex, community ->
                val colorIndex = communityIndex % communitiesColors.size
                val communityColor = communitiesColors[colorIndex]

                community.forEach { vertex ->
                    val vertexId = vertex.id
                    viewModel.vertices.find { it.vertexID == vertexId }?.let { vertexViewModel ->
                        vertexViewModel.color = communityColor
                        delay(30L)
                    }
                }
            }
        }
    }

    fun minimalSpanningTree() {
        CoroutineScope(Dispatchers.Default).launch {
            var mstFinder: MinimalSpanningTree<V, E> = MinimalSpanningTree(graph = viewModel.graph)
            var resultEdges: List<Edge<E, V>>?
            val components = mstFinder.forCheckingConnectivity()
            if (components > 1) {
                errorViewModel.showError("Warning: Components of connection > 1")
                return@launch
            }
            if (viewModel.isDirectedGraph.value) {
                errorViewModel.showError("Warning: Graph should be undirected")
                return@launch
            }
            resetAllColorsToDefaults()
            if (viewModel.isWeightedGraph.value) {

                resultEdges = mstFinder.kraskalSpanningTree()
            } else {
                resultEdges = mstFinder.bfsSpanningTree()
            }

            viewModel.edges.forEach { edgeVM ->
                edgeVM.color = Color.Gray.copy(alpha = 0.3f)
            }
            val mstColor = Color(0xFF4CAF50)
            resultEdges?.forEach { edge ->
                val edgeId = findEdgeByVertices(edge.vertices.first.id, edge.vertices.second.id)
                edgeId?.let { edgeVM ->
                    edgeVM.color = mstColor
                }
                val vertexID = findVertices(edge.vertices.first.id, edge.vertices.second.id)
                vertexID?.let { vertexID ->
                    vertexID.first.color = mstColor
                    vertexID.second.color = mstColor
                }
                delay(30L)
            }
        }
    }

    fun findCycles(
        startVertexIdOrLabel: String
    ) {
        CoroutineScope(Dispatchers.Default).launch {
            resetAllColorsToDefaults()

            viewModel.edges.forEach { edgeVM ->
                edgeVM.color = Color.Gray.copy(alpha = 0.3f)
            }

            val startVertex = findVertexByLabel(startVertexIdOrLabel)
            if (startVertex == null) {
                errorViewModel.showError("Warning: There are no such point in the graph")
                return@launch
            }

            val findCycle = FindCycles(graph = viewModel.graph)

            val isDirected = viewModel.graph.edges.firstOrNull() is DirectedEdge<*, *>

            val result = if (isDirected) {
                findCycle.findCycleDirected(startVertex)
            } else {
                findCycle.findCycleUndirected(startVertex)
            }

            if (result == null) {
                errorViewModel.showError("No cycles here")
                return@launch
            }

            val (vertexPath, edgePath) = result

            val cycleColor = Color(0xFFFF9800)

            vertexPath?.forEach { vertexId ->
                viewModel.vertices.find { it.vertexID == vertexId }?.let { vertexVM ->
                    vertexVM.color = cycleColor
                    delay(30L)
                }
            }

            edgePath.forEach { edge ->
                val fromId = edge.vertices.first.id
                val toId = edge.vertices.second.id

                val edgeVM = findEdgeByVertices(fromId, toId)
                edgeVM?.let {
                    it.color = cycleColor
                    delay(30L)
                }
            }
        }
    }

    fun fordBellman(
        startVertexId: String,
        endVertexId: String
    ) {
        CoroutineScope(Dispatchers.Default).launch {
            resetAllColorsToDefaults()

            viewModel.edges.forEach { edgeVM ->
                edgeVM.color = Color.Gray.copy(alpha = 0.3f)
            }

            val startVertex = findVertexByLabel(startVertexId)
            val endVertex = findVertexByLabel(endVertexId)

            if (startVertex == null) {
                errorViewModel.showError("Warning: There are no start vertex in the graph")
                return@launch
            }
            if (endVertex == null) {
                errorViewModel.showError("Warning: There are no end vertex in the graph")
                return@launch
            }

            val fordBellman = FordBellman(graph = viewModel.graph, errorViewModel = errorViewModel)

            val result = fordBellman.fordBellman(startVertex.id, endVertex.id)

            if (result == null) {
                return@launch
            }

            val (vertexPath, edgePath) = result

            val pathColor = Color(0xFFF44336)

            vertexPath?.forEach { vertexId ->
                viewModel.vertices.find { it.vertexID == vertexId }?.let { vertexVM ->
                    vertexVM.color = pathColor
                    delay(30L)
                }
            }

            edgePath.forEach { edge ->
                val fromId = edge.vertices.first.id
                val toId = edge.vertices.second.id

                val edgeVM = findEdgeByVertices(fromId, toId)
                edgeVM?.let {
                    it.color = pathColor
                    delay(30L)
                }
            }
        }
    }

    fun findCommunities() {
        CoroutineScope(Dispatchers.Default).launch {
            resetAllColorsToDefaults()

            viewModel.edges.forEach { edgeVM ->
                edgeVM.color = Color.Gray.copy(alpha = 0.3f)
            }

            val findCommunitiesAlgorithm = FindCommunities(graph = viewModel.graph)
            val communities = findCommunitiesAlgorithm.findCommunities()

            if (communities == null) {
                errorViewModel.showError("No communities here")
                return@launch
            }

            communities.entries.forEachIndexed { index, (communityId, vertexIds) ->
                val colorIndex = index % communitiesColors.size
                val communityColor = communitiesColors[colorIndex]

                vertexIds.forEach { vertexId ->
                    viewModel.vertices.find { it.vertexID == vertexId }?.let { vertexViewModel ->
                        vertexViewModel.color = communityColor
                    }
                }

                delay(30L)
            }

            communities.entries.forEach { (communityId, vertexIds) ->
                val colorIndex = communities.keys.indexOf(communityId) % communitiesColors.size
                val communityColor = communitiesColors[colorIndex]

                viewModel.edges.forEach { edgeVM ->
                    val uVertexId = edgeVM.u.vertexID
                    val vVertexId = edgeVM.v.vertexID

                    if (vertexIds.contains(uVertexId) && vertexIds.contains(vVertexId)) {
                        edgeVM.color = communityColor.copy(alpha = 0.8f)
                    }
                }
            }
        }
    }

    private fun findVertexByLabel(
        id: String
    ): Vertex<V>? {

        val vertex = viewModel.graph.vertices.find {
            it.element.toString() == id
        }

        if (vertex != null) {
            return vertex
        }

        val newId = id.toLongOrNull()
        return if (newId != null) {
            viewModel.graph.vertices.find { it.id == newId }
        } else {
            null
        }
    }

    private fun findEdgeByVertices(fromId: Long, toId: Long) = viewModel.edges.find { edgeVM ->
        (edgeVM.u.vertexID == fromId && edgeVM.v.vertexID == toId) ||
                (edgeVM.u.vertexID == toId && edgeVM.v.vertexID == fromId)
    }


    private fun findVertices(
        vertexIDF: Long,
        vertexIDS: Long
    ): Pair<VertexViewModel<V>, VertexViewModel<V>>? {
        val first = viewModel.vertices.find { it.vertexID == vertexIDF } ?: return null
        val second = viewModel.vertices.find { it.vertexID == vertexIDS } ?: return null
        return first to second
    }

    fun resetAllColorsToDefaults() {
        viewModel.vertices.forEach { vertex ->
            vertex.color = highlightColor
        }
        viewModel.edges.forEach { edge ->
            edge.color = highlightColor
        }
    }
}