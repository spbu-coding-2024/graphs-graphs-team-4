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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter


@Composable
fun OpenDialog(
    onDismissRequest: () -> Unit,
    onLoadSuccess: (GraphViewModel<Any, Any>, String, String) -> Unit = { _, _, _ -> },
) {
    val resources = LocalTextResources.current
    var selectedOption by remember { mutableStateOf("SQLite")}
    val options = listOf("JSON", "Neo4j", "SQLite")
    var name by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    var selectedFile by remember { mutableStateOf<File?>(null) }

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
                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = MaterialTheme.colors.error,
                        fontSize = 12.sp
                    )
                }

                Text(
                    text = "Choose a format",
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
                            val file = chooseDbFile()
                            if (file != null) {
                                selectedFile = file
                                name = file.nameWithoutExtension
                            }
                        }
                    )
                    Text(
                        text = "Choose a file",
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
                        loadButton(
                            onClick = {

                                isLoading = true
                                errorMessage = ""

                                CoroutineScope(Dispatchers.Default).launch {
                                    try {
                                        when (selectedOption) {
                                            "SQLite" -> {
                                                try {
                                                    val sanitizedName = name.replace(Regex("[^a-zA-Z0-9_-]"), "_")
                                                    val dbFolder = selectedFile?.absolutePath
                                                    val dbFileName = selectedFile
                                                    if (dbFileName == null || !dbFileName.exists()) {
                                                        errorMessage = "Database file not found"
                                                        return@launch
                                                    }


                                                    if (!dbFileName.exists()) {
                                                        errorMessage = "Database file not found: $dbFileName"
                                                        return@launch
                                                    }

                                                    val sqliteConnection = SQLiteExposed(dbFileName.absolutePath)
                                                    val sqliteLogic = SQLiteMainLogic<Any, Any>(sqliteConnection)

                                                    val loadedGraphViewModel = sqliteLogic.readFromSQLiteDataBase<Any, Any>(sanitizedName)

                                                    if (loadedGraphViewModel != null) {
                                                        onLoadSuccess(loadedGraphViewModel, sanitizedName, selectedOption)
                                                        onDismissRequest()
                                                        println("загрузился")
                                                    } else {
                                                        errorMessage = "Failed to load graph from SQLite database"
                                                    }
                                                } catch (sqliteException: Exception) {
                                                    errorMessage = "SQLite error: ${sqliteException.localizedMessage ?: "Database connection failed"}"
                                                    println("SQLite Exception details: ${sqliteException.printStackTrace()}")
                                                }
                                            }
                                            "JSON" -> {
                                                errorMessage = "JSON loading not implemented yet"
                                            }
                                            "Neo4j" -> {
                                                errorMessage = "Neo4j loading not implemented yet"
                                            }
                                        }
                                    } catch (e: Exception) {
                                        errorMessage = "Error loading graph: ${e.localizedMessage ?: "Unknown error"}"
                                        println("General Exception: ${e.printStackTrace()}")
                                    } finally {
                                        isLoading = false
                                    }
                                }
                            },
                            text = resources.load
                        )
                    }
                }
            }
        }
    }
}

fun chooseDbFile(): File? {
    val fileChooser = JFileChooser()
    fileChooser.fileFilter = FileNameExtensionFilter("SQLite DB files", "db")
    val result = fileChooser.showOpenDialog(null)
    return if (result == JFileChooser.APPROVE_OPTION) fileChooser.selectedFile else null
}
