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
    selectedTheme: String,
    onThemeChange: (String) -> Unit,
    onDismissRequest: () -> Unit
) {
    var sliderPosition by remember { mutableStateOf(START_ZOOM_POSITION) }
    val listOfThemes = listOf("Light", "Dark")
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            color = MaterialTheme.colors.surface,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.width(350.dp).height(310.dp)

        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(4.dp)
            ) {
                Text(
                    text = "View",
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.padding(top = 16.dp)
                )
                Divider(
                    modifier = Modifier.padding(
                        start = 30.dp,
                        end = 30.dp,
                        top = 10.dp,
                        bottom = 10.dp
                    ),
                    color = MaterialTheme.colors.onPrimary.copy(alpha = 0.6f),
                    thickness = 1.dp
                )
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = chooseYourTheme,
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                            .offset(30.dp)
                    )
                    Spacer(Modifier.width(5.dp))
                    Text(
                        text = "Theme",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colors.onPrimary,
                        modifier = Modifier.padding(start = 16.dp).offset(14.dp)
                    )
                }

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
                                onClick = {
                                    if (text != selectedTheme) {
                                        onThemeChange(text)
                                    }
                                },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = MaterialTheme.colors.primaryVariant,
                                    unselectedColor = MaterialTheme.colors.onPrimary
                                )
                            )
                            Text(
                                text = text,
                                style = MaterialTheme.typography.body1,
                                color = MaterialTheme.colors.onPrimary,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
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
                            onDismissRequest()
                        }, text = "OK")
                    }
                }

            }

        }
    }
}

