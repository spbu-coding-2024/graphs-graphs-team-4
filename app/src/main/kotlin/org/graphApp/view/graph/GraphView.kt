package org.graphApp.view.graph

import androidx.compose.animation.core.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import org.graphApp.view.graph.edge.EdgeView
import org.graphApp.view.graph.vertex.VertexView
import org.graphApp.viewmodel.graph.GraphViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.*
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.compose.ui.zIndex
import org.graphApp.viewmodel.MainScreenViewModel
import androidx.compose.ui.unit.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun <E> RightClickPopupOnEmptyArea(
    viewModel: MainScreenViewModel<E>,
    modifier: Modifier = Modifier,
) {
    var showPopup by remember { mutableStateOf(false) }
    var popupX by remember { mutableStateOf(0f) }
    var popupY by remember { mutableStateOf(0f) }
    var eventHandledByChild by remember { mutableStateOf(false) }
    var vertexLabel by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
            .onPointerEvent(PointerEventType.Press) {
                if (!eventHandledByChild && it.buttons.isSecondaryPressed) {
                    popupX = it.changes.first().position.x
                    popupY = it.changes.first().position.y
                    showPopup = true
                }
                eventHandledByChild = false
            }
            .zIndex(-1f)

    ) {
    if (showPopup) {
        val offsetX = popupX - (280.dp.value / 2).toInt()
        val offsetY = popupY - (200.dp.value / 2).toInt()
        Popup(
            alignment = Alignment.TopStart,
            offset = IntOffset(offsetX.toInt(), offsetY.toInt()),
            onDismissRequest = { showPopup = false },
            properties = PopupProperties(focusable = true)
        ) {
            val animatedProgress = remember { Animatable(0f) }

            LaunchedEffect(key1 = true) {
                animatedProgress.animateTo(
                    targetValue = 1f,
                    animationSpec = tween(500, easing = EaseOutQuint)
                )
            }

            val scale = animatedProgress.value
            Card(
                modifier = Modifier
                    .width(280.dp)
                    .height(200.dp)
                    .padding(16.dp)
                    .graphicsLayer {
                        transformOrigin = TransformOrigin(0f, 0f)
                        scaleX = scale
                        scaleY = scale
                    },
                elevation = 8.dp,
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        "Введите лейбл вершины",
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = vertexLabel,
                        onValueChange = { vertexLabel = it },
                        label = { Text("Vertex label") },
                        modifier = Modifier.fillMaxWidth().height(70.dp),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    val density = LocalDensity.current

                    Button(onClick = {
                        if (vertexLabel.isNotEmpty()) {
                            val xDp = with(density) { popupX.toDp() - 20.dp }
                            val yDp = with(density) { popupY.toDp() - 20.dp }

                            viewModel.graphViewModel.addVertex(
                                vertexLabel,
                                xDp,
                                yDp
                            )

                            println("Added vertex: $vertexLabel at ($popupX, $popupY)")
                            vertexLabel = ""
                            showPopup = false
                        }
                    }) {
                        Text("ADD")
                    }
                }
            }
        }
    }
}
}


@Composable
fun <E>GraphView(
    viewModel: GraphViewModel<String, E>,
    zoom: Float
) {
    BoxWithConstraints(modifier = Modifier
        .fillMaxSize()
        .clipToBounds()
        .graphicsLayer {
            scaleX = zoom
            scaleY = zoom
            transformOrigin = TransformOrigin(0f, 0f)
        }
    ) {
        println("Drawing ${viewModel.vertices.size} vertices")

        viewModel.edges.forEach { e ->
            EdgeView(e, Modifier)
        }

        viewModel.vertices.forEach { v ->
            println("Drawing vertex ${v.value} at (${v.x}, ${v.y})")
            VertexView(v, Modifier, zoom)
        }
    }
}