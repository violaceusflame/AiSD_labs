package io.github.violaceusflame.lab3;

import io.github.violaceusflame.common.SinglyLinkedList;

import java.util.Objects;

public class Graph<T> {
    public static class Vertex<V> {
        public V data;
        public SinglyLinkedList<Vertex<V>> linkedVertexList = new SinglyLinkedList<>();

        public Vertex(V data) {
            this.data = data;
        }

        public void link(Vertex<V> vertex) {
            linkedVertexList.pushBack(vertex);
        }

        public boolean isLinked(Vertex<V> vertex) {
            return linkedVertexList.contains(vertex);
        }

        public boolean unlink(Vertex<V> vertex) {
            if (linkedVertexList.contains(vertex)) {
                linkedVertexList.remove(vertex);
                return true;
            }
            return false;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex<?> vertex = (Vertex<?>) o;
            return Objects.equals(data, vertex.data);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(data);
        }

        @Override
        public String toString() {
            return "Vertex{" +
                    "data=" + data +
                    ", linkedVertexList=" + linkedVertexList +
                    '}';
        }
    }

    private final SinglyLinkedList<Vertex<T>> vertices = new SinglyLinkedList<>();

    public boolean addVertex(T data) {
        Vertex<T> vertex = new Vertex<>(data);
        if (vertices.contains(vertex)) {
            return false;
        }

        vertices.pushBack(vertex);
        return true;
    }

    public boolean addLinkedVertex(T targetVertexData, T linkedVertexData) {
        Vertex<T> targetVertex = getVertex(targetVertexData);
        Vertex<T> linkedVertex = getVertex(linkedVertexData);
        if (targetVertex == null || linkedVertex == null) {
            return false;
        }
        targetVertex.link(linkedVertex);
        return true;
    }

    private Vertex<T> getVertex(T data) {
        Vertex<T> targetVertex = new Vertex<>(data);
        for (Vertex<T> vertex : vertices) {
            if (vertex.data.equals(data)) {
                targetVertex = vertex;
            }
        }
        if (vertices.contains(targetVertex)) {
            return targetVertex;
        }
        return null;
    }

    public boolean isLinked(T targetVertexData, T linkedVertexData) {
        Vertex<T> targetVertex = getVertex(targetVertexData);
        Vertex<T> linkedVertex = getVertex(linkedVertexData);
        if (targetVertex == null || linkedVertex == null) {
            return false;
        }
        return targetVertex.isLinked(linkedVertex);
    }

    public boolean removeVertex(T data) {
        Vertex<T> targetVertex = getVertex(data);
        if (targetVertex == null) {
            return false;
        }

        for (var vertex : vertices) {
            if (vertex.isLinked(targetVertex)) {
                vertex.unlink(targetVertex);
            }
        }
        vertices.remove(targetVertex);
        return true;
    }

    public boolean containsVertex(T data) {
        for (var vertex : vertices) {
            if (vertex.data.equals(data)) {
                return true;
            }
        }
        return false;
    }

    public void print() {
        vertices.print();
    }
}
