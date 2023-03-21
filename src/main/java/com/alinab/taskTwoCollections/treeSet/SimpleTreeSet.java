package com.alinab.taskTwoCollections.treeSet;

public interface SimpleTreeSet<V> {

    boolean add(V value);

    V first();

    V last();

    boolean remove(Object o);

    boolean contains(Object o);

    boolean isEmpty();

    void clear();

    int size();
}
