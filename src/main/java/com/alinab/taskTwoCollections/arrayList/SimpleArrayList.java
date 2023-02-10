package com.alinab.taskTwoCollections.arrayList;

public interface SimpleArrayList<E> {

    boolean add(E element);

    void add(int index, E e);

    E get(int index);

    E set(int index, E element);

    E remove(int index);

    boolean remove(Object o);

    boolean contains(Object o);

    boolean isEmpty();

    void clear();

    int size();
}
