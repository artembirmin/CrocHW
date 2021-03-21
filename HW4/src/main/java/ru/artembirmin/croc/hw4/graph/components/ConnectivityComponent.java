package ru.artembirmin.croc.hw4.graph.components;

import java.util.*;

/**
 * Компонента связности.
 *
 * @param <V> Тип значения вершин.
 */
public class ConnectivityComponent<V> implements Comparable<ConnectivityComponent<V>> {
    private final Set<Vertex<V>> vertices = new HashSet<>();

    @SafeVarargs
    public ConnectivityComponent(Vertex<V>... vertices) {
        this.vertices.addAll(Arrays.asList(vertices));
    }

    public ConnectivityComponent(Set<Vertex<V>> vertices) {
        this.vertices.addAll(vertices);
    }

    public void addVertex(Vertex<V> vertex) {
        vertices.add(vertex);
    }

    public void addVertex(Collection<Vertex<V>> vertex) {
        vertices.addAll(vertex);
    }

    public void deleteVertex(Vertex<V> vertex){
        vertices.remove(vertex);
    }

    public boolean contains(Vertex<V> vertex) {
        return vertices.contains(vertex);
    }

    public Vertex<V> findVertexLink(Vertex<V> vertex){
        Vertex<V> vertexLink = null;
        for (Vertex<V> v : vertices) {
            if(v.equals(vertex)){
                vertexLink = v;
            }
        }
        return vertexLink;
    }

    public int getVerticesCount() {
        return vertices.size();
    }

    @Override
    public int compareTo(ConnectivityComponent<V> o) {
//        if(this.equals(o)){
//            return this.getVerticesCount() - o.getVerticesCount() +1;
//        } else{
//            return this.getVerticesCount() - o.getVerticesCount() + 1;
//        }
        //System.out.println("");
       if(this == o){
           //System.out.println("0");
           return 0;
       } else if(this.getVerticesCount()  < o.getVerticesCount()){
           //System.out.println("1");
           return 1;
       } else if(this.getVerticesCount() >= o.getVerticesCount() ) {
           //System.out.println("-1");
           return -1;
       }    else{
           //System.out.println("else");
           return 0;
       }
    }

    public Collection<Vertex<V>> getAllVertices() {
        return vertices;
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
