package org.graphApp.view.algorithms

import androidx.compose.ui.unit.dp
import org.gephi.graph.api.GraphController
import org.graphApp.view.graph.RepresentationStrategy
import org.graphApp.viewmodel.graph.GraphViewModel
import org.graphApp.viewmodel.graph.VertexViewModel
import org.gephi.graph.api.Node
import org.gephi.layout.plugin.forceAtlas2.ForceAtlas2
import org.gephi.project.api.ProjectController
import org.openide.util.Lookup
import java.awt.Toolkit
import kotlin.random.Random
import kotlin.uuid.ExperimentalUuidApi

class GraphsLayout<V, E> : RepresentationStrategy<V, E> {
    @OptIn(ExperimentalUuidApi::class)
    override fun place(
        width: Double,
        height: Double,
        originalGraph: GraphViewModel<V, E>
    ) {
        val width = Toolkit.getDefaultToolkit().screenSize.width
        val height = Toolkit.getDefaultToolkit().screenSize.height
        val pc = Lookup.getDefault().lookup(ProjectController::class.java)
        pc.newProject()

        val graphModel = Lookup.getDefault().lookup(GraphController::class.java).graphModel
        val graph = graphModel.undirectedGraph
        val map = mutableMapOf<Long, Node>()

        originalGraph.vertices.forEach { vertex ->
            val node = graphModel.factory().newNode(vertex.vertexID.toString())
            node.setX(Random.nextFloat())
            node.setY(Random.nextFloat())
            map[vertex.vertexID] = node
            graph.addNode(node)
        }

        originalGraph.edges.forEach { edge ->
            val edge = graphModel.factory().newEdge(
                map[edge.u.vertexID]!!,
                map[edge.v.vertexID]!!,
                1,
                false
            )

            graph.addEdge(edge)
        }

        val layout = ForceAtlas2(null)
        layout.setGraphModel(graphModel)
        layout.resetPropertiesValues()

        layout.initAlgo()
        repeat(100) {
            if (!layout.canAlgo()) return@repeat
            layout.goAlgo()
        }
        layout.endAlgo()

        originalGraph.vertices.forEach { vertex ->
            val node = graph.getNode(vertex.vertexID.toString())
            val x = ((width / 2 + node.x() * 30))
            val y = ((height / 2 + node.y() * 30))
            vertex.x = x.dp
            vertex.y = y.dp
        }
    }

    override fun move(
        old: Pair<Int, Int>,
        new: Pair<Int, Int>,
        vertices: Collection<VertexViewModel<V>>
    ) {
    }

}