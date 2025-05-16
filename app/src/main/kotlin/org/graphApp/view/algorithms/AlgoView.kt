package org.graphApp.view.algorithms

import androidx.compose.ui.graphics.Color
import org.graphApp.model.graph.Vertex
import org.graphApp.model.graph.algorithms.FindStrongCommunities
import org.graphApp.model.graph.algorithms.FordBellman
import org.graphApp.model.graph.algorithms.MinimalSpanningTree
import org.graphApp.model.graph.algorithms.FindCycles
import org.graphApp.viewmodel.graph.GraphViewModel

class AlgorithmsView<V,E>(
    val viewModel: GraphViewModel<V, E>,
) {
    private val highlightColor = Color(0xFF8BF1E2)
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
                }
            }
        }
    }

    fun minimalSpanningTree() {
        resetAllColorsToDefaults()
        val mstFinder = MinimalSpanningTree(graph = viewModel.graph)
        val mstEdges = mstFinder.kraskalSpanningTree()
        val resultEdges = mstEdges ?: mstFinder.bfsSpanningTree()

        viewModel.edges.forEach { edgeVM ->
            edgeVM.color = Color.Gray.copy(alpha = 0.3f)
        }
        val mstColor = Color(0xFF4CAF50)
        resultEdges?.forEach { edge ->
            val edgeId = findEdgeByVertices(edge.vertices.first.id, edge.vertices.second.id)
            edgeId?.let { edgeVM ->
                edgeVM.color = mstColor
            }
        }
    }


    fun fordBellman(
        startVertexId: String,
        endVertexId: String
    ) {
        resetAllColorsToDefaults()

        viewModel.edges.forEach { edgeVM ->
            edgeVM.color = Color.Gray.copy(alpha = 0.3f)
        }

        val startVertex = findVertexByLabel(startVertexId)
        val endVertex = findVertexByLabel(endVertexId)

        if (startVertex == null || endVertex == null) {
            return
        }

        val fordBellman = FordBellman(graph = viewModel.graph)

        val result = fordBellman.fordBellman(startVertex, endVertex)

        if (result == null) {
            return
        }

        val (vertexPath, edgePath) = result

        val pathColor = Color(0xFFF44336)

        vertexPath?.forEach { vertexId ->
            viewModel.vertices.find { it.vertexID == vertexId }?.let { vertexVM ->
                vertexVM.color = pathColor
            }
        }

        edgePath.forEach { edge ->
            val fromId = if (edge.vertices != null) edge.vertices.first.id else -1L
            val toId = if (edge.vertices != null) edge.vertices.second.id else -1L

            val edgeVM = findEdgeByVertices(fromId, toId)
            edgeVM?.let { it.color = pathColor }
        }
    }

    private fun findVertexByLabel(
        idOrLabel: String
    ): Vertex<V>? {
        val id = idOrLabel.toLongOrNull()

        return if (id != null) {
            viewModel.graph.vertices.find { it.id == id }
        } else {
            viewModel.graph.vertices.find {
                it.element.toString().equals(idOrLabel, ignoreCase = true)
            }
        }
    }

    private fun findEdgeByVertices(fromId: Long, toId: Long) = viewModel.edges.find { edgeVM ->
        (edgeVM.u.vertexID == fromId && edgeVM.v.vertexID == toId) ||
                (edgeVM.u.vertexID == toId && edgeVM.v.vertexID == fromId)
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