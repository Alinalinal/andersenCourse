package com.alinab.taskTwoCollections.treeMap;

import java.util.Objects;

public class MySimpleTreeMap<K, V> implements SimpleTreeMap<K, V> {

    private Node<K, V> root;
    private int size;

    public MySimpleTreeMap() {
    }

    @Override
    public V put(K key, V value) {
        checkKeyWithException(key);
        if (size == 0) {
            root = new Node<>(key, value);
            size = 1;
            return null;
        }
        Node<K, V> temp = root;
        Node<K, V> parent;
        Comparable<? super K> k = (Comparable<? super K>) key;
        int comp;
        do {
            comp = k.compareTo(temp.getKey());
            if (comp == 0) {
                V oldValue = temp.getValue();
                temp.setValue(value);
                return oldValue;
            } else {
                parent = temp;
                if (comp < 0) {
                    temp = temp.getLeft();
                } else {
                    temp = temp.getRight();
                }
            }
        } while (temp != null);
        temp = new Node<>(key, value);
        temp.setParent(parent);
        if (comp < 0) {
            parent.setLeft(temp);
        } else {
            parent.setRight(temp);
        }
        size++;
        return null;
    }

    @Override
    public Node<K, V> firstEntry() {
        Node<K, V> node = root;
        Node<K, V> leftNode = node.getLeft();
        while (leftNode != null) {
            node = leftNode;
            leftNode = node.getLeft();
        }
        return node;
    }

    @Override
    public Node<K, V> lastEntry() {
        Node<K, V> node = root;
        Node<K, V> rightNode = node.getRight();
        while (rightNode != null) {
            node = rightNode;
            rightNode = node.getRight();
        }
        return node;
    }

    @Override
    public V get(Object key) {
        Node<K, V> node = getNode(key);
        return node == null ? null : node.getValue();
    }

    @Override
    public boolean containsKey(Object key) {
        return getNode(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        if (size == 0) {
            return false;
        }
        Node<K, V> temp = firstEntry();
        do {
            if (Objects.equals(value, temp.getValue())) {
                return true;
            }
            temp = getHeirNode(temp);
        } while (temp != null);
        return false;
    }

    @Override
    public V remove(Object key) {
        Node<K, V> node = getNode(key);
        V oldValue = null;
        if (node != null) {
            oldValue = node.getValue();
            Node<K,V> parentNode = node.getParent();
            Node<K, V> leftNode = node.getLeft();
            Node<K, V> rightNode = node.getRight();
            if (leftNode == null && rightNode == null) {
                if (node.equals(root)) {
                    root = null;
                } else if (Objects.equals(parentNode.getLeft(), node)) {
                    parentNode.setLeft(null);
                } else {
                    parentNode.setRight(null);
                }
            } else if (rightNode == null) {
                rebaseNode(node, leftNode, parentNode);
            } else if (leftNode == null) {
                rebaseNode(node, rightNode, parentNode);
            } else {
                Node<K, V> heirNode = getHeirNode(node);
                if (node.equals(root)) {
                    heirNode.setParent(null);
                    heirNode.setLeft(root.getLeft());
                    root.getLeft().setParent(heirNode);
                    root = heirNode;
                } else {
                    parentNode.setRight(heirNode);
                    heirNode.setParent(parentNode);
                    heirNode.setLeft(leftNode);
                    leftNode.setParent(heirNode);
                }
            }
            size--;
        }
        return oldValue;
    }

    @Override
    public V replace(K key, V value) {
        Node<K, V> node = getNode(key);
        if (node != null) {
            V oldValue = node.getValue();
            node.setValue(value);
            return oldValue;
        }
        return null;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MySimpleTreeMap{");
        if (size == 0) {
            sb.append("isEmpty");
        } else {
            Node<K, V> temp = firstEntry();
            int i = 0;
            do {
                sb.append(temp);
                if (i < size - 1) {
                    sb.append(", ");
                }
                i++;
                temp = getHeirNode(temp);
            } while (temp != null);
        }
        return sb.append('}').toString();
    }

    private void checkKeyWithException(K key) {
        if (key == null) {
            throw new NullPointerException("Key mustn't be null");
        }
    }

    private Node<K, V> getNode(Object key) {
        checkKeyWithException((K) key);
        if (size == 0) {
            return null;
        }
        Node<K, V> temp = root;
        Comparable<? super K> k = (Comparable<? super K>) key;
        int comp;
        do {
            comp = k.compareTo(temp.getKey());
            if (comp == 0) {
                return temp;
            } else if (comp < 0) {
                temp = temp.getLeft();
            } else {
                temp = temp.getRight();
            }
        } while (temp != null);
        return null;
    }

    private Node<K, V> getHeirNode(Node<K, V> node) {
        if (node.getRight() != null) {
            node = node.getRight();
            while (node.getLeft() != null) {
                node = node.getLeft();
            }
        } else {
            Node<K, V> nodeCh = node;
            node = node.getParent();
            while (node != null && nodeCh == node.getRight()) {
                nodeCh = node;
                node = node.getParent();
            }
        }
        return node;
    }

    private void rebaseNode(Node<K, V> oldNode, Node<K, V> newNode, Node<K, V> parentNode) {
        if (oldNode.equals(root)) {
            root = newNode;
            root.setParent(null);
        } else if (Objects.equals(parentNode.getLeft(), oldNode)) {
            parentNode.setLeft(newNode);
            newNode.setParent(parentNode);
        } else {
            parentNode.setRight(newNode);
            newNode.setParent(parentNode);
        }
    }

    static class Node<K, V> {

        private K key;
        private V value;
        private Node<K, V> left;
        private Node<K, V> right;
        private Node<K, V> parent;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
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

        public Node<K, V> getLeft() {
            return left;
        }

        public void setLeft(Node<K, V> left) {
            this.left = left;
        }

        public Node<K, V> getRight() {
            return right;
        }

        public void setRight(Node<K, V> right) {
            this.right = right;
        }

        public Node<K, V> getParent() {
            return parent;
        }

        public void setParent(Node<K, V> parent) {
            this.parent = parent;
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node<?, ?> node = (Node<?, ?>) o;

            if (!key.equals(node.key)) return false;
            return Objects.equals(value, node.value);
        }

        @Override
        public String toString() {
            return key + "=" + value;
        }
    }
}
