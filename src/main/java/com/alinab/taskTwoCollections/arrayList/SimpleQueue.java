package com.alinab.taskTwoCollections.arrayList;

public interface SimpleQueue<E> {

    boolean offer(E element);

    E poll();

    E peek();

    boolean contains(E element);

    void clear();

    int size();
}
