package org.graphApp.view.graph.edge

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

@Composable
fun <E, V> EdgeView(
    viewModel: EdgeViewModel<E, V>,
    modifier: Modifier = Modifier,
) {
    Canvas(modifier = modifier.fillMaxSize()) {
        drawLine(
            start = Offset(
                viewModel.u.x.toPx() + viewModel.u.radius.toPx(),
                viewModel.u.y.toPx() + viewModel.u.radius.toPx(),
            ),
            end = Offset(
                viewModel.v.x.toPx() + viewModel.v.radius.toPx(),
                viewModel.v.y.toPx() + viewModel.v.radius.toPx(),
            ),
            color = Color.Black
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
            val start = Offset(
                viewModel.u.x.toPx() + viewModel.u.radius.toPx(),
                viewModel.u.y.toPx() + viewModel.u.radius.toPx(),
            )
            val end = Offset(
                viewModel.v.x.toPx() + viewModel.v.radius.toPx(),
                viewModel.v.y.toPx() + viewModel.v.radius.toPx(),
            )

            val fromUtoV = viewModel.direction == true

            val tail = if (fromUtoV) start else end
            val head = if (fromUtoV) end else start

            val mid = Offset(
                (tail.x + head.x) / 2f,
                (tail.y + head.y) / 2f,
            )

            val angle = atan2(head.y - tail.y, head.x - tail.x)

            val arrowLen = 8.dp.toPx()
            val halfAngle = Math.toRadians(20.0).toFloat()

            val p1 = Offset(
                mid.x - arrowLen * cos(angle - halfAngle),
                mid.y - arrowLen * sin(angle - halfAngle),
            )
            val p2 = Offset(
                mid.x - arrowLen * cos(angle + halfAngle),
                mid.y - arrowLen * sin(angle + halfAngle),
            )

            drawLine(color = Color.Black, start = mid, end = p1, strokeWidth = 3f)
            drawLine(color = Color.Black, start = mid, end = p2, strokeWidth = 3f)
        }
    }

}
