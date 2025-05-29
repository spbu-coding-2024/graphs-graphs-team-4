package org.graphApp.view.graph

import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
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
                if(viewModel.highlight.value) {
                    for (i in 15 downTo 1) {
                        drawCircle(
                            color = (if (viewModel.selected) selectedColor else viewModel.color).copy(alpha = 0.03f),
                            radius = size.minDimension / 2 + i * 8f
                        )
                    }

                    for (i in 7 downTo 1) {
                        drawCircle(
                            color = (if (viewModel.selected) selectedColor else viewModel.color).copy(alpha = 0.05f),
                            radius = size.minDimension / 2 + i * 6f
                        )
                    }

                    for (i in 5 downTo 1) {
                        drawCircle(
                            color = (if (viewModel.selected) selectedColor else viewModel.color).copy(alpha = 0.1f),
                            radius = size.minDimension / 2 + i * 3f
                        )
                    }
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

