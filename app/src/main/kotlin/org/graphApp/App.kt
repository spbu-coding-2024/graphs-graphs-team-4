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
import org.graphApp.viewmodel.graph.CircularPlacementStrategy

val sampleGraph: Graph<String, Long> = DirectGraph<String, Long>().apply {
    val vertexA = addVertex("A", "1")
    val vertexB = addVertex("B", "2")
    val vertexC = addVertex("C", "3")
    val vertexD = addVertex("D", "4")
    val vertexE = addVertex("E", "5")
    val vertexF = addVertex("F", "6")
    val vertexG = addVertex("G", "7")

    addEdge(vertexA, vertexB, 1L)
    addEdge(vertexA, vertexC, 2L)
    addEdge(vertexA, vertexD, 3L)
    addEdge(vertexA, vertexE, 4L)
    addEdge(vertexA, vertexF, 5L)
    addEdge(vertexA, vertexG, 6L)

    val vertexH = addVertex("H", "8")
    val vertexI = addVertex("I", "9")
    val vertexJ = addVertex("J", "10")
    val vertexK = addVertex("K", "11")
    val vertexL = addVertex("L", "12")
    val vertexM = addVertex("M", "13")
    val vertexN = addVertex("N", "14")
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
