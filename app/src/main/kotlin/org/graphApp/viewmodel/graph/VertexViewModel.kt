package org.graphApp.viewmodel.graph

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.graphApp.model.graph.Vertex

class VertexViewModel<V>(
    x: Dp = 0.dp,
    y: Dp = 0.dp,
    color: Color,
    private val v: Vertex<V>,
    private val _labelVisible: State<Boolean>,
    val radius: Dp = 25.dp
) {
    private var _x = mutableStateOf(x)
    var x: Dp
        get() = _x.value
        set(value) {
            _x.value = value
        }
    private var _y = mutableStateOf(y)
    var y: Dp
        get() = _y.value
        set(value) {
            _y.value = value
        }
    private var _color = mutableStateOf(color)
    var color: Color
        get() = _color.value
        set(value) {
            _color.value = value
        }

    val label
        get() = v.element.toString()

    val labelVisible
        get() = _labelVisible.value

    fun onDrag(offset: Offset) {
        _x.value += offset.x.dp
        _y.value += offset.y.dp
    }
}
