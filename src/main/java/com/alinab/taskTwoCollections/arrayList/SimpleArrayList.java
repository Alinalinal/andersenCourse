package com.alinab.taskTwoCollections.arrayList;

public interface SimpleArrayList<E> {

    void add(E element);

    void add(int index, E e);

    E get(int index);

    E set(int index, E element);

    E remove(int index);

    boolean remove(E e);

    boolean contains(E e);

    void clear();

    int size();
}
