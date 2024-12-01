package hu.bme.aut.android.receptes_konyv.ui.animation

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LoadingCirlce(
    circleRadius: Float = 40f,
    circleDistance: Float = 120f,
    animationDuration: Int = 2400,
    modifier: Modifier = Modifier,
    color: Color
) {
    val infiniteTransition = rememberInfiniteTransition()

    val angle1 by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = animationDuration, easing = LinearEasing)
        )
    )
    val angle2 = (angle1 + 120f) % 360f
    val angle3 = (angle1 + 240f) % 360f

    Canvas(modifier = modifier.size(100.dp)) {
        val centerX = size.width / 2
        val centerY = size.height / 2

        fun drawCircleAtAngle(angle: Float) {
            val radians = Math.toRadians(angle.toDouble())
            val x = (centerX + circleDistance * kotlin.math.cos(radians)).toFloat()
            val y = (centerY + circleDistance * kotlin.math.sin(radians)).toFloat()
            drawCircle(
                color = color,
                radius = circleRadius,
                center = Offset(x, y)
            )
        }

        drawCircleAtAngle(angle1)
        drawCircleAtAngle(angle2)
        drawCircleAtAngle(angle3)
    }
}