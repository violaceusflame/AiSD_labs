package io.github.violaceusflame.lab1.queue;

public class LinkedListQueue<T> implements Queue<T> {
    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }

        public T getData() {
            return data;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setData(T data) {
            this.data = data;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }

    private Node<T> head;
    private Node<T> tail;

    public LinkedListQueue() {
        this.head = this.tail = new Node<>(null);
    }

    @Override
    public boolean enqueue(T item) {
        Node<T> newNode = new Node<>(item);
        tail.setNext(newNode);
        tail = newNode;
        return true;
    }

    @Override
    public T dequeue() {
        // TODO: тут косяк
        Node<T> node = head.getNext();
        head.setNext(node.getNext());
        node.setNext(null);
        return node.getData();
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        return false;
    }

    @Override
    public T front() {
        return head.getNext().getData();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node<T> currentNode = head.getNext();
        while (currentNode != null) {
            result.append(currentNode.getData());
            currentNode = currentNode.getNext();
            result.append(currentNode != null ? ", " : "");
        }
        return "[%s]".formatted(result.toString());
    }
}
