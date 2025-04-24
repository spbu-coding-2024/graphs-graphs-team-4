package org.graphApp.view.dialogs

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// перенести отсюда ввод имени во ViewDialog
// ввод названия графа вынести в SaveAsDialog
// тут только display options должны быть или, мб, что-нибудь еще (???)
@Composable
fun NewGraphPanel(
    modifier: Modifier = Modifier,
    onClose: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var showWeights by remember { mutableStateOf(true) }
    var showId by remember { mutableStateOf(false) }
    var showDirected by remember { mutableStateOf(false) }

    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.colors.surface,
        elevation = 8.dp
    ) {
        val scrollState = rememberScrollState()

        Column(

            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(8.dp)
                .verticalScroll(scrollState)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.weight(0.67f))

                Text(
                    text = "Graph",
                    style = MaterialTheme.typography.h6.copy(
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 16.sp,
                        color = activeTrackColor
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                )

                // Правая часть — кнопка закрытия
                IconButton(onClick = onClose) {
                    Icon(Icons.Default.Close, contentDescription = "Close")
                }
            }

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                placeholder = { Text(
                    "Enter name",
                    color = Color(0xFFB3B3B3),
                    fontSize = 13.sp) },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                textStyle = MaterialTheme.typography.body2.copy(fontSize = 14.sp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    backgroundColor = Color(0xFF3C3030)
                )
            )

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(6.dp),
                color = MaterialTheme.colors.onSurface.copy(alpha = 0.1f),
            ) {
                Column(
                    modifier = Modifier.padding(2.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        "Display Options:",
                        style = MaterialTheme.typography.h6.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = activeTrackColor
                        ),
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        LabelCheckbox("Show weights", showWeights) { showWeights = it }
                        LabelCheckbox("Show id", showId) { showId = it }
                    }

                    LabelCheckbox("Show directed", showDirected) { showDirected = it }
                }
            }
        }
    }
}

@Composable
fun LabelCheckbox(text: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = checked, onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = activeTrackColor,
                uncheckedColor = activeTrackColor
            ),
//            modifier = Modifier.scale(0.8f)
        )
        Text(
            text = text,
            fontSize = 14.sp
        )
    }
}
