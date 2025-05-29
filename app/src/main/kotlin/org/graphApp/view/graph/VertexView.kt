package org.graphApp.view.graph

import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.graphApp.viewmodel.graph.VertexViewModel

@Composable
fun <V> VertexView(
    viewModel: VertexViewModel<V>,
    modifier: Modifier = Modifier,
    onVertexClick: (VertexViewModel<V>) -> Unit = {},
    onVertexClickDeletion: (VertexViewModel<V>) -> Unit = {}
) {
    var textWidth by remember { mutableStateOf(0) }
    val measurer = rememberTextMeasurer()
    LaunchedEffect(viewModel.value) {
        val layout = measurer.measure(AnnotatedString(viewModel.value))
        textWidth = layout.size.width
    }

    val radiusDp = viewModel.radius
    val selectedColor = MaterialTheme.colors.primaryVariant
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(viewModel.selected) {
        if (viewModel.selected) {
            focusRequester.requestFocus()
        }
    }
    DisposableEffect(Unit) {
        onDispose {}
    }
    Box(
        modifier = modifier
            .size(radiusDp * 2, radiusDp * 2)
            .offset(viewModel.x, viewModel.y)

            .drawBehind {
                val baseColor = if (viewModel.selected) selectedColor else viewModel.color
                if (viewModel.highlight.value) {
                    drawCircle(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                baseColor.copy(alpha = 0.3f),
                                baseColor.copy(alpha = 0.2f),
                                baseColor.copy(alpha = 0.0f)
                            ),
                            center = center,
                            radius = size.minDimension * 2.5f
                        ),
                        radius = size.minDimension * 4f
                    )
                }

                drawCircle(
                    color = baseColor,
                    radius = size.minDimension / 2
                )

                drawCircle(
                    color = Color.Black.copy(alpha = 0.25f),
                    radius = size.minDimension / 2 * 0.95f,
                    center = Offset(center.x + 4f, center.y + 4f)
                )

                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            baseColor.copy(alpha = 1.0f),
                            baseColor.copy(alpha = 0.85f),
                            baseColor.copy(
                                red = baseColor.red * 0.6f,
                                green = baseColor.green * 0.6f,
                                blue = baseColor.blue * 0.6f,
                                alpha = 1.0f
                            )
                        ),
                        center = Offset(center.x - size.minDimension * 0.2f, center.y - size.minDimension * 0.2f),
                        radius = size.minDimension * 0.7f
                    ),
                    radius = size.minDimension / 2
                )

                drawCircle(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            baseColor.copy(
                                red = Math.min(1.0f, baseColor.red * 1.2f),
                                green = Math.min(1.0f, baseColor.green * 1.2f),
                                blue = Math.min(1.0f, baseColor.blue * 1.2f),
                                alpha = 0.4f
                            ),
                            Color.Transparent
                        ),
                        center = Offset(0f, 0f),
                        radius = size.minDimension * 0.2f
                    ),
                    radius = size.minDimension * 0.15f,
                    center = Offset(center.x + size.minDimension * 0.1f, center.y + size.minDimension * 0.25f)
                )
            }
            .focusRequester(focusRequester)
            .focusable()
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
            .onKeyEvent { event ->
                println("Pressed key ${event.key}")
                if (event.key == Key.Delete && event.type == KeyEventType.KeyDown && viewModel.selected) {
                    onVertexClickDeletion(viewModel)
                    true
                } else {
                    false
                }
            },
        contentAlignment = Alignment.Center
    ) {


    }
    Box(
        modifier = Modifier
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
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp),
                    fontSize = 20.sp
                )
            }
        }
    }
}
