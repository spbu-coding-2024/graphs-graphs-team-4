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
import org.graphApp.viewmodel.graph.CircularPlacementStrategy

val sampleGraph: Graph<String, Long> = UndirectedGraph<String, Long>().apply {
    addVertex("A", "1")
    addVertex("B", "2")
    addVertex("C", "3")
    addVertex("D", "4")
    addVertex("E", "5")
    addVertex("F", "6")
    addVertex("G", "7")

    addEdge("1" to "A", "2" to "B", 1)
    addEdge("1" to "A", "3" to "C", 2)
    addEdge("1" to "A", "4" to "D", 3)
    addEdge("1" to "A", "5" to "E", 4)
    addEdge("1" to "A", "6" to "F", 5)
    addEdge("1" to "A", "7" to "G", 6)

    addVertex("H", "8")
    addVertex("I", "9")
    addVertex("J", "10")
    addVertex("K", "11")
    addVertex("L", "12")
    addVertex("M", "13")
    addVertex("N", "14")

    addEdge("8" to "H", "9" to "I", 7)
    addEdge("8" to "H", "10" to "J", 8)
    addEdge("8" to "H", "11" to "K", 9)
    addEdge("8" to "H", "12" to "L", 10)
    addEdge("8" to "H", "13" to "M", 11)
    addEdge("8" to "H", "14" to "N", 12)
    addEdge("1" to "A", "8" to "H", 0)
}

@Composable
@Preview
fun App() {
    MaterialTheme {
        MainScreen(MainScreenViewModel(sampleGraph, CircularPlacementStrategy()))
    }
}

//  надо будет добавить иконку приложения
//  перенести кнопки управления окном и название приложения в topbar
fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "GraphViz"
    ) {
        App()
    }
}
