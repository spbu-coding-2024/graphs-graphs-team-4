package org.graphApp.view.dialogs

import androidx.compose.foundation.background
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
import org.graphApp.model.AppLanguage
import org.graphApp.model.LocalTextResources
import org.graphApp.model.getResources
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.graphApp.viewmodel.MainScreenViewModel

@Composable
fun<E> NewGraphPanel(
    onLanguageChange: (AppLanguage) -> Unit,
    currentLanguage: AppLanguage,
    modifier: Modifier = Modifier,
    onClose: () -> Unit,
    vm: MainScreenViewModel<E>
) {

    Surface(
        color = MaterialTheme.colors.surface,
        shape = RoundedCornerShape(10.dp)
    ) {
        val scrollState = rememberScrollState()

        Column(

            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(8.dp)
                .verticalScroll(scrollState)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.weight(0.6f))

                Text(
                    text = resources.newGraph,
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colors.onPrimary
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                )

                IconButton(onClick = onClose) {
                    Icon(Icons.Default.Close,
                        contentDescription = "Close",
                        tint = MaterialTheme.colors.onSecondary
                    )
                }
            }

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(6.dp),
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.1f),
            ) {
                Column(
                    modifier = Modifier.padding(15.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        resources.chooseType,

                        style = MaterialTheme.typography.h6.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colors.onPrimary
                        ),
                        modifier = Modifier.align(Alignment.Start),
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        LabelCheckbox("Weights", vm.isWeightedGraph) { vm.isWeightedGraph = it }
                        LabelCheckbox("Directed", vm.isDirectedGraph) { vm.isDirectedGraph = it }

                    }
                    Row (
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)

                    ){
                        Button(
                            onClick = {
                                onClose()
                                vm.createNewGraph(vm.isWeightedGraph, vm.isDirectedGraph)
                            },
                        ) {
                            Text(resources.createGraph, color = MaterialTheme.colors.onSecondary)

                        }
                    }
                }

            }
        }
    }
}

@Composable
fun LabelCheckbox(text: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = checked, onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = MaterialTheme.colors.primaryVariant,
                uncheckedColor = MaterialTheme.colors.onPrimary
            ),
        )
        Text(
            text = text,
            fontSize = 14.sp,
            color = MaterialTheme.colors.onSecondary
        )
    }
}
