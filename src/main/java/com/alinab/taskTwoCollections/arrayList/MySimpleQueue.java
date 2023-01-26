package com.alinab.taskTwoCollections.arrayList;

public class MySimpleQueue<E> implements SimpleQueue<E> {

    private static final int DEFAULT_CAPACITY = 11;

    private final Object[] storage;
    private final int storageCapacity;
    private int size;
    private int tail = -1;
    private int head = -1;

    public MySimpleQueue() {
        this(0);
    }

    public MySimpleQueue(int initialCapacity) {
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
    public boolean offer(E element) {
        if (element == null) {
            throw new NullPointerException();
        }
        if (size == storageCapacity) {
            return false;
        }
        if (head > 0 && size < storageCapacity) {
            System.arraycopy(storage, head, storage, 0, tail - head + 1);
            tail = tail - head;
            head = 0;
        }
        storage[++tail] = element;
        size++;
        if (head == -1) {
            head++;
        }
        return true;
    }

    @Override
    public E peek() {
        return size == 0 ? null : (E) storage[head];
    }

    @Override
    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E element = (E) storage[head];
        if (size == 1) {
            storage[head] = null;
            head = -1;
            tail = -1;
        } else {
            storage[head++] = null;
        }
        size--;
        return element;
    }

    @Override
    public boolean contains(E element) {
        for (int i = head; i <= tail; i++) {
            if (storage[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        while (head <= tail) {
            storage[head++] = null;
        }
        size = 0;
        head = -1;
        tail = -1;
    }

    @Override
    public int size() {
        return size;
    }

    private boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("MySimpleQueue{");
        if (isEmpty()) {
            return sb.append("is empty}").toString();
        }
        for (int i = head; i <= tail; i++) {
            sb.append(storage[i]);
            if (i < tail) {
                sb.append(", ");
            }
        }
        return sb.append('}').toString();
    }
}
