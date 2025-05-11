package org.graphApp.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.compose.ui.graphics.Color
import org.graphApp.model.AppLanguage
import org.graphApp.model.LocalTextResources
import org.graphApp.model.getResources
import org.graphApp.view.components.TopBarMenu
import org.graphApp.view.graph.GraphView
import org.graphApp.viewmodel.MainScreenViewModel
import org.graphApp.view.theme.GraphTheme
import org.graphApp.view.dialogs.AlgorithmsDialog
import org.graphApp.view.dialogs.NewGraphPanel
import org.graphApp.view.graph.RightClickPopupOnEmptyArea

@Composable
fun <E> MainScreen(viewModel: MainScreenViewModel<E>, onCloseRequest: () -> Unit) {
    var mainThemeDark by remember { mutableStateOf(false) }
    var showAlgorithmsPanel by remember { mutableStateOf(false) }
    var showNewGraphPanel by remember { mutableStateOf(false) }
    var startCliked by remember { mutableStateOf(false)}
    var currentLanguage by remember { mutableStateOf(AppLanguage.ENGLISH) }

    val resources = getResources(currentLanguage)
    CompositionLocalProvider(LocalTextResources provides resources) {
        GraphTheme(darkTheme = mainThemeDark) {
            Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.background)) {
                TopBarMenu(
                    onToggleAlgorithms = { showAlgorithmsPanel = !showAlgorithmsPanel },
                    onToggleTheme = { mainThemeDark = !mainThemeDark },
                    onShowNewGraph = { showNewGraphPanel = true },
                    onCloseRequest = onCloseRequest,
                    mainThemeDark = mainThemeDark,
                    onLanguageChange = { lang ->
                        currentLanguage = lang
                    },
                    currentLanguage = currentLanguage,
                    mainVm = viewModel
                )

                Row(modifier = Modifier.fillMaxSize()) {
                    AnimatedVisibility(
                        visible = showAlgorithmsPanel || showNewGraphPanel,
                        modifier = Modifier.background(MaterialTheme.colors.background)
                    ) {
                        Column(
                            modifier = Modifier
                                .width(300.dp)
                                .fillMaxHeight()
                                .padding(10.dp),
                            verticalArrangement = Arrangement.Top
                        ) {
                            if (showAlgorithmsPanel) {
                                AlgorithmsDialog(
                                    onLanguageChange = { lang ->
                                        currentLanguage = lang
                                    },
                                    currentLanguage = currentLanguage,
                                    modifier = Modifier.fillMaxWidth(),
                                    onClose = { showAlgorithmsPanel = false },
                                    onDismissRequest = { startCliked = false}
                                )
                            }

                            if (showAlgorithmsPanel && showNewGraphPanel) {
                                Spacer(modifier = Modifier.height(16.dp))
                            }

                            if (showNewGraphPanel) {
                                NewGraphPanel(
                                    onLanguageChange = { lang ->
                                        currentLanguage = lang
                                    },
                                    currentLanguage = currentLanguage,
                                    modifier = Modifier.fillMaxWidth(),
                                    onClose = { showNewGraphPanel = false },
                                    onCreateGraph = { showWeights, showDirected ->
                                        viewModel.showWeight = showWeights
                                        viewModel.showDirections = showDirected
                                        viewModel.createNewGraph(showWeights, showDirected)
                                        viewModel.graphViewModel.clear()
                                    }
                                )
                            }
                        }
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colors.background)
                    ) {
                        GraphView(
                            viewModel.graphViewModel,
                            viewModel,
                        )
                    }
                }
            }
        }
    }
}