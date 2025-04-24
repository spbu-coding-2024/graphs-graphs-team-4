package org.graphApp.view.components

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
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


public val chooseYourTheme: ImageVector
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
