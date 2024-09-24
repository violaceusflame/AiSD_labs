package io.github.violaceusflame.lab1.queue;

public class ArrayQueue<ElType> implements Queue<ElType> {
    private final int capacity;
    private final Object[] array;
    private int headIndex = 0;
    private int tailIndex = 0;

    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        array = new Object[capacity];
    }

    @Override
    public boolean enqueue(ElType elType) {
        if (isFull()) {
            return false;
        }

        array[tailIndex] = elType;
        tailIndex = (tailIndex + 1) % capacity;
        return true;
    }

    @Override
    public boolean isFull() {
        // TODO: тут хуй знает
        // TODO: вроде переделал, но надо проверить
        return (tailIndex + 1) % capacity == headIndex;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ElType dequeue() {
        if (isEmpty()) {
            return null;
        }

        ElType result = (ElType) array[headIndex];
//        array[headIndex] = null;
        headIndex = (headIndex + 1) % capacity;
        return result;
    }

    @Override
    public boolean isEmpty() {
        return headIndex == tailIndex;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ElType front() {
        if (isEmpty()) {
            return null;
        }

        return (ElType) array[headIndex];
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int currentIndex = headIndex;
        while (currentIndex != tailIndex) {
            result.append(array[currentIndex]).append("\n");
            currentIndex = (currentIndex + 1) % capacity;
        }
        return result.toString();
    }
}
