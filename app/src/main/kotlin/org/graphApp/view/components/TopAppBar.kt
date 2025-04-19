package org.graphApp.view.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.graphApp.view.dialogs.AboutDialog
import org.graphApp.view.dialogs.AlgorithmsDialog
import org.graphApp.view.dialogs.DocumentationDialog
import org.graphApp.view.dialogs.NewGraphDialog
import org.graphApp.view.dialogs.SaveAsDialog
import org.graphApp.view.dialogs.ViewDialog
import androidx.compose.foundation.background
import androidx.compose.material.ButtonDefaults.textButtonColors

@Composable
fun TopBarMenu() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(37.dp),
        color = MaterialTheme.colors.primary
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            FileMenu()
            EditMenu()
            ViewMenu()
            AlgorithmsMenu()
            SettingsMenu()
            HelpMenu()
        }
    }
}

@Composable
private fun FileMenu() {
    var expanded by remember { mutableStateOf(false) }
    var showNewGraphDialog by remember { mutableStateOf(false) }
    var showSaveAsDialog by remember { mutableStateOf(false) }

    Box {
        TextButton(
            onClick = { expanded = true },
            colors = textButtonColors(
                contentColor = MaterialTheme.colors.onPrimary
            )
        ) {
            Text("File")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color.White)  // цвет меню
        ) {
            DropdownMenuItem(onClick = {
                expanded = false
                showNewGraphDialog = true
            }) { Text("New Graph", color = Color.Black) }
            DropdownMenuItem(onClick = { }) {
                Text("Open", color = Color.Black)
            }
            DropdownMenuItem(onClick = { /* Handle save! */ }) { Text("Save", color = Color.Black) }
            DropdownMenuItem(onClick = {
                expanded = false
                showSaveAsDialog = true
            }) { Text("Save as", color = Color.Black) }
            DropdownMenuItem(onClick = { /* Handle delete! */ }) { Text("Delete", color = Color.Black) }
            DropdownMenuItem(onClick = { /* Handle example! */ }) { Text("Example", color = Color.Black) }
        }
    }

    if (showNewGraphDialog) {
        NewGraphDialog(onDismissRequest = { showNewGraphDialog = false })
    }

    if (showSaveAsDialog) {
        SaveAsDialog(onDismissRequest = { showSaveAsDialog = false })
    }
}

@Composable
private fun EditMenu() {
    var expanded by remember { mutableStateOf(false) }

    Box {
        TextButton(
            onClick = { expanded = true },
            colors = textButtonColors(
                contentColor = MaterialTheme.colors.onPrimary
            )
        ) {
            Text("Edit")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color.White)
        ) {
            DropdownMenuItem(onClick = { /* Handle Undo! */ }) { Text("Undo", color = Color.Black) }
            DropdownMenuItem(onClick = { /* Handle Redo! */ }) { Text("Redo", color = Color.Black) }
        }
    }
}

@Composable
private fun ViewMenu() {
    var showViewDialog by remember { mutableStateOf(false) }

    TextButton(
        onClick = { showViewDialog = true },
        colors = textButtonColors(
            contentColor = MaterialTheme.colors.onPrimary
        )
    ) {
        Text("View")
    }

    if (showViewDialog) {
        ViewDialog(onDismissRequest = { showViewDialog = false })
    }
}

@Composable
private fun AlgorithmsMenu() {
    var showAlgorithmsDialog by remember { mutableStateOf(false) }

    TextButton(
        onClick = { showAlgorithmsDialog = true },
        colors = textButtonColors(
            contentColor = MaterialTheme.colors.onPrimary
        )
    ) {
        Text("Algorithms")
    }

    if (showAlgorithmsDialog) {
        AlgorithmsDialog(onDismissRequest = { showAlgorithmsDialog = false })
    }
}

//добавить меню выбора языка с radiobutton
@Composable
fun SettingsMenu() {
    var expanded by remember { mutableStateOf(false) }

    Box {
        TextButton(
            onClick = { expanded = true },
            colors = textButtonColors(
                contentColor = MaterialTheme.colors.onPrimary
            )
        ) {
            Text("Settings")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color.White)
        ) {
            DropdownMenuItem(onClick = {expanded = false}) {
                Text("Language", color = Color.Black)
            }
            DropdownMenuItem(onClick = {expanded = false}) {
                Text("Reset default", color = Color.Black)
            }
        }
    }
}

@Composable
fun HelpMenu() {
    var expanded by remember { mutableStateOf(false) }
    var showDocumentation by remember { mutableStateOf(false) }
    var showAbout by remember { mutableStateOf(false) }

    Box {
        TextButton(
            onClick = { expanded = true },
            colors = textButtonColors(
                contentColor = MaterialTheme.colors.onPrimary
            )
        ) {
            Text("Help")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color.White)
        ) {
            DropdownMenuItem(onClick = {
                expanded = false
                showDocumentation = true
            }) {
                Text("Documentation", color = Color.Black)
            }
            DropdownMenuItem(onClick = {
                expanded = false
                showAbout = true
            }) {
                Text("About Program", color = Color.Black)
            }
        }
    }

    if (showDocumentation) {
        DocumentationDialog(onDismissRequest = {showDocumentation = false}
        )
    }

    if (showAbout) {
        AboutDialog(onDismissRequest = {showAbout = false})
    }
}
