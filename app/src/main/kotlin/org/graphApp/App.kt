package org.graphApp

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import org.graphApp.model.graph.Graph
import org.graphApp.model.graph.UndirectedGraph
import org.graphApp.view.MainScreen
import org.graphApp.viewmodel.MainScreenViewModel

val currentGraph: Graph<String, Long> = UndirectedGraph<String, Long>()


@Composable
@Preview
fun App(
    state: WindowState,
    onCloseRequest: () -> Unit,
    onMinimize: () -> Unit,
    onToggleFullscreen: () -> Unit
) {
    MaterialTheme {
        MainScreen(
            viewModel = MainScreenViewModel(currentGraph),
            state = state,
            onCloseRequest = onCloseRequest,
            onMinimize = onMinimize,
            onToggleFullscreen = onToggleFullscreen
        )
    }
}

// надо будет добавить иконку приложения
// никак перемещать окно
fun main() = application {
    val state = rememberWindowState()

    Window(
        onCloseRequest = ::exitApplication,
        undecorated = true,
        state = state
    ) {
        App(
            state = state,
            onCloseRequest = ::exitApplication,
            onMinimize = { state.isMinimized = true },
            onToggleFullscreen = {
                state.placement =
                    if (state.placement == WindowPlacement.Fullscreen) {
                        WindowPlacement.Floating
                    }
                    else {
                        WindowPlacement.Fullscreen
                    }
            }
        )
    }
}
