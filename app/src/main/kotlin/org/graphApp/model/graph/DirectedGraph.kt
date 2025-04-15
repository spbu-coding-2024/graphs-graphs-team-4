package org.graphApp.model.graph

internal class DirectedGraph<V,E> : DirectedGraphInterface<V, E> {
    private val _vertices = hashMapOf<V, Vertex<V>>()
    private val _edges = hashMapOf<E, Edge<E, V>>()

    override val vertices: Collection<Vertex<V>>
        get() = _vertices.values

    override val edges: Collection<Edge<E, V>>
        get() = _edges.values

    override fun getOutDegree(v: V): Int =
        _edges.values.count {
            it is DirectedEdge && it.from == v
        }

    override fun getInDegree(v: V): Int =
        _edges.values.count {
            it is DirectedEdge && it.to == v
        }

    override fun addVertex(v: V): Vertex<V> = _vertices.getOrPut(v) { DirectedVertex(v) }

    override fun addEdge(u: V, v: V, e: E): Edge<E, V> {
        val first = addVertex(u)
        val second = addVertex(v)
        return _edges.getOrPut(e) { DirectedEdge(e, first, second) }
    }

    private data class DirectedVertex<V>(override var element: V) : Vertex<V>

    private data class DirectedEdge<E, V>(
        override var element: E?,
        var from: Vertex<V>,
        var to: Vertex<V>,
    ) : Edge<E, V> {
        override val vertices
            get() = from to to
    }

}