package org.graphApp.view.dialogs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.graphApp.model.LocalTextResources
import org.graphApp.view.components.*
import org.graphApp.viewmodel.graph.GraphViewModel
import data.SQLiteMainLogic.SQLiteExposed
import data.SQLiteMainLogic.SQLiteMainLogic
import kotlinx.coroutines.launch
import java.io.File
import javax.swing.JFileChooser


@Composable
fun SaveAsDialog(
    graphViewModel: GraphViewModel<Any, Any>?,
    onDismissRequest: () -> Unit,
    onSaveSuccess: (String, String) -> Unit = { _, _ -> },
    onSaveError: (String) -> Unit = { }
) {
    val resources = LocalTextResources.current
    var selectedOption by remember { mutableStateOf("SQLite")}
    val options = listOf("JSON", "Neo4j", "SQLite")
    var name by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var folderChoose by remember { mutableStateOf("~/")}
    val coroutineScope = rememberCoroutineScope()
    var selectedFolder by remember {mutableStateOf<File?>(null) }
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            shape = RoundedCornerShape(10.dp),
            color = MaterialTheme.colors.surface,
            elevation = 8.dp
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(16.dp)
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        name = it
                        errorMessage = ""
                    },
                    placeholder = { Text(
                        resources.enterGraphName,
                        color = MaterialTheme.colors.onPrimary,
                        fontSize = 13.sp) },
                    singleLine = true,
                    enabled = !isLoading,
                    isError = errorMessage.isNotEmpty(),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    textStyle = MaterialTheme.typography.body2.copy(fontSize = 14.sp, color = MaterialTheme.colors.onBackground),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = MaterialTheme.colors.surface
                    )
                )

                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = MaterialTheme.colors.error,
                        fontSize = 12.sp
                    )
                }

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
                                enabled = !isLoading,
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = MaterialTheme.colors.primaryVariant
                                )
                            )
                            Spacer(Modifier.width(4.dp))
                            Text(text)
                        }
                    }
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Spacer(Modifier.width(8.dp))
                    AddFolderButton(
                        onClick = {
                            val folder = chooseFolder()
                            if (folder != null) {
                                selectedFolder = folder
                            }
                        }
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
                    CloseButton(
                        onClick = onDismissRequest,
                        text = resources.cancel
                    )
                    Spacer(modifier = Modifier.weight(1f))

                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            strokeWidth = 2.dp
                        )
                    } else {
                        SaveButton(
                            onClick = {
                                if (name.isBlank()) {
                                    errorMessage = "Enter a graph name"
                                    return@SaveButton
                                }

                                if (graphViewModel == null) {
                                    errorMessage = "No graph to save"
                                    return@SaveButton
                                }

                                isLoading = true
                                errorMessage = ""

                                coroutineScope.launch {
                                    try {
                                        when (selectedOption) {
                                            "SQLite" -> {
                                                try {
                                                    val sanitizedName = name.replace(Regex("[^a-zA-Z0-9_-]"), "_")
                                                    val dbFolder = if (selectedFolder != null ) selectedFolder else "src/main/kotlin/org/graphApp/data/SQLite/StorageSQLite"
                                                    val dbFileName = "$dbFolder/$sanitizedName.db"
                                                    val sqliteConnection = SQLiteExposed(dbFileName)

                                                    val graphViewModel = graphViewModel as GraphViewModel<Any, Any>

                                                    val sqliteLogic = SQLiteMainLogic<Any, Any>(sqliteConnection)
                                                    val success = sqliteLogic.saveToSQLiteDataBase(graphViewModel, sanitizedName)

                                                    if (success) {
                                                        onSaveSuccess(sanitizedName, selectedOption)
                                                        onDismissRequest()
                                                    } else {
                                                        errorMessage = "Failed to save graph to SQLite database"
                                                    }
                                                } catch (sqliteException: Exception) {
                                                    errorMessage = "SQLite error: ${sqliteException.localizedMessage ?: "Database connection failed"}"
                                                    println("SQLite Exception details: ${sqliteException.printStackTrace()}")
                                                }
                                            }
                                            "JSON" -> {
                                                errorMessage = "todo"
                                            }
                                            "Neo4j" -> {
                                                errorMessage = "todo"
                                            }
                                        }
                                    } catch (e: Exception) {
                                        errorMessage = "Error saving graph: ${e.localizedMessage ?: "Unknown error"}"
                                        println("General Exception: ${e.printStackTrace()}")
                                    } finally {
                                        isLoading = false
                                    }
                                }
                            },
                            text = resources.save
                        )
                    }
                }
            }
        }
    }
}

fun chooseFolder(): File? {
    val chooser = JFileChooser()
    chooser.fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
    chooser.dialogTitle = "Choose a folder for saving"
    val result = chooser.showOpenDialog(null)
    return if (result == JFileChooser.APPROVE_OPTION) chooser.selectedFile else null

}