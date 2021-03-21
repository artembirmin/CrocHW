package ru.artembirmin.croc.hw4.graph.components;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Вершина.
 *
 * @param <V> Тип значения вершины.
 */
public class Vertex<V> {
    private final String key;
    private final V value;
    private final Set<Edge<V>> edges = new HashSet<>();
    private final Set<Vertex<V>> neighbors = new HashSet<>();

    public Vertex(String key, V value) {
        this.key = key;
        this.value = value;
    }

    public Set<Vertex<V>> getNeighbors() {
        return neighbors;
    }

    public Set<Edge<V>> getEdges() {
        return edges;
    }

    public void addEdge(Edge<V> edge) {
        edges.add(edge);
        if (!edge.getVertex1().equals(this)) {
            neighbors.add(edge.getVertex1());
        } else {
            neighbors.add(edge.getVertex2());
        }
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "key='" + key + '\'' +
                ", value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex)) return false;
        Vertex<?> vertex = (Vertex<?>) o;
        return Objects.equals(key, vertex.key) &&
                Objects.equals(value, vertex.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, value);
    }

    public void deleteEdge(Edge<V> edge) {
        edges.remove(edge);
        if (!edge.getVertex1().equals(this)) {
            neighbors.remove(edge.getVertex1());
        } else {
            neighbors.remove(edge.getVertex2());
        }
    }
}
