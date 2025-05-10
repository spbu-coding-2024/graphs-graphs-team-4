package org.graphApp.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.zIndex
import org.graphApp.view.components.TopBarMenu
import org.graphApp.view.graph.GraphView
import org.graphApp.viewmodel.MainScreenViewModel
import org.graphApp.view.theme.GraphTheme
import org.graphApp.view.dialogs.AlgorithmsDialog
import org.graphApp.view.dialogs.NewGraphPanel
import org.graphApp.view.graph.RightClickPopupOnEmptyArea

@Composable
fun <E> MainScreen(
    viewModel: MainScreenViewModel<E>,
    state: WindowState,
    onCloseRequest: () -> Unit,
    onMinimize: () -> Unit,
    onToggleFullscreen: () -> Unit
) {
    var mainThemeDark by remember { mutableStateOf(false) }
    var showAlgorithmsPanel by remember { mutableStateOf(false) }
    var showNewGraphPanel by remember { mutableStateOf(false) }

    GraphTheme(darkTheme = mainThemeDark) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopBarMenu(
                onToggleAlgorithms = { showAlgorithmsPanel = !showAlgorithmsPanel },
                onToggleTheme = { mainThemeDark = !mainThemeDark },
                onShowNewGraph = { showNewGraphPanel = true },
                onCloseRequest = onCloseRequest,
                onMinimize = onMinimize,
                onToggleFullscreen = onToggleFullscreen,
                mainThemeDark = mainThemeDark,
                mainVm = viewModel,
                windowState = state
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
                                modifier = Modifier.fillMaxWidth(),
                                onClose = { showAlgorithmsPanel = false }
                            )
                        }

                        if (showAlgorithmsPanel && showNewGraphPanel) {
                            Spacer(modifier = Modifier.height(16.dp))
                        }

                        if (showNewGraphPanel) {
                            NewGraphPanel(
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