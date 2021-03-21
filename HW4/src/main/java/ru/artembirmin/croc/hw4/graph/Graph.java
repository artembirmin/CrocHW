package ru.artembirmin.croc.hw4.graph;

import ru.artembirmin.croc.hw4.graph.components.ConnectivityComponent;
import ru.artembirmin.croc.hw4.graph.components.Edge;
import ru.artembirmin.croc.hw4.graph.components.Vertex;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Graph<V> {

    private final Set<ConnectivityComponent<V>> components = new TreeSet<>();

    public Graph() {
    }

    /**
     * Добавление вершины в граф.
     * Вершина должна быть одиночной, то есть без ребер.
     * Если вершина с этим ключем уже есть в графе, то добавение будет игнорированно.
     *
     * @param vertex Добавляемая вершина.
     */
    public void addVertex(Vertex<V> vertex) {
        if (findContainingComponent(vertex) == null) {
            components.add(new ConnectivityComponent<>(vertex));
        }
    }

    /**
     * Удаление вершины из графа.
     * При удалении вершины, удаляются все ребра, связанные с ней.
     * Возможно образование новых компонент связности.
     *
     * @param vertex Удаляемая вершина.
     */
    public void deleteVertex(Vertex<V> vertex) {
        ConnectivityComponent<V> component = findContainingComponent(vertex);
        Vertex<V> vertexLink = component.findVertexLink(vertex);
        for (Edge<V> edge : vertexLink.getEdges()) {
            deleteEdge(edge);
        }
        components.remove(component);
    }

    /**
     * Удаляет ребро.
     * При удалении могут образоваться новые компоненты связности.
     * Для проверки этого будет совершен обход компоненты от каждой вершины
     * с добавлением в коллекцию каждой найденной вершины. Будет два сета. Если в каждом из них содержатся обе вершины,
     * то связность не нарушена, иначе их этих сетов будут сделаны новые компоненты.
     *
     * @param edge Удаляемое ребро.
     */
    public void deleteEdge(Edge<V> edge) {
        System.out.println("Удаляемое " + edge);
        Vertex<V> vertex1 = edge.getVertex1();
        //Компонента, где содержатся обе вершины
        Vertex<V> vertex2 = edge.getVertex2();
        System.out.println("V1 edges " + vertex1.getEdges());
        System.out.println("V2 edges " + vertex2.getEdges());
        ConnectivityComponent<V> component = findContainingComponent(vertex1);
        edge.clear();
        System.out.println("V1 edges " + vertex1.getEdges());
        System.out.println("V2 edges " + vertex2.getEdges());
        Set<Vertex<V>> connectedVertices1 = findConnectedVertices(vertex1);
        Set<Vertex<V>> connectedVertices2 = findConnectedVertices(vertex2);
        if (connectedVertices1.equals(connectedVertices2)) {
            return;
        } else {
            components.remove(component);
            components.add(new ConnectivityComponent<>(connectedVertices1));
            components.add(new ConnectivityComponent<>(connectedVertices2));
        }
    }

    /**
     * TODO
     *
     * @param vertex
     * @return
     */
    public Set<Vertex<V>> findConnectedVertices(Vertex<V> vertex) {
        System.out.println(vertex);
        Set<Vertex<V>> component = new HashSet<>();
        if (vertex.getEdges().size() == 0) {
            component.add(vertex);
            return component;
        }
        System.out.println(vertex.getEdges());
        for (Edge<V> edge : vertex.getEdges()) {
            System.out.println(edge);
            Vertex<V> vertex1 = edge.getVertex1();
            Vertex<V> vertex2 = edge.getVertex2();
            if (vertex1.equals(vertex)) {
                System.out.println("then");
                component.add(vertex2);
                component.addAll(findConnectedVertices(vertex2));
            } else {
                System.out.println("else");
                component.add(vertex1);
            }
        }
        //Если точка не была в ребрах
        component.add(vertex);
        System.out.println("Return");
        return component;
    }

    /**
     * Находит компоненту, содержащую вершину.
     *
     * @param vertex Проверяемая вершина
     * @return Компонента связности, если содержит вершину, иначе null
     */
    private ConnectivityComponent<V> findContainingComponent(Vertex<V> vertex) {
        for (ConnectivityComponent<V> component : components) {
            if (component.contains(vertex)) {
                return component;
            }
        }
        return null;
    }

    /**
     * Добавляет ребро в граф.
     * Обе вершины должны быть в графе.
     * Если они в разных компонентах, то они объединяются.
     * Если в одной, то ребро было проведено во время его создания и доп. действий не требуется.
     *
     * @param edge Добавляемое ребро.
     */
    public void addEdge(Edge<V> edge) {
        Vertex<V> vertex1 = edge.getVertex1();
        Vertex<V> vertex2 = edge.getVertex2();
        vertex1.addEdge(edge);
        vertex2.addEdge(edge);
        ConnectivityComponent<V> component1 = findContainingComponent(vertex1);
        ConnectivityComponent<V> component2 = findContainingComponent(vertex2);
        if (component1 != null && component2 != null && component1 != component2) {
            component1.addVertex(component2.getAllVertices());
            components.remove(component2);
        }
    }

    @Override
    public String toString() {
        return "Graph{ \n" +
                "components=" + components +
                "\n}";
    }
}
