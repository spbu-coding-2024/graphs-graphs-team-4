package org.graphApp

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.graphApp.model.graph.Graph
import org.graphApp.model.graph.UndirectedGraph
import org.graphApp.view.MainScreen
import org.graphApp.viewmodel.MainScreenViewModel

val currentGraph: Graph<String, Long> = UndirectedGraph<String, Long>()


@Composable
@Preview
fun App(onCloseRequest: () -> Unit) {
    MaterialTheme {
        MainScreen(MainScreenViewModel(currentGraph), onCloseRequest = onCloseRequest)
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