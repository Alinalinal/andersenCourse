package com.alinab.taskTwoCollections.linkedList;

public interface SimpleLinkedList<E> {

    void addFirst(E e);

    void addLast(E e);

    boolean add(E element);

    void add(int index, E element);

    E get(int index);

    E getFirst();

    E getLast();

    E removeFirst();

    E removeLast();

    E remove(int index);

    boolean contains(Object o);

    int size();

    void clear();

    int indexOf(Object o);
}
