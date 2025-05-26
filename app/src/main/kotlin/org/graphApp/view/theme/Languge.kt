package org.graphApp.model

import androidx.annotation.RequiresFeature
import androidx.compose.runtime.compositionLocalOf
import org.graphApp.model.graph.WeightedEdge

enum class AppLanguage {
    ENGLISH,
    RUSSIAN,
    CHINESE
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
    val chinese : String,

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
    val load : String,

    val guide : String,
    val createVertex : String,
    val rightClick1 : String,
    val createEdge : String,
    val rightClick2 : String,
    val editElements: String,
    val doubleClick : String,
    val deleteElements : String,
    val clickOnVertex : String,
    val additional : String,
    val newGraphFile: String,
    val aboutGravViz : String,
    val version : String,
    val aLightweight : String,
    val keyFeatures : String,
    val createAndEdit : String,
    val vertexLabels : String,
    val edgeWeights : String,

    val enterEdgeWeight : String,
    val confirm : String,
    val add : String,
    val enterVertexLabel : String,
    val vertexLabel : String,
    val weight : String,
    val generateGraph : String,
    val randomGraph: String
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
    chinese = "Chinese",

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

    load = "Load",

    guide = "Graph Creation Guide",
    createVertex = "Create Vertex:",
    rightClick1 = """  
                                1) Right-click on empty area
                                2) Enter vertex value in the pop-up window
                        """,
    createEdge = "Create Edge:",
    rightClick2 = """
                                1) Right-click first vertex (source)
                                2) Right-click second vertex (target):
                                        - Directed graph: edge from 1st → 2nd vertex
                                        - Undirected graph: bidirectional edge 
                        """,
    editElements = "Edit Elements:",
    doubleClick = """
                                1) Double-click vertex ID or edge weight
                                2) Enter new value
                                """,
    deleteElements = "Delete Elements:",
    clickOnVertex = """
                                1) Click on vertex/edge to select
                                2) Press Delete key
                            """,
    additional = "Additional:",
    newGraphFile = """
                                1) New Graph: File → New Graph
                            """,
    aboutGravViz = "About GraphViz",
    version = "Version 1.0",
    aLightweight = "A lightweight tool for graph visualization and analysis",
    keyFeatures = "Key features:",
    createAndEdit = """
                                        - Create and edit graphs
                                        - 10+ built-in algorithms
                                        - Export to JSON/SQLite/Neo4j
                        """,
    vertexLabels = "Vertex Labels",
    edgeWeights = "Edge weights",
    enterEdgeWeight = "Enter edge weight",
    confirm = "Confirm",
    add = "Add",
    enterVertexLabel = "Enter vertex label",
    vertexLabel = "Vertex label",
    weight = "Weight",

    generateGraph = "Generate Graph",
    randomGraph = "Random Graph"
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
    chinese = "Китайский",

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

    load = "Открыть",

    guide = "Руководство по созданию графиков",
    createVertex = "Создать вершину:",
    rightClick1 = """  
                                1) Щелкните правой кнопкой мыши по пустой области
                                2) Введите значение вершины во всплывающем окне
                        """,
    createEdge = "Создать ребро:",
    rightClick2 = """
                                1) Щелкните правой кнопкой мыши первую вершину (источник)
                                2) Щелкните правой кнопкой мыши вторую вершину (цель):
                                    - Направленный граф: ребро от 1-й → 2-й вершины
                                    - Ненаправленный граф: двунаправленное ребро
                        """,
    editElements = "Редактировать элементы: ",
    doubleClick = """
                                1) Дважды щелкните идентификатор вершины или вес ребра
                                2) Введите новое значение
                                """,
    deleteElements = "Удалить элементы:",
    clickOnVertex = """
                                1) Щелкните вершину/ребро, чтобы выбрать
                                2) Нажмите клавишу Delete
                            """,
    additional = "Дополнительный:",
    newGraphFile = """
                                1) Новый график: Файл → Новый график
                            """,
    aboutGravViz = "Про GraphViz",
    version = "Версия 1.0",
    aLightweight = "Легкий инструмент для визуализации и анализа графиков",
    keyFeatures = "Ключевые фичи",
    createAndEdit = """
                                - Создание и редактирование графиков
                                - 10+ встроенных алгоритмов
                                - Экспорт в JSON/SQLite/Neo4j
    """,
    vertexLabels = "Названия вершин",
    edgeWeights = "Веса ребер",
    enterEdgeWeight = "Введите вес ребра",
    confirm = "Подтвердить",
    add = "Добавить",
    enterVertexLabel = "Добавить название вершины",
    vertexLabel = "Название вершины",
    weight = "Вес",
    generateGraph = "Сгенерировать граф",
    randomGraph = "Рандомный граф"
)
val chineseResources = TextResources(
    fileMenu = "文件",
    editMenu = "编辑",
    viewMenu = "视图",
    algorithmsMenu = "算法",
    settingsMenu = "设置",
    helpMenu = "帮助",

    newGraph = "新建图表",
    open = "打开",
    recentGraphs = "最近打开的图表:",
    loadGraph = "加载图表",
    save = "保存",
    saveAs = "另存为",
    reset = "重置",

    undo = "撤销",
    redo = "重做",

    settings = "设置",
    resetDefault = "恢复默认设置",

    language = "语言",

    aboutProgram = "关于程序",
    quickStart = "快速入门",

    english = "英文",
    russian = "俄文",
    chinese = "中文",

    themeLight = "浅色",
    themeDark = "深色",

    algorithmsDialog = "算法",
    basic = "基础",
    classic = "经典",
    stronglyConnected = "强连通分量",
    findCycles = "查找循环",
    minimalTree = "最小生成树",
    fordBellman = "福特-贝尔曼算法",
    layout = "平面图布局",
    findCommunities = "查找社区",
    startAlgo = "开始",

    viewDialog = "视图",
    theme = "主题:",
    cancel = "取消",
    ok = "确定",
    light = "浅色",
    dark = "深色",

    close = "关闭",
    chooseType = "选择类型",
    weights = "权重",
    directed = "有向图",
    createGraph = "创建图表",

    enterGraphName = "输入图表名称",
    chooseFormat = "选择保存格式:",
    addFolder = "添加文件夹",
    showWeights = "显示边权重",
    showVertexLabels = "显示顶点标签",

    load = "加载",

    guide = "图表创建指南",
    createVertex = "创建顶点:",
    rightClick1 = """  
                                1) 在空白处右键点击
                                2) 在弹出窗口中输入顶点值
                        """,
    createEdge = "创建边:",
    rightClick2 = """
                                1) 右键点击第一个顶点(源)
                                2) 右键点击第二个顶点(目标):
                                        - 有向图: 边从第1个顶点指向第2个顶点
                                        - 无向图: 双向边 
                        """,
    editElements = "编辑元素:",
    doubleClick = """
                                1) 双击顶点ID或边权重
                                2) 输入新值
                                """,
    deleteElements = "删除元素:",
    clickOnVertex = """
                                1) 点击顶点/边进行选择
                                2) 按下删除键
                            """,
    additional = "附加说明:",
    newGraphFile = """
                                1) 新建图表: 文件 → 新建图表
                            """,
    aboutGravViz = "关于 GraphViz",
    version = "版本 1.0",
    aLightweight = "轻量级图可视化与分析工具",
    keyFeatures = "主要功能",
    createAndEdit = """
                                - 创建和编辑图结构
                                - 10+ 内置算法
                                - 导出为 JSON/SQLite/Neo4j
    """,
    vertexLabels = "顶点标签",
    edgeWeights = "边权重",
    enterEdgeWeight = "输入边权重",
    confirm = "确认",
    add = "添加",
    enterVertexLabel = "输入顶点标签",
    vertexLabel = "顶点标签",
    weight = "戈伊达",
    generateGraph = "戈伊达",
    randomGraph = "随机图",
)

fun getResources(language: AppLanguage): TextResources {
    return when (language) {
        AppLanguage.ENGLISH -> englishResources
        AppLanguage.RUSSIAN -> russianResources
        AppLanguage.CHINESE -> chineseResources
    }
}

val LocalTextResources = compositionLocalOf { englishResources }

