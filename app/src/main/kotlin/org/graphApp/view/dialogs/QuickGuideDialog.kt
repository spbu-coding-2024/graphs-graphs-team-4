package org.graphApp.view.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.graphApp.view.components.CloseButton

// переписать текст
@Composable
fun QuickGuideDialog(onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            shape = RoundedCornerShape(10.dp),
            color = MaterialTheme.colors.surface,
            modifier = Modifier.padding(vertical = 16.dp),
            elevation = 8.dp
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "Graph Creation Guide",
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold)
                )

                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Create Vertex:",
                        color = MaterialTheme.colors.onBackground,
                        style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text =
                            """  
                                - Right-click on empty area
                                - Enter vertex value in the pop-up window
                        """
                                .trimIndent(),
                        color = MaterialTheme.colors.onBackground
                    )
                    Text(
                        text = "Create Edge:",
                        color = MaterialTheme.colors.onBackground,
                        style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text =
                            """
                                1. Right-click first vertex (source)
                                2. Right-click second vertex (target)
                                    - Directed graph: edge from 1st → 2nd vertex
                                    - Undirected graph: bidirectional edge 
                        """
                                .trimIndent(),
                        color = MaterialTheme.colors.onBackground
                    )
                    Text(
                        text = "Edit Elements:",
                        color = MaterialTheme.colors.onBackground,
                        style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text =
                            """
                                - Double-click vertex ID or edge weight
                                - Enter new value
                                """
                                .trimIndent(),
                        color = MaterialTheme.colors.onBackground
                    )
                    Text(
                        text = "Delete Elements:",
                        color = MaterialTheme.colors.onBackground,
                        style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text =
                            """
                                1. Click on vertex/edge to select
                                2. Press Delete key
                            """
                                .trimIndent(),
                        color = MaterialTheme.colors.onBackground
                    )
                    Text(
                        text = "Additional:",
                        color = MaterialTheme.colors.onBackground,
                        style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        text =
                            """
                                - New Graph: File → New Graph
                            """
                                .trimIndent(),
                        color = MaterialTheme.colors.onBackground
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    CloseButton(onClick = onDismissRequest, text = "Close")
                }
            }
        }
    }
}
