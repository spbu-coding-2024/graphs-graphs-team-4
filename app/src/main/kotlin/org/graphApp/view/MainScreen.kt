package org.graphApp.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import org.graphApp.view.components.TopBarMenu
import org.graphApp.view.graph.GraphView
import org.graphApp.viewmodel.MainScreenViewModel
import org.graphApp.view.theme.GraphTheme
import org.graphApp.view.dialogs.AlgorithmsDialog
import org.graphApp.view.dialogs.NewGraphPanel
import org.graphApp.view.graph.RightClickPopupOnEmptyArea



const val START_ZOOM_POSITION = 250

@Composable
fun <E> MainScreen(viewModel: MainScreenViewModel<E>, onCloseRequest: () -> Unit) {
    var mainThemeDark by remember { mutableStateOf(false) }
    var sliderPositionLocal by remember { mutableStateOf(START_ZOOM_POSITION) }
    var showAlgorithmsPanel by remember { mutableStateOf(false) }
    var showNewGraphPanel by remember { mutableStateOf(false) }

    GraphTheme(darkTheme = mainThemeDark) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopBarMenu(
                onToggleAlgorithms = { showAlgorithmsPanel = !showAlgorithmsPanel },
                onToggleTheme = { mainThemeDark = !mainThemeDark },
                onShowNewGraph = { showNewGraphPanel = true },
                onCloseRequest = onCloseRequest,
                mainThemeDark = mainThemeDark,
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
                                onClose = { showNewGraphPanel = false }
                            )
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    GraphView(
                        viewModel.graphViewModel,
                        viewModel,
                    )

                    RightClickPopupOnEmptyArea(
                        viewModel = viewModel,
                        modifier = Modifier
                            .fillMaxSize()
                            .zIndex(2f)
                    )
                }
            }
        }
    }
}