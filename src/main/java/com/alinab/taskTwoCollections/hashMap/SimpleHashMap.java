package com.alinab.taskTwoCollections.hashMap;

public interface SimpleHashMap<K, V> {

    V get(Object key);

    boolean containsKey(Object key);

    boolean containsValue(Object value);

    V put(K key, V value);

    V remove(Object key);

    boolean remove(Object key, Object value);

    void clear();

    boolean isEmpty();

    int size();
}
