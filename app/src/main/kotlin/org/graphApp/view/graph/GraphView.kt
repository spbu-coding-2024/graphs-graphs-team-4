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
import androidx.compose.ui.focus.FocusRequester
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.graphApp.model.LocalTextResources
import kotlin.math.sign

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun <E> RightClickPopupOnEmptyArea(
    viewModel: MainScreenViewModel<E>,
) {
    val resources = LocalTextResources.current
    var showPopup by remember { mutableStateOf(false) }
    var popupX by remember { mutableStateOf(0f) }
    var popupY by remember { mutableStateOf(0f) }
    var eventHandledByChild by remember { mutableStateOf(false) }
    var vertexLabel by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
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
                            text = resources.enterVertexLabel,
                            style = MaterialTheme.typography.subtitle1,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = vertexLabel,
                            onValueChange = { vertexLabel = it },
                            label = { Text(text = resources.vertexLabel) },
                            modifier = Modifier.fillMaxWidth().height(70.dp),
                            singleLine = true
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        val density = LocalDensity.current

                        Button(
                            onClick = {
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
                                    vertexLabel = ""
                                    showPopup = false
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = MaterialTheme.colors.primaryVariant,
                                contentColor = MaterialTheme.colors.onPrimary
                            )
                        ) {
                            Text(text = resources.add)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EdgeWeightPopup(
    isVisible: Boolean,
    position: DpOffset,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    val resources = LocalTextResources.current
    if (isVisible) {
        var weight by remember { mutableStateOf("") }
        val density = LocalDensity.current
        Popup(
            alignment = Alignment.TopStart,
            offset =
                with(density) {
                    IntOffset(
                        position.x.roundToPx(),
                        position.y.roundToPx()
                    )
                },
            onDismissRequest = onDismiss,
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
                    .wrapContentSize()
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
                        .width(280.dp)
                        .height(200.dp)
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = resources.enterEdgeWeight,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = weight,
                        onValueChange = { weight = it },
                        label = { Text(text = resources.weight) },
                        modifier = Modifier.fillMaxWidth().height(70.dp),
                        singleLine = true
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(onClick = onDismiss) {
                            Text(text = resources.cancel)
                        }

                        Button(onClick = {
                            onConfirm(weight)
                            onDismiss()
                        }) {
                            Text(text = resources.confirm)
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun <E> GraphView(
    viewModel: GraphViewModel<String, E>,
    mainScreenViewModel: MainScreenViewModel<E>
) {
    val state = rememberTransformableState { zoomChange, offsetChange, rotationChange ->
        mainScreenViewModel.scale *= zoomChange
        mainScreenViewModel.rotation += rotationChange
        mainScreenViewModel.offset += offsetChange
    }



    BoxWithConstraints(
        modifier = Modifier
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
                    val cursorPosition = change.position
                    mainScreenViewModel.scaleAt(delta, cursorPosition)
                }
                .transformable(state = state)
        ) {
            RightClickPopupOnEmptyArea(
                viewModel = mainScreenViewModel
            )
            EdgeWeightPopup(
                isVisible = viewModel.showEdgeWeight,
                position = viewModel.edgeWeightPopupPosition,
                onDismiss = { viewModel.dismissEdgeWeight() },
                onConfirm = { weight -> viewModel.confirmEdgeWeight(weight) }
            )
            Box(
                modifier = Modifier
                    .graphicsLayer {
                        scaleX = mainScreenViewModel.scale
                        scaleY = mainScreenViewModel.scale
                        rotationZ = mainScreenViewModel.rotation
                        translationX = mainScreenViewModel.offset.x
                        translationY = mainScreenViewModel.offset.y
                        transformOrigin = TransformOrigin(0.5f, 0.5f)
                    })
            {
                viewModel.edges.forEach { e ->
                    EdgeView(e, Modifier)
                }
                viewModel.vertices.forEach { v ->

                    VertexView(
                        v, Modifier, onVertexClick = { vertex ->
                            viewModel.onVertexSelected(vertex)
                        },
                        onVertexClickDeletion = { vertex ->
                            viewModel.removeVertex(vertex)
                        })
                }

            }
        }
    }
}
