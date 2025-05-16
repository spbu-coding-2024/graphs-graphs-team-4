package org.graphApp.view.graph

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
    val colorMain = viewModel.color
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
            color = colorMain,
            strokeWidth = 4f
        )
    }
    if (viewModel.weightVisible) {
        Box(
            modifier = Modifier.wrapContentSize().padding(8.dp)
                .offset(
                    x = (viewModel.u.x + (viewModel.v.x - viewModel.u.x) / 2) + viewModel.u.radius,
                    y = (viewModel.u.y + (viewModel.v.y - viewModel.u.y) / 2) + viewModel.u.radius
                )

        ) {
            Card(
                backgroundColor = MaterialTheme.colors.background.copy(alpha = 0.7f),
                elevation = 2.dp,
                modifier = Modifier.alpha(0.8f)
            ) {
                Text(
                    text = viewModel.weight ?: "",
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.caption
                )
            }
        }
    }

    if (viewModel.directVisible) {

        Canvas(modifier = modifier.fillMaxSize()) {
            val arrowPosition = Offset(
                start.x + (end.x - start.x) * PLACE_ARROW_PARAM,
                start.y + (end.y - start.y) * PLACE_ARROW_PARAM
            )

            val angle = atan2(end.y - start.y, end.x - start.x)
            val arrowLen = 28.dp.toPx()
            val arrowWidth = 19.dp.toPx()

            val tip = arrowPosition
            val baseAngle = angle + Math.toRadians(90.0).toFloat()
            val basePoint1 = Offset(
                tip.x - arrowLen * cos(angle) + arrowWidth / 2 * cos(baseAngle),
                tip.y - arrowLen * sin(angle) + arrowWidth / 2 * sin(baseAngle)
            )
            val basePoint2 = Offset(
                tip.x - arrowLen * cos(angle) - arrowWidth / 2 * cos(baseAngle),
                tip.y - arrowLen * sin(angle) - arrowWidth / 2 * sin(baseAngle)
            )

            drawPath(
                androidx.compose.ui.graphics.Path().apply {
                    moveTo(tip.x, tip.y)
                    lineTo(basePoint1.x, basePoint1.y)
                    lineTo(basePoint2.x, basePoint2.y)
                    close()
                },
                color = colorMain,
                style = androidx.compose.ui.graphics.drawscope.Fill
            )
        }
    }
}
