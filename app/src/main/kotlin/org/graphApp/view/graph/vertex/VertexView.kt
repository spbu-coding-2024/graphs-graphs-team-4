package org.graphApp.view.graph.vertex

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import org.graphApp.viewmodel.graph.VertexViewModel
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
@Composable
fun <V> VertexView(
    viewModel: VertexViewModel<V>,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(viewModel.radius * 3f)
            .offset(viewModel.x - viewModel.radius * 0.5f, viewModel.y - viewModel.radius * 0.5f),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            viewModel.color.copy(alpha = 0.8f),
                            Color.Transparent
                        ),
                    ),
                    shape = CircleShape
                )
                .blur(200.dp)
        )
        Box(
            modifier = Modifier
                .size(viewModel.radius * 2)
                .background(viewModel.color, CircleShape)
                .pointerInput(viewModel) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        viewModel.onDrag(dragAmount)
                    }
                }
        )
        if (viewModel.labelVisible) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .offset(y = -viewModel.radius - 10.dp),
                text = viewModel.value,
            )
        }
    }
}
