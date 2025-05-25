package data.SQLiteMainLogic

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.dp
import org.graphApp.model.graph.DirectGraph
import org.graphApp.model.graph.DirectWeightedGraph
import org.graphApp.model.graph.DirectedEdge
import org.graphApp.model.graph.Graph
import org.graphApp.model.graph.UndirectedGraph
import org.graphApp.model.graph.WeightedGraph
import org.graphApp.viewmodel.graph.GraphViewModel
import org.graphApp.viewmodel.graph.VertexViewModel

class SQLiteMainLogic<E, V>(val connection: SQLiteExposed) {

    fun saveToSQLiteDataBase(viewModel: GraphViewModel<V, E>, name: String): Boolean {
        return try {
            println("Saving graph: $name")
            println("Vertices: ${viewModel.vertices}")
            println("Edges: ${viewModel.edges}")

            val id = connection.addGraph(
                graphName = name,
                isWeightedVal = viewModel.showEdgesWeights.value,
                isDirected = viewModel.graph.edges.any { it is DirectedEdge<*, *> }
            )

            if (id == -1) {
                println("Graph not added, ID is -1")
                return false
            }

            connection.addAllVertices(id, viewModel.vertices)
            connection.addAllEdges(id, viewModel.edges)
            true
        } catch (e: Exception) {
            println("Exception while saving graph: ${e.message}")
            e.printStackTrace()
            false
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <V,E> readFromSQLiteDataBase(name: String): GraphViewModel<V,E>? {
        try {
            val gi = connection.getGraph(name) ?: return null
            val vertices = connection.getVertices(gi.id)
            val edges = connection.getEdges(gi.id)

            val graph: Graph<V, E> = when {
                gi.isDirected && gi.isWeighted -> DirectWeightedGraph()
                gi.isDirected && !gi.isWeighted -> DirectGraph()
                !gi.isDirected && gi.isWeighted -> WeightedGraph()
                else -> UndirectedGraph()
            }

            val showVerticesLabels = mutableStateOf(true)
            val showEdgeWeights = mutableStateOf(true)
            val isWeightedGraph = mutableStateOf(gi.isWeighted)
            val isDirectedGraph = mutableStateOf(gi.isDirected)

            val viewModel = GraphViewModel(
                graph = graph,
                showVerticesLabels = showVerticesLabels,
                showEdgesWeights = showEdgeWeights,
                isWeightedGraph = isWeightedGraph,

                isDirectedGraph = isDirectedGraph
            )

            val vertexMap = mutableMapOf<Long, VertexViewModel<V>>()
            vertices.forEach { vertexInfo ->
                val vertexViewModel = viewModel.addVertex(
                    label = vertexInfo.label as V,
                    x = vertexInfo.x.dp,
                    y = vertexInfo.y.dp
                )

                vertexMap[vertexInfo.id] = vertexViewModel

            }

            edges.forEach { edgeInfo ->
                val fromVertex = vertexMap[edgeInfo.vertexFrom]
                val toVertex = vertexMap[edgeInfo.vertexTo]

                if (fromVertex != null && toVertex != null) {
                    viewModel.addEdge(
                        fromVertedID = fromVertex.vertexID,
                        toVertexID = toVertex.vertexID,
                        edgeValue = edgeInfo.label as E,
                        weight = edgeInfo.weight
                    )
                }
            }

            return viewModel
        } catch(e : Exception) {
            println("Exception while reading graph ${e.message}")
            e.printStackTrace()
            return null
        }
    }
}
