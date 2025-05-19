package org.graphApp.view.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Matrix
import androidx.compose.ui.unit.dp

@Composable
fun CloseButton(
    onClick: () -> Unit,
    text: String,
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor =  MaterialTheme.colors.primaryVariant,
            contentColor = MaterialTheme.colors.surface
        ),
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = text,
            color = MaterialTheme.colors.onSecondary
        )
    }
}
@Composable
fun SaveButton(
    onClick: () -> Unit,
    text: String,
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primaryVariant,
            contentColor = MaterialTheme.colors.surface
        ),
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = text,
            color = MaterialTheme.colors.onSecondary
        )
    }
}

@Composable
fun ResetButton(
    onClick: () -> Unit,
    text: String,
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primaryVariant,
            contentColor = MaterialTheme.colors.surface
        ),
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = text,
            color = MaterialTheme.colors.onSecondary
        )
    }
}

@Composable
fun OkButton(
    onClick: () -> Unit,
    text: String,
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primaryVariant,
            contentColor = MaterialTheme.colors.surface
        ),
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = text,
            color = MaterialTheme.colors.onSecondary
        )
    }
}

@Composable
fun AddFolderButton(
    onClick: () -> Unit,
) {
    Icon(
        imageVector = addFolder,
        contentDescription = "Add Folder",
        modifier = Modifier.size(40.dp)
            .clickable(onClick = onClick)
            .padding(5.dp)
    )
}

@Composable
fun startAlgorithmButton(
    onClick : () -> Unit,
    text : String
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primaryVariant,
            contentColor = MaterialTheme.colors.surface
        ),
        modifier = Modifier.padding(6.dp)
    ) {
        Text(
            text = text,
            color = MaterialTheme.colors.onSecondary
        )
    }
}

@Composable
fun resetButton(
    onClick : () -> Unit,
    text : String
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primaryVariant,
            contentColor = MaterialTheme.colors.surface
        ),
        modifier = Modifier.padding(6.dp)
    ) {
        Text(
            text = text,
            color = MaterialTheme.colors.onSecondary
        )
    }
}

@Composable
fun loadButton(
    onClick : () -> Unit,
    text : String
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(15.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primaryVariant,
            contentColor = MaterialTheme.colors.surface
        ),
        modifier = Modifier.padding(6.dp)
    ) {
        Text(
            text = text,
            color = MaterialTheme.colors.onSecondary
        )
    }
}
