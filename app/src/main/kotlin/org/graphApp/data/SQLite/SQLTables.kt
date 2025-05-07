package org.graphApp.data.SQLite

import org.jetbrains.exposed.dao.id.IntIdTable

object GraphTable : IntIdTable() {
    val graphName = varchar("name", 255).uniqueIndex()
    val isDirected = bool("directed")
    val isWeighted = bool("weighted")
    val label = text("label")
}

object VertexTable : IntIdTable() {
    val vertex_id = long("vid")
    val x = float("x_coord")
    val y = float("y_coord")
    val label = text("label")
    val graph_id = integer("graph_id").references(GraphTable.id)

    init {
        uniqueIndex(vertex_id, graph_id)
    }
}

object EdgeTable : IntIdTable() {
    val from = long("from_id")
    val to = long("to_id")
    val weight = text("weight")
    val label = text("label")
    val graph_id = integer("graph_id").references(GraphTable.id)
}
