package com.alinab.taskTwoCollections.treeMap;

public interface SimpleTreeMap<K, V> {

    V put(K key, V value);

    V get(Object key);

    boolean containsKey(Object key);

    boolean containsValue(Object value);

    MySimpleTreeMap.Node<K, V> firstEntry();

    MySimpleTreeMap.Node<K, V> lastEntry();

    V remove(Object key);

    V replace(K key, V value);

    void clear();

    boolean isEmpty();

    int size();
}
