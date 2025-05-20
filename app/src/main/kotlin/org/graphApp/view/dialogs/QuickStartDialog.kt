package org.graphApp.view.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import org.graphApp.model.LocalTextResources
import org.graphApp.view.components.CloseButton

@Composable
fun QuickGuideDialog(onDismissRequest: () -> Unit) {
    val resources = LocalTextResources.current

    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            shape = RoundedCornerShape(10.dp),
            color = MaterialTheme.colors.surface,
            modifier = Modifier.padding(vertical = 16.dp),
            elevation = 8.dp
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
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
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        text = "Create Vertex:",
                        color = MaterialTheme.colors.onBackground,
                        style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
                    )
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text =
                            """  
                                1) Right-click on empty area
                                2) Enter vertex value in the pop-up window
                        """
                                .trimIndent(),
                        color = MaterialTheme.colors.onBackground
                    )
                }

                Box(modifier = Modifier.padding(start = 10.dp).height(16.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Create Edge:",
                        color = MaterialTheme.colors.onBackground,
                        style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
                    )
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text =
                            """
                                1) Right-click first vertex (source)
                                2) Right-click second vertex (target):
                                        - Directed graph: edge from 1st → 2nd vertex
                                        - Undirected graph: bidirectional edge 
                        """
                                .trimIndent(),
                        color = MaterialTheme.colors.onBackground
                    )
                }

                Box(modifier = Modifier.padding(start = 10.dp).height(16.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Edit Elements:",
                        color = MaterialTheme.colors.onBackground,
                        style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
                    )
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text =
                            """
                                1) Double-click vertex ID or edge weight
                                2) Enter new value
                                """
                                .trimIndent(),
                        color = MaterialTheme.colors.onBackground
                    )
                }

                Box(modifier = Modifier.padding(start = 10.dp).height(16.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Delete Elements:",
                        color = MaterialTheme.colors.onBackground,
                        style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
                    )
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text =
                            """
                                1) Click on vertex/edge to select
                                2) Press Delete key
                            """
                                .trimIndent(),
                        color = MaterialTheme.colors.onBackground
                    )
                }

                Box(modifier = Modifier.padding(start = 10.dp).height(16.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Additional:",
                        color = MaterialTheme.colors.onBackground,
                        style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
                    )
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text =
                            """
                                1) New Graph: File → New Graph
                            """
                                .trimIndent(),
                        color = MaterialTheme.colors.onBackground
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    CloseButton(onClick = onDismissRequest, text = resources.close)
                }
            }
        }
    }
}
