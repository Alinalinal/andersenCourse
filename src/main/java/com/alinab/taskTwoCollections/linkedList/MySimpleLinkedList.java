package com.alinab.taskTwoCollections.linkedList;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.NoSuchElementException;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class MySimpleLinkedList<E> implements SimpleLinkedList<E> {

    Node<E> firstNode;
    Node<E> lastNode;
    int size;

    @Override
    public void addFirst(E element) {
        Node<E> oldFirstNode = firstNode;
        firstNode = new Node<>(null, element, oldFirstNode);
        if (size == 0) {
            lastNode = firstNode;
        } else {
            oldFirstNode.setPrevNode(firstNode);
        }
        size++;
    }

    @Override
    public void addLast(E element) {
        Node<E> oldLastNode = lastNode;
        lastNode = new Node<>(oldLastNode, element, null);
        if (size == 0) {
            firstNode = lastNode;
        } else {
            oldLastNode.setNextNode(lastNode);
        }
        size++;
    }

    @Override
    public boolean add(E element) {
        addLast(element);
        return true;
    }

    @Override
    public void add(int index, E element) {
        if (index == size) {
            addLast(element);
        } else if (index == 0) {
            addFirst(element);
        } else {
            checkWithException(index);
            Node<E> indexNode = getNode(index);
            Node<E> prevNode = indexNode.getPrevNode();
            Node<E> newNode = new Node<>(prevNode, element, indexNode);
            prevNode.setNextNode(newNode);
            indexNode.setPrevNode(newNode);
            size++;
        }
    }

    @Override
    public E get(int index) {
        checkWithException(index);
        return getNode(index).getElement();
    }

    @Override
    public E getFirst() {
        checkWithException();
        return firstNode.getElement();
    }

    @Override
    public E getLast() {
        checkWithException();
        return lastNode.getElement();
    }

    @Override
    public E removeFirst() {
        checkWithException();
        E temp = firstNode.getElement();
        if (!removeSingleNode()) {
            Node<E> newFirst = firstNode.getNextNode();
            unlink(firstNode);
            newFirst.setPrevNode(null);
            firstNode = newFirst;
        }
        size--;
        return temp;
    }

    @Override
    public E removeLast() {
        checkWithException();
        E temp = lastNode.getElement();
        if (!removeSingleNode()) {
            Node<E> newLast = lastNode.getPrevNode();
            unlink(lastNode);
            newLast.setNextNode(null);
            lastNode = newLast;
        }
        size--;
        return temp;
    }

    @Override
    public E remove(int index) {
        checkWithException(index);
        E element;
        if (index == 0) {
            element = removeFirst();
        } else if (index == size - 1) {
            element = removeLast();
        } else {
            Node<E> temp = getNode(index);
            element = temp.getElement();
            Node<E> prevNode = temp.getPrevNode();
            Node<E> nextNode = temp.getNextNode();
            prevNode.setNextNode(nextNode);
            nextNode.setPrevNode(prevNode);
            unlink(temp);
            size--;
        }
        return element;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) > -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        Node<E> temp = firstNode;
        for (int i = 0; i < size; i++) {
            Node<E> nextNode = temp.getNextNode();
            unlink(temp);
            temp = nextNode;
        }
        firstNode = null;
        lastNode = null;
        size = 0;
    }

    @Override
    public int indexOf(Object o) {
        Node<E> temp = firstNode;
        int index = -1;
        if (o == null) {
            for (int i = 0; i < size; i++) {
                E element = temp.getElement();
                if (element == null) {
                    return i;
                }
                temp = temp.getNextNode();
            }
        } else {
            for (int i = 0; i < size; i++) {
                E element = temp.getElement();
                if (o.equals(element)) {
                    return i;
                }
                temp = temp.getNextNode();
            }
        }
        return index;
    }

    private void checkWithException() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
    }

    private void checkWithException(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private Node<E> getNode(int index) {
        if (index == size - 1) {
            return lastNode;
        } else if (index == 0) {
            return firstNode;
        }
        Node<E> temp = firstNode;
        for (int i = 0; i < index; i++) {
            temp = temp.getNextNode();
        }
        return temp;
    }

    private void unlink(Node<E> node) {
        node.setPrevNode(null);
        node.setElement(null);
        node.setNextNode(null);
    }

    private boolean removeSingleNode() {
        if (size == 1) {
            unlink(firstNode);
            unlink(lastNode);
            firstNode = null;
            lastNode = null;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MySimpleLinkedList{");
        if (firstNode == null) {
            sb.append("is empty");
        } else {
            Node<E> temp = firstNode;
            for (int i = 0; i < size; i++) {
                sb.append(temp.getElement());
                if (i < size - 1) {
                    sb.append(", ");
                }
                temp = temp.getNextNode();
            }
        }
        return sb.append('}').toString();
    }

    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Getter
    @Setter
    static class Node<E> {

        E element;
        Node<E> prevNode;
        Node<E> nextNode;

        Node(Node<E> prevElement, E element, Node<E> nextElement) {
            this.element = element;
            this.prevNode = prevElement;
            this.nextNode = nextElement;
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }
}
