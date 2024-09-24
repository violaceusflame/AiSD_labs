package io.github.violaceusflame.lab1.queue;

public class LinkedListQueue<ElType> implements Queue<ElType> {
    private static class Node<ElType> {
        private ElType data;
        private Node<ElType> next;

        public Node(ElType data) {
            this.data = data;
        }

        public ElType getData() {
            return data;
        }

        public Node<ElType> getNext() {
            return next;
        }

        public void setData(ElType data) {
            this.data = data;
        }

        public void setNext(Node<ElType> next) {
            this.next = next;
        }
    }

    private Node<ElType> head;
    private Node<ElType> tail;

    public LinkedListQueue() {
        this.head = this.tail = new Node<>(null);
    }

    @Override
    public boolean enqueue(ElType item) {
        Node<ElType> newNode = new Node<>(item);
        tail.setNext(newNode);
        tail = newNode;
        return true;
    }

    @Override
    public ElType dequeue() {
        if (isEmpty()) {
            return null;
        }

        Node<ElType> node = head.getNext();
        ElType data = node.getData();
        head.setNext(node.getNext());

        if (head.getNext() == null) {
            tail = head;
        }

        return data;
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
    public ElType front() {
        if (isEmpty()) {
            return null;
        }

//        return (head.getNext() == null) ? null : head.getNext().getData();
        return head.getNext().getData();
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        Node<ElType> currentNode = head.getNext();
        while (currentNode != null) {
            result.append(currentNode.getData()).append("\n");
            currentNode = currentNode.getNext();
        }
        return result.toString();
    }
}
