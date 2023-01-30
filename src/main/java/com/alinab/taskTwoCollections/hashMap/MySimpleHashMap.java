package com.alinab.taskTwoCollections.hashMap;

import java.util.Arrays;
import java.util.Objects;

public class MySimpleHashMap<K, V> implements SimpleHashMap<K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final int STORAGE_EXPANSION_FACTOR = 2;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private Node<K, V>[] storage;
    private float threshold;
    private int size;

    public MySimpleHashMap() {
        this(DEFAULT_CAPACITY);
    }

    public MySimpleHashMap(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Initial capacity must be more than 0");
        }
        storage = new Node[initialCapacity];
        threshold = initialCapacity * DEFAULT_LOAD_FACTOR;
    }

    @Override
    public V get(Object key) {
        int bucket = countBucket((K) key);
        Node<K, V> temp = storage[bucket];
        if (temp != null) {
            do {
                K k = temp.getKey();
                if (k == null || k.equals(key)) {
                    return temp.getValue();
                }
                temp = temp.getNext();
            } while (temp != null);
        }
        return null;
    }

    @Override
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        int i = 0;
        int b = 0;
        Node<K, V> temp = storage[b];
        while (i < size) {
            if (temp != null) {
                do {
                    V v = temp.getValue();
                    if (Objects.equals(value, v)) {
                        return true;
                    }
                    temp = temp.getNext();
                    i++;
                } while (temp != null);
            }
            temp = storage[++b];
        }
        return false;
    }

    @Override
    public V put(K key, V value) {
        if (size >= threshold) {
            threshold *= STORAGE_EXPANSION_FACTOR;
            increaseStorage();
        }
        int bucket = countBucket(key);
        Node<K, V> newNode = new Node<>(key, value);
        V oldValue = null;
        if (storage[bucket] == null) {
            storage[bucket] = newNode;
            size++;
            return oldValue;
        }
        Node<K, V> temp = storage[bucket];
        Node<K, V> prevTemp;
        do {
            if (temp.equals(newNode)) {
                oldValue = temp.getValue();
                temp.setValue(value);
                return oldValue;
            }
            prevTemp = temp;
            temp = temp.getNext();
        } while (temp != null);
        prevTemp.setNext(newNode);
        size++;
        return null;
    }

    @Override
    public V remove(Object key) {
        Node<K, V> node = removeNode(key, null, false);
        return node == null ? null : node.getValue();
    }

    @Override
    public boolean remove(Object key, Object value) {
        return removeNode(key, value, true) != null;
    }

    @Override
    public void clear() {
        if (storage!= null && size > 0) {
            Arrays.fill(storage, null);
            size = 0;
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    private int countBucket(K key) {
        int hash = key != null ? key.hashCode() : 0;
        return Math.abs(hash % storage.length);
    }

    private Node<K, V> removeNode(Object key, Object value, boolean compareValue) {
        int bucket = countBucket((K) key);
        Node<K, V> temp = storage[bucket];
        Node<K, V> prevTemp = null;
        if (temp != null) {
            int indexInBucket = 0;
            do {
                Node<K, V> nextTemp = temp.getNext();
                if (Objects.equals(key, temp.getKey())) {
                    V v = temp.getValue();
                    if (compareValue && Objects.equals(value, v)) {
                        doRemove(indexInBucket, bucket, prevTemp, temp);
                        return temp;
                    } else if (!compareValue){
                        doRemove(indexInBucket, bucket, prevTemp, temp);
                        return temp;
                    }
                }
                prevTemp = temp;
                temp = nextTemp;
                indexInBucket++;
            } while (temp != null);
        }
        return null;
    }

    private void doRemove(int indexInBucket, int bucket, Node<K, V> prevNode, Node<K, V> nodeForDelete) {
        if (indexInBucket == 0) {
            storage[bucket] = nodeForDelete.getNext();
        } else {
            prevNode.setNext(nodeForDelete.getNext());
        }
        size--;
    }

    private void increaseStorage() {
        Node<K, V>[] oldStorage = storage;
        int destSize = size;
        storage = new Node[oldStorage.length * STORAGE_EXPANSION_FACTOR];
        size = 0;
        int i = 0;
        int j = 0;
        Node<K, V> temp = oldStorage[j];
        while (i < destSize) {
            if (temp != null) {
                do {
                    put(temp.getKey(), temp.getValue());
                    temp = temp.getNext();
                    i++;
                } while (temp != null);
            }
            temp = oldStorage[++j];
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MySimpleHashMap{");
        int i = 0;
        int j = 0;
        Node<K, V> temp = storage[j];
        if (size == 0) {
            sb.append("is empty");
        } else {
            while (i < size) {
                if (temp != null) {
                    do {
                        sb.append(temp);
                        if (i < size - 1) {
                            sb.append(", ");
                        }
                        temp = temp.getNext();
                        i++;
                    } while (temp != null);
                }
                temp = storage[++j];
            }
        }
        return sb.append('}').toString();
    }

    static class Node<K, V> {

        K key;
        V value;
        int hash;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.hash = hashCode();
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Node<K, V> getNext() {
            return next;
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
        }

        @Override
        public int hashCode() {
            return key != null ? key.hashCode() : 0;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<K, V> node = (Node<K, V>) o;
            return (Objects.equals(key, node.key) && Objects.equals(value, node.value)) ||
                    Objects.equals(hash, node.hashCode());
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }
}
