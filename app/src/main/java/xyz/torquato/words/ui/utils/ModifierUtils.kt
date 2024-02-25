package xyz.torquato.words.ui.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

fun Modifier.lineBelow(strokeWidth: Float = 4f) = this.drawBehind {
    val y = size.height - strokeWidth
    drawLine(
        Color.LightGray,
        Offset(0f, y),
        Offset(size.width, y),
        strokeWidth
    )
}
