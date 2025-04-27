package org.graphApp.view.graph.vertex

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import org.graphApp.viewmodel.graph.VertexViewModel

@Composable
fun <V> VertexView(
    viewModel: VertexViewModel<V>,
    modifier: Modifier = Modifier,
    zoom: Float = 2.5f
) {
    Box(modifier = modifier
        .size(viewModel.radius * 2 * zoom, viewModel.radius * 2 * zoom)
        .offset(viewModel.x, viewModel.y)
        .background(
            color = viewModel.color,
            shape = CircleShape
        )
        .pointerInput(viewModel, zoom) {
            detectDragGestures { change, dragAmount ->
                change.consume()
                viewModel.onDrag(dragAmount)
            }
        }
    ) {
        if (viewModel.labelVisible) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(0.dp, -viewModel.radius - 10.dp),
                text = viewModel.value,
            )
        }
    }
}



