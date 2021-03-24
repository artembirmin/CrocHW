package ru.artembirmin.croc.hw4.graph.components;

import java.util.*;

/**
 * Компонента связности.
 *
 * @param <V> Тип значения вершин
 */
public class ConnectivityComponent<V> implements Comparable<ConnectivityComponent<V>> {

    /**
     *
     */
    private final Set<Vertex<V>> vertices = new TreeSet<>();

    /**
     * @param vertices Массив вершин
     */
    @SafeVarargs
    public ConnectivityComponent(Vertex<V>... vertices) {
        this.vertices.addAll(Arrays.asList(vertices));
    }

    /**
     * @param vertices Множество вершин
     */
    public ConnectivityComponent(Set<Vertex<V>> vertices) {
        this.vertices.addAll(vertices);
    }

    /**
     * Добавляет вершину в компоненту.
     *
     * @param vertex Добавляемая вершина
     */
    public void addVertex(Vertex<V> vertex) {
        vertices.add(vertex);
    }

    /**
     * Добавляет множество вершин.
     *
     * @param vertex Добавляемое множество вершин
     */
    public void addVertex(Collection<Vertex<V>> vertex) {
        vertices.addAll(vertex);
    }

    /**
     * Проверяет веришну на содержиание в компоненте.
     *
     * @param vertex Проверяемая вершина
     * @return {@code true}, если вершина содержится в компоненте
     */
    public boolean contains(Vertex<V> vertex) {
        return vertices.contains(vertex);
    }

    /**
     * @return Количество вершин в компоненте
     */
    public int getVerticesCount() {
        return vertices.size();
    }

    public Collection<Vertex<V>> getAllVertices() {
        return vertices;
    }

    @Override
    public int compareTo(ConnectivityComponent<V> that) {
        if (this.getVerticesCount() == that.getVerticesCount()) {
            int count = 0;//FIXME Название придумай
            Iterator<Vertex<V>> iteratorOfThis = this.vertices.iterator();
            Iterator<Vertex<V>> iteratorOfThat = that.vertices.iterator();
            while (iteratorOfThis.hasNext()) {
                count = iteratorOfThis.next().compareTo(iteratorOfThat.next());
            }
            return count;
        } else {
            return this.getVerticesCount() - that.getVerticesCount();
        }
    }

    @Override
    public String toString() {
        return "ConnectivityComponent{\n" +
                "vertices=" + vertices +
                "\n}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConnectivityComponent)) return false;
        ConnectivityComponent<?> that = (ConnectivityComponent<?>) o;
        return Objects.equals(vertices, that.vertices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertices);
    }
}
