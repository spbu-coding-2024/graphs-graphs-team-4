package org.graphApp.view.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// нужно еще будет реализовать переключение темы, а еще цвета подобрать
val DarkColorScheme = darkColors(
    primary = TopAppBarColor,
    primaryVariant = Color(0xFFFF48C8),
    background = Color(0xFF1E1E1E),
    surface = Color(0xFF373737),
    onPrimary = Color(0xFFD5D5D5),
    onSecondary = Color(0xFFFFFFFF),
    onBackground = Color(0xFFD5D5D5),
    onSurface = Color(0xFFB3B3B3),
)

val LightColorScheme = lightColors(
    primary = TopAppBarColor,
    background = Color.White,
    primaryVariant = Color(0xFF419FE7),
    surface = Color(0xFFF3F3F3),
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
)

@Composable
fun GraphTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) LightColorScheme else DarkColorScheme

    MaterialTheme(
        colors = colorScheme,
        typography = replyTypography,
        shapes = replyShapes,
        content = content
    )
}
