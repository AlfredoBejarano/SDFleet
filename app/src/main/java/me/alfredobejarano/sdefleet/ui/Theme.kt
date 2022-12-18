package me.alfredobejarano.sdefleet.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Teal200 = Color(0xFF03DAC5)
val Teal500 = Color(0xFF009688)
val Teal700 = Color(0xFF00796b)
val Amber200 = Color(0xFFffe082)

private val DarkColorPalette = darkColors(
    primary = Teal200,
    primaryVariant = Teal700,
    secondary = Amber200
)

private val LightColorPalette = lightColors(
    primary = Teal500,
    primaryVariant = Teal700,
    secondary = Amber200
)

@Composable
fun AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        content = content
    )
}