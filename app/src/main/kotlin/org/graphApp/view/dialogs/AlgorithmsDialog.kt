package org.graphApp.view.dialogs

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun AlgorithmsDialog(
    modifier: Modifier = Modifier,
    onClose: () -> Unit
) {
    Surface(
        color = MaterialTheme.colors.surface,
        shape = RoundedCornerShape(10.dp),

        ) {
        val scrollState = rememberScrollState()

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
                    text = "Algorithms",
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colors.onPrimary
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                )

                IconButton(onClick = onClose) {
                    Icon(Icons.Default.Close, contentDescription = "Close")
                }
            }


            Divider(
                color = MaterialTheme.colors.onPrimary.copy(alpha = 0.5f),
                thickness = 1.dp
            )

            Text(
                text = "Basic",
                style = MaterialTheme.typography.h6.copy(
                    fontWeight = FontWeight.Bold, color = MaterialTheme.colors.onPrimary
                )
            )
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                var checked1 by remember { mutableStateOf(true) }
                var checked2 by remember { mutableStateOf(false) }
                var checked3 by remember { mutableStateOf(false) }

                LabeledCheckbox("Graph layouts on the plane", checked1) { checked1 = it }
                LabeledCheckbox("Highlighting key vertices", checked2) { checked2 = it }
                LabeledCheckbox("Find communities", checked3) { checked3 = it }
            }

            Text(
                text = "Classical",
                style = MaterialTheme.typography.h6.copy(
                    fontWeight = FontWeight.Bold, color = MaterialTheme.colors.onPrimary
                )
            )
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                var selectedOption by remember { mutableStateOf("Strongly connected") }

                listOf(
                    "Strongly connected",
                    "Find Bridges",
                    "Find Circle",
                    "Minimal Spanning Tree",
                    "Dijkstra Path",
                    "Ford Bellman"
                ).forEach { option ->
                    LabeledRadioButton(
                        text = option,
                        selected = selectedOption == option
                    ) {
                        selectedOption = option
                    }
                }
            }
        }
    }
}

val activeTrackColor: Color = Color(0xFF1B0B0B)

@Composable
fun LabeledCheckbox(text: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
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
fun LabeledRadioButton(text: String, selected: Boolean, onClick: () -> Unit) {
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
