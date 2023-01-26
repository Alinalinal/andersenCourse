package com.alinab.taskTwoCollections.arrayList;

import java.util.Arrays;

public class MySimpleArrayList<E> implements SimpleArrayList<E> {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] storage;
    private int size;
    private int storageCapacity;

    public MySimpleArrayList() {
        this(0);
    }

    public MySimpleArrayList(int initialCapacity) {
        if (initialCapacity > 0) {
            this.storage = new Object[initialCapacity];
            this.storageCapacity = initialCapacity;
        } else if (initialCapacity == 0) {
            this.storage = new Object[DEFAULT_CAPACITY];
            this.storageCapacity = DEFAULT_CAPACITY;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return (E) storage[index];
    }


    @Override
    public void add(E element) {
        doAdd(size, element);
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        doAdd(index, element);
    }

    @Override
    public E set(int index, E element) {
        E oldElement = get(index);
        storage[index] = element;
        return oldElement;
    }

    @Override
    public E remove(int index) {
        E element = get(index);
        doRemove(index);
        return element;
    }

    @Override
    public boolean remove(E element) {
        int index = getIndex(element);
        if (index > -1) {
            doRemove(index);
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(E element) {
        return getIndex(element) > -1;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    private void increaseStorage() {
        storageCapacity = (int) (storageCapacity * 1.5 + 1);
        storage = Arrays.copyOf(storage, storageCapacity);
    }

    private void doAdd(int index, E element) {
        if (size == storageCapacity) {
            increaseStorage();
        }
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = element;
        size++;
    }

    private void doRemove(int index) {
        if (index != size - 1) {
            System.arraycopy(storage, index + 1, storage, index, size - index - 1);
        }
        storage[size - 1] = null;
        size--;
    }

    private int getIndex(E element) {
        for (int i = 0; i < size; i++) {
            if (storage[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MyArrayList{");
        for (int i = 0; i < size; i++) {
            sb.append(storage[i]);
            if (i < (size - 1)) {
                sb.append(", ");
            }
        }
        return sb.append('}').toString();
    }
}
