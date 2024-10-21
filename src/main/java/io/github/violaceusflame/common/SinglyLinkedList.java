package io.github.violaceusflame.common;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class SinglyLinkedList<T> implements Iterable<T> {
    private static class Descriptor<ElType> {
        private ElType head;
        private ElType tail;

        public Descriptor(ElType head, ElType tail) {
            this.head = head;
            this.tail = tail;
        }

        public ElType getHead() {
            return head;
        }

        public void setHead(ElType head) {
            this.head = head;
        }

        public ElType getTail() {
            return tail;
        }

        public void setTail(ElType tail) {
            this.tail = tail;
        }
    }

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

    private final Descriptor<Node<T>> descriptor;

    public SinglyLinkedList() {
        descriptor = new Descriptor<>(null, null);
    }

    public void pushBack(T data) {
        if (isEmpty()) {
            Node<T> node = new Node<>(data);
            descriptor.setHead(node);
            descriptor.setTail(node);
            return;
        }

        Node<T> node = new Node<>(data);
        descriptor.getTail().setNext(node);
        descriptor.setTail(node);
    }

    public boolean isEmpty() {
        return descriptor.head == null;
    }

    public T front() {
        return descriptor.getHead().data;
    }

    public T back() {
        return descriptor.getTail().data;
    }

    public boolean contains(T data) {
        Node<T> current = descriptor.getHead();
        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public boolean remove(T data) {
        // если список пуст
        if (isEmpty()) {
            return false;
        }

        // если удаляемый элемент первый в списке
        if (descriptor.getHead().data.equals(data)) {
            descriptor.setHead(descriptor.getHead().getNext());
            return true;
        }

        Node<T> current = descriptor.getHead();
        while (current.getNext() != null && !current.getNext().data.equals(data)) {
            current = current.getNext();
        }

        if (current.getNext() != null) {
            current.setNext(current.getNext().getNext());
            if (current.getNext() == descriptor.getTail()) {
                descriptor.setTail(current);
            }
            return true;
        }
        return false;
    }

    public T get(T item) {
        Node<T> current = descriptor.getHead();
        while (current != null) {
            if (current.data.equals(item)) {
                return current.data;
            }
            current = current.getNext();
        }
        return null;
    }

    public void print() {
        Node<T> current = descriptor.getHead();
        while (current != null) {
            System.out.println(current.data);
            current = current.getNext();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new SinglyLinkedListIterator();
    }

    private class SinglyLinkedListIterator implements Iterator<T> {
        private Node<T> current = descriptor.getHead();

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            T data = current.data;
            current = current.getNext();
            return data;
        }
    }

    @Override
    public String toString() {
        StringJoiner stringJoiner = new StringJoiner(", ", "[", "]");
        Node<T> current = descriptor.getHead();
        while (current != null) {
            stringJoiner.add(String.valueOf(current.data));
            current = current.getNext();
        }
        return stringJoiner.toString();
    }
}
