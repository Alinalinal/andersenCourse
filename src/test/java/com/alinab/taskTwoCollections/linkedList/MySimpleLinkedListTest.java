package com.alinab.taskTwoCollections.linkedList;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class MySimpleLinkedListTest {

    SimpleLinkedList<String> linkedList;

    static final String VALUE_1 = "first";
    static final String VALUE_2 = "second";
    static final String VALUE_3 = "third";
    static final String VALUE_4 = "fourth";
    static final String VALUE_5 = "a fifth";
    static final String VALUE_TEST = "test";
    static final String VALUE_NULL = null;
    static final Integer ILLEGAL_INDEX = -1;


    @Before
    public void initAndFill() {
        linkedList = new MySimpleLinkedList<>();
        linkedList.add(VALUE_4);
        linkedList.add(VALUE_1);
        linkedList.add(VALUE_5);
        linkedList.add(VALUE_3);
        linkedList.add(VALUE_2);
    }

    @Test
    public void addFirst() {
        linkedList.addFirst(VALUE_TEST);
        assertGetElement(0, VALUE_TEST);
        linkedList.addFirst(VALUE_NULL);
        assertGetElement(0, VALUE_NULL);
        assertSize(7);
    }

    @Test
    public void addLast() {
        linkedList.addLast(VALUE_TEST);
        assertGetElement(linkedList.size() - 1, VALUE_TEST);
        linkedList.addLast(VALUE_NULL);
        assertGetElement(linkedList.size() - 1, VALUE_NULL);
        assertSize(7);
    }

    @Test
    public void add() {
        assertTrue(linkedList.add(VALUE_TEST));
        assertGetElement(linkedList.size() - 1, VALUE_TEST);
        assertTrue(linkedList.add(VALUE_NULL));
        assertGetElement(linkedList.size() - 1, VALUE_NULL);
        assertSize(7);
    }

    @Test
    public void addByIndex() {
        String value2 = linkedList.get(2);
        linkedList.add(2, VALUE_TEST);
        assertGetElement(2, VALUE_TEST);
        assertGetElement(3, value2);
        String value0 = linkedList.get(0);
        linkedList.add(0, VALUE_NULL);
        assertGetElement(0, VALUE_NULL);
        assertGetElement(1, value0);
        assertSize(7);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void addByIllegalIndex() {
        linkedList.add(ILLEGAL_INDEX, VALUE_TEST);
    }

    @Test
    public void get() {
        assertGetElement(0, VALUE_4);
        assertGetElement(2, VALUE_5);
        assertGetElement(4, VALUE_2);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void getByIllegalIndex() {
        linkedList.get(ILLEGAL_INDEX);
    }

    @Test
    public void getFirst() {
        assertGetElement(0, linkedList.getFirst());
        linkedList.addFirst(VALUE_TEST);
        assertEquals(VALUE_TEST, linkedList.getFirst());
    }

    @Test(expected = NoSuchElementException.class)
    public void getFirstFromEmpty() {
        linkedList.clear();
        linkedList.getFirst();
    }

    @Test
    public void getLast() {
        assertGetElement(linkedList.size() - 1, linkedList.getLast());
        assertTrue(linkedList.add(VALUE_TEST));
        assertEquals(VALUE_TEST, linkedList.getLast());
    }

    @Test(expected = NoSuchElementException.class)
    public void getLastFromEmpty() {
        linkedList.clear();
        linkedList.getLast();
    }

    @Test
    public void removeFirst() {
        String element0 = linkedList.getFirst();
        String element1 = linkedList.get(1);
        assertEquals(element0, linkedList.removeFirst());
        assertGetElement(0, element1);
        assertSize(4);
        linkedList.clear();
        linkedList.add(VALUE_1);
        assertEquals(linkedList.getFirst(), linkedList.getLast());
        assertEquals(VALUE_1, linkedList.removeFirst());
        assertSize(0);
    }

    @Test(expected = NoSuchElementException.class)
    public void removeFirstFromEmpty() {
        linkedList.clear();
        linkedList.removeFirst();
    }

    @Test
    public void removeLast() {
        String lastElement = linkedList.getLast();
        assertEquals(lastElement, linkedList.removeLast());
        assertSize(4);
        linkedList.clear();
        linkedList.add(VALUE_1);
        assertEquals(linkedList.getLast(), linkedList.getLast());
        assertEquals(VALUE_1, linkedList.removeLast());
        assertSize(0);
    }

    @Test(expected = NoSuchElementException.class)
    public void removeLastFromEmpty() {
        linkedList.clear();
        linkedList.removeLast();
    }

    @Test
    public void remove() {
        String oldEl = linkedList.get(2);
        String newEl = linkedList.get(3);
        assertEquals(oldEl, linkedList.remove(2));
        assertSize(4);
        assertGetElement(2, newEl);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeByIllegalIndex() {
        linkedList.remove(ILLEGAL_INDEX);
    }

    @Test
    public void contains() {
        assertTrue(linkedList.contains(VALUE_4));
        assertTrue(linkedList.contains(VALUE_2));
        assertFalse(linkedList.contains(VALUE_NULL));
        linkedList.add(VALUE_NULL);
        assertTrue(linkedList.contains(VALUE_NULL));
        assertFalse(linkedList.contains(VALUE_TEST));
    }

    @Test
    public void size() {
        assertSize(5);
        linkedList.remove(2);
        assertSize(4);
        linkedList.clear();
        assertSize(0);
        linkedList.add(VALUE_1);
        assertSize(1);
    }

    @Test
    public void clear() {
        assertSize(5);
        linkedList.clear();
        assertSize(0);
    }

    @Test
    public void indexOf() {
        assertEquals(0, linkedList.indexOf(linkedList.get(0)));
        assertEquals(linkedList.size() - 1, linkedList.indexOf(linkedList.get(linkedList.size() - 1)));
        linkedList.add(2, VALUE_1);
        assertGetElement(2, VALUE_1);
        assertEquals(-1, linkedList.indexOf(VALUE_TEST));
        assertEquals(-1, linkedList.indexOf(-1));
    }

    private void assertSize(int size) {
        assertEquals(size, linkedList.size());
    }

    private void assertGetElement(int index, String element) {
        assertEquals(linkedList.get(index), element);
    }
}