package org.graphApp.view.graph

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import org.graphApp.viewmodel.graph.EdgeViewModel
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import androidx.compose.ui.unit.dp

const val PLACE_ARROW_PARAM = 0.7f

@Composable
fun <E, V> EdgeView(
    viewModel: EdgeViewModel<E, V>,
    modifier: Modifier = Modifier,
) {
    var start = Offset.Zero
    var end = Offset.Zero
    Canvas(modifier = modifier.fillMaxSize()) {
        val vCenter = Offset(
            viewModel.u.x.toPx() + viewModel.u.radius.toPx(),
            viewModel.u.y.toPx() + viewModel.u.radius.toPx(),
        )
        val uCenter = Offset(
            viewModel.v.x.toPx() + viewModel.v.radius.toPx(),
            viewModel.v.y.toPx() + viewModel.v.radius.toPx(),
        )

        val angle = atan2(
            vCenter.y - uCenter.y,
            vCenter.x - uCenter.x
        )

        val r = viewModel.u.radius.toPx()

        end = Offset(
            uCenter.x + cos(angle) * r,
            uCenter.y + sin(angle) * r
        )
        start = Offset(
            vCenter.x - cos(angle) * r,
            vCenter.y - sin(angle) * r
        )
        drawLine(
            start = start,
            end = end,
            color = Color.Red,
            strokeWidth = 4f
        )
    }
    if (viewModel.weightVisible) {
        Text(
            modifier = Modifier
                .offset(
                    viewModel.u.x + (viewModel.v.x - viewModel.u.x) / 2,
                    viewModel.u.y + (viewModel.v.y - viewModel.u.y) / 2
                ),
            text = viewModel.weight ?: "",
        )
    }

    if (viewModel.directionVisible) {

        Canvas(modifier = modifier.fillMaxSize()) {
            val arrowColor = Color(0xFF696969)
            val arrowPosition = Offset(
                start.x + (end.x - start.x) * PLACE_ARROW_PARAM,
                start.y + (end.y - start.y) * PLACE_ARROW_PARAM
            )

            val angle = atan2(end.y - start.y, end.x - start.x)
            val arrowLen = 8.dp.toPx()
            val arrowAngle = Math.toRadians(20.0).toFloat()

            val arrowPoint1 = Offset(
                arrowPosition.x - arrowLen * cos(angle - arrowAngle),
                arrowPosition.y - arrowLen * sin(angle - arrowAngle)
            )
            val arrowPoint2 = Offset(
                arrowPosition.x - arrowLen * cos(angle + arrowAngle),
                arrowPosition.y - arrowLen * sin(angle + arrowAngle)
            )

            drawLine(color = arrowColor, start = arrowPosition, end = arrowPoint1, strokeWidth = 3f)
            drawLine(color = arrowColor, start = arrowPosition, end = arrowPoint2, strokeWidth = 3f)
        }
    }
}
