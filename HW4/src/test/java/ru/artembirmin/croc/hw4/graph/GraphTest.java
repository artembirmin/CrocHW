package ru.artembirmin.croc.hw4.graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.artembirmin.croc.hw4.graph.components.Edge;
import ru.artembirmin.croc.hw4.graph.components.Vertex;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    Graph<Integer> graph;
    Vertex<Integer> vertex1;
    Vertex<Integer> vertex2;
    Vertex<Integer> vertex3;
    Vertex<Integer> vertex4;
    Vertex<Integer> vertex5;

    @BeforeEach
    void setUp() {
        graph = new Graph<>();
        vertex1 = new Vertex<>("1",1);
        vertex2= new Vertex<>("2",2);
        vertex3= new Vertex<>("3",3);
        vertex4= new Vertex<>("4",4);
        vertex5= new Vertex<>("5",5);
    }

    @Test
    void addVertex() {
        graph.addVertex(vertex1);
        graph.addVertex(vertex2);
        graph.addVertex(vertex3);
        graph.addVertex(vertex4);
        graph.addVertex(vertex5);
        graph.addEdge(new Edge<>(vertex1,vertex3));
        graph.addEdge(new Edge<>(vertex1,vertex5));
        graph.addEdge(new Edge<>(vertex1,vertex4));
        graph.addEdge(new Edge<>(vertex3,vertex4));
        Edge<Integer> edge24 = new Edge<>(vertex2,vertex4);
        //graph.addEdge(edge24);

//        System.out.println(vertex1.getEdges());
//        System.out.println(vertex2.getEdges());
//        System.out.println(vertex3.getEdges());
//        System.out.println(vertex4.getEdges());

        //System.out.println(vertex2.getEdges());

        //graph.deleteEdge(edge24);

        System.out.println(graph);
        System.out.println(graph.findConnectedVertices(vertex4));
        //Так как компоненты содержат свои элементы в произвольном порядке, то сравнивать в цикле и помощью contains
    }

    @Test
    void addEdge() {
    }
}