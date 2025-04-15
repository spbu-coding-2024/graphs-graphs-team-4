package org.graphApp.model.graph

interface UndirectedGraphInterface<V, E> : GraphInterface<V, E> {
    override val vertices: Collection<Vertex<V>>
    override val edges: Collection<Edge<E, V>>

    override fun addVertex(v: V): Vertex<V>
    override fun addEdge(u: V, v: V, e: E): Edge<E, V>
    fun getVertexDegree(v: V): Int
}