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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.graphApp.model.AppLanguage
import org.graphApp.model.LocalTextResources
import org.graphApp.view.dialogs.AboutDialog
import org.graphApp.view.dialogs.OpenDialog
import org.graphApp.view.dialogs.QuickGuideDialog
import org.graphApp.view.dialogs.SaveAsDialog
import org.graphApp.view.dialogs.ViewDialog
import org.graphApp.viewmodel.MainScreenViewModel
import org.graphApp.viewmodel.graph.GraphViewModel
import org.graphApp.viewmodel.*

@Composable
fun <E> TopBarMenu(
    onToggleAlgorithms: () -> Unit,
    onShowNewGraph: () -> Unit,
    onCloseRequest: () -> Unit,
    onToggleTheme: () -> Unit,
    onLanguageChange: (AppLanguage) -> Unit,
    currentLanguage: AppLanguage,
    mainThemeDark: Boolean,
    mainVm: MainScreenViewModel<E>,
) {
    val resources = LocalTextResources.current
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
                FileMenu(
                    mainVm = mainVm,
                    resources = resources,
                    onNewGraph = onShowNewGraph
                )
                MenuDivider()
                EditMenu(resources = resources)
                MenuDivider()
                ViewMenu(
                    currentLanguage = currentLanguage,
                    onLanguageChange = onLanguageChange,
                    resources = resources,
                    mainVm = mainVm,
                    mainThemeDark = mainThemeDark,
                    onToggleTheme = onToggleTheme
                )
                MenuDivider()
                AlgorithmsMenu(

                    resources = resources,
                    onClick = onToggleAlgorithms
                )
                MenuDivider()
                SettingsMenu(
                    resources = resources,
                    currentLanguage = currentLanguage,
                    onLanguageChange = onLanguageChange
                )
                MenuDivider()
                HelpMenu(resources = resources)
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
private fun MenuDivider() {
    Divider(
        color = MaterialTheme.colors.onPrimary.copy(alpha = 0.18f),
        modifier = Modifier
            .fillMaxHeight()
            .padding(vertical = 10.dp)
            .offset(y = 2.dp)
            .width(1.dp)
    )
}

@Composable
private fun <E> FileMenu(
    mainVm : MainScreenViewModel<E>,
    resources: org.graphApp.model.TextResources,
    onNewGraph: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var openExpanded by remember { mutableStateOf(false) }
    var showSaveAsDialog by remember { mutableStateOf(false) }
    var showOpenAsDialog by remember { mutableStateOf(false) }

    Box {
        TextButton(
            onClick = { expanded = true },
            colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colors.onPrimary)
        ) {
            Text(resources.fileMenu, fontWeight = FontWeight.Light, color = MaterialTheme.colors.onSecondary)
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
                        resources.newGraph,
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.ExtraLight
                    )
                }
            }

            DropdownMenuItem(
                onClick = { openExpanded = !openExpanded }
            ) {
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
                        resources.open,
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.ExtraLight
                    )
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
                    Box(
                        modifier = Modifier.padding(start = 40.dp)
                    ) {
                        Text(
                            resources.recentGraphs,
                            color = MaterialTheme.colors.onSurface,
                            fontWeight = FontWeight.ExtraLight
                        )
                    }
                }
                DropdownMenuItem(
                    onClick = { /* TODO: open */ }
                ) {
                    Box(
                        modifier = Modifier.padding(start = 55.dp)
                    ) {
                        Text(
                            "ooops-1",
                            color = MaterialTheme.colors.onSurface,
                            fontWeight = FontWeight.ExtraLight
                        )
                    }
                }
                DropdownMenuItem(
                    onClick = { /* TODO: open */ }
                ) {
                    Box(modifier = Modifier.padding(start = 55.dp)) {
                        Text(
                            "ooops-2",
                            color = MaterialTheme.colors.onSurface,
                            fontWeight = FontWeight.ExtraLight
                        )
                    }
                }

                DropdownMenuItem(onClick = { /* TODO: open */ }) {
                    Box(
                        modifier = Modifier.padding(start = 55.dp)
                    ) {
                        Text(
                            "ooops-3",
                            color = MaterialTheme.colors.onSurface,
                            fontWeight = FontWeight.ExtraLight
                        )
                    }
                }

                DropdownMenuItem(
                    onClick = { showOpenAsDialog = !showOpenAsDialog}
                ) {
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
                            resources.loadGraph,
                            color = MaterialTheme.colors.onSurface,
                            fontWeight = FontWeight.ExtraLight
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
                        resources.save,
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.ExtraLight
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
                        resources.saveAs,
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.ExtraLight
                    )
                }
            }

            DropdownMenuItem(onClick = { /* TODO: reset */ }) {
                Box(modifier = Modifier.padding(start = 28.dp)) {
                    Text(
                        resources.reset,
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.ExtraLight
                    )
                }
            }
        }
    }

    if (showSaveAsDialog) {
        SaveAsDialog(
            graphViewModel = mainVm.graphViewModel as GraphViewModel<Any, Any>,
            onDismissRequest = { showSaveAsDialog = false }
        )
    }
    if (showOpenAsDialog) {
        OpenDialog(
            graphViewModel = mainVm.graphViewModel,
            onDismissRequest = { showOpenAsDialog = false },
            onLoadSuccess = { loadedGraphViewModel, name, type ->
                mainVm.loadGraphFromDatabase(loadedGraphViewModel, name)
            }
        )
    }
}

@Composable
private fun EditMenu(resources: org.graphApp.model.TextResources) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        TextButton(
            onClick = { expanded = true },
            colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colors.onPrimary)
        ) {
            Text(resources.editMenu, fontWeight = FontWeight.Light, color = MaterialTheme.colors.onSecondary)
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
                        resources.undo,
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.ExtraLight
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
                        resources.redo,
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.ExtraLight
                    )
                }
            }
        }
    }
}

@Composable
private fun <E> ViewMenu(
    currentLanguage: AppLanguage,
    onLanguageChange : (AppLanguage) -> Unit,
    resources: org.graphApp.model.TextResources,
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
            Text(resources.viewMenu, fontWeight = FontWeight.Light, color = MaterialTheme.colors.onSecondary)
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.background(MaterialTheme.colors.surface)
                .wrapContentSize().padding(end = 8.dp)
        ) {
            DropdownMenuItem(onClick = { showViewDialog = true }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        resources.settings,
                        fontWeight = FontWeight.ExtraLight
                    )
                }
            }
            Column (
                modifier = Modifier.background(MaterialTheme.colors.surface),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.Start

            ) {
                if (mainVm.isWeightedGraph) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Checkbox(
                            checked = mainVm.showEdgesWeight,
                            onCheckedChange = {
                                mainVm.showEdgesWeight = it
                            },
                            colors = CheckboxDefaults.colors(
                                checkedColor = MaterialTheme.colors.primaryVariant,
                                uncheckedColor = MaterialTheme.colors.onPrimary
                            ),
                        )

                        Text(
                            resources.edgeWeights,
                            fontWeight = FontWeight.ExtraLight
                        )
                    }
                }


                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Checkbox(
                        checked = mainVm.showVertexLabels,
                        onCheckedChange = {
                            mainVm.showVertexLabels = it
                        },
                        colors = CheckboxDefaults.colors(
                            checkedColor = MaterialTheme.colors.primaryVariant,
                            uncheckedColor = MaterialTheme.colors.onPrimary
                        ),
                    )

                    Text(
                        text = resources.vertexLabels,
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.ExtraLight
                    )
                }
            }

            if (showViewDialog) {
                ViewDialog(
                    selectedTheme = if (mainThemeDark) resources.themeLight else resources.themeDark,
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
private fun AlgorithmsMenu(resources: org.graphApp.model.TextResources, onClick: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        TextButton(
            onClick = { expanded = true },
            colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colors.onPrimary)
        ) {
            Text(
                resources.algorithmsMenu,
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
                    Text(
                        resources.algorithmsMenu,
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.ExtraLight
                    )
                }
            }
        }
    }
}

@Composable
fun SettingsMenu(
    resources: org.graphApp.model.TextResources,
    currentLanguage: AppLanguage,
    onLanguageChange: (AppLanguage) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var languageExpanded by remember { mutableStateOf(false) }

    Box {
        TextButton(
            onClick = { expanded = true },
            colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colors.onPrimary)
        ) {
            Text(
                resources.settingsMenu,
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
                        resources.language,
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.ExtraLight
                    )
                }
            }

            if (languageExpanded) {
                Box(modifier = Modifier.padding(start = 40.dp)) { Divider() }

                DropdownMenuItem(onClick = {}) {
                    SelectLanguage(
                        resources = resources,
                        currentLanguage = currentLanguage,
                        onLanguageChange = onLanguageChange
                    )
                }

                Box(modifier = Modifier.padding(start = 40.dp)) { Divider() }
            }

            DropdownMenuItem(onClick = { expanded = false }) {
                Box(modifier = Modifier.padding(start = 28.dp)) {
                    Text(
                        resources.resetDefault,
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.ExtraLight
                    )
                }
            }
        }
    }
}

@Composable
fun SelectLanguage(
    resources: org.graphApp.model.TextResources,
    currentLanguage: AppLanguage,
    onLanguageChange: (AppLanguage) -> Unit,
    modifier: Modifier = Modifier
) {
    val radioOptions = listOf(
        Pair(AppLanguage.ENGLISH, resources.english),
        Pair(AppLanguage.RUSSIAN, resources.russian),
        Pair(AppLanguage.CHINESE, resources.chinese)
    )

    Column(modifier = modifier.selectableGroup()) {
        radioOptions.forEach { (language, text) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (language == currentLanguage),
                        onClick = { onLanguageChange(language) },
                        role = Role.RadioButton
                    )
                    .padding(vertical = 4.dp, horizontal = 40.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (language == currentLanguage),
                    onClick = null,
                    colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colors.primaryVariant)
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
fun HelpMenu(resources: org.graphApp.model.TextResources) {
    var expanded by remember { mutableStateOf(false) }
    var showQuickGuide by remember { mutableStateOf(false) }
    var showAbout by remember { mutableStateOf(false) }

    Box {
        TextButton(
            onClick = { expanded = true },
            colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colors.onPrimary)
        ) {
            Text(resources.helpMenu, fontWeight = FontWeight.Light, color = MaterialTheme.colors.onSecondary)
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
                Text(
                    resources.aboutProgram,
                    color = MaterialTheme.colors.onSurface,
                    fontWeight = FontWeight.ExtraLight
                )
            }

            DropdownMenuItem(
                onClick = {
                    expanded = false
                    showQuickGuide = true
                }
            ) {
                Text(
                    resources.quickStart,
                    color = MaterialTheme.colors.onSurface,
                    fontWeight = FontWeight.ExtraLight
                )
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
