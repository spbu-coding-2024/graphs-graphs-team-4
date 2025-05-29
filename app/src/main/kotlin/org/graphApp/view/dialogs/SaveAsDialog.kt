package org.graphApp.view.dialogs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import org.graphApp.model.LocalTextResources
import org.graphApp.view.components.*
import org.graphApp.viewmodel.graph.GraphViewModel
import data.SQLiteMainLogic.SQLiteExposed
import data.SQLiteMainLogic.SQLiteMainLogic
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.graphApp.data.Neo4j.Neo4jDataBase
import org.graphApp.viewmodel.MainScreenViewModel
import org.neo4j.driver.AuthTokens
import org.neo4j.driver.GraphDatabase
import java.io.File
import javax.swing.JFileChooser


@Composable
fun <E> SaveAsDialog(
    graphViewModel: GraphViewModel<Any, Any>?,
    mainViewModel: MainScreenViewModel<E>,
    onDismissRequest: () -> Unit,
    onSaveSuccess: (String, String) -> Unit = { _, _ -> },
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
    var selectedFolder by remember { mutableStateOf<File?>(null) }
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            shape = RoundedCornerShape(10.dp), color = MaterialTheme.colors.surface, elevation = 8.dp,
            modifier = Modifier.wrapContentSize(align = Alignment.Center)
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
                    placeholder = {
                        Text(
                            resources.enterGraphName, color = MaterialTheme.colors.onPrimary, fontSize = 13.sp
                        )
                    },
                    singleLine = true,
                    enabled = !isLoading,
                    isError = errorMessage.isNotEmpty(),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    textStyle = MaterialTheme.typography.body2.copy(
                        fontSize = 14.sp, color = MaterialTheme.colors.onBackground
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        backgroundColor = MaterialTheme.colors.surface
                    )
                )

                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage, color = MaterialTheme.colors.error, fontSize = 12.sp
                    )
                }

                Text(
                    text = resources.chooseFormat,
                    fontWeight = FontWeight.Medium,
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(5.dp)
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
                if (selectedOption != "Neo4j") {
                    Row(
                        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start
                    ) {
                        Spacer(Modifier.width(8.dp))
                        AddFolderButton(
                            onClick = {
                                val folder = chooseFolder()
                                if (folder != null) {
                                    selectedFolder = folder
                                }
                            })
                        Text(
                            text = resources.addFolder, fontWeight = FontWeight.Medium
                        )
                    }

                }
                if (selectedOption == "Neo4j") {
                    var uriFocused by remember { mutableStateOf(false) }
                    var usernameFocused by remember { mutableStateOf(false) }
                    var passwordFocused by remember { mutableStateOf(false) }

                    val uriFocusRequester = remember { FocusRequester() }
                    val usernameFocusRequester = remember { FocusRequester() }
                    val passwordFocusRequester = remember { FocusRequester() }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        OutlinedTextField(
                            value = uri,
                            onValueChange = { uri = it },
                            singleLine = true,
                            enabled = !isLoading,
                            isError = errorMessage.isNotEmpty(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .focusRequester(uriFocusRequester)
                                .onFocusChanged { uriFocused = it.isFocused },
                            shape = RoundedCornerShape(8.dp),
                            textStyle = MaterialTheme.typography.body2.copy(
                                fontSize = 14.sp, color = MaterialTheme.colors.onBackground
                            ),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                backgroundColor = MaterialTheme.colors.surface
                            ),
                            placeholder = {
                                Text(
                                    text = "Uri",
                                    color = if (uriFocused) MaterialTheme.colors.onPrimary.copy(alpha = 0.3f)
                                    else MaterialTheme.colors.onPrimary,
                                    fontSize = 13.sp
                                )
                            }
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        OutlinedTextField(
                            value = username,
                            onValueChange = { username = it },
                            singleLine = true,
                            enabled = !isLoading,
                            isError = errorMessage.isNotEmpty(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .focusRequester(usernameFocusRequester)
                                .onFocusChanged { usernameFocused = it.isFocused },
                            shape = RoundedCornerShape(8.dp),
                            textStyle = MaterialTheme.typography.body2.copy(
                                fontSize = 14.sp, color = MaterialTheme.colors.onBackground
                            ),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                backgroundColor = MaterialTheme.colors.surface
                            ),
                            placeholder = {
                                Text(
                                    text = "Username",
                                    color = if (usernameFocused) MaterialTheme.colors.onPrimary.copy(alpha = 0.3f)
                                    else MaterialTheme.colors.onPrimary,
                                    fontSize = 13.sp
                                )
                            }
                        )
                    }

                    var passwordVisible by remember { mutableStateOf(false) }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            singleLine = true,
                            enabled = !isLoading,
                            isError = errorMessage.isNotEmpty(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .focusRequester(passwordFocusRequester)
                                .onFocusChanged { passwordFocused = it.isFocused },
                            shape = RoundedCornerShape(8.dp),
                            textStyle = MaterialTheme.typography.body2.copy(
                                fontSize = 14.sp, color = MaterialTheme.colors.onBackground
                            ),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                backgroundColor = MaterialTheme.colors.surface
                            ),
                            placeholder = {
                                Text(
                                    text = "Password",
                                    color = if (passwordFocused) MaterialTheme.colors.onPrimary.copy(alpha = 0.3f)
                                    else MaterialTheme.colors.onPrimary,
                                    fontSize = 13.sp
                                )
                            },
                            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                            trailingIcon = {
                                val image: ImageVector = passwordViewer

                                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                    Icon(imageVector = image, contentDescription = "Toggle Password Visibility")
                                }
                            }
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start
                ) {

                    CloseButton(
                        onClick = onDismissRequest, text = resources.cancel
                    )
                    Spacer(modifier = Modifier.weight(1f))

                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp), strokeWidth = 2.dp
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


                                if (selectedOption == "Neo4j") {
                                    if (username.isBlank()) {
                                        errorMessage = "Enter a username"
                                        return@SaveButton
                                    }

                                    if (password.isBlank()) {
                                        errorMessage = "Enter a password"
                                        return@SaveButton
                                    }
                                }


                                isLoading = true
                                errorMessage = ""
                                CoroutineScope(Dispatchers.Main).launch {
                                    try {
                                        when (selectedOption) {
                                            "SQLite" -> {
                                                try {
                                                    val sanitizedName = name.replace(Regex("[^a-zA-Z0-9_-]"), "_")
                                                    val dbFolder =
                                                        if (selectedFolder != null) selectedFolder else "src/main/kotlin/org/graphApp/data/SQLite/StorageSQLite"
                                                    val dbFileName = "$dbFolder/$sanitizedName.db"
                                                    val sqliteConnection = SQLiteExposed(dbFileName)

                                                    val graphViewModel = graphViewModel as GraphViewModel<Any, Any>

                                                    val sqliteLogic = SQLiteMainLogic<Any, Any>(sqliteConnection)
                                                    val success =
                                                        sqliteLogic.saveToSQLiteDataBase(graphViewModel, sanitizedName)

                                                    if (success) {
                                                        onSaveSuccess(sanitizedName, selectedOption)
                                                        onDismissRequest()
                                                    } else {
                                                        errorMessage = "Failed to save graph to SQLite database"
                                                    }
                                                } catch (sqliteException: Exception) {
                                                    errorMessage =
                                                        "SQLite error: ${sqliteException.localizedMessage ?: "Database connection failed"}"
                                                    println("SQLite Exception details: ${sqliteException.printStackTrace()}")
                                                }
                                            }

                                            "JSON" -> {
                                                errorMessage = "Not implemented yet"
                                            }

                                            "Neo4j" -> {
                                                try {
                                                    val neo4jLoader = Neo4jDataBase(
                                                        mainViewModel = mainViewModel,
                                                        graphName = name,
                                                        username = username,
                                                        password = password,
                                                        uri = uri,
                                                        graphViewModel = mainViewModel.graphViewModel
                                                    )
                                                    neo4jLoader.storeGraph()
                                                    onDismissRequest()
                                                } catch (neo4jException: Exception) {
                                                    errorMessage = neo4jException.localizedMessage ?: "Unknown error"
                                                }
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
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    if (errorMessage.isNotEmpty()) {
                        ErrorPopup(
                            message = errorMessage, onDismiss = { errorMessage = "" })
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