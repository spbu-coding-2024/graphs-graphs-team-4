package org.graphApp.view.graph

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import org.graphApp.viewmodel.graph.VertexViewModel

@Composable
fun <V> VertexView(
    viewModel: VertexViewModel<V>,
    modifier: Modifier = Modifier,
    onVertexClick: (VertexViewModel<V>) -> Unit = {},
) {
    var textWidth by remember { mutableStateOf(0) }
    val measurer = rememberTextMeasurer()
    LaunchedEffect(viewModel.value) {
        val layout = measurer.measure(AnnotatedString(viewModel.value))
        textWidth = layout.size.width
    }

    val radiusDp      = viewModel.radius
    val selectedColor = MaterialTheme.colors.primaryVariant
    viewModel
    Box(
        modifier = modifier
            .size(radiusDp * 2, radiusDp * 2)
            .offset(viewModel.x, viewModel.y)
            .drawBehind {
                for (i in 15 downTo 1) {
                    drawCircle(
                        color = (if (viewModel.selected) selectedColor else viewModel.color).copy(alpha = 0.03f),
                        radius = size.minDimension / 2 + i * 4f
                    )
                }

                for (i in 7 downTo 1) {
                    drawCircle(
                        color = (if (viewModel.selected) selectedColor else viewModel.color).copy(alpha = 0.05f),
                        radius = size.minDimension / 2 + i * 2.5f
                    )
                }

                for (i in 5 downTo 1) {
                    drawCircle(
                        color = (if (viewModel.selected) selectedColor else viewModel.color).copy(alpha = 0.1f),
                        radius = size.minDimension / 2 + i * 1.5f
                    )
                }
                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            if (viewModel.selected) selectedColor else viewModel.color,
                            if (viewModel.selected) selectedColor else viewModel.color
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
            },
        contentAlignment = Alignment.Center
    ) {


    }
    Box(modifier = Modifier
        .offset {
            IntOffset(
                x = viewModel.x.roundToPx() - textWidth / 2,
                y = viewModel.y.roundToPx() - 30
            )
        }
        .wrapContentSize(align = Alignment.TopCenter),
        contentAlignment = Alignment.Center
    ) {
        if (viewModel.labelVisible) {
            Card(
                backgroundColor = MaterialTheme.colors.background.copy(alpha = 0.8f),
                elevation = 2.dp,
                modifier = Modifier
                    .alpha(0.8f)
                    .wrapContentSize(Alignment.Center)
                    .padding(2.dp)

            ) {
                Text(
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.caption,
                    fontWeight = FontWeight.Bold,
                    text = viewModel.value,
                    maxLines = 1,
                    softWrap = false,
                    overflow = androidx.compose.ui.text.style.TextOverflow.Visible,
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                )
            }
        }
    }
}

