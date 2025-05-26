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
import data.SQLiteMainLogic.Graphs
import data.SQLiteMainLogic.Graphs.graphName
import org.graphApp.model.LocalTextResources
import org.graphApp.view.components.*
import org.graphApp.viewmodel.graph.GraphViewModel
import data.SQLiteMainLogic.SQLiteExposed
import data.SQLiteMainLogic.SQLiteMainLogic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.graphApp.data.Neo4j.Neo4jDataBase
import org.graphApp.main
import org.graphApp.viewmodel.MainScreenViewModel
import java.io.File
import javax.swing.JFileChooser
import javax.swing.filechooser.FileNameExtensionFilter


@Composable
fun OpenDialog(
    graphViewModel: GraphViewModel<*,*>,
    onDismissRequest: () -> Unit,
    onLoadSuccess: (GraphViewModel<Any, Any>, String, String) -> Unit = { _, _, _ -> },
    mainViewModel: MainScreenViewModel<Any>
) {
    val resources = LocalTextResources.current
    var selectedOption by remember { mutableStateOf("SQLite") }
    val options = listOf("JSON", "Neo4j", "SQLite")
    var name by remember { mutableStateOf("") }
    var uri by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var graphName by remember { mutableStateOf("") }
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
                if (selectedOption == "SQLite") {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Spacer(Modifier.width(8.dp))
                        AddFolderButton(
                            onClick = {
                                val _file = chooseDbFile()
                                if (_file != null) {
                                    selectedFile = _file
                                }
                            }
                        )
                        Text(
                            text = resources.addFolder,
                            fontWeight = FontWeight.Medium
                        )

                        if (selectedFile != null) {
                            Text(
                                text = "Selected: ${selectedFile!!.name}",
                                fontSize = 12.sp,
                                color = MaterialTheme.colors.onSurface,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }

                if (selectedOption == "Neo4j") {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        OutlinedTextField(
                            value = uri,
                            onValueChange = {
                                uri = it
                            },
                            placeholder = {
                                Text(
                                    "Uri",
                                    color = MaterialTheme.colors.onPrimary,
                                    fontSize = 13.sp
                                )
                            },
                            singleLine = true,
                            enabled = !isLoading,
                            isError = errorMessage.isNotEmpty(),
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            textStyle = MaterialTheme.typography.body2.copy(
                                fontSize = 14.sp,
                                color = MaterialTheme.colors.onBackground
                            ),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                backgroundColor = MaterialTheme.colors.surface
                            )
                        )
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        OutlinedTextField(
                            value = username,
                            onValueChange = {
                                username = it
                            },
                            placeholder = {
                                Text(
                                    "Username",
                                    color = MaterialTheme.colors.onPrimary,
                                    fontSize = 13.sp
                                )
                            },
                            singleLine = true,
                            enabled = !isLoading,
                            isError = errorMessage.isNotEmpty(),
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            textStyle = MaterialTheme.typography.body2.copy(
                                fontSize = 14.sp,
                                color = MaterialTheme.colors.onBackground
                            ),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                backgroundColor = MaterialTheme.colors.surface
                            )
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        OutlinedTextField(
                            value = password,
                            onValueChange = {
                                password = it
                            },
                            placeholder = {
                                Text(
                                    "Password",
                                    color = MaterialTheme.colors.onPrimary,
                                    fontSize = 13.sp
                                )
                            },
                            singleLine = true,
                            enabled = !isLoading,
                            isError = errorMessage.isNotEmpty(),
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            textStyle = MaterialTheme.typography.body2.copy(
                                fontSize = 14.sp,
                                color = MaterialTheme.colors.onBackground
                            ),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                backgroundColor = MaterialTheme.colors.surface
                            )
                        )

                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        OutlinedTextField(
                            value = graphName,
                            onValueChange = {
                                graphName = it
                            },
                            placeholder = {
                                Text(
                                    "GraphName",
                                    color = MaterialTheme.colors.onPrimary,
                                    fontSize = 13.sp
                                )
                            },
                            singleLine = true,
                            enabled = !isLoading,
                            isError = errorMessage.isNotEmpty(),
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            textStyle = MaterialTheme.typography.body2.copy(
                                fontSize = 14.sp,
                                color = MaterialTheme.colors.onBackground
                            ),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                backgroundColor = MaterialTheme.colors.surface
                            )
                        )

                    }
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
                                                    if (selectedFile == null || !selectedFile!!.exists()) {
                                                        errorMessage = "Please select a valid database file"
                                                        return@launch
                                                    }

                                                    val fileName = selectedFile!!.nameWithoutExtension
                                                    val sanitizedName = fileName.replace(Regex("[^a-zA-Z0-9_-]"), "_")

                                                    println("Loading graph '$sanitizedName' from ${selectedFile!!.absolutePath}")

                                                    val sqliteConnection = SQLiteExposed(selectedFile!!.absolutePath)
                                                    val sqliteLogic = SQLiteMainLogic<Any, Any>(sqliteConnection)

                                                    val loadedGraphViewModel =
                                                        sqliteLogic.readFromSQLiteDataBase<Any, Any>(sanitizedName)

                                                    if (loadedGraphViewModel != null) {
                                                        onLoadSuccess(
                                                            loadedGraphViewModel,
                                                            sanitizedName,
                                                            selectedOption
                                                        )
                                                        onDismissRequest()
                                                        println("загрузился")
                                                    } else {
                                                        errorMessage = "Failed to load graph from SQLite database"
                                                    }
                                                } catch (sqliteException: Exception) {
                                                    errorMessage =
                                                        "SQLite error: ${sqliteException.localizedMessage ?: "Database connection failed"}"
                                                    println("SQLite Exception details: ${sqliteException.printStackTrace()}")
                                                }
                                            }

                                            "JSON" -> {
                                                errorMessage = "JSON loading not implemented yet"
                                            }

                                            "Neo4j" -> {
                                                try {
                                                    val neo4jLoader = Neo4jDataBase(
                                                        mainViewModel = mainViewModel,
                                                        graphViewModel = mainViewModel.graphViewModel,
                                                        uri = uri,
                                                        username = username,
                                                        password = password,
                                                        graphName = graphName
                                                    )
                                                    neo4jLoader.uploadGraph()
                                                } catch (error: Exception) {
                                                    errorMessage = error.localizedMessage ?: "Unknown error"
                                                }
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
