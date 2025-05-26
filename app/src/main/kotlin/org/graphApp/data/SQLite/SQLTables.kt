package data.SQLiteMainLogic

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption

object Vertices : IntIdTable() {
    val vertex_id = long("vid")
    val x = float("x_coord")
    val y = float("y_coord")
    val label = text("label")
    val graph_id = integer("graph_id").references(Graphs.id, onDelete = ReferenceOption.CASCADE)

    init {
        uniqueIndex(vertex_id, graph_id)
    }
}

object Edges : IntIdTable() {
    val fromVertex = long("from_id")
    val toVertex = long("to_id")
    val weight = text("weight")
    val label = text("label")
    val graph_id = integer("graph_id").references(Graphs.id, onDelete = ReferenceOption.CASCADE)
}

object Graphs : IntIdTable() {
    val graphName = varchar("name", 255).uniqueIndex()
    val isDirected = bool("directed")
    val isWeighted = bool("weighted")
    val label = text("label")
}
