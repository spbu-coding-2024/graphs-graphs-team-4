package org.graphApp.view.graph

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import org.graphApp.viewmodel.graph.VertexViewModel

@Composable
fun <V> VertexView(
    viewModel: VertexViewModel<V>,
    modifier: Modifier = Modifier,
    onVertexClick: (VertexViewModel<V>) -> Unit = {},
) {
    val hotPink = MaterialTheme.colors.primaryVariant
    val softPink = MaterialTheme.colors.primaryVariant
    val selectedColor = Color(0xFF8BF1E2)
    val radiusDp = viewModel.radius

    Box(modifier = modifier
        .size(radiusDp * 2, radiusDp * 2)
        .offset(viewModel.x, viewModel.y)
        .background(if (viewModel.selected) selectedColor else Color.Black, CircleShape)
        .drawBehind {
            for (i in 15 downTo 1) {
                drawCircle(
                    color = (if (viewModel.selected) selectedColor else hotPink).copy(alpha = 0.03f),
                    radius = size.minDimension / 2 + i * 4f
                )
            }

            for (i in 7 downTo 1) {
                drawCircle(
                    color = (if (viewModel.selected) selectedColor else hotPink).copy(alpha = 0.05f),
                    radius = size.minDimension / 2 + i * 2.5f
                )
            }

            for (i in 5 downTo 1) {
                drawCircle(
                    color = (if (viewModel.selected) selectedColor else hotPink).copy(alpha = 0.1f),
                    radius = size.minDimension / 2 + i * 1.5f
                )
            }
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        if (viewModel.selected) selectedColor else softPink,
                        if (viewModel.selected) selectedColor else hotPink
                    ),
                    center = center,
                    radius = 20.0F
                ),
                radius = size.minDimension / 2
            )
        }
        .pointerInput(viewModel) {
            detectDragGestures { change, dragAmount ->
                change.consume()
                viewModel.onDrag(dragAmount)
            }
        }
        .pointerInput(viewModel) {
            detectTapGestures(
                onTap = {
                    onVertexClick(viewModel)
                }
            )
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

