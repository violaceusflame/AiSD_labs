package io.github.violaceusflame.lab1.queue;

public interface Queue<ElType> {
    boolean enqueue(ElType elType);
    ElType dequeue();
    boolean isEmpty();
    boolean isFull();
    ElType front();
}
