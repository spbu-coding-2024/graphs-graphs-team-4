package org.graphApp.model.graph


internal class GenericGraph<V, E> : Graph<V, E> {
    private val _vertices = hashMapOf<V, Vertex<V>>()
    private val _edges = hashMapOf<E, Edge<E, V>>()

    override val vertices: Collection<Vertex<V>>
        get() = _vertices.values

    override val edges: Collection<Edge<E, V>>
        get() = _edges.values

    override fun addVertex(v: V): Vertex<V> = _vertices.getOrPut(v) { UndirectedVertex(v) }

    override fun addEdge(u: V, v: V, e: E): Edge<E, V> {
        val first = addVertex(u)
        val second = addVertex(v)
        return _edges.getOrPut(e) { UndirectedEdge(e, first, second) }
    }

    override fun getVertexDegree(v: Vertex<V>): Int =
        _edges.values.count {
            val (first, second) = it.vertices
            it is UndirectedEdge && (first == v || second == v)
        }

    fun getInDegree(v: Vertex<V>): Int =
        _edges.values.count {
            it is DirectedEdge && it.to == v
        }


    fun getOutDegree(v: Vertex<V>): Int =
        _edges.values.count {
            it is DirectedEdge && it.from == v
        }

    private data class UndirectedVertex<V>(override var element: V) : Vertex<V>
    private data class DirectedVertex<V>(override var element: V) : Vertex<V>

    private data class UndirectedEdge<E, V>(
        override var element: E,
        var first: Vertex<V>,
        var second: Vertex<V>,
    ) : Edge<E, V> {
        override val vertices
            get() = first to second
    }

    private data class DirectedEdge<E, V>(
        override var element: E,
        var from: Vertex<V>,
        var to: Vertex<V>,
    ) : Edge<E, V> {
        override val vertices
            get() = from to to
    }

}