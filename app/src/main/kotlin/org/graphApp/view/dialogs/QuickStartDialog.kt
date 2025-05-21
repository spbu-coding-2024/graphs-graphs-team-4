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
                    text = resources.guide,
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.h5.copy(fontWeight = FontWeight.Bold)
                )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(
                        text = resources.createVertex,
                        color = MaterialTheme.colors.onBackground,
                        style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
                    )
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = resources.rightClick1
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
                        text = resources.createEdge,
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
                           resources.rightClick2
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
                        text = resources.editElements,
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
                            resources.doubleClick
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
                        text = resources.deleteElements,
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
                            resources.clickOnVertex
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
                        text = resources.additional,
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
                            resources.newGraphFile
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
