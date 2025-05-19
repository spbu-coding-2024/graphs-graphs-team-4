package  org.graphApp.model.graph.algorithms

import org.graphApp.model.graph.*
import org.jetbrains.research.ictl.louvain.Link
import org.jetbrains.research.ictl.louvain.getPartition

data class ClusterEdge(
    private val origin: Int,
    private val destination: Int,
    private val connectionWeight: Double
) : Link {
    override fun source(): Int = origin
    override fun target(): Int = destination
    override fun weight(): Double = connectionWeight
}

class FindCommunities<V,E>(
    private val graph: Graph<V,E>
) {
    private val resolutionDepth = 3

    private fun extractWeight(edge: Edge<E, V>): Double {
        return if (edge is WeightedEdge<*, *>) {
            try {
                (edge as WeightedEdge<E, V>).weight.toString().toDouble()
            } catch (e: Exception) {
                1.0
            }
        } else {
            1.0
        }
    }

    fun findCommunities(): Map<Int, List<Long>>? {
        if (graph !is WeightedGraph<*, *> && graph !is DirectedWeightedGraph<*, *>) return null

        val linkSet = graph.edges.map {
            ClusterEdge(
                origin = it.vertices.first.id.toInt(),
                destination = it.vertices.second.id.toInt(),
                connectionWeight = extractWeight(it)
            )
        }

        val allVertices = graph.vertices
        val detectedGroups = getPartition(linkSet, resolutionDepth).toMutableMap()

        var unusedLabel = (detectedGroups.values.maxOrNull() ?: -1) + 1

        for (node in allVertices) {
            val nodeId = node.id.toInt()
            if (!detectedGroups.containsKey(nodeId)) {
                detectedGroups[nodeId] = unusedLabel++
            }
        }

        return detectedGroups.entries.groupBy(
            keySelector = { it.value },
            valueTransform = { it.key.toLong() }
        )
    }
}
