package ru.artembirmin.croc.hw4.graph.components;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Вершина.
 *
 * @param <V> Тип значения вершины
 */
public class Vertex<V> implements Comparable<Vertex<V>> {
    private final String key;
    private final V value;
    private final Set<Edge<V>> edges = new HashSet<>();
    private final Set<Vertex<V>> neighbors = new HashSet<>();

    /**
     * @param key   Ключ
     * @param value Значение
     */
    public Vertex(String key, V value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Добавляет ребро вершине.
     *
     * @param edge Добавляемое ребро
     */
    public void addEdge(Edge<V> edge) {
        edges.add(edge);
        if (!edge.getVertex1().equals(this)) {
            neighbors.add(edge.getVertex1());
        } else {
            neighbors.add(edge.getVertex2());
        }
    }

    /**
     * Удаляет ребро из веришины.
     *
     * @param edge Удаляемое ребро
     */
    public void deleteEdge(Edge<V> edge) {
        edges.remove(edge);
        if (!edge.getVertex1().equals(this)) {
            neighbors.remove(edge.getVertex1());
        } else {
            neighbors.remove(edge.getVertex2());
        }
    }

    public Set<Vertex<V>> getNeighbors() {
        return neighbors;
    }

    public Set<Edge<V>> getEdges() {
        return edges;
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

    @Override
    public int compareTo(Vertex<V> o) {
        return o.key.compareTo(this.key);
    }

}
