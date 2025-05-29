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
import org.graphApp.viewmodel.graph.GraphViewModel
import org.graphApp.viewmodel.graph.VertexViewModel
import kotlin.Long
import kotlin.collections.List

class AlgorithmsView<V, E>(
    val viewModel: GraphViewModel<V, E>,
) {
    private val highlightColor = Color(0xFF419FE7)

    private val communitiesColors = listOf(
        Color(0xFFFF0000), Color(0xFFFF8000), Color(0xFFFFFF00), Color(0xFF80FF00),
        Color(0xFF00FF00), Color(0xFF00FF80), Color(0xFF00FFFF), Color(0xFF0080FF),
        Color(0xFF0000FF), Color(0xFF8000FF), Color(0xFFFF00FF), Color(0xFFFF0080),
        Color(0xFF8B0000), Color(0xFF8B4513), Color(0xFFFF4500), Color(0xFFFFD700),
        Color(0xFF808000), Color(0xFF006400), Color(0xFF50C878), Color(0xFF008B8B),
        Color(0xFF4682B4), Color(0xFF000080), Color(0xFF4B0082), Color(0xFF8B008B),
        Color(0xFFFFB6C1), Color(0xFFFFCBA4), Color(0xFFFFFDD0), Color(0xFF90EE90),
        Color(0xFFF0FFFF), Color(0xFF87CEEB), Color(0xFFE6E6FA), Color(0xFFDDA0DD),
        Color(0xFF40E0D0), Color(0xFFFF7F50), Color(0xFFF0E68C), Color(0xFFFA8072),
        Color(0xFFDDA0DD), Color(0xFFDC143C), Color(0xFFED9121), Color(0xFF7DF9FF),
        Color(0xFFE2725B), Color(0xFFCC7722), Color(0xFFC2B280), Color(0xFF2E8B57),
        Color(0xFF708090), Color(0xFF722F37), Color(0xFFADFF2F), Color(0xFFFF6EC7),
        Color(0xFF0080FF), Color(0xFFCCFF00)
    )


    fun findStrongCommunities() {
        CoroutineScope(Dispatchers.Default).launch {
            if (!viewModel.isDirectedGraph.value) {
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
            if (viewModel.isDirectedGraph.value || components > 1) return@launch
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

            val startVertex = findVertexByLabel(startVertexIdOrLabel) ?: return@launch

            val findCycle = FindCycles(graph = viewModel.graph)

            val isDirected = viewModel.graph.edges.firstOrNull() is DirectedEdge<*, *>

            val result = if (isDirected) {
                findCycle.findCycleDirected(startVertex)
            } else {
                findCycle.findCycleUndirected(startVertex)
            }

            if (result == null) {
                return@launch
            }

            val (vertexPath, edgePath) = result

            val cycleColor = Color(0xFFFF9800)

            vertexPath?.forEach { vertexId ->
                viewModel.vertices.find { it.vertexID == vertexId }?.let { vertexVM ->
                    vertexVM.color = cycleColor
                    delay(300L)
                }
            }

            edgePath.forEach { edge ->
                val fromId = edge.vertices.first.id
                val toId = edge.vertices.second.id

                val edgeVM = findEdgeByVertices(fromId, toId)
                edgeVM?.let {
                    it.color = cycleColor
                    delay(300L)
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

            if (startVertex == null || endVertex == null) {
                return@launch
            }

            val fordBellman = FordBellman(graph = viewModel.graph)

            val result = fordBellman.fordBellman(startVertex.id, endVertex.id)

            if (result == null) {
                return@launch
            }

            val (vertexPath, edgePath) = result

            val pathColor = Color(0xFFF44336)

            vertexPath?.forEach { vertexId ->
                viewModel.vertices.find { it.vertexID == vertexId }?.let { vertexVM ->
                    vertexVM.color = pathColor
                    delay(300L)
                }
            }

            edgePath.forEach { edge ->
                val fromId = edge.vertices.first.id
                val toId = edge.vertices.second.id

                val edgeVM = findEdgeByVertices(fromId, toId)
                edgeVM?.let {
                    it.color = pathColor
                    delay(300L)
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

                delay(3L)
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