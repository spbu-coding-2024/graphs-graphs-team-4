package org.graphApp.view.dialogs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.graphApp.view.components.*
import org.graphApp.viewmodel.MainScreenViewModel
import kotlin.math.roundToInt

const val START_ZOOM_POSITION = 250

@Composable
fun <E> ViewDialog(
    mainVm: MainScreenViewModel<E>,
    onDismissRequest: () -> Unit,
) {
    var sliderPosition by remember { mutableStateOf(START_ZOOM_POSITION) }
    var selectedTheme by remember { mutableStateOf("Auto") }
    val listOfThemes = listOf("Light", "Dark", "Auto")
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colors.surface,
            modifier = Modifier,
            elevation = 8.dp
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(24.dp)
            ) {
                Text(
                    text = "View",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        horizontalArrangement = Arrangement.Start,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Icon(
                            imageVector = chooseYourTheme,
                            contentDescription = null,
                            modifier = Modifier.size(30.dp)
                        )
                        Spacer(Modifier.width(5.dp))
                        Text(
                            text = "Theme",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    listOfThemes.forEach { text ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                            ) {
                                RadioButton(
                                    selected = text == selectedTheme,
                                    onClick = { selectedTheme = text },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = Color.Black
                                    )
                                )
                                Text(
                                    text = text,
                                    style = MaterialTheme.typography.body1,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                )
                            }
                        }
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = zoomSign,
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(Modifier.width(5.dp))
                    Text(
                        text = "Zoom",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }

                ZoomSlider(
                    value = sliderPosition,
                    valueRange = 100..400,
                    onValueChange = { sliderPosition = it },
                    modifier = Modifier.padding(top = 8.dp),
                    zoomInIcon = zoomIn,
                    zoomOutIcon = zoomOut
                )

                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    ResetButton(
                        onClick = { sliderPosition = 250 },
                        text = "Reset"
                    )
                }


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Column (
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier.fillMaxWidth(0.5f)
                    ){
                        CloseButton(onClick = onDismissRequest, text = "Cancel")
                    }
                    Column (horizontalAlignment = Alignment.End,
                        modifier = Modifier.fillMaxWidth()) {
                        OkButton(onClick = {
                            mainVm.zoomState.floatValue = mainVm.zoomConverter(sliderPosition)
                            onDismissRequest()
                            println("zoom now is ${mainVm.zoomState}")
                        }, text = "OK")
                    }
                }
            }
        }
    }
}

@Composable
fun ZoomSlider(
    value: Int,
    valueRange: IntRange = 0..100,
    onValueChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    zoomInIcon: ImageVector,
    zoomOutIcon: ImageVector,
    bubbleColor: Color = Color(0xFF2F2F2F),
    stemColor: Color = Color(0xFF2F2F2F),
    thumbColor: Color = Color.Black,
    activeTrackColor: Color = Color(0xFF1B0B0B),
    inactiveTrackColor: Color = Color(0xFFDDDDDD)
) {
    val density = LocalDensity.current
    var rowWidthPx by remember { mutableStateOf(0f) }
    var sliderHeightPx by remember { mutableStateOf(0f) }

    val thumbRadiusDp = 10.dp
    val bubbleWidthDp = 36.dp
    val bubbleHeightDp = 30.dp
    val stemHeightDp = 8.dp
    val iconSize = 24.dp
    val spacerWidthDp = 8.dp

    val fraction = remember(value, valueRange) {
        val range = (valueRange.last - valueRange.first).coerceAtLeast(1)
        ((value.toFloat() - valueRange.first) / range).coerceIn(0f, 1f)
    }

    val bubbleOffsetXpx = remember(fraction, rowWidthPx, density) {
        if (rowWidthPx > 0) {
            val iconSizePx = with(density) { iconSize.toPx() }
            val spacerWidthPx = with(density) { spacerWidthDp.toPx() }
            val bubbleWidthPx = with(density) { bubbleWidthDp.toPx() }
            val thumbRadiusPx = with(density) { thumbRadiusDp.toPx() }
            val trackStartPx = iconSizePx + spacerWidthPx + thumbRadiusPx
            val trackEndPx = rowWidthPx - iconSizePx - spacerWidthPx - thumbRadiusPx
            val trackWidthPx = (trackEndPx - trackStartPx).coerceAtLeast(0f)
            val thumbCenterPx = trackStartPx + trackWidthPx * fraction
            val bubbleStartX = thumbCenterPx - bubbleWidthPx / 2f
            bubbleStartX.coerceIn(0f, rowWidthPx - bubbleWidthPx)
        } else 0f
    }

    val bubbleOffsetX = with(density) { bubbleOffsetXpx.toDp() }

    Column(modifier = modifier.padding(horizontal = spacerWidthDp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(bubbleHeightDp + stemHeightDp + 4.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .offset(x = bubbleOffsetX)
                    .align(Alignment.TopStart)
            ) {
                Surface(
                    shape = CircleShape,
                    color = bubbleColor,
                    elevation = 2.dp,
                    modifier = Modifier.size(bubbleWidthDp, bubbleHeightDp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = value.toString(),
                            color = Color.White,
                            style = MaterialTheme.typography.body2,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .height(stemHeightDp)
                        .background(stemColor)
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned {
                    rowWidthPx = it.size.width.toFloat()
                    sliderHeightPx = it.size.height.toFloat()
                }
        ) {
            Icon(
                imageVector = zoomOutIcon,
                contentDescription = "Zoom Out",
                modifier = Modifier.size(iconSize)
            )

            Spacer(Modifier.width(spacerWidthDp))

            Slider(
                value = value.toFloat(),
                onValueChange = { onValueChange(it.roundToInt())

                                },
                valueRange = valueRange.first.toFloat()..valueRange.last.toFloat(),
                colors = SliderDefaults.colors(
                    thumbColor = thumbColor,
                    activeTrackColor = activeTrackColor,
                    inactiveTrackColor = inactiveTrackColor
                ),
                modifier = Modifier
                    .weight(1f)
                    .height(20.dp)
            )

            Spacer(Modifier.width(spacerWidthDp))

            Icon(
                imageVector = zoomInIcon,
                contentDescription = "Zoom In",
                modifier = Modifier.size(iconSize)
            )
        }
    }
}
