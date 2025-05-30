package org.graphApp.view.dialogs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import org.graphApp.model.LocalTextResources
import org.graphApp.view.components.CloseButton

@Composable
fun AboutDialog(onDismissRequest: () -> Unit) {
    val resources = LocalTextResources.current

    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            shape = RoundedCornerShape(10.dp),
            color = MaterialTheme.colors.surface,
            modifier = Modifier,
            elevation = 8.dp
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = resources.aboutGravViz,
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.h6
                )
                Column (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = resources.version,
                        color = MaterialTheme.colors.onBackground,
                        style = MaterialTheme.typography.subtitle1.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.Start,
                    ) {
                        Text(
                            text = resources.aLightweight,
                            modifier = Modifier.padding(vertical = 8.dp),
                            color = MaterialTheme.colors.onBackground,
                            style = MaterialTheme.typography.body1.copy(
                                    fontWeight = FontWeight.Medium
                            )
                        )
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.Start,
                    ) {
                        Text(
                            text = resources.keyFeatures,
                            modifier = Modifier.padding(vertical = 8.dp),
                            color = MaterialTheme.colors.onBackground,
                            style = MaterialTheme.typography.body1.copy(
                                    fontWeight = FontWeight.Bold,
                                    textDecoration = TextDecoration.Underline
                            )
                        )
                    }
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.Start,
                    ) {
                        Text(
                            text = resources.createAndEdit
                                    .trimIndent(),
                            color = MaterialTheme.colors.onBackground
                        )
                    }
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
