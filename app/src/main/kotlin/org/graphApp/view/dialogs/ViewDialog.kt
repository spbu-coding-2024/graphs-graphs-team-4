package org.graphApp.view.dialogs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.graphApp.model.LocalTextResources
import org.graphApp.view.components.*


@Composable
fun ViewDialog(
    selectedTheme: String,
    onThemeChange: (String) -> Unit,
    onDismissRequest: () -> Unit
) {
    val resources = LocalTextResources.current
    val listOfThemes = listOf(resources.light, resources.dark)
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            color = MaterialTheme.colors.surface,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.width(350.dp).height(310.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(4.dp)
            ) {
                Text(
                    text = resources.viewDialog,
                    style = MaterialTheme.typography.h6,
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.padding(top = 16.dp)
                )
                Divider(
                    modifier = Modifier.padding(
                        start = 30.dp,
                        end = 30.dp,
                        top = 10.dp,
                        bottom = 10.dp
                    ),
                    color = MaterialTheme.colors.onPrimary.copy(alpha = 0.6f),
                    thickness = 1.dp
                )
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = chooseYourTheme,
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp)
                            .offset(30.dp),
                        tint = MaterialTheme.colors.onSecondary
                    )
                    Spacer(Modifier.width(5.dp))
                    Text(
                        text = resources.theme,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = MaterialTheme.colors.onPrimary,
                        modifier = Modifier.padding(start = 16.dp).offset(14.dp)
                    )
                }

                listOfThemes.forEach { text ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.clip(RoundedCornerShape(30.dp))
                                .selectable(
                                    role = Role.RadioButton,
                                    selected = text == selectedTheme,
                                    onClick = { if (text != selectedTheme) onThemeChange(text) }
                                )
                                .padding(horizontal = 30.dp, vertical = 12.dp)
                                .wrapContentSize(),
                            verticalAlignment = Alignment.CenterVertically,

                            ) {
                            RadioButton(
                                selected = text == selectedTheme,
                                onClick = null,
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = MaterialTheme.colors.primaryVariant,
                                    unselectedColor = MaterialTheme.colors.onPrimary
                                )
                            )
                            Text(
                                text = text,
                                style = MaterialTheme.typography.body1,
                                color = MaterialTheme.colors.onSecondary,
                                fontWeight = FontWeight.Light,
                                fontSize = 16.sp,
                                modifier = Modifier.padding(start = 20.dp)
                            )
                        }
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,

                    ) {

                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier.fillMaxWidth(0.5f)

                    ) {
                        CloseButton(onClick = onDismissRequest, text = resources.cancel)
                    }
                    Column(
                        horizontalAlignment = Alignment.End,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OkButton(onClick = {
                            onDismissRequest()
                        }, text = resources.ok)
                    }
                }
            }
        }
    }
}

