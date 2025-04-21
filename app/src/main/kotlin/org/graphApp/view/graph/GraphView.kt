package org.graphApp.view.graph

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import org.graphApp.view.graph.edge.EdgeView
import org.graphApp.view.graph.vertex.VertexView
import org.graphApp.viewmodel.graph.GraphViewModel
import org.graphApp.viewmodel.graph.VertexViewModel

@Composable
fun <V, E>GraphView(
    viewModel: GraphViewModel<V, E>,
) {
    Box(modifier = Modifier
        .fillMaxSize()

    ) {
        viewModel.vertices.forEach { v ->
            VertexView(v, Modifier)
        }
        viewModel.edges.forEach { e ->
            EdgeView(e, Modifier)
        }
    }
}
