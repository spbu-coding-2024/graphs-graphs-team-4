package org.graphApp.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.graphApp.view.components.TopBarMenu
import org.graphApp.view.graph.GraphView
import org.graphApp.viewmodel.MainScreenViewModel
import org.graphApp.view.theme.GraphTheme

@Composable
fun <V, E> MainScreen(viewModel: MainScreenViewModel<V, E>) {
    GraphTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            TopBarMenu()
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 17.dp, vertical = 17.dp),
                color = MaterialTheme.colors.surface
            ) {
                GraphView(viewModel.graphViewModel)
            }
        }
    }
}

