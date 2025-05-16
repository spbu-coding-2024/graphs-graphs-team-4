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
import androidx.compose.ui.unit.sp
import org.graphApp.model.AppLanguage
import org.graphApp.model.LocalTextResources
import org.graphApp.view.components.*

// добавить обработку названия графа с подсказками для пользователя, чтобы был корректный ввод
// чтобы видно было, что можно печатать
@Composable
fun SaveAsDialog(
    currentLanguage: AppLanguage,
    onLanguageSelect: (AppLanguage) -> Unit,
    onDismissRequest: () -> Unit
) {
    val resources = LocalTextResources.current
    var selectedOption by remember { mutableStateOf("JSON")}
    val options = listOf("JSON", "Neo4j", "SQLite")
    var name by remember { mutableStateOf("") }

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
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    placeholder = { Text(
                        resources.enterGraphName,
                        color = Color(0xFFB3B3B3),
                        fontSize = 13.sp) },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    textStyle = MaterialTheme.typography.body2.copy(fontSize = 14.sp, color = Color.White),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = MaterialTheme.colors.surface
                    )
                )
                Text(
                    text = resources.chooseFormat,
                    fontWeight = FontWeight.Medium,
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
                        text = resources.addFolder,
                        fontWeight = FontWeight.Medium
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start
                ) {
                    CloseButton(onClick = onDismissRequest, text = resources.cancel)
                    Spacer(modifier = Modifier.weight(1f))
                    SaveButton(onClick = {  }, text = resources.save)
                }
            }
        }
    }
}
