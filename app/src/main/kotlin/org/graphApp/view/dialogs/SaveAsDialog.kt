package org.graphApp.view.dialogs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.text.font.FontWeight
import org.graphApp.view.components.*

// ввод названия графа NewGraphDialog вынести сюда
@Composable
fun SaveAsDialog(
    onDismissRequest: () -> Unit
) {
    var selectedOption by remember { mutableStateOf("JSON")}
    val options = listOf("JSON", "Neo4j", "SQLite")

    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            shape = RoundedCornerShape(10.dp),
            color = MaterialTheme.colors.surface,
            modifier = Modifier,
            elevation = 8.dp
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Choose a saving format:",
                    fontWeight = FontWeight.Bold,
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    options.forEach { text ->

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = text == selectedOption,
                                onClick = { selectedOption = text },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = Color.Black
                                )
                            )
                            Spacer(Modifier.width(4.dp))
                            Text(text
                            )
                        }

                    }

                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Spacer(Modifier.width(8.dp))
                    AddFolderButton(
                        onClick = {}
                    )
                    Text(
                        text = "Add folder",
                        fontWeight = FontWeight.Bold
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    CloseButton(onClick = onDismissRequest, text = "Cancel")
                    Spacer(modifier = Modifier.weight(1f))
                    SaveButton(onClick = {  }, text = "Save")
                }

            }
        }
    }
}
