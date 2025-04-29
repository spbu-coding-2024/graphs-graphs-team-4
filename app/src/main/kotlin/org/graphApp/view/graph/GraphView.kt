package org.graphApp.view.graph

import androidx.compose.animation.core.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import org.graphApp.viewmodel.graph.GraphViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
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
import org.graphApp.viewmodel.MainScreenViewModel
import androidx.compose.ui.unit.*
import androidx.compose.ui.zIndex
import kotlin.math.sign

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun <E> RightClickPopupOnEmptyArea(
    viewModel: MainScreenViewModel<E>,
) {
    var showPopup by remember { mutableStateOf(false) }
    var popupX by remember { mutableStateOf(0f) }
    var popupY by remember { mutableStateOf(0f) }
    var eventHandledByChild by remember { mutableStateOf(false) }
    var vertexLabel by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .onPointerEvent(PointerEventType.Press) {
                if (!eventHandledByChild && it.buttons.isSecondaryPressed) {
                    popupX = it.changes.first().position.x
                    popupY = it.changes.first().position.y
                    showPopup = true
                }
                eventHandledByChild = false
            }
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
                            val adjustedX = (popupX - viewModel.offset.x) / viewModel.scale
                            val adjustedY = (popupY - viewModel.offset.y) / viewModel.scale
                            val xDp = with(density) { adjustedX.toDp() - 20.dp }
                            val yDp = with(density) { adjustedY.toDp() - 20.dp }

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





@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun <E>GraphView(
    viewModel: GraphViewModel<String, E>,
    mainScreenViewModel: MainScreenViewModel<E>
) {
    val state = rememberTransformableState {
            zoomChange, offsetChange, rotationChange ->
        mainScreenViewModel.scale *= zoomChange
        mainScreenViewModel.rotation += rotationChange
        mainScreenViewModel.offset += offsetChange
    }

    BoxWithConstraints(modifier = Modifier
        .fillMaxSize()
        .clipToBounds()
    ) {
        Box(
            modifier = Modifier
                .background(Color.Gray)
                .focusable()
                .onPointerEvent(PointerEventType.Scroll) {
                    val change = it.changes.first()
                    val delta = -change.scrollDelta.y.toInt().sign
                    mainScreenViewModel.scale(delta)
                    println("Scroll")
                }
                .transformable(state = state)
        ) {
            Box(modifier = Modifier
                .graphicsLayer {
                    scaleX = mainScreenViewModel.scale
                    scaleY = mainScreenViewModel.scale
                    rotationZ = mainScreenViewModel.rotation
                    translationX = mainScreenViewModel.offset.x
                    translationY = mainScreenViewModel.offset.y
                    transformOrigin = TransformOrigin(0.5f, 0.5f)
                })
            {
                RightClickPopupOnEmptyArea(
                    viewModel = mainScreenViewModel
                )
                println("Drawing ${viewModel.vertices.size} vertices")

                viewModel.edges.forEach { e ->
                    EdgeView(e, Modifier)
                }

                viewModel.vertices.forEach { v ->
                    println("Drawing vertex ${v.value} at (${v.x}, ${v.y})")
                    VertexView(v, Modifier)
                }
            }
        }
    }
}