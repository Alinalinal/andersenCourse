package com.alinab.taskTwoCollections.treeSet;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.NoSuchElementException;
import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class MySimpleTreeSet<V> implements SimpleTreeSet<V> {

    Node<V> root;
    int size;

    @Override
    public boolean add(V value) {
        checkWithException(value);
        if (size == 0) {
            root = new Node<>(value);
            size = 1;
            return true;
        }
        Node<V> temp = root;
        Node<V> parent;
        Comparable<? super V> k = (Comparable<? super V>) value;
        int comp;
        do {
            comp = k.compareTo(temp.getValue());
            if (comp == 0) {
                return false;
            } else {
                parent = temp;
                if (comp < 0) {
                    temp = temp.getLeft();
                } else {
                    temp = temp.getRight();
                }
            }
        } while (temp != null);
        temp = new Node<>(value);
        temp.setParent(parent);
        if (comp < 0) {
            parent.setLeft(temp);
        } else {
            parent.setRight(temp);
        }
        size++;
        return true;
    }

    @Override
    public V first() {
        return getFirstNode().getValue();
    }

    @Override
    public V last() {
        checkSizeWithException();
        Node<V> node = root;
        Node<V> rightNode = node.getRight();
        while (rightNode != null) {
            node = rightNode;
            rightNode = node.getRight();
        }
        return node.getValue();
    }

    @Override
    public boolean remove(Object o) {
        Node<V> node = getNode(o);
        if (node != null) {
            Node<V> parentNode = node.getParent();
            Node<V> leftNode = node.getLeft();
            Node<V> rightNode = node.getRight();
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
                Node<V> heirNode = getHeirNode(node);
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
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return getNode(o) != null;
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

    private void checkWithException(Object value) {
        if (value == null) {
            throw new NullPointerException("Value mustn't be null");
        }
    }

    private void checkSizeWithException() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
    }

    private Node<V> getFirstNode() {
        checkSizeWithException();
        Node<V> node = root;
        Node<V> leftNode = node.getLeft();
        while (leftNode != null) {
            node = leftNode;
            leftNode = node.getLeft();
        }
        return node;
    }

    private Node<V> getHeirNode(Node<V> node) {
        if (node.getRight() != null) {
            node = node.getRight();
            while (node.getLeft() != null) {
                node = node.getLeft();
            }
        } else {
            Node<V> nodeCh = node;
            node = node.getParent();
            while (node != null && nodeCh == node.getRight()) {
                nodeCh = node;
                node = node.getParent();
            }
        }
        return node;
    }

    private Node<V> getNode(Object value) {
        checkWithException(value);
        if (size == 0) {
            return null;
        }
        Node<V> temp = root;
        Comparable<? super V> v = (Comparable<? super V>) value;
        int comp;
        do {
            comp = v.compareTo(temp.getValue());
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

    private void rebaseNode(Node<V> oldNode, Node<V> newNode, Node<V> parentNode) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MySimpleTreeMap{");
        if (size == 0) {
            sb.append("isEmpty");
        } else {
            Node<V> temp = getFirstNode();
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

    @FieldDefaults(level = AccessLevel.PRIVATE)
    static class Node<V> {

        @Getter
        final V value;

        @Getter
        @Setter
        Node<V> left;

        @Getter
        @Setter
        Node<V> right;

        @Getter
        @Setter
        Node<V> parent;

        public Node(V value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            return value != null ? value.hashCode() : 0;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node<?> node = (Node<?>) o;

            return Objects.equals(value, node.value);
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }
}
