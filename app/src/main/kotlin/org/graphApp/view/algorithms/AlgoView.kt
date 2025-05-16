package org.graphApp.view.algorithms

import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.graphApp.model.graph.Edge
import org.graphApp.model.graph.Vertex
import org.graphApp.model.graph.algorithms.FindStrongCommunities
import org.graphApp.model.graph.algorithms.MinimalSpanningTree
import org.graphApp.viewmodel.MainScreenViewModel
import org.graphApp.viewmodel.graph.GraphViewModel
import org.graphApp.viewmodel.graph.VertexViewModel

class AlgorithmsView<V, E>(
    val viewModel: GraphViewModel<V, E>,
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
            if (!viewModel.isDirectedGraph.value) return@launch
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
                        delay(500)
                    }
                }
            }
        }
    }

    fun minimalSpanningTree() {
        CoroutineScope(Dispatchers.Default).launch {
            var mstFinder: MinimalSpanningTree<V, E> = MinimalSpanningTree(graph = viewModel.graph)
            var resultEdges: List<Edge<E, V>>?

            if (viewModel.isDirectedGraph.value) return@launch
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
                delay(500)
            }
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