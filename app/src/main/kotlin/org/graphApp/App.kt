package org.graphApp

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.graphApp.model.graph.Graph
import org.graphApp.model.graph.UndirectedGraph
import org.graphApp.view.MainScreen
import org.graphApp.viewmodel.ErrorViewModel
import org.graphApp.viewmodel.MainScreenViewModel
import org.graphApp.viewmodel.graph.GraphViewModel
val currentGraph: Graph<String, Long> = UndirectedGraph()


@Composable
@Preview
fun App(onCloseRequest: () -> Unit) {
    MaterialTheme {
        val viewModel = MainScreenViewModel(currentGraph)
        MainScreen(
            viewModel = viewModel,
            onCloseRequest = onCloseRequest,
            viewModelError = ErrorViewModel()
        )
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = ""
    ) {
        App(::exitApplication)
    }
}