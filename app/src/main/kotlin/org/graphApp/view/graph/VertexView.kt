package org.graphApp.view.graph

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import org.graphApp.viewmodel.graph.VertexViewModel

@Composable
fun <V> VertexView(
    viewModel: VertexViewModel<V>,
    modifier: Modifier = Modifier,
) {
    val radiusDp = viewModel.radius
    Box(modifier = modifier
        .size(radiusDp * 2, radiusDp * 2)
        .offset(viewModel.x, viewModel.y)
        .background(Color.Black )
        .pointerInput(viewModel) {
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


