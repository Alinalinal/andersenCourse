package com.alinab.taskTwoCollections.treeSet;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class MySimpleTreeSetTest {

    SimpleTreeSet<String> treeSet;

    static final String VALUE_1 = "first";
    static final String VALUE_2 = "second";
    static final String VALUE_3 = "third";
    static final String VALUE_4 = "fourth";
    static final String VALUE_5 = "a fifth";
    static final String VALUE_TEST = "test";
    static final String VALUE_NULL = null;

    @Before
    public void initAndFill() {
        treeSet = new MySimpleTreeSet<>();
        treeSet.add(VALUE_1);
        treeSet.add(VALUE_2);
        treeSet.add(VALUE_3);
        treeSet.add(VALUE_4);
        treeSet.add(VALUE_5);
    }

    @Test
    public void add() {
        assertTrue(treeSet.add(VALUE_TEST));
        assertSize(6);
    }

    @Test
    public void addExistValue() {
        assertFalse(treeSet.add(VALUE_4));
        assertSize(5);
    }

    @Test(expected = NullPointerException.class)
    public void addNullValue() {
        treeSet.add(VALUE_NULL);
    }

    @Test
    public void first() {
        assertEquals(treeSet.first(), VALUE_5);
    }

    @Test(expected = NoSuchElementException.class)
    public void firstFromEmpty() {
        assertEquals(treeSet.first(), VALUE_5);
        treeSet.clear();
        assertSize(0);
        treeSet.first();
    }

    @Test
    public void last() {
        assertEquals(treeSet.last(), VALUE_3);
    }

    @Test(expected = NoSuchElementException.class)
    public void lastFromEmpty() {
        assertEquals(treeSet.last(), VALUE_3);
        treeSet.clear();
        assertSize(0);
        treeSet.last();
    }

    @Test
    public void remove() {
        assertSize(5);
        assertTrue(treeSet.remove(VALUE_5));
        assertSize(4);
        assertFalse(treeSet.remove(VALUE_TEST));
        assertSize(4);
    }

    @Test(expected = NullPointerException.class)
    public void removeNullValue() {
        treeSet.remove(VALUE_NULL);
    }

    @Test
    public void contains() {
        assertTrue(treeSet.contains(VALUE_4));
        assertFalse(treeSet.contains(VALUE_TEST));
    }

    @Test(expected = NullPointerException.class)
    public void containsNullValue() {
        treeSet.remove(VALUE_NULL);
    }

    @Test
    public void clear() {
        assertSize(5);
        treeSet.clear();
        assertSize(0);
    }

    @Test
    public void isEmpty() {
        assertFalse(treeSet.isEmpty());
        assertSize(5);
        treeSet.clear();
        assertTrue(treeSet.isEmpty());
        assertSize(0);
    }

    @Test
    public void size() {
        assertSize(5);
        treeSet.remove(VALUE_3);
        assertSize(4);
        treeSet.clear();
        assertSize(0);
        treeSet.add(VALUE_TEST);
        assertSize(1);
    }

    private void assertSize(int size) {
        assertEquals(size, treeSet.size());
    }
}