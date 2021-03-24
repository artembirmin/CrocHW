package ru.artembirmin.croc.hw4.graph;

import ru.artembirmin.croc.hw4.graph.components.ConnectivityComponent;
import ru.artembirmin.croc.hw4.graph.components.Edge;
import ru.artembirmin.croc.hw4.graph.components.Vertex;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Граф.
 *
 * @param <V> Тип значения веришны
 */
public class Graph<V> {

    private final Set<ConnectivityComponent<V>> components = new TreeSet<>();

    public Graph() {
    }

    /**
     * Добавление вершины в граф.
     * Вершина должна быть одиночной, то есть без ребер.
     *
     * @param vertex Добавляемая вершина
     */
    public void addVertex(Vertex<V> vertex) {
        components.add(new ConnectivityComponent<>(vertex));
    }

    /**
     * Добавляет ребро в граф.
     * Обе вершины должны быть в графе.
     * Если они в разных компонентах, то они объединяются.
     * Если в одной, то ребро было проведено во время его создания и доп. действий не требуется.
     *
     * @param edge Добавляемое ребро
     */
    public void addEdge(Edge<V> edge) {
        Vertex<V> vertex1 = edge.getVertex1();
        Vertex<V> vertex2 = edge.getVertex2();
        vertex1.addEdge(edge);
        vertex2.addEdge(edge);
        ConnectivityComponent<V> component1 = findContainingComponent(vertex1);
        ConnectivityComponent<V> component2 = findContainingComponent(vertex2);
        if (component1 != null && component2 != null && !component1.equals(component2)) {
            component1.addVertex(component2.getAllVertices());
            components.remove(component2);
        }
    }

    /**
     * Удаление вершины из графа.
     * При удалении вершины, удаляются все ребра, связанные с ней.
     * Возможно образование новых компонент связности.
     *
     * @param vertex Удаляемая вершина
     */
    public void deleteVertex(Vertex<V> vertex) {
        ConnectivityComponent<V> component = findContainingComponent(vertex);
        //Без массива исключение о том, что коллекция изменяется во время итерации.
        Object[] edges = vertex.getEdges().toArray();
        for (Object edge : edges) {
            deleteEdge((Edge<V>) edge);
        }
        components.remove(component);
        components.remove(findContainingComponent(vertex));
    }

    /**
     * Удаляет ребро.
     * При удалении могут образоваться новые компоненты связности.
     * Для проверки этого будут найдены все вершины, с которыми связана первая и вторая врешина ребра.
     * Если множества таких вершин в обоих случаях одинаковые, значит компонента связности осталасть связной,
     * иначе будут сделаны новые компоненты.
     *
     * @param edge Удаляемое ребро
     */
    public void deleteEdge(Edge<V> edge) {
        Vertex<V> vertex1 = edge.getVertex1();
        Vertex<V> vertex2 = edge.getVertex2();
        ConnectivityComponent<V> component = findContainingComponent(vertex1);
        edge.clear();
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

    public Set<ConnectivityComponent<V>> getComponents() {
        return components;
    }

    public int getComponentsCount() {
        return components.size();
    }

    /**
     * Ищет вершины, до которых можно добраться из переданной веришны.
     *
     * @param vertex Вершина, с которой начинается поиск.
     * @return Множество всех найденных вершин.
     */
    private Set<Vertex<V>> findConnectedVertices(Vertex<V> vertex) {
        Set<Vertex<V>> component = new HashSet<>();
        if (vertex.getNeighbors().size() == 0) {
            component.add(vertex);
            return component;
        }
        //Перебираем всех соседей
        for (Vertex<V> neighbor : vertex.getNeighbors()) {
            //Добавили соседа
            component.add(neighbor);
            //Выполняем поиск из соседа
            component.addAll(findConnectedVertices(neighbor, component));
        }
        //Не всегда добавляется в вспомогательных методах
        component.add(vertex);
        return component;
    }

    /**
     * Ищет вершины, до которых можно добраться из вершины-соседа.
     *
     * @param neighbor  Вершина-сосед
     * @param component Множество вершин, до которых добрались из веришны-родителя
     * @return Множество вершин, до которых можно добраться. Вместе с врешинами, до которых можно одобраться из соседа
     */
    private Set<Vertex<V>> findConnectedVertices(Vertex<V> neighbor, Set<Vertex<V>> component) {
        if (neighbor.getNeighbors().size() == 0) {
            component.add(neighbor);
            return component;
        }
        //Перебор соседов соседа
        Set<Vertex<V>> neighbors = neighbor.getNeighbors();
        for (Vertex<V> neighborsNeighbor : neighbors) {
            //Вершина-родитель является соседом. Если текущий neighborsNeighbor является родителем neighbor
            //то добавление вернет false, иначе добавит в множество и пойдет дальше
            if (!component.add(neighborsNeighbor)) {
                continue;
            }
            //Вызов метода для каждого соседа соседа
            component.addAll(findConnectedVertices(neighborsNeighbor, component));
        }
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

    @Override
    public String toString() {
        return "Graph{ \n" +
                "components=" + components +
                "\n}";
    }
}
