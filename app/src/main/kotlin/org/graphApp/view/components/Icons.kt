package org.graphApp.view.components

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

public val zoomIn: ImageVector
    get() {
        if (_zoomIn != null) {
            return _zoomIn!!
        }
        _zoomIn = ImageVector.Builder(
            name = "Zoom_in",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(784f, 840f)
                lineTo(532f, 588f)
                quadToRelative(-30f, 24f, -69f, 38f)
                reflectiveQuadToRelative(-83f, 14f)
                quadToRelative(-109f, 0f, -184.5f, -75.5f)
                reflectiveQuadTo(120f, 380f)
                reflectiveQuadToRelative(75.5f, -184.5f)
                reflectiveQuadTo(380f, 120f)
                reflectiveQuadToRelative(184.5f, 75.5f)
                reflectiveQuadTo(640f, 380f)
                quadToRelative(0f, 44f, -14f, 83f)
                reflectiveQuadToRelative(-38f, 69f)
                lineToRelative(252f, 252f)
                close()
                moveTo(380f, 560f)
                quadToRelative(75f, 0f, 127.5f, -52.5f)
                reflectiveQuadTo(560f, 380f)
                reflectiveQuadToRelative(-52.5f, -127.5f)
                reflectiveQuadTo(380f, 200f)
                reflectiveQuadToRelative(-127.5f, 52.5f)
                reflectiveQuadTo(200f, 380f)
                reflectiveQuadToRelative(52.5f, 127.5f)
                reflectiveQuadTo(380f, 560f)
                moveToRelative(-40f, -60f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(-80f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(80f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(80f)
                horizontalLineToRelative(-80f)
                verticalLineToRelative(80f)
                close()
            }
        }.build()
        return _zoomIn!!
    }

private var _zoomIn: ImageVector? = null



public val zoomOut: ImageVector
    get() {
        if (_zoomOut != null) {
            return _zoomOut!!
        }
        _zoomOut = ImageVector.Builder(
            name = "Zoom_out",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(784f, 840f)
                lineTo(532f, 588f)
                quadToRelative(-30f, 24f, -69f, 38f)
                reflectiveQuadToRelative(-83f, 14f)
                quadToRelative(-109f, 0f, -184.5f, -75.5f)
                reflectiveQuadTo(120f, 380f)
                reflectiveQuadToRelative(75.5f, -184.5f)
                reflectiveQuadTo(380f, 120f)
                reflectiveQuadToRelative(184.5f, 75.5f)
                reflectiveQuadTo(640f, 380f)
                quadToRelative(0f, 44f, -14f, 83f)
                reflectiveQuadToRelative(-38f, 69f)
                lineToRelative(252f, 252f)
                close()
                moveTo(380f, 560f)
                quadToRelative(75f, 0f, 127.5f, -52.5f)
                reflectiveQuadTo(560f, 380f)
                reflectiveQuadToRelative(-52.5f, -127.5f)
                reflectiveQuadTo(380f, 200f)
                reflectiveQuadToRelative(-127.5f, 52.5f)
                reflectiveQuadTo(200f, 380f)
                reflectiveQuadToRelative(52.5f, 127.5f)
                reflectiveQuadTo(380f, 560f)
                moveTo(280f, 420f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(200f)
                verticalLineToRelative(80f)
                close()
            }
        }.build()
        return _zoomOut!!
    }

private var _zoomOut: ImageVector? = null

public val addFolder: ImageVector
    get() {
        if (_addFolder != null) {
            return _addFolder!!
        }
        _addFolder = ImageVector.Builder(
            name = "Add_ad",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(720f, 800f)
                verticalLineToRelative(-120f)
                horizontalLineTo(600f)
                verticalLineToRelative(-80f)
                horizontalLineToRelative(120f)
                verticalLineToRelative(-120f)
                horizontalLineToRelative(80f)
                verticalLineToRelative(120f)
                horizontalLineToRelative(120f)
                verticalLineToRelative(80f)
                horizontalLineTo(800f)
                verticalLineToRelative(120f)
                close()
                moveToRelative(-600f, 40f)
                quadToRelative(-33f, 0f, -56.5f, -23.5f)
                reflectiveQuadTo(40f, 760f)
                verticalLineToRelative(-560f)
                quadToRelative(0f, -33f, 23.5f, -56.5f)
                reflectiveQuadTo(120f, 120f)
                horizontalLineToRelative(560f)
                quadToRelative(33f, 0f, 56.5f, 23.5f)
                reflectiveQuadTo(760f, 200f)
                verticalLineToRelative(200f)
                horizontalLineToRelative(-80f)
                verticalLineToRelative(-80f)
                horizontalLineTo(120f)
                verticalLineToRelative(440f)
                horizontalLineToRelative(520f)
                verticalLineToRelative(80f)
                close()
                moveToRelative(0f, -600f)
                horizontalLineToRelative(560f)
                verticalLineToRelative(-40f)
                horizontalLineTo(120f)
                close()
                moveToRelative(0f, 0f)
                verticalLineToRelative(-40f)
                close()
            }
        }.build()
        return _addFolder!!
    }

private var _addFolder: ImageVector? = null

public val zoomSign: ImageVector
    get() {
        if (_search != null) {
            return _search!!
        }
        _search = ImageVector.Builder(
            name = "Search",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(784f, 840f)
                lineTo(532f, 588f)
                quadToRelative(-30f, 24f, -69f, 38f)
                reflectiveQuadToRelative(-83f, 14f)
                quadToRelative(-109f, 0f, -184.5f, -75.5f)
                reflectiveQuadTo(120f, 380f)
                reflectiveQuadToRelative(75.5f, -184.5f)
                reflectiveQuadTo(380f, 120f)
                reflectiveQuadToRelative(184.5f, 75.5f)
                reflectiveQuadTo(640f, 380f)
                quadToRelative(0f, 44f, -14f, 83f)
                reflectiveQuadToRelative(-38f, 69f)
                lineToRelative(252f, 252f)
                close()
                moveTo(380f, 560f)
                quadToRelative(75f, 0f, 127.5f, -52.5f)
                reflectiveQuadTo(560f, 380f)
                reflectiveQuadToRelative(-52.5f, -127.5f)
                reflectiveQuadTo(380f, 200f)
                reflectiveQuadToRelative(-127.5f, 52.5f)
                reflectiveQuadTo(200f, 380f)
                reflectiveQuadToRelative(52.5f, 127.5f)
                reflectiveQuadTo(380f, 560f)
            }
        }.build()
        return _search!!
    }

private var _search: ImageVector? = null


val chooseYourTheme: ImageVector
    get() {
        if (_theme != null) {
            return _theme!!
        }
        _theme = ImageVector.Builder(
            name = "Cards",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 960f,
            viewportHeight = 960f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(240f, 460f)
                verticalLineToRelative(-220f)
                horizontalLineToRelative(220f)
                verticalLineToRelative(220f)
                close()
                moveToRelative(0f, 260f)
                verticalLineToRelative(-220f)
                horizontalLineToRelative(220f)
                verticalLineToRelative(220f)
                close()
                moveToRelative(260f, -260f)
                verticalLineToRelative(-220f)
                horizontalLineToRelative(220f)
                verticalLineToRelative(220f)
                close()
                moveToRelative(0f, 260f)
                verticalLineToRelative(-220f)
                horizontalLineToRelative(220f)
                verticalLineToRelative(220f)
                close()
                moveTo(320f, 380f)
                horizontalLineToRelative(60f)
                verticalLineToRelative(-60f)
                horizontalLineToRelative(-60f)
                close()
                moveToRelative(260f, 0f)
                horizontalLineToRelative(60f)
                verticalLineToRelative(-60f)
                horizontalLineToRelative(-60f)
                close()
                moveTo(320f, 640f)
                horizontalLineToRelative(60f)
                verticalLineToRelative(-60f)
                horizontalLineToRelative(-60f)
                close()
                moveToRelative(260f, 0f)
                horizontalLineToRelative(60f)
                verticalLineToRelative(-60f)
                horizontalLineToRelative(-60f)
                close()
                moveTo(200f, 840f)
                quadToRelative(-33f, 0f, -56.5f, -23.5f)
                reflectiveQuadTo(120f, 760f)
                verticalLineToRelative(-560f)
                quadToRelative(0f, -33f, 23.5f, -56.5f)
                reflectiveQuadTo(200f, 120f)
                horizontalLineToRelative(560f)
                quadToRelative(33f, 0f, 56.5f, 23.5f)
                reflectiveQuadTo(840f, 200f)
                verticalLineToRelative(560f)
                quadToRelative(0f, 33f, -23.5f, 56.5f)
                reflectiveQuadTo(760f, 840f)
                close()
                moveToRelative(0f, -80f)
                horizontalLineToRelative(560f)
                verticalLineToRelative(-560f)
                horizontalLineTo(200f)
                close()
            }
        }.build()
        return _theme!!
    }

private var _theme: ImageVector? = null


public val CornerUpLeft: ImageVector
    get() {
        if (_CornerUpLeft != null) {
            return _CornerUpLeft!!
        }
        _CornerUpLeft = ImageVector.Builder(
            name = "CornerUpLeft",
            defaultWidth = 32.dp,
            defaultHeight = 29.dp,
            viewportWidth = 32f,
            viewportHeight = 29f
        ).apply {
            path(
                fill = null,
                fillAlpha = 1.0f,
                stroke = SolidColor(Color(0xFF1E1E1E)),
                strokeAlpha = 1.0f,
                strokeLineWidth = 4f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(11.9999f, 16.9166f)
                lineTo(5.33325f, 10.875f)
                moveTo(5.33325f, 10.875f)
                lineTo(11.9999f, 4.83331f)
                moveTo(5.33325f, 10.875f)
                horizontalLineTo(21.3333f)
                curveTo(22.74770f, 10.8750f, 24.10430f, 11.38420f, 25.10450f, 12.29060f)
                curveTo(26.10470f, 13.19710f, 26.66660f, 14.42640f, 26.66660f, 15.70830f)
                verticalLineTo(24.1666f)
            }
        }.build()
        return _CornerUpLeft!!
    }

private var _CornerUpLeft: ImageVector? = null


public val CornerUpRight: ImageVector
    get() {
        if (_CornerUpRight != null) {
            return _CornerUpRight!!
        }
        _CornerUpRight = ImageVector.Builder(
            name = "CornerUpRight",
            defaultWidth = 32.dp,
            defaultHeight = 29.dp,
            viewportWidth = 32f,
            viewportHeight = 29f
        ).apply {
            path(
                fill = null,
                fillAlpha = 1.0f,
                stroke = SolidColor(Color(0xFF1E1E1E)),
                strokeAlpha = 1.0f,
                strokeLineWidth = 4f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(19.9999f, 16.9166f)
                lineTo(26.6666f, 10.875f)
                moveTo(26.6666f, 10.875f)
                lineTo(19.9999f, 4.83331f)
                moveTo(26.6666f, 10.875f)
                horizontalLineTo(10.6666f)
                curveTo(9.25210f, 10.8750f, 7.89550f, 11.38420f, 6.89530f, 12.29060f)
                curveTo(5.89520f, 13.19710f, 5.33320f, 14.42640f, 5.33320f, 15.70830f)
                verticalLineTo(24.1666f)
            }
        }.build()
        return _CornerUpRight!!
    }

private var _CornerUpRight: ImageVector? = null


public val MessageSquare: ImageVector
    get() {
        if (_MessageSquare != null) {
            return _MessageSquare!!
        }
        _MessageSquare = ImageVector.Builder(
            name = "MessageSquare",
            defaultWidth = 35.dp,
            defaultHeight = 34.dp,
            viewportWidth = 35f,
            viewportHeight = 34f
        ).apply {
            path(
                fill = null,
                fillAlpha = 1.0f,
                stroke = SolidColor(Color(0xFF1E1E1E)),
                strokeAlpha = 1.0f,
                strokeLineWidth = 4f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(30.625f, 21.25f)
                curveTo(30.6250f, 22.00140f, 30.31770f, 22.72210f, 29.77070f, 23.25350f)
                curveTo(29.22370f, 23.78480f, 28.48190f, 24.08330f, 27.70830f, 24.08330f)
                horizontalLineTo(10.2083f)
                lineTo(4.375f, 29.75f)
                verticalLineTo(7.08333f)
                curveTo(4.3750f, 6.33190f, 4.68230f, 5.61120f, 5.22930f, 5.07990f)
                curveTo(5.77630f, 4.54850f, 6.51810f, 4.250f, 7.29170f, 4.250f)
                horizontalLineTo(27.7083f)
                curveTo(28.48190f, 4.250f, 29.22370f, 4.54850f, 29.77070f, 5.07990f)
                curveTo(30.31770f, 5.61120f, 30.6250f, 6.33190f, 30.6250f, 7.08330f)
                verticalLineTo(21.25f)
                close()
            }
        }.build()
        return _MessageSquare!!
    }

private var _MessageSquare: ImageVector? = null


public val FilePlus: ImageVector
    get() {
        if (_filePlus != null) {
            return _filePlus!!
        }
        _filePlus = ImageVector.Builder(
            name = "FilePlus",
            defaultWidth = 35.dp,
            defaultHeight = 33.dp,
            viewportWidth = 35f,
            viewportHeight = 33f
        ).apply {
            path(
                fill = null,
                fillAlpha = 1.0f,
                stroke = SolidColor(Color(0xFF1E1E1E)),
                strokeAlpha = 1.0f,
                strokeLineWidth = 4f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(20.4167f, 2.75f)
                horizontalLineTo(8.75f)
                curveTo(7.9765f, 2.75f, 7.2346f, 3.0397f, 6.6876f, 3.5555f)
                curveTo(6.1407f, 4.0712f, 5.8334f, 4.7706f, 5.8334f, 5.5f)
                verticalLineTo(27.5f)
                curveTo(5.8334f, 28.2293f, 6.1407f, 28.9288f, 6.6876f, 29.4445f)
                curveTo(7.2346f, 29.9603f, 7.9765f, 30.25f, 8.75f, 30.25f)
                horizontalLineTo(26.25f)
                curveTo(27.0236f, 30.25f, 27.7655f, 29.9603f, 28.3124f, 29.4445f)
                curveTo(28.8594f, 28.9288f, 29.1667f, 28.2293f, 29.1667f, 27.5f)
                verticalLineTo(11f)
                moveTo(20.4167f, 2.75f)
                lineTo(29.1667f, 11f)
                moveTo(20.4167f, 2.75f)
                verticalLineTo(11f)
                horizontalLineTo(29.1667f)
                moveTo(17.5f, 24.75f)
                verticalLineTo(16.5f)
                moveTo(13.125f, 20.625f)
                horizontalLineTo(21.875f)
            }
        }.build()
        return _filePlus!!
    }

private var _filePlus: ImageVector? = null


public val Trash2: ImageVector
    get() {
        if (_Trash2 != null) {
            return _Trash2!!
        }
        _Trash2 = ImageVector.Builder(
            name = "Trash2",
            defaultWidth = 35.dp,
            defaultHeight = 37.dp,
            viewportWidth = 35f,
            viewportHeight = 37f
        ).apply {
            path(
                fill = null,
                fillAlpha = 1.0f,
                stroke = SolidColor(Color(0xFF1E1E1E)),
                strokeAlpha = 1.0f,
                strokeLineWidth = 4f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(4.375f, 9.24998f)
                horizontalLineTo(7.29167f)
                moveTo(7.29167f, 9.24998f)
                horizontalLineTo(30.625f)
                moveTo(7.29167f, 9.24998f)
                verticalLineTo(30.8333f)
                curveTo(7.29170f, 31.65110f, 7.5990f, 32.43530f, 8.14590f, 33.01360f)
                curveTo(8.69290f, 33.59180f, 9.43480f, 33.91660f, 10.20830f, 33.91660f)
                horizontalLineTo(24.7917f)
                curveTo(25.56520f, 33.91660f, 26.30710f, 33.59180f, 26.85410f, 33.01360f)
                curveTo(27.4010f, 32.43530f, 27.70830f, 31.65110f, 27.70830f, 30.83330f)
                verticalLineTo(9.24998f)
                moveTo(11.6667f, 9.24998f)
                verticalLineTo(6.16665f)
                curveTo(11.66670f, 5.34890f, 11.9740f, 4.56460f, 12.52090f, 3.98640f)
                curveTo(13.06790f, 3.40820f, 13.80980f, 3.08330f, 14.58330f, 3.08330f)
                horizontalLineTo(20.4167f)
                curveTo(21.19020f, 3.08330f, 21.93210f, 3.40820f, 22.47910f, 3.98640f)
                curveTo(23.0260f, 4.56460f, 23.33330f, 5.34890f, 23.33330f, 6.16660f)
                verticalLineTo(9.24998f)
                moveTo(14.5833f, 16.9583f)
                verticalLineTo(26.2083f)
                moveTo(20.4167f, 16.9583f)
                verticalLineTo(26.2083f)
            }
        }.build()
        return _Trash2!!
    }

private var _Trash2: ImageVector? = null

public val Save1: ImageVector
    get() {
        if (_Save1 != null) {
            return _Save1!!
        }
        _Save1 = ImageVector.Builder(
            name = "Save1",
            defaultWidth = 29.dp,
            defaultHeight = 35.dp,
            viewportWidth = 29f,
            viewportHeight = 35f
        ).apply {
            path(
                fill = null,
                fillAlpha = 1.0f,
                stroke = SolidColor(Color(0xFF1E1E1E)),
                strokeAlpha = 1.0f,
                strokeLineWidth = 4f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(20.5417f, 30.625f)
                verticalLineTo(18.9583f)
                horizontalLineTo(8.45833f)
                verticalLineTo(30.625f)
                moveTo(8.45833f, 4.375f)
                verticalLineTo(11.6667f)
                horizontalLineTo(18.125f)
                moveTo(22.9583f, 30.625f)
                horizontalLineTo(6.04167f)
                curveTo(5.40070f, 30.6250f, 4.7860f, 30.31770f, 4.33280f, 29.77070f)
                curveTo(3.87960f, 29.22370f, 3.6250f, 28.48190f, 3.6250f, 27.70830f)
                verticalLineTo(7.29167f)
                curveTo(3.6250f, 6.51810f, 3.87960f, 5.77630f, 4.33280f, 5.22930f)
                curveTo(4.7860f, 4.68230f, 5.40070f, 4.3750f, 6.04170f, 4.3750f)
                horizontalLineTo(19.3333f)
                lineTo(25.375f, 11.6667f)
                verticalLineTo(27.7083f)
                curveTo(25.3750f, 28.48190f, 25.12040f, 29.22370f, 24.66720f, 29.77070f)
                curveTo(24.2140f, 30.31770f, 23.59930f, 30.6250f, 22.95830f, 30.6250f)
                close()
            }
        }.build()
        return _Save1!!
    }

private var _Save1: ImageVector? = null

public val Folder: ImageVector
    get() {
        if (_Folder != null) {
            return _Folder!!
        }
        _Folder = ImageVector.Builder(
            name = "Folder",
            defaultWidth = 32.dp,
            defaultHeight = 29.dp,
            viewportWidth = 32f,
            viewportHeight = 29f
        ).apply {
            path(
                fill = null,
                fillAlpha = 1.0f,
                stroke = SolidColor(Color(0xFF1E1E1E)),
                strokeAlpha = 1.0f,
                strokeLineWidth = 4f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(29.3333f, 22.9583f)
                curveTo(29.33330f, 23.59930f, 29.05230f, 24.2140f, 28.55220f, 24.66720f)
                curveTo(28.05210f, 25.12040f, 27.37390f, 25.3750f, 26.66660f, 25.3750f)
                horizontalLineTo(5.33329f)
                curveTo(4.62610f, 25.3750f, 3.94780f, 25.12040f, 3.44770f, 24.66720f)
                curveTo(2.94760f, 24.2140f, 2.66660f, 23.59930f, 2.66660f, 22.95830f)
                verticalLineTo(6.04167f)
                curveTo(2.66660f, 5.40070f, 2.94760f, 4.7860f, 3.44770f, 4.33280f)
                curveTo(3.94780f, 3.87960f, 4.62610f, 3.6250f, 5.33330f, 3.6250f)
                horizontalLineTo(12f)
                lineTo(14.6666f, 7.25f)
                horizontalLineTo(26.6666f)
                curveTo(27.37390f, 7.250f, 28.05210f, 7.50460f, 28.55220f, 7.95780f)
                curveTo(29.05230f, 8.4110f, 29.33330f, 9.02570f, 29.33330f, 9.66670f)
                verticalLineTo(22.9583f)
                close()
            }
        }.build()
        return _Folder!!
    }

private var _Folder: ImageVector? = null

public val Reset8: ImageVector
    get() {
        if (_Reset8 != null) {
            return _Reset8!!
        }
        _Reset8 = ImageVector.Builder(
            name = "Reset8",
            defaultWidth = 1024.dp,
            defaultHeight = 1024.dp,
            viewportWidth = 1024f,
            viewportHeight = 1024f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(502.714987f, 58.258904f)
                lineToRelative(-126.531056f, -54.617723f)
                arcToRelative(52.797131f, 52.797131f, 0f, isMoreThanHalf = false, isPositiveArc = false, -41.873587f, 96.855428f)
                arcTo(447.865322f, 447.865322f, 0f, isMoreThanHalf = false, isPositiveArc = false, 392.02307f, 946.707184f)
                arcToRelative(61.535967f, 61.535967f, 0f, isMoreThanHalf = false, isPositiveArc = false, 13.83649f, 1.820591f)
                arcToRelative(52.797131f, 52.797131f, 0f, isMoreThanHalf = false, isPositiveArc = false, 13.65443f, -103.773672f)
                arcToRelative(342.453118f, 342.453118f, 0f, isMoreThanHalf = false, isPositiveArc = true, -31.678278f, -651.771485f)
                lineToRelative(-8.374718f, 19.480321f)
                arcToRelative(52.615072f, 52.615072f, 0f, isMoreThanHalf = false, isPositiveArc = false, 27.855039f, 69.182448f)
                arcToRelative(51.522718f, 51.522718f, 0f, isMoreThanHalf = false, isPositiveArc = false, 20.572675f, 4.369418f)
                arcTo(52.797131f, 52.797131f, 0f, isMoreThanHalf = false, isPositiveArc = false, 476.498481f, 254.882703f)
                lineTo(530.205907f, 127.441352f)
                arcToRelative(52.979191f, 52.979191f, 0f, isMoreThanHalf = false, isPositiveArc = false, -27.49092f, -69.182448f)
                close()
                moveTo(962.960326f, 509.765407f)
                arcTo(448.775617f, 448.775617f, 0f, isMoreThanHalf = false, isPositiveArc = false, 643.992829f, 68.090094f)
                arcToRelative(52.797131f, 52.797131f, 0f, isMoreThanHalf = true, isPositiveArc = false, -30.403866f, 101.042786f)
                arcTo(342.635177f, 342.635177f, 0f, isMoreThanHalf = false, isPositiveArc = true, 674.578753f, 801.059925f)
                arcToRelative(52.615072f, 52.615072f, 0f, isMoreThanHalf = false, isPositiveArc = false, -92.30395f, -50.612422f)
                lineToRelative(-71.913335f, 117.246043f)
                arcToRelative(52.433013f, 52.433013f, 0f, isMoreThanHalf = false, isPositiveArc = false, 17.295612f, 72.82363f)
                lineToRelative(117.063985f, 72.823629f)
                arcToRelative(52.797131f, 52.797131f, 0f, isMoreThanHalf = true, isPositiveArc = false, 54.617722f, -89.755123f)
                lineToRelative(-16.021198f, -10.013249f)
                arcTo(448.593558f, 448.593558f, 0f, isMoreThanHalf = false, isPositiveArc = false, 962.960326f, 509.765407f)
                close()
            }
        }.build()
        return _Reset8!!
    }

private var _Reset8: ImageVector? = null


public val Settings: ImageVector
    get() {
        if (_Settings != null) {
            return _Settings!!
        }
        _Settings = ImageVector.Builder(
            name = "Icon",
            defaultWidth = 22.dp,
            defaultHeight = 20.dp,
            viewportWidth = 22f,
            viewportHeight = 20f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF1D1B20)),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(8.24995f, 20f)
                lineTo(7.84995f, 16.8f)
                curveTo(7.63330f, 16.71670f, 7.42910f, 16.61670f, 7.23740f, 16.50f)
                curveTo(7.04580f, 16.38330f, 6.85830f, 16.25830f, 6.67490f, 16.1250f)
                lineTo(3.69995f, 17.375f)
                lineTo(0.949951f, 12.625f)
                lineTo(3.52495f, 10.675f)
                curveTo(3.50830f, 10.55830f, 3.50f, 10.44580f, 3.50f, 10.33750f)
                verticalLineTo(9.6625f)
                curveTo(3.50f, 9.55420f, 3.50830f, 9.44170f, 3.5250f, 9.3250f)
                lineTo(0.949951f, 7.375f)
                lineTo(3.69995f, 2.625f)
                lineTo(6.67495f, 3.875f)
                curveTo(6.85830f, 3.74170f, 7.04990f, 3.61670f, 7.250f, 3.50f)
                curveTo(7.450f, 3.38330f, 7.64990f, 3.28330f, 7.84990f, 3.20f)
                lineTo(8.24995f, 0f)
                horizontalLineTo(13.75f)
                lineTo(14.15f, 3.2f)
                curveTo(14.36660f, 3.28330f, 14.57080f, 3.38330f, 14.76250f, 3.50f)
                curveTo(14.95410f, 3.61670f, 15.14160f, 3.74170f, 15.3250f, 3.8750f)
                lineTo(18.3f, 2.625f)
                lineTo(21.05f, 7.375f)
                lineTo(18.475f, 9.325f)
                curveTo(18.49160f, 9.44170f, 18.50f, 9.55420f, 18.50f, 9.66250f)
                verticalLineTo(10.3375f)
                curveTo(18.50f, 10.44580f, 18.48330f, 10.55830f, 18.450f, 10.6750f)
                lineTo(21.025f, 12.625f)
                lineTo(18.275f, 17.375f)
                lineTo(15.325f, 16.125f)
                curveTo(15.14160f, 16.25830f, 14.950f, 16.38330f, 14.750f, 16.50f)
                curveTo(14.550f, 16.61670f, 14.350f, 16.71670f, 14.150f, 16.80f)
                lineTo(13.75f, 20f)
                horizontalLineTo(8.24995f)
                close()
                moveTo(11.05f, 13.5f)
                curveTo(12.01660f, 13.50f, 12.84160f, 13.15830f, 13.5250f, 12.4750f)
                curveTo(14.20830f, 11.79170f, 14.550f, 10.96670f, 14.550f, 100f)
                curveTo(14.550f, 9.03330f, 14.20830f, 8.20830f, 13.5250f, 7.5250f)
                curveTo(12.84160f, 6.84170f, 12.01660f, 6.50f, 11.050f, 6.50f)
                curveTo(10.06660f, 6.50f, 9.23750f, 6.84170f, 8.56250f, 7.5250f)
                curveTo(7.88750f, 8.20830f, 7.54990f, 9.03330f, 7.54990f, 100f)
                curveTo(7.54990f, 10.96670f, 7.88750f, 11.79170f, 8.56250f, 12.4750f)
                curveTo(9.23750f, 13.15830f, 10.06660f, 13.50f, 11.050f, 13.50f)
                close()
            }
        }.build()
        return _Settings!!
    }

private var _Settings: ImageVector? = null


public val Menu: ImageVector
    get() {
        if (_Menu != null) {
            return _Menu!!
        }
        _Menu = ImageVector.Builder(
            name = "Menu",
            defaultWidth = 48.dp,
            defaultHeight = 48.dp,
            viewportWidth = 48f,
            viewportHeight = 48f
        ).apply {
            path(
                fill = null,
                fillAlpha = 1.0f,
                stroke = SolidColor(Color(0xFF1E1E1E)),
                strokeAlpha = 1.0f,
                strokeLineWidth = 4f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(6f, 24f)
                horizontalLineTo(42f)
                moveTo(6f, 12f)
                horizontalLineTo(42f)
                moveTo(6f, 36f)
                horizontalLineTo(42f)
            }
        }.build()
        return _Menu!!
    }

private var _Menu: ImageVector? = null

public val HowToIcon: ImageVector
    get() {
        if (_HowToIcon != null) {
            return _HowToIcon!!
        }
        _HowToIcon = ImageVector.Builder(
            name = "HowToIcon",
            defaultWidth = 120.47.dp,
            defaultHeight = 122.88.dp,
            viewportWidth = 120.47f,
            viewportHeight = 122.88f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(17.2f, 0f)
                horizontalLineTo(79.49f)
                arcToRelative(17.24f, 17.24f, 0f, isMoreThanHalf = false, isPositiveArc = true, 17.2f, 17.2f)
                verticalLineTo(55.55f)
                arcToRelative(17.24f, 17.24f, 0f, isMoreThanHalf = false, isPositiveArc = true, -17.2f, 17.2f)
                horizontalLineTo(46.92f)
                lineTo(20.81f, 95.2f)
                arcTo(2.9f, 2.9f, 0f, isMoreThanHalf = false, isPositiveArc = true, 16f, 92.81f)
                lineToRelative(1.39f, -20.07f)
                horizontalLineTo(17.2f)
                arcTo(17.24f, 17.24f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, 55.55f)
                verticalLineTo(17.2f)
                arcTo(17.24f, 17.24f, 0f, isMoreThanHalf = false, isPositiveArc = true, 17.2f, 0f)
                close()
                moveTo(52.6f, 44.65f)
                horizontalLineTo(41.29f)
                verticalLineToRelative(-0.46f)
                arcToRelative(13.89f, 13.89f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0.65f, -4.69f)
                arcToRelative(10.39f, 10.39f, 0f, isMoreThanHalf = false, isPositiveArc = true, 2f, -3.29f)
                arcTo(59.46f, 59.46f, 0f, isMoreThanHalf = false, isPositiveArc = true, 49.72f, 31f)
                quadToRelative(2.41f, -2f, 2.41f, -3.6f)
                arcToRelative(3.35f, 3.35f, 0f, isMoreThanHalf = false, isPositiveArc = false, -1f, -2.55f)
                arcToRelative(4.19f, 4.19f, 0f, isMoreThanHalf = false, isPositiveArc = false, -2.94f, -0.91f)
                arcToRelative(4.67f, 4.67f, 0f, isMoreThanHalf = false, isPositiveArc = false, -3.49f, 1.41f)
                curveToRelative(-0.930f, 0.920f, -1.520f, 2.560f, -1.770f, 4.870f)
                lineTo(31.4f, 28.75f)
                arcTo(15.81f, 15.81f, 0f, isMoreThanHalf = false, isPositiveArc = true, 36f, 18.49f)
                quadToRelative(4f, -3.88f, 12.39f, -3.88f)
                curveToRelative(4.330f, 00f, 7.810f, 0.90f, 10.480f, 2.70f)
                arcToRelative(11.3f, 11.3f, 0f, isMoreThanHalf = false, isPositiveArc = true, 5.42f, 9.77f)
                arcTo(9.36f, 9.36f, 0f, isMoreThanHalf = false, isPositiveArc = true, 62.9f, 32f)
                arcToRelative(23.65f, 23.65f, 0f, isMoreThanHalf = false, isPositiveArc = true, -5.71f, 5.75f)
                arcToRelative(15.44f, 15.44f, 0f, isMoreThanHalf = false, isPositiveArc = false, -3.8f, 3.83f)
                arcToRelative(5.57f, 5.57f, 0f, isMoreThanHalf = false, isPositiveArc = false, -0.79f, 3.1f)
                close()
                moveTo(40.9f, 48.32f)
                horizontalLineTo(53f)
                verticalLineTo(57f)
                horizontalLineTo(40.9f)
                verticalLineTo(48.32f)
                close()
                moveTo(106.79f, 28f)
                arcToRelative(16.92f, 16.92f, 0f, isMoreThanHalf = false, isPositiveArc = true, 13.68f, 16.55f)
                verticalLineTo(82.88f)
                arcToRelative(16.9f, 16.9f, 0f, isMoreThanHalf = false, isPositiveArc = true, -16.85f, 16.85f)
                horizontalLineTo(103f)
                lineToRelative(1.42f, 20.44f)
                arcToRelative(2.54f, 2.54f, 0f, isMoreThanHalf = false, isPositiveArc = true, -4.18f, 2.1f)
                lineTo(74f, 99.07f)
                horizontalLineTo(39.49f)
                lineToRelative(17f, -17.3f)
                horizontalLineToRelative(36f)
                arcToRelative(14.34f, 14.34f, 0f, isMoreThanHalf = false, isPositiveArc = false, 14.3f, -14.3f)
                verticalLineTo(29.11f)
                curveToRelative(00f, -0.380f, 00f, -0.760f, 00f, -1.130f)
                close()
                moveTo(79.48f, 5.8f)
                horizontalLineTo(17.2f)
                arcTo(11.44f, 11.44f, 0f, isMoreThanHalf = false, isPositiveArc = false, 5.8f, 17.2f)
                verticalLineTo(55.55f)
                arcTo(11.44f, 11.44f, 0f, isMoreThanHalf = false, isPositiveArc = false, 17.2f, 67f)
                horizontalLineToRelative(3.53f)
                arcToRelative(2.9f, 2.9f, 0f, isMoreThanHalf = false, isPositiveArc = true, 2.69f, 3.08f)
                lineTo(22.29f, 86.31f)
                lineTo(43.83f, 67.79f)
                arcToRelative(2.87f, 2.87f, 0f, isMoreThanHalf = false, isPositiveArc = true, 2f, -0.84f)
                horizontalLineTo(79.48f)
                arcToRelative(11.44f, 11.44f, 0f, isMoreThanHalf = false, isPositiveArc = false, 11.4f, -11.4f)
                verticalLineTo(17.2f)
                arcTo(11.44f, 11.44f, 0f, isMoreThanHalf = false, isPositiveArc = false, 79.48f, 5.8f)
                close()
            }
        }.build()
        return _HowToIcon!!
    }

private var _HowToIcon: ImageVector? = null


public val InstructionManualsBookIcon: ImageVector
    get() {
        if (_InstructionManualsBookIcon != null) {
            return _InstructionManualsBookIcon!!
        }
        _InstructionManualsBookIcon = ImageVector.Builder(
            name = "InstructionManualsBookIcon",
            defaultWidth = 103.19.dp,
            defaultHeight = 122.88.dp,
            viewportWidth = 103.19f,
            viewportHeight = 122.88f
        ).apply {
            group {
                path(
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1.0f,
                    stroke = null,
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 1.0f,
                    strokeLineCap = StrokeCap.Butt,
                    strokeLineJoin = StrokeJoin.Miter,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(17.16f, 0f)
                    horizontalLineToRelative(82.72f)
                    curveToRelative(1.820f, 00f, 3.310f, 1.490f, 3.310f, 3.310f)
                    verticalLineToRelative(92.32f)
                    curveToRelative(-0.150f, 2.580f, -3.480f, 2.640f, -7.080f, 2.480f)
                    horizontalLineTo(15.94f)
                    curveToRelative(-4.980f, 00f, -9.050f, 4.070f, -9.050f, 9.050f)
                    curveToRelative(00f, 4.980f, 4.070f, 9.050f, 9.050f, 9.050f)
                    lineToRelative(80.17f, 0f)
                    verticalLineToRelative(-9.63f)
                    lineToRelative(7.08f, 0f)
                    verticalLineToRelative(12.24f)
                    curveToRelative(00f, 2.230f, -1.820f, 4.050f, -4.050f, 4.050f)
                    lineToRelative(-82.85f, 0f)
                    curveTo(7.330f, 122.880f, 00f, 115.550f, 00f, 106.590f)
                    verticalLineTo(17.16f)
                    curveTo(00f, 7.720f, 7.720f, 00f, 17.160f, 00f)
                    lineTo(17.16f, 0f)
                    close()
                    moveTo(54.53f, 14.05f)
                    curveToRelative(20.050f, 00f, 36.30f, 16.250f, 36.30f, 36.30f)
                    curveToRelative(00f, 20.050f, -16.250f, 36.30f, -36.30f, 36.30f)
                    curveToRelative(-20.050f, 00f, -36.30f, -16.250f, -36.30f, -36.30f)
                    curveTo(18.230f, 30.30f, 34.480f, 14.050f, 54.530f, 14.050f)
                    lineTo(54.53f, 14.05f)
                    close()
                    moveTo(52.5f, 36.67f)
                    curveToRelative(0.60f, 0.210f, 1.290f, 0.320f, 2.050f, 0.320f)
                    curveToRelative(0.80f, 00f, 1.490f, -0.110f, 2.10f, -0.320f)
                    curveToRelative(0.580f, -0.20f, 1.10f, -0.510f, 1.550f, -0.920f)
                    curveToRelative(0.430f, -0.380f, 0.770f, -0.840f, 0.980f, -1.360f)
                    curveToRelative(0.230f, -0.540f, 0.340f, -1.150f, 0.340f, -1.840f)
                    curveToRelative(00f, -0.70f, -0.110f, -1.330f, -0.340f, -1.870f)
                    curveToRelative(-0.210f, -0.520f, -0.540f, -0.980f, -0.980f, -1.380f)
                    lineToRelative(-0.02f, -0.02f)
                    curveToRelative(-0.440f, -0.410f, -0.970f, -0.720f, -1.550f, -0.930f)
                    curveToRelative(-0.610f, -0.210f, -1.30f, -0.320f, -2.10f, -0.320f)
                    curveToRelative(-0.770f, 00f, -1.460f, 0.110f, -2.040f, 0.320f)
                    curveToRelative(-0.570f, 0.20f, -1.070f, 0.510f, -1.520f, 0.920f)
                    lineToRelative(-0.03f, 0.03f)
                    curveToRelative(-0.440f, 0.40f, -0.770f, 0.870f, -0.980f, 1.380f)
                    curveToRelative(-0.230f, 0.540f, -0.340f, 1.160f, -0.340f, 1.870f)
                    curveToRelative(00f, 0.690f, 0.110f, 1.30f, 0.340f, 1.840f)
                    curveToRelative(0.210f, 0.490f, 0.520f, 0.930f, 0.920f, 1.30f)
                    curveToRelative(0.020f, 0.020f, 0.050f, 0.030f, 0.060f, 0.050f)
                    curveToRelative(0.440f, 0.410f, 0.970f, 0.720f, 1.550f, 0.920f)
                    verticalLineTo(36.67f)
                    lineTo(52.5f, 36.67f)
                    close()
                    moveTo(46.11f, 67.64f)
                    verticalLineToRelative(5.03f)
                    horizontalLineToRelative(17.01f)
                    verticalLineToRelative(-5.03f)
                    horizontalLineToRelative(-0.38f)
                    curveToRelative(-0.350f, 00f, -0.70f, -0.090f, -1.060f, -0.290f)
                    curveToRelative(-0.280f, -0.150f, -0.540f, -0.350f, -0.780f, -0.610f)
                    curveToRelative(-0.250f, -0.240f, -0.460f, -0.520f, -0.610f, -0.80f)
                    curveTo(60.10f, 65.590f, 600f, 65.240f, 600f, 64.90f)
                    verticalLineTo(44.93f)
                    curveToRelative(00f, -0.80f, -0.090f, -1.360f, -0.290f, -1.690f)
                    curveToRelative(-0.080f, -0.120f, -0.180f, -0.180f, -0.350f, -0.180f)
                    horizontalLineTo(45.93f)
                    verticalLineToRelative(5.5f)
                    horizontalLineToRelative(0.57f)
                    curveToRelative(0.350f, 00f, 0.720f, 0.110f, 1.070f, 0.310f)
                    curveToRelative(0.250f, 0.140f, 0.480f, 0.320f, 0.70f, 0.570f)
                    curveToRelative(0.030f, 0.020f, 0.050f, 0.050f, 0.080f, 0.080f)
                    curveToRelative(0.250f, 0.260f, 0.430f, 0.520f, 0.570f, 0.780f)
                    curveToRelative(0.170f, 0.340f, 0.260f, 0.670f, 0.260f, 1.010f)
                    verticalLineTo(64.9f)
                    curveToRelative(00f, 0.320f, -0.080f, 0.630f, -0.230f, 0.950f)
                    lineToRelative(-0.05f, 0.09f)
                    curveToRelative(-0.140f, 0.260f, -0.320f, 0.520f, -0.570f, 0.780f)
                    curveToRelative(-0.250f, 0.260f, -0.510f, 0.480f, -0.770f, 0.630f)
                    curveToRelative(-0.350f, 0.20f, -0.70f, 0.290f, -1.070f, 0.290f)
                    horizontalLineTo(46.11f)
                    lineTo(46.11f, 67.64f)
                    close()
                }
            }
        }.build()
        return _InstructionManualsBookIcon!!
    }

private var _InstructionManualsBookIcon: ImageVector? = null


public val Arrow_right: ImageVector
    get() {
        if (_Arrow_right != null) {
            return _Arrow_right!!
        }
        _Arrow_right = ImageVector.Builder(
            name = "Arrow_right",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF1D1B20)),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(10f, 17f)
                verticalLineTo(7f)
                lineTo(15f, 12f)
                lineTo(10f, 17f)
                close()
            }
        }.build()
        return _Arrow_right!!
    }

private var _Arrow_right: ImageVector? = null


public val Arrow_drop_down: ImageVector
    get() {
        if (_Arrow_drop_down != null) {
            return _Arrow_drop_down!!
        }
        _Arrow_drop_down = ImageVector.Builder(
            name = "Arrow_drop_down",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF1D1B20)),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(12f, 15f)
                lineTo(7f, 10f)
                horizontalLineTo(17f)
                lineTo(12f, 15f)
                close()
            }
        }.build()
        return _Arrow_drop_down!!
    }

private var _Arrow_drop_down: ImageVector? = null

public val RestoreWindowIcon: ImageVector
    get() {
        if (_RestoreWindowIcon != null) {
            return _RestoreWindowIcon!!
        }
        _RestoreWindowIcon = ImageVector.Builder(
            name = "RestoreWindowIcon",
            defaultWidth = 122.88.dp,
            defaultHeight = 114.64.dp,
            viewportWidth = 122.88f,
            viewportHeight = 114.64f
        ).apply {
            group {
                path(
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1.0f,
                    stroke = null,
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 1.0f,
                    strokeLineCap = StrokeCap.Butt,
                    strokeLineJoin = StrokeJoin.Miter,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.EvenOdd
                ) {
                    moveTo(8.284f, 36.331f)
                    horizontalLineToRelative(22.438f)
                    verticalLineTo(9.749f)
                    curveTo(30.7220f, 4.3880f, 35.110f, 00f, 40.4710f, 00f)
                    horizontalLineToRelative(72.66f)
                    curveToRelative(5.3610f, 00f, 9.7490f, 4.3880f, 9.7490f, 9.7490f)
                    verticalLineToRelative(72.66f)
                    curveToRelative(00f, 5.360f, -4.3880f, 9.7490f, -9.7490f, 9.7490f)
                    horizontalLineToRelative(-34.82f)
                    verticalLineToRelative(14.197f)
                    curveToRelative(00f, 4.5610f, -3.7230f, 8.2840f, -8.2830f, 8.2840f)
                    horizontalLineTo(8.284f)
                    curveToRelative(-4.5610f, 00f, -8.2840f, -3.7240f, -8.2840f, -8.2840f)
                    verticalLineTo(44.614f)
                    curveTo(00f, 40.0540f, 3.7230f, 36.3310f, 8.2840f, 36.3310f)
                    lineTo(8.284f, 36.331f)
                    close()
                    moveTo(30.722f, 56.694f)
                    horizontalLineTo(7.851f)
                    verticalLineToRelative(43.469f)
                    curveToRelative(00f, 3.6460f, 2.9790f, 6.6260f, 6.6260f, 6.6260f)
                    horizontalLineToRelative(49.357f)
                    curveToRelative(3.6460f, 00f, 6.6270f, -2.9790f, 6.6270f, -6.6260f)
                    verticalLineToRelative(-8.005f)
                    horizontalLineToRelative(-29.99f)
                    curveToRelative(-5.3610f, 00f, -9.7490f, -4.3890f, -9.7490f, -9.7490f)
                    verticalLineTo(56.694f)
                    lineTo(30.722f, 56.694f)
                    close()
                    moveTo(113.635f, 25.634f)
                    horizontalLineToRelative(-73.67f)
                    verticalLineToRelative(49.487f)
                    curveToRelative(00f, 4.2880f, 3.5040f, 7.7920f, 7.7940f, 7.7920f)
                    horizontalLineToRelative(58.084f)
                    curveToRelative(4.2880f, 00f, 7.7920f, -3.5040f, 7.7920f, -7.7920f)
                    verticalLineTo(25.634f)
                    lineTo(113.635f, 25.634f)
                    close()
                }
            }
        }.build()
        return _RestoreWindowIcon!!
    }

private var _RestoreWindowIcon: ImageVector? = null


public val CollapseMinusIcon: ImageVector
    get() {
        if (_CollapseMinusIcon != null) {
            return _CollapseMinusIcon!!
        }
        _CollapseMinusIcon = ImageVector.Builder(
            name = "CollapseMinusIcon",
            defaultWidth = 122.88.dp,
            defaultHeight = 119.72.dp,
            viewportWidth = 122.88f,
            viewportHeight = 119.72f
        ).apply {
            group {
                path(
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1.0f,
                    stroke = null,
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 1.0f,
                    strokeLineCap = StrokeCap.Butt,
                    strokeLineJoin = StrokeJoin.Miter,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(22.72f, 0f)
                    horizontalLineToRelative(77.45f)
                    curveToRelative(6.250f, 00f, 11.930f, 2.560f, 16.050f, 6.670f)
                    curveToRelative(4.110f, 4.110f, 6.670f, 9.790f, 6.670f, 16.050f)
                    verticalLineToRelative(74.29f)
                    curveToRelative(00f, 6.250f, -2.560f, 11.930f, -6.670f, 16.050f)
                    lineToRelative(-0.32f, 0.29f)
                    curveToRelative(-4.090f, 3.940f, -9.640f, 6.380f, -15.730f, 6.380f)
                    horizontalLineTo(22.72f)
                    curveToRelative(-6.250f, 00f, -11.930f, -2.560f, -16.050f, -6.670f)
                    lineToRelative(-0.3f, -0.32f)
                    curveTo(2.430f, 108.640f, 00f, 103.090f, 00f, 97.010f)
                    verticalLineTo(22.71f)
                    curveToRelative(00f, -6.250f, 2.550f, -11.930f, 6.670f, -16.050f)
                    curveTo(10.780f, 2.550f, 16.460f, 00f, 22.720f, 00f)
                    lineTo(22.72f, 0f)
                    close()
                    moveTo(39.92f, 65.83f)
                    curveToRelative(-3.30f, 00f, -5.970f, -2.670f, -5.970f, -5.970f)
                    curveToRelative(00f, -3.30f, 2.670f, -5.970f, 5.970f, -5.970f)
                    horizontalLineToRelative(43.05f)
                    curveToRelative(3.30f, 00f, 5.970f, 2.670f, 5.970f, 5.970f)
                    curveToRelative(00f, 3.30f, -2.670f, 5.970f, -5.970f, 5.970f)
                    horizontalLineTo(39.92f)
                    lineTo(39.92f, 65.83f)
                    close()
                    moveTo(100.16f, 10.24f)
                    horizontalLineTo(22.72f)
                    curveToRelative(-3.430f, 00f, -6.550f, 1.410f, -8.810f, 3.670f)
                    curveToRelative(-2.260f, 2.260f, -3.670f, 5.380f, -3.670f, 8.810f)
                    verticalLineToRelative(74.29f)
                    curveToRelative(00f, 3.330f, 1.310f, 6.350f, 3.430f, 8.590f)
                    lineToRelative(0.24f, 0.22f)
                    curveToRelative(2.260f, 2.260f, 5.380f, 3.670f, 8.810f, 3.670f)
                    horizontalLineToRelative(77.45f)
                    curveToRelative(3.320f, 00f, 6.350f, -1.310f, 8.590f, -3.440f)
                    lineToRelative(0.21f, -0.23f)
                    curveToRelative(2.260f, -2.260f, 3.670f, -5.380f, 3.670f, -8.810f)
                    verticalLineTo(22.71f)
                    curveToRelative(00f, -3.420f, -1.410f, -6.540f, -3.670f, -8.810f)
                    curveTo(106.710f, 11.650f, 103.590f, 10.240f, 100.160f, 10.240f)
                    lineTo(100.16f, 10.24f)
                    close()
                }
            }
        }.build()
        return _CollapseMinusIcon!!
    }

private var _CollapseMinusIcon: ImageVector? = null

public val CloseWindowIcon: ImageVector
    get() {
        if (_CloseWindowIcon != null) {
            return _CloseWindowIcon!!
        }
        _CloseWindowIcon = ImageVector.Builder(
            name = "CloseWindowIcon",
            defaultWidth = 122.88.dp,
            defaultHeight = 119.79.dp,
            viewportWidth = 122.88f,
            viewportHeight = 119.79f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1.0f,
                stroke = null,
                strokeAlpha = 1.0f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1.0f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(23.5f, 0f)
                horizontalLineTo(99.38f)
                arcToRelative(23.56f, 23.56f, 0f, isMoreThanHalf = false, isPositiveArc = true, 23.5f, 23.5f)
                verticalLineTo(96.29f)
                arcToRelative(23.56f, 23.56f, 0f, isMoreThanHalf = false, isPositiveArc = true, -23.5f, 23.5f)
                horizontalLineTo(23.5f)
                arcToRelative(23.44f, 23.44f, 0f, isMoreThanHalf = false, isPositiveArc = true, -16.6f, -6.9f)
                lineToRelative(-0.37f, -0.4f)
                arcTo(23.43f, 23.43f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, 96.29f)
                verticalLineTo(23.5f)
                arcTo(23.56f, 23.56f, 0f, isMoreThanHalf = false, isPositiveArc = true, 23.5f, 0f)
                close()
                moveTo(41f, 49.35f)
                arcToRelative(7f, 7f, 0f, isMoreThanHalf = false, isPositiveArc = true, 9.89f, -9.89f)
                lineTo(61.44f, 50f)
                lineTo(72f, 39.46f)
                arcToRelative(7f, 7f, 0f, isMoreThanHalf = true, isPositiveArc = true, 9.89f, 9.9f)
                lineTo(71.33f, 59.9f)
                lineTo(81.87f, 70.43f)
                arcTo(7f, 7f, 0f, isMoreThanHalf = false, isPositiveArc = true, 72f, 80.33f)
                lineTo(61.44f, 69.79f)
                lineTo(50.9f, 80.33f)
                arcTo(7f, 7f, 0f, isMoreThanHalf = false, isPositiveArc = true, 41f, 70.43f)
                lineTo(51.55f, 59.89f)
                lineTo(41f, 49.35f)
                close()
                moveTo(99.38f, 12.52f)
                horizontalLineTo(23.5f)
                arcToRelative(11f, 11f, 0f, isMoreThanHalf = false, isPositiveArc = false, -11f, 11f)
                verticalLineTo(96.29f)
                arcToRelative(10.92f, 10.92f, 0f, isMoreThanHalf = false, isPositiveArc = false, 3f, 7.49f)
                lineToRelative(0.27f, 0.26f)
                arcToRelative(11f, 11f, 0f, isMoreThanHalf = false, isPositiveArc = false, 7.75f, 3.23f)
                horizontalLineTo(99.38f)
                arcToRelative(11f, 11f, 0f, isMoreThanHalf = false, isPositiveArc = false, 11f, -11f)
                verticalLineTo(23.5f)
                arcToRelative(11f, 11f, 0f, isMoreThanHalf = false, isPositiveArc = false, -11f, -11f)
                close()
            }
        }.build()
        return _CloseWindowIcon!!
    }

private var _CloseWindowIcon: ImageVector? = null


public val SquareLineIcon: ImageVector
    get() {
        if (_SquareLineIcon != null) {
            return _SquareLineIcon!!
        }
        _SquareLineIcon = ImageVector.Builder(
            name = "SquareLineIcon",
            defaultWidth = 122.661.dp,
            defaultHeight = 122.88.dp,
            viewportWidth = 122.661f,
            viewportHeight = 122.88f
        ).apply {
            group {
                path(
                    fill = SolidColor(Color.Black),
                    fillAlpha = 1.0f,
                    stroke = null,
                    strokeAlpha = 1.0f,
                    strokeLineWidth = 1.0f,
                    strokeLineCap = StrokeCap.Butt,
                    strokeLineJoin = StrokeJoin.Miter,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.EvenOdd
                ) {
                    moveTo(21.26f, 0f)
                    horizontalLineToRelative(80.142f)
                    curveToRelative(11.6920f, 00f, 21.260f, 9.7060f, 21.260f, 21.5690f)
                    verticalLineToRelative(79.741f)
                    curveToRelative(00f, 11.8640f, -9.5670f, 21.5690f, -21.260f, 21.5690f)
                    horizontalLineTo(21.26f)
                    curveTo(9.5660f, 122.880f, 00f, 113.1750f, 00f, 101.3110f)
                    verticalLineTo(21.569f)
                    curveTo(00f, 9.7060f, 9.5660f, 00f, 21.260f, 00f)
                    lineTo(21.26f, 0f)
                    close()
                    moveTo(21.674f, 11.14f)
                    horizontalLineToRelative(79.312f)
                    curveToRelative(5.8550f, 00f, 10.6470f, 4.7880f, 10.6470f, 10.6410f)
                    verticalLineToRelative(79.313f)
                    curveToRelative(00f, 5.8550f, -4.7920f, 10.6460f, -10.6470f, 10.6460f)
                    horizontalLineTo(21.674f)
                    curveToRelative(-5.8550f, 00f, -10.6460f, -4.790f, -10.6460f, -10.6460f)
                    verticalLineTo(21.78f)
                    curveTo(11.0270f, 15.9280f, 15.8180f, 11.140f, 21.6740f, 11.140f)
                    lineTo(21.674f, 11.14f)
                    close()
                }
            }
        }.build()
        return _SquareLineIcon!!
    }

private var _SquareLineIcon: ImageVector? = null
