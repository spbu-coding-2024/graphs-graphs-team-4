package org.graphApp.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import org.graphApp.view.components.addFolder
import javax.management.monitor.StringMonitor

enum class AppLanguage {
    ENGLISH,
    RUSSIAN
}
data class TextResources(
    val fileMenu: String,
    val editMenu: String,
    val viewMenu: String,
    val algorithmsMenu: String,
    val settingsMenu: String,
    val helpMenu: String,

    val newGraph: String,
    val open: String,
    val recentGraphs: String,
    val loadGraph: String,
    val save: String,
    val saveAs: String,
    val reset: String,

    val undo: String,
    val redo: String,

    val settings: String,
    val resetDefault: String,

    val language: String,

    val aboutProgram: String,
    val quickStart: String,

    val english: String,
    val russian: String,

    val themeLight: String,
    val themeDark: String,

    val algorithmsDialog : String,
    val basic : String,
    val classic : String,
    val stronglyConnected : String,
    val findCycles : String,
    val minimalTree : String,
    val fordBellman : String,
    val layout : String,
    val findCommunities : String,
    val startAlgo : String,
    val viewDialog : String,
    val theme : String,
    val cancel : String,
    val ok : String,
    val light : String,
    val dark : String,

    val close : String,
    val chooseType : String,
    val weights : String,
    val directed : String,
    val createGraph : String,
    val enterGraphName : String,
    val chooseFormat : String,
    val addFolder : String,
    val showWeights : String,
    val showVertexLabels : String,
    val load : String
)

val englishResources = TextResources(
    fileMenu = "File",
    editMenu = "Edit",
    viewMenu = "View",
    algorithmsMenu = "Algorithms",
    settingsMenu = "Settings",
    helpMenu = "Help",

    newGraph = "New graph",
    open = "Open",
    recentGraphs = "Recent graphs:",
    loadGraph = "Load Graph",
    save = "Save",
    saveAs = "Save as",
    reset = "Reset",

    undo = "Undo",
    redo = "Redo",

    settings = "Settings",
    resetDefault = "Reset default",

    language = "Language",

    aboutProgram = "About Program",
    quickStart = "Quick Start",

    english = "English",
    russian = "Russian",

    themeLight = "Light",
    themeDark = "Dark",

    algorithmsDialog = "Algorithms",
    basic = "Basic",
    classic = "Classic",
    stronglyConnected = "Strongly connected",
    findCycles = "Find Cycles",
    minimalTree = "Minimal Spanning Tree",
    fordBellman = "Ford Bellman",
    layout = "Graph layout on the plane",
    findCommunities = "Find communities",
    startAlgo = "Start",

    viewDialog = "View",
    theme = "Theme:",
    cancel = "Cancel",
    ok = "OK",
    light = "Light",
    dark = "Dark",

    close = "Close",
    chooseType = "Choose Type",
    weights = "Weights",
    directed = "Directed",
    createGraph = "Create Graph",

    enterGraphName = "Enter graph name",
    chooseFormat = "Choose a saving format:",
    addFolder = "Add Folder",
    showWeights = "Edge weights",
    showVertexLabels = "Vertex labels",

    load = "Load"
)

val russianResources = TextResources(
    fileMenu = "Файл",
    editMenu = "Редактировать",
    viewMenu = "Вид",
    algorithmsMenu = "Алгоритмы",
    settingsMenu = "Настройки",
    helpMenu = "Помощь",

    newGraph = "Новый граф",
    open = "Открыть",
    recentGraphs = "Недавние графы:",
    loadGraph = "Загрузить граф",
    save = "Сохранить",
    saveAs = "Сохранить как",
    reset = "Сбросить",

    undo = "Отменить",
    redo = "Повторить",

    settings = "Настройки",
    resetDefault = "Сбросить настройки",

    language = "Язык",

    aboutProgram = "О программе",
    quickStart = "Быстрый старт",

    english = "Английский",
    russian = "Русский",

    themeLight = "Светлая",
    themeDark = "Тёмная",

    algorithmsDialog = "Алгоритмы",
    basic = "Базовые",
    classic = "Классические",
    stronglyConnected = "Компоненты сильной связанности",
    findCycles = "Поиск циклов",
    minimalTree = "Минимальное остовное дерево",
    fordBellman = "Форд-Беллман",
    layout = "Раскладка графа на плоскости",
    findCommunities = "Поиск сообществ",
    startAlgo = "Старт",

    viewDialog = "Вид",
    theme = "Тема:",
    cancel = "Отменить",
    ok = "Окей",
    light = "Светлая",
    dark = "Темная",

    close = "Закрыть",
    chooseType = "Выберите тип:",
    weights = "Весовой",
    directed = "Ориентированный",
    createGraph = "Создать граф",

    enterGraphName = "Введите имя графа",
    chooseFormat = "Введите формат сохранения:",
    addFolder = "Добавьте папку",
    showWeights = "Веса ребер",
    showVertexLabels = "Лейблы вершин",

    load = "Открыть"

)

fun getResources(language: AppLanguage): TextResources {
    return when (language) {
        AppLanguage.ENGLISH -> englishResources
        AppLanguage.RUSSIAN -> russianResources
    }
}

val LocalTextResources = compositionLocalOf { englishResources }

