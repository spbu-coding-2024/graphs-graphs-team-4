package org.graphApp.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.graphApp.view.dialogs.AboutDialog
import org.graphApp.view.dialogs.QuickGuideDialog
import org.graphApp.view.dialogs.SaveAsDialog
import org.graphApp.view.dialogs.ViewDialog
import org.graphApp.viewmodel.MainScreenViewModel

// убрать AlgorithmMenu во ViewMenu (???)
// ViewDialog: вытащить theme и zoom отдельно в view (???)
// добавить кнопку "Свернуть"
// добавить кнопку "Развернуть"

@Composable
fun <E> TopBarMenu(
    onToggleAlgorithms: () -> Unit,
    onShowNewGraph: () -> Unit,
    onCloseRequest: () -> Unit,
    onToggleTheme: () -> Unit,
    mainThemeDark: Boolean,
    mainVm: MainScreenViewModel<E>
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(37.dp),
        color = MaterialTheme.colors.surface
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                FileMenu(onNewGraph = onShowNewGraph)
                EditMenu()
                ViewMenu(mainVm = mainVm, mainThemeDark, onToggleTheme)
                AlgorithmsMenu(onClick = onToggleAlgorithms)
                SettingsMenu()
                HelpMenu()
            }

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "GraphViz",
                    color = MaterialTheme.colors.onPrimary,
                    fontWeight = FontWeight.ExtraBold
                )
            }

            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = onCloseRequest,
                    modifier = Modifier.size(20.dp)
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = null
                    )
                }
            }
        }
    }
}

@Composable
private fun FileMenu(onNewGraph: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    var openExpanded by remember { mutableStateOf(false) }
    var showSaveAsDialog by remember { mutableStateOf(false) }

    Box {
        TextButton(
            onClick = { expanded = true },
            colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colors.onPrimary)
        ) {
            Text("File", fontWeight = FontWeight.Light, color = MaterialTheme.colors.onSecondary)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(MaterialTheme.colors.surface)
        ) {
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onNewGraph()
                }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = FilePlus,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        "New graph...",
                        color = Color.Black,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            DropdownMenuItem(onClick = { openExpanded = !openExpanded }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Folder,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        "Open",
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.Medium
                    )

                    if (openExpanded) {
                        Icon(
                            imageVector = Arrow_drop_down,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    } else {
                        Icon(
                            imageVector = Arrow_right,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            if (openExpanded) {
                Box(modifier = Modifier.padding(start = 40.dp)) {
                    Divider()
                }

                DropdownMenuItem(
                    onClick = {},
                    enabled = false
                ) {
                    Box(modifier = Modifier.padding(start = 40.dp)) {
                        Text(
                            "Recent graphs:",
                            color = MaterialTheme.colors.onSurface,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                DropdownMenuItem(onClick = { /* TODO: open */ }) {
                    Box(modifier = Modifier.padding(start = 55.dp)) {
                        Text(
                            "ooops-1",
                            color = MaterialTheme.colors.onSurface,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                DropdownMenuItem(onClick = { /* TODO: open */ }) {
                    Box(modifier = Modifier.padding(start = 55.dp)) {
                        Text(
                            "ooops-2",
                            color = MaterialTheme.colors.onSurface,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                DropdownMenuItem(onClick = { /* TODO: open */ }) {
                    Box(modifier = Modifier.padding(start = 55.dp)) {
                        Text(
                            "ooops-3",
                            color = MaterialTheme.colors.onSurface,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }

                DropdownMenuItem(onClick = { /* TODO: Load */ }) {
                    Row(
                        modifier = Modifier.padding(start = 40.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = addFolder,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            "Load Graph",
                            color = MaterialTheme.colors.onSurface,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                Box(modifier = Modifier.padding(start = 40.dp)) {
                    Divider()
                }
            }

            DropdownMenuItem(onClick = { /* TODO: Save */ }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Save1,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        "Save",
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            DropdownMenuItem(
                onClick = {
                    expanded = false
                    showSaveAsDialog = true
                }
            ) {
                Box(modifier = Modifier.padding(start = 28.dp)) {
                    Text(
                        "Save as...",
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            DropdownMenuItem(onClick = { /* TODO: reset */ }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Reset8,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        "Reset",
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.Medium
                    )
                }
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
            colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colors.onPrimary)
        ) {
            Text("Edit", fontWeight = FontWeight.Light, color = MaterialTheme.colors.onSecondary)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(MaterialTheme.colors.surface)
        ) {
            DropdownMenuItem(onClick = { /* Undo */ }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = CornerUpLeft,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        "Undo",
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            DropdownMenuItem(onClick = { /* Redo */ }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = CornerUpRight,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        "Redo",
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
// sliderposition: Int = START_ZOOM_POSITION,
private fun <E> ViewMenu(
    mainVm: MainScreenViewModel<E>,
    mainThemeDark: Boolean,
    onToggleTheme: () -> Unit
    ) {
    var expanded by remember { mutableStateOf(false) }
    var showViewDialog by remember { mutableStateOf(false) }

    Box {
        TextButton(
            onClick = { expanded = true },
            colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colors.onPrimary)
        ) {
            Text("View", fontWeight = FontWeight.Light, color = MaterialTheme.colors.onSecondary)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(MaterialTheme.colors.surface)
        ) {
            DropdownMenuItem(onClick = { showViewDialog = true }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Settings,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        "Theme & Zoom...",
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            DropdownMenuItem(onClick = { /* TODO: reset */ }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Reset8,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        "Reset default",
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            if (showViewDialog) {
                ViewDialog(
                    mainVm = mainVm,
                    selectedTheme = if (mainThemeDark) "Light" else "Dark",
                    onThemeChange = { newTheme ->
                        onToggleTheme()
                    },
                    onDismissRequest = { showViewDialog = false }
                )
            }
        }
    }
}

@Composable
private fun AlgorithmsMenu(onClick: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        TextButton(
            onClick = { expanded = true },
            colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colors.onPrimary)
        ) {
            Text(
                "Algorithms",
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colors.onSecondary
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(MaterialTheme.colors.surface)
        ) {
            DropdownMenuItem(onClick = onClick) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Menu,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        "Algorithms...",
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            DropdownMenuItem(onClick = { /* TODO: reset */ }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Reset8,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        "Reset default",
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
fun SettingsMenu() {
    var expanded by remember { mutableStateOf(false) }
    var languageExpanded by remember { mutableStateOf(false) }

    Box {
        TextButton(
            onClick = { expanded = true },
            colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colors.onPrimary)
        ) {
            Text(
                "Settings",
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colors.onSecondary
            )

        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
                languageExpanded = false
            },
            modifier = Modifier.background(MaterialTheme.colors.surface)
        ) {
            DropdownMenuItem(onClick = { languageExpanded = !languageExpanded }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = MessageSquare,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        "Language",
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.Medium
                    )

                    if (languageExpanded) {
                        Icon(
                            imageVector = Arrow_drop_down,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    } else {
                        Icon(
                            imageVector = Arrow_right,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            if (languageExpanded) {
                Box(modifier = Modifier.padding(start = 40.dp)) { Divider() }

                DropdownMenuItem(onClick = {}) { SelectLanguage() }

                Box(modifier = Modifier.padding(start = 40.dp)) { Divider() }
            }

            DropdownMenuItem(onClick = { /* TODO: reset */ }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Reset8,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        "Reset default",
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
fun SelectLanguage(modifier: Modifier = Modifier) {
    val radioOptions = listOf("English", "Russian")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

    Column(modifier = modifier.selectableGroup()) {
        radioOptions.forEach { text ->
            Row(
                modifier = Modifier.fillMaxWidth()
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) },
                        role = Role.RadioButton
                    )
                    .padding(vertical = 4.dp, horizontal = 40.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = null,
                    colors =
                        RadioButtonDefaults.colors(selectedColor = MaterialTheme.colors.primary)
                )
                Text(
                    text = text,
                    modifier = Modifier.padding(start = 8.dp),
                    style = MaterialTheme.typography.body2
                )
            }
        }
    }
}

@Composable
fun HelpMenu() {
    var expanded by remember { mutableStateOf(false) }
    var showQuickGuide by remember { mutableStateOf(false) }
    var showAbout by remember { mutableStateOf(false) }

    Box {
        TextButton(
            onClick = { expanded = true },
            colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colors.onPrimary)
        ) {
            Text("Help", fontWeight = FontWeight.Light, color = MaterialTheme.colors.onSecondary)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(MaterialTheme.colors.surface)
        ) {
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    showAbout = true
                }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = InstructionManualsBookIcon,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        "About Program...",
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            DropdownMenuItem(
                onClick = {
                    expanded = false
                    showQuickGuide = true
                }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = HowToIcon,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        "Quick Start...",
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }

    if (showQuickGuide) {
        QuickGuideDialog(onDismissRequest = { showQuickGuide = false })
    }

    if (showAbout) {
        AboutDialog(onDismissRequest = { showAbout = false })
    }
}
