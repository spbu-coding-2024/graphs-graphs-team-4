package org.graphApp.view.dialogs

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.graphApp.model.LocalTextResources
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.graphApp.view.algorithms.AlgorithmsView
import org.graphApp.view.algorithms.GraphsLayout
import org.graphApp.view.components.*

import org.graphApp.view.theme.GraphTheme
import org.graphApp.viewmodel.MainScreenViewModel
import org.graphApp.viewmodel.graph.GraphViewModel
import kotlin.math.exp


@Composable
fun <V, E> AlgorithmsDialog(
    algoVM: AlgorithmsView<V, E>,
    onClose: () -> Unit,
    onDismissRequest: () -> Unit,
    viewModel: MainScreenViewModel<E>
) {
    val resources = LocalTextResources.current

    val algoLayout: GraphsLayout<V,E> = GraphsLayout()

    Surface(
        color = MaterialTheme.colors.surface,
        shape = RoundedCornerShape(10.dp),
    ) {
        val scrollState = rememberScrollState()

        var startVertex by remember { mutableStateOf("") }
        var finishVertex by remember { mutableStateOf("") }
        var startVertexCycle by remember { mutableStateOf("") }
        var checked1 by remember { mutableStateOf(false) }
        var checked2 by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.weight(0.5f))

                Text(
                    text = resources.algorithmsDialog,
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        color = MaterialTheme.colors.onPrimary
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                )

                IconButton(onClick = onClose) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "Close",
                        tint = MaterialTheme.colors.onSecondary
                    )
                }
            }

            Divider(
                color = MaterialTheme.colors.onPrimary.copy(alpha = 0.5f),
                thickness = 1.dp
            )

            Text(
                text = resources.basic,
                style = MaterialTheme.typography.h6.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onPrimary
                )
            )
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                var checked1 by remember { mutableStateOf(false) }
                var checked2 by remember { mutableStateOf(false) }

                LabeledCheckbox(resources.layout, checked1) { checked1 = it }
//                LabeledCheckbox(resources.findCommunities, checked2) { checked2 = it }
            }

            Text(
                text = resources.classic,
                style = MaterialTheme.typography.h6.copy(
                    fontWeight = FontWeight.Bold, color = MaterialTheme.colors.onPrimary
                )
            )
            var selectedOption by remember { mutableStateOf("") }
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                listOf(
                    resources.stronglyConnected,
                    resources.minimalTree,
                    resources.findCycles,
                    resources.fordBellman,
                    resources.findCommunities
                ).forEach { option ->
                    LabeledRadioButton(
                        text = option,
                        selected = selectedOption == option
                    ) {
                        selectedOption = option
                    }
                }
            }
            AnimatedVisibility(
                visible = selectedOption == resources.findCycles,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    backgroundColor = MaterialTheme.colors.surface,
                    elevation = 4.dp
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "Parameters:",
                            fontWeight = FontWeight.Light,
                            color = MaterialTheme.colors.onPrimary,
                            fontSize = 16.sp
                        )

                        OutlinedTextField(
                            value = startVertexCycle,
                            onValueChange = { startVertexCycle = it },
                            label = {
                                Text(
                                    text = "Start Vertex",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.ExtraLight,
                                    color = MaterialTheme.colors.onPrimary
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = MaterialTheme.colors.primaryVariant,
                                unfocusedLabelColor = MaterialTheme.colors.onPrimary,
                                textColor = MaterialTheme.colors.onPrimary
                            )
                        )
                    }
                }
            }
            AnimatedVisibility(
                visible = selectedOption == resources.fordBellman,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    backgroundColor = MaterialTheme.colors.surface,
                    elevation = 4.dp
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "Parameters:",
                            fontWeight = FontWeight.Light,
                            color = MaterialTheme.colors.onPrimary,
                            fontSize = 16.sp,
                        )

                        OutlinedTextField(
                            value = startVertex,
                            onValueChange = { startVertex = it },
                            label = {
                                Text(
                                    text = "Start Vertex",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.ExtraLight,
                                    color = MaterialTheme.colors.onPrimary
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = MaterialTheme.colors.primaryVariant,
                                unfocusedLabelColor = MaterialTheme.colors.onPrimary,
                                textColor = MaterialTheme.colors.onPrimary
                            )
                        )

                        OutlinedTextField(
                            value = finishVertex,
                            onValueChange = { finishVertex = it },
                            label = {
                                Text(
                                    text = "Finish Vertex",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.ExtraLight,
                                    color = MaterialTheme.colors.onPrimary
                                )
                            },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = MaterialTheme.colors.primaryVariant,
                                unfocusedLabelColor = MaterialTheme.colors.onPrimary,
                                textColor = MaterialTheme.colors.onPrimary
                            )
                        )
                    }
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                startAlgorithmButton(onClick = {
                    onDismissRequest()
                    when (selectedOption) {
                        resources.stronglyConnected -> algoVM.findStrongCommunities()
                        resources.minimalTree -> algoVM.minimalSpanningTree()
                        resources.fordBellman -> {
                            algoVM.fordBellman(startVertex, finishVertex)
                        }
                        resources.findCycles -> {
                           algoVM.findCycles(startVertexCycle)
                        }
                        resources.findCommunities -> {
                            algoVM.findCommunities()
                        }
                    }

                    if(checked1) {
                        algoLayout.place(1000.0,900.0, viewModel.graphViewModel as GraphViewModel<V, E>)
                    }

                }, text = resources.startAlgo)
                resetButton(
                    onClick = {
                        algoVM.resetAllColorsToDefaults()
                    },
                    text = resources.reset
                )
            }
        }
    }
}

@Composable
fun LabeledCheckbox(
    text: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colors.primaryVariant,
                uncheckedColor = MaterialTheme.colors.onPrimary
            )
        )
        Text(
            text = text,
            fontSize = 15.sp,
            color = MaterialTheme.colors.onPrimary,
            fontWeight = FontWeight.Light
        )
    }
}

@Composable
fun LabeledRadioButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colors.primaryVariant,
                unselectedColor = MaterialTheme.colors.onPrimary
            )
        )
        Text(
            text = text,
            fontSize = 15.sp,
            color = MaterialTheme.colors.onPrimary,
            fontWeight = FontWeight.Light
        )
    }
}