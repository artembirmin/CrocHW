package ru.artembirmin.croc.hw4.graph.components;

import ru.artembirmin.croc.hw4.graph.Graph;

import java.util.Objects;

/**
 * Ребро.
 *
 * @param <V> Тип значения вершин.
 */
public class Edge<V> {
    private Vertex<V> vertex1;
    private Vertex<V> vertex2;

    /**
     * Для добавления ребра в граф не достаточно создать ребро.
     * Необходимо использовать метод {@link Graph#addEdge(Edge)}.
     *
     * @param vertex1 Первая вершина
     * @param vertex2 Вторая вершина
     */
    public Edge(Vertex<V> vertex1, Vertex<V> vertex2) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
    }

    public Vertex<V> getVertex1() {
        return vertex1;
    }

    public Vertex<V> getVertex2() {
        return vertex2;
    }

    /**
     * Удаляет ребро из вершин и вершины из ребра.
     */
    public void clear() {
        vertex1.deleteEdge(this);
        vertex2.deleteEdge(this);
        vertex1 = null;
        vertex2 = null;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "vertex1=" + vertex1 +
                ", vertex2=" + vertex2 +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;
        Edge<?> edge = (Edge<?>) o;
        return Objects.equals(getVertex1(), edge.getVertex1()) &&
                Objects.equals(getVertex2(), edge.getVertex2());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVertex1(), getVertex2());
    }
}
