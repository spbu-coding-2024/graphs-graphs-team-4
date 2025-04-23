package org.graphApp.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.graphApp.view.dialogs.AboutDialog
import org.graphApp.view.dialogs.DocumentationDialog
import org.graphApp.view.dialogs.SaveAsDialog
import org.graphApp.view.dialogs.ViewDialog

@Composable
fun TopBarMenu(
    onToggleAlgorithms: () -> Unit,
    onShowNewGraph: () -> Unit
) {
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
            FileMenu(onNewGraph = onShowNewGraph)
            EditMenu()
            ViewMenu()
            AlgorithmsMenu(onClick = onToggleAlgorithms)
            SettingsMenu()
            HelpMenu()
        }
    }
}

@Composable
private fun FileMenu(onNewGraph: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var showSaveAsDialog by remember { mutableStateOf(false) }

    Box {
        TextButton(
            onClick = { expanded = true },
            colors = ButtonDefaults.textButtonColors(
                contentColor = MaterialTheme.colors.onPrimary
            )
        ) {
            Text("File")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(Color.White)
        ) {
            DropdownMenuItem(onClick = {
                expanded = false
                onNewGraph()
            }) {
                Text("New Graph", color = Color.Black)
            }
            DropdownMenuItem(onClick = { /* TODO: Open */ }) {
                Text("Open", color = Color.Black)
            }
            DropdownMenuItem(onClick = { /* TODO: Save */ }) {
                Text("Save", color = Color.Black)
            }
            DropdownMenuItem(onClick = {
                expanded = false
                showSaveAsDialog = true
            }) {
                Text("Save as", color = Color.Black)
            }
            DropdownMenuItem(onClick = { /* TODO: Delete */ }) {
                Text("Delete", color = Color.Black)
            }
            DropdownMenuItem(onClick = { /* TODO: Example */ }) {
                Text("Example", color = Color.Black)
            }
        }
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
            colors = ButtonDefaults.textButtonColors(
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
            DropdownMenuItem(onClick = { /* Undo */ }) {
                Text("Undo", color = Color.Black)
            }
            DropdownMenuItem(onClick = { /* Redo */ }) {
                Text("Redo", color = Color.Black)
            }
        }
    }
}

@Composable
private fun ViewMenu() {
    var showViewDialog by remember { mutableStateOf(false) }

    TextButton(
        onClick = { showViewDialog = true },
        colors = ButtonDefaults.textButtonColors(
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
private fun AlgorithmsMenu(onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        colors = ButtonDefaults.textButtonColors(
            contentColor = MaterialTheme.colors.onPrimary
        )
    ) {
        Text("Algorithms")
    }
}

@Composable
fun SettingsMenu() {
    var expanded by remember { mutableStateOf(false) }

    Box {
        TextButton(
            onClick = { expanded = true },
            colors = ButtonDefaults.textButtonColors(
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
            DropdownMenuItem(onClick = { expanded = false }) {
                Text("Language", color = Color.Black)
            }
            DropdownMenuItem(onClick = { expanded = false }) {
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
            colors = ButtonDefaults.textButtonColors(
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
        DocumentationDialog(onDismissRequest = { showDocumentation = false })
    }

    if (showAbout) {
        AboutDialog(onDismissRequest = { showAbout = false })
    }
}
