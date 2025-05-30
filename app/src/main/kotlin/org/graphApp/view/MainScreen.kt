package org.graphApp.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import org.graphApp.model.AppLanguage
import org.graphApp.model.LocalTextResources
import org.graphApp.model.getResources
import org.graphApp.view.algorithms.AlgorithmsView
import org.graphApp.view.components.TopBarMenu
import org.graphApp.view.graph.GraphView
import org.graphApp.viewmodel.MainScreenViewModel
import org.graphApp.view.theme.GraphTheme
import org.graphApp.view.dialogs.AlgorithmsDialog
import org.graphApp.view.dialogs.NewGraphPanel
import kotlinx.coroutines.delay
import org.graphApp.viewmodel.ErrorViewModel
import kotlin.concurrent.timer


@Composable
fun <E> MainScreen(viewModel: MainScreenViewModel<E>, onCloseRequest: () -> Unit, viewModelError : ErrorViewModel) {
    var mainThemeDark by remember { mutableStateOf(false) }
    val errorId by viewModelError.errorId
    val errorMessage by viewModelError.errorMessage

    var showAlgorithmsPanel by remember { mutableStateOf(false) }
    var showNewGraphPanel by remember { mutableStateOf(false) }
    val currentGraphViewModel by remember { derivedStateOf { viewModel.graphViewModel } }
    var currentLanguage by remember { mutableStateOf(AppLanguage.ENGLISH) }
    val resources = getResources(currentLanguage)
    val algoVM = AlgorithmsView(viewModel = viewModel.graphViewModel, errorViewModel = viewModelError)
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
                        AnimatedVisibility(visible = showAlgorithmsPanel || showNewGraphPanel) {
                            Column(
                                modifier = Modifier
                                    .width(350.dp)
                                    .fillMaxHeight()
                                    .padding(10.dp),
                                verticalArrangement = Arrangement.Top
                            ) {
                                AnimatedVisibility(visible = showAlgorithmsPanel) {
                                    AlgorithmsDialog(
                                        algoVM = algoVM,
                                        onClose = { showAlgorithmsPanel = false },
                                        onDismissRequest = { false },
                                        viewModel = viewModel
                                    )
                                }
                                AnimatedVisibility(visible = showAlgorithmsPanel && showNewGraphPanel) {
                                    Spacer(modifier = Modifier.height(height = 16.dp))
                                }

                                AnimatedVisibility(visible = showNewGraphPanel) {
                                    NewGraphPanel(
                                        onClose = { showNewGraphPanel = false },
                                        vm = viewModel
                                    )
                                }
                            }
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = MaterialTheme.colors.background)
                        ) {
                            GraphView(
                                currentGraphViewModel,
                                mainScreenViewModel = viewModel,
                            )
                        }


                }

                AnimatedVisibility(
                    visible = showNewGraphPanel,
                    modifier = Modifier
                        .padding(top = 60.dp, end = 16.dp)
                        .zIndex(1f)
                ) {
                    NewGraphPanel(
                        onClose = { showNewGraphPanel = false },
                        vm = viewModel
                    )
                }


            }
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Timer(
                    errorMessage,
                    errorId
                )
            }


        }
    }
}

@Composable
fun WarningPanel(
    message : String,
    ) {
    Card(
        modifier = Modifier,

    ) {
        Text(
            message,
            color = MaterialTheme.colors.onBackground,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun Timer(
    errorMessage : String,
    errorId : Int
) {
    var visible by remember { mutableStateOf(true) }

    LaunchedEffect(errorId) {
        visible = true
        delay(4000L)
        visible = false
    }
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            WarningPanel(message = errorMessage)
        }
    }

}
