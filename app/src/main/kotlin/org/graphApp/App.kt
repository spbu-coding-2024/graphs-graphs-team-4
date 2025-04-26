package org.graphApp

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.graphApp.model.graph.DirectGraph
import org.graphApp.model.graph.Graph
import org.graphApp.model.graph.UndirectedGraph
import org.graphApp.view.MainScreen
import org.graphApp.viewmodel.MainScreenViewModel

val sampleGraph: Graph<String, Long> = UndirectedGraph<String, Long>().apply {

    listOf("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N").forEach {
        addVertex(it)
    }

    addEdge("A", "B", 1)
    addEdge("A", "C", 2)
    addEdge("A", "D", 3)
    addEdge("A", "E", 4)
    addEdge("A", "F", 5)
    addEdge("A", "G", 6)

    addEdge("H", "I", 7)
    addEdge("H", "J", 8)
    addEdge("H", "K", 9)
    addEdge("H", "L", 10)
    addEdge("H", "M", 11)
    addEdge("H", "N", 12)
    addEdge("A", "H", 0)
}

@Composable
@Preview
fun App(onCloseRequest: () -> Unit) {
    MaterialTheme {
        MainScreen(MainScreenViewModel(sampleGraph), onCloseRequest = onCloseRequest)
    }
}

//  надо будет добавить иконку приложения
//  перенести кнопки управления окном в topbar
fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = ""
    ) {
        App(::exitApplication)
    }
}
