package org.graphApp.view.algorithms

import androidx.compose.ui.graphics.Color
import data.SQLiteMainLogic.Edges.fromVertex
import org.graphApp.currentGraph
import org.graphApp.model.graph.Edge
import org.graphApp.model.graph.Vertex
import org.graphApp.model.graph.WeightedEdge
import org.graphApp.model.graph.algorithms.FindStrongCommunities
import org.graphApp.model.graph.algorithms.MinimalSpanningTree
import org.graphApp.viewmodel.MainScreenViewModel
import org.graphApp.viewmodel.graph.GraphViewModel

class AlgorithmsView<V, E>(
    private val viewModel: GraphViewModel<V, E>,
    private val mainScreenViewModel: MainScreenViewModel<E>
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
        if(!viewModel.isDirectedGraph.value) {
            return
        }


        val result = FindStrongCommunities(graph = viewModel.graph)
            .findStrongCommunitiesInGraph() ?: return

        val vertexIdToVM = viewModel.vertices.associateBy { it.vertexID }

        viewModel.vertices.forEach { evm ->
                evm.color = Color.Red
        }

    }

    fun minimalSpanningTree() {
        resetAllColorsToDefaults()
        val mst = MinimalSpanningTree(graph = viewModel.graph)
        val mstColor = Color(0xFF4CAF50)

        viewModel.edges.forEach { evm ->
            evm.color = mstColor
        }
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
