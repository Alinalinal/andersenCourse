package com.alinab.taskTwoCollections.queue;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class MySimpleQueueTest {

    SimpleQueue<String> queue;

    static final String VALUE_1 = "first";
    static final String VALUE_2 = "second";
    static final String VALUE_3 = "third";
    static final String VALUE_4 = "fourth";
    static final String VALUE_TEST = "test";
    static final String VALUE_NULL = null;

    @Before
    public void initAndFill() {
        queue = new MySimpleQueue<>(5);
        queue.offer(VALUE_2);
        queue.offer(VALUE_1);
        queue.offer(VALUE_3);
        queue.offer(VALUE_4);
    }

    @Test
    public void offer() {
        assertSize(4);
        assertTrue(queue.offer(VALUE_TEST));
        assertFalse(queue.offer(VALUE_1));
        assertSize(5);
    }

    @Test(expected = NullPointerException.class)
    public void offerNull() {
        queue.offer(VALUE_NULL);
    }

    @Test
    public void peek() {
        assertEquals(queue.peek(), VALUE_2);
        assertEquals(queue.peek(), VALUE_2);
        assertSize(4);
    }

    @Test
    public void poll() {
        assertEquals(queue.poll(), VALUE_2);
        assertEquals(queue.poll(), VALUE_1);
        assertEquals(queue.poll(), VALUE_3);
        assertEquals(queue.poll(), VALUE_4);
        assertNull(queue.poll());
        assertSize(0);
    }

    @Test
    public void contains() {
        assertTrue(queue.contains(VALUE_2));
        assertTrue(queue.contains(VALUE_3));
        assertFalse(queue.contains(VALUE_TEST));
        assertFalse(queue.contains(VALUE_NULL));
    }

    @Test
    public void clear() {
        assertSize(4);
        queue.clear();
        assertSize(0);
    }

    @Test
    public void size() {
        assertSize(4);
        queue.poll();
        assertSize(3);
        queue.clear();
        assertSize(0);
        queue.offer(VALUE_TEST);
        assertSize(1);
    }

    private void assertSize(int size) {
        assertEquals(size, queue.size());
    }
}