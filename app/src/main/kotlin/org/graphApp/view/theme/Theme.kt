package org.graphApp.view.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// нужно еще будет реализовать переключение темы, а еще цвета подобрать
val LightColorScheme = lightColors(
    primary = PrimaryColor,
    primaryVariant = ButtonColor,
    background = FrameColor,
    surface = DarkGray,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black
)

val DarkColorScheme = darkColors(
    primary = PrimaryColor,
    background = FrameColor,
    surface = DarkGray,
    onPrimary = Color.Black,
    onSecondary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White,
)

@Composable
fun GraphTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colors = colorScheme,
        typography = replyTypography,
        shapes = replyShapes,
        content = content
    )
}



