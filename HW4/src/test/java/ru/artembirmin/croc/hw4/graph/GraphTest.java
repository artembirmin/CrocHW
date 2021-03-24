package ru.artembirmin.croc.hw4.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.artembirmin.croc.hw4.graph.components.ConnectivityComponent;
import ru.artembirmin.croc.hw4.graph.components.Edge;
import ru.artembirmin.croc.hw4.graph.components.Vertex;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GraphTest {

    Graph<Integer> graph;
    Vertex<Integer> vertex1;
    Vertex<Integer> vertex2;
    Vertex<Integer> vertex3;
    Vertex<Integer> vertex4;
    Vertex<Integer> vertex5;
    Vertex<Integer> vertex6;
    Vertex<Integer> vertex7;
    Vertex<Integer> vertex8;

    @BeforeEach
    void setUp() {
        graph = new Graph<>();
        vertex1 = new Vertex<>("1", 1);
        vertex2 = new Vertex<>("2", 2);
        vertex3 = new Vertex<>("3", 3);
        vertex4 = new Vertex<>("4", 4);
        vertex5 = new Vertex<>("5", 5);
        vertex6 = new Vertex<>("6", 6);
        vertex7 = new Vertex<>("7", 7);
        vertex8 = new Vertex<>("8", 8);
    }

    @Test
    void addVertexTest1() {
        addVertices();

        Object[] expectedComponents1 = {
                new ConnectivityComponent<>(vertex8),
                new ConnectivityComponent<>(vertex7),
                new ConnectivityComponent<>(vertex6),
                new ConnectivityComponent<>(vertex5),
                new ConnectivityComponent<>(vertex4),
                new ConnectivityComponent<>(vertex3),
                new ConnectivityComponent<>(vertex2),
                new ConnectivityComponent<>(vertex1)
        };
        Object[] graphComponents = graph.getComponents().toArray();
        for (int i = 0; i < expectedComponents1.length; i++) {
            assertEquals(expectedComponents1[i], graphComponents[i]);
        }
        assertEquals(graphComponents.length, graph.getComponentsCount());
    }

    @Test
    void addEdgeTest1() {
        addVertices();

        graph.addEdge(new Edge<>(vertex1, vertex2));
        graph.addEdge(new Edge<>(vertex2, vertex3));
        graph.addEdge(new Edge<>(vertex3, vertex4));
        graph.addEdge(new Edge<>(vertex1, vertex3));

        graph.addEdge(new Edge<>(vertex5, vertex8));
        graph.addEdge(new Edge<>(vertex7, vertex6));
        graph.addEdge(new Edge<>(vertex5, vertex6));

        Object[] expectedComponents1 = {
                new ConnectivityComponent<>(vertex8, vertex7, vertex6, vertex5),
                new ConnectivityComponent<>(vertex4, vertex3, vertex2, vertex1)
        };
        Object[] graphComponents = graph.getComponents().toArray();
        for (int i = 0; i < expectedComponents1.length; i++) {
            assertEquals(expectedComponents1[i], graphComponents[i]);
        }
        assertEquals(graphComponents.length, graph.getComponentsCount());
    }

    @Test
    void addEdgeTest2() {
        addVertices();

        graph.addEdge(new Edge<>(vertex1, vertex2));
        graph.addEdge(new Edge<>(vertex1, vertex3));
        graph.addEdge(new Edge<>(vertex1, vertex4));
        graph.addEdge(new Edge<>(vertex1, vertex5));
        graph.addEdge(new Edge<>(vertex1, vertex6));
        graph.addEdge(new Edge<>(vertex1, vertex7));
        graph.addEdge(new Edge<>(vertex1, vertex8));

        Object[] expectedComponents1 = {
                new ConnectivityComponent<>(vertex8, vertex7, vertex6,
                        vertex5, vertex4, vertex3,
                        vertex2, vertex1)
        };
        Object[] graphComponents = graph.getComponents().toArray();
        for (int i = 0; i < expectedComponents1.length; i++) {
            assertEquals(expectedComponents1[i], graphComponents[i]);
        }
        assertEquals(graphComponents.length, graph.getComponentsCount());
    }

    @Test
    void addEdgeTest3() {
        addVertices();

        graph.addEdge(new Edge<>(vertex1, vertex2));
        graph.addEdge(new Edge<>(vertex2, vertex3));
        graph.addEdge(new Edge<>(vertex3, vertex4));
        graph.addEdge(new Edge<>(vertex1, vertex3));

        graph.addEdge(new Edge<>(vertex5, vertex8));
        graph.addEdge(new Edge<>(vertex7, vertex6));
        graph.addEdge(new Edge<>(vertex5, vertex6));
        graph.addEdge(new Edge<>(vertex1, vertex5));

        Object[] expectedComponents1 = {
                new ConnectivityComponent<>(vertex8, vertex7, vertex6,
                        vertex5, vertex4, vertex3,
                        vertex2, vertex1)
        };
        Object[] graphComponents = graph.getComponents().toArray();
        for (int i = 0; i < expectedComponents1.length; i++) {
            assertEquals(expectedComponents1[i], graphComponents[i]);
        }
        assertEquals(graphComponents.length, graph.getComponentsCount());
    }

    @Test
    void addEdgeTest4() {
        addVertices();

        graph.addEdge(new Edge<>(vertex1, vertex2));
        graph.addEdge(new Edge<>(vertex2, vertex3));
        graph.addEdge(new Edge<>(vertex3, vertex4));
        graph.addEdge(new Edge<>(vertex1, vertex3));
        graph.addEdge(new Edge<>(vertex5, vertex8));
        graph.addEdge(new Edge<>(vertex1, vertex5));

        Object[] expectedComponents1 = {
                new ConnectivityComponent<>(vertex7),
                new ConnectivityComponent<>(vertex6),
                new ConnectivityComponent<>(
                        vertex8, vertex5, vertex4,
                        vertex3, vertex2, vertex1
                )
        };
        Object[] graphComponents = graph.getComponents().toArray();
        for (int i = 0; i < expectedComponents1.length; i++) {
            assertEquals(expectedComponents1[i], graphComponents[i]);
        }
        assertEquals(graphComponents.length, graph.getComponentsCount());
    }

    @Test
    void deleteVertexTest1() {
        addVertices();

        graph.addEdge(new Edge<>(vertex1, vertex2));
        graph.addEdge(new Edge<>(vertex1, vertex3));
        graph.addEdge(new Edge<>(vertex1, vertex4));
        graph.addEdge(new Edge<>(vertex1, vertex5));
        graph.addEdge(new Edge<>(vertex1, vertex6));
        graph.addEdge(new Edge<>(vertex1, vertex7));
        graph.addEdge(new Edge<>(vertex1, vertex8));

        graph.deleteVertex(vertex1);

        Object[] expectedComponents1 = {
                new ConnectivityComponent<>(vertex8),
                new ConnectivityComponent<>(vertex7),
                new ConnectivityComponent<>(vertex6),
                new ConnectivityComponent<>(vertex5),
                new ConnectivityComponent<>(vertex4),
                new ConnectivityComponent<>(vertex3),
                new ConnectivityComponent<>(vertex2)

        };
        Object[] graphComponents = graph.getComponents().toArray();
        for (int i = 0; i < expectedComponents1.length; i++) {
            assertEquals(expectedComponents1[i], graphComponents[i]);
        }
        assertEquals(graphComponents.length, graph.getComponentsCount());
    }

    @Test
    void deleteVertexTest2() {
        addVertices();

        graph.addEdge(new Edge<>(vertex1, vertex2));
        graph.addEdge(new Edge<>(vertex2, vertex3));
        graph.addEdge(new Edge<>(vertex3, vertex4));
        graph.addEdge(new Edge<>(vertex4, vertex5));
        graph.addEdge(new Edge<>(vertex5, vertex6));
        graph.addEdge(new Edge<>(vertex6, vertex7));
        graph.addEdge(new Edge<>(vertex6, vertex8));

        graph.deleteVertex(vertex4);

        Object[] expectedComponents1 = {
                new ConnectivityComponent<>(vertex3,
                        vertex2, vertex1),
                new ConnectivityComponent<>(vertex8, vertex7, vertex6,
                        vertex5)
        };
        Object[] graphComponents = graph.getComponents().toArray();
        for (int i = 0; i < expectedComponents1.length; i++) {
            assertEquals(expectedComponents1[i], graphComponents[i]);
        }
        assertEquals(graphComponents.length, graph.getComponentsCount());
    }

    @Test
    void deleteVertexTest3() {
        addVertices();

        graph.addEdge(new Edge<>(vertex1, vertex5));
        graph.addEdge(new Edge<>(vertex2, vertex4));
        graph.addEdge(new Edge<>(vertex1, vertex4));
        graph.addEdge(new Edge<>(vertex1, vertex3));
        graph.addEdge(new Edge<>(vertex4, vertex3));

        graph.deleteVertex(vertex1);

        Object[] expectedComponents1 = {
                new ConnectivityComponent<>(vertex8),
                new ConnectivityComponent<>(vertex7),
                new ConnectivityComponent<>(vertex6),
                new ConnectivityComponent<>(vertex5),
                new ConnectivityComponent<>(vertex4,
                        vertex3, vertex2)
        };
        Object[] graphComponents = graph.getComponents().toArray();
        for (int i = 0; i < expectedComponents1.length; i++) {
            assertEquals(expectedComponents1[i], graphComponents[i]);
        }
        assertEquals(graphComponents.length, graph.getComponents().size());
    }

    @Test
    void deleteVertexTest4() {
        addVertices();

        graph.addEdge(new Edge<>(vertex1, vertex5));
        graph.addEdge(new Edge<>(vertex2, vertex4));
        graph.addEdge(new Edge<>(vertex1, vertex4));
        graph.addEdge(new Edge<>(vertex1, vertex3));
        graph.addEdge(new Edge<>(vertex4, vertex3));

        graph.deleteVertex(vertex4);

        Object[] expectedComponents1 = {
                new ConnectivityComponent<>(vertex8),
                new ConnectivityComponent<>(vertex7),
                new ConnectivityComponent<>(vertex6),
                new ConnectivityComponent<>(vertex2),
                new ConnectivityComponent<>(vertex5,
                        vertex3, vertex1)
        };
        Object[] graphComponents = graph.getComponents().toArray();
        for (int i = 0; i < expectedComponents1.length; i++) {
            assertEquals(expectedComponents1[i], graphComponents[i]);
        }
        assertEquals(graphComponents.length, graph.getComponentsCount());
    }

    @Test
    void deleteEdgeTest1() {
        addVertices();

        graph.addEdge(new Edge<>(vertex1, vertex2));
        graph.addEdge(new Edge<>(vertex2, vertex3));
        graph.addEdge(new Edge<>(vertex3, vertex4));
        Edge<Integer> edge45 = new Edge<>(vertex4, vertex5);
        graph.addEdge(new Edge<>(vertex4, vertex5));
        graph.addEdge(new Edge<>(vertex5, vertex6));
        graph.addEdge(new Edge<>(vertex6, vertex7));
        graph.addEdge(new Edge<>(vertex6, vertex8));

        graph.deleteEdge(edge45);

        Object[] expectedComponents1 = {
                new ConnectivityComponent<>(vertex8, vertex7, vertex6, vertex5),
                new ConnectivityComponent<>(vertex4, vertex3, vertex2, vertex1)
        };
        Object[] graphComponents = graph.getComponents().toArray();
        for (int i = 0; i < expectedComponents1.length; i++) {
            assertEquals(expectedComponents1[i], graphComponents[i]);
        }
        assertEquals(graphComponents.length, graph.getComponentsCount());
    }

    @Test
    void deleteEdgeTest2() {
        addVertices();

        Edge<Integer> edge12 = new Edge<>(vertex1, vertex2);
        graph.addEdge(edge12);
        graph.addEdge(new Edge<>(vertex1, vertex3));
        graph.addEdge(new Edge<>(vertex1, vertex4));
        graph.addEdge(new Edge<>(vertex1, vertex5));
        graph.addEdge(new Edge<>(vertex1, vertex6));
        graph.addEdge(new Edge<>(vertex1, vertex7));
        graph.addEdge(new Edge<>(vertex1, vertex8));

        graph.deleteEdge(edge12);

        Object[] expectedComponents1 = {
                new ConnectivityComponent<>(vertex2),
                new ConnectivityComponent<>(vertex8, vertex7, vertex6, vertex5, vertex4, vertex3, vertex1)
        };
        Object[] graphComponents = graph.getComponents().toArray();
        for (int i = 0; i < expectedComponents1.length; i++) {
            assertEquals(expectedComponents1[i], graphComponents[i]);
        }
        assertEquals(graphComponents.length, graph.getComponentsCount());
    }

    private void addVertices() {
        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);
        graph.addVertex(vertex4);
        graph.addVertex(vertex5);
        graph.addVertex(vertex6);
        graph.addVertex(vertex7);
        graph.addVertex(vertex8);
    }
}