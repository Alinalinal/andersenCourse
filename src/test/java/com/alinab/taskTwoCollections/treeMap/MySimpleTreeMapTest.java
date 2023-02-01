package com.alinab.taskTwoCollections.treeMap;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class MySimpleTreeMapTest {

    SimpleTreeMap<String, Integer> treeMap;

    static final String KEY_1 = "first";
    static final String KEY_2 = "second";
    static final String KEY_3 = "third";
    static final String KEY_4 = "fourth";
    static final String KEY_5 = "a fifth";
    static final String KEY_TEST = "test";
    static final String KEY_NULL = null;

    static final Integer VALUE_1 = 1;
    static final Integer VALUE_2 = 2;
    static final Integer VALUE_3 = 3;
    static final Integer VALUE_4 = 4;
    static final Integer VALUE_5 = 5;
    static final Integer VALUE_TEST = 100;
    static final Integer VALUE_NULL = null;

    @Before
    public void initAndFill() {
        treeMap = new MySimpleTreeMap<>();
        treeMap.put(KEY_1, VALUE_1);
        treeMap.put(KEY_2, VALUE_2);
        treeMap.put(KEY_3, VALUE_3);
        treeMap.put(KEY_4, VALUE_4);
        treeMap.put(KEY_5, VALUE_5);
    }

    @Test
    public void put() {
        assertNull(treeMap.put(KEY_TEST, VALUE_TEST));
        assertSize(6);
        assertGetValue(KEY_TEST, VALUE_TEST);
    }

    @Test
    public void putExistKey() {
        treeMap.put(KEY_2, VALUE_TEST);
        assertSize(5);
        assertGetValue(KEY_2, VALUE_TEST);
    }

    @Test(expected = NullPointerException.class)
    public void putNullKey() {
        treeMap.put(null, VALUE_TEST);
    }

    @Test
    public void firstEntry() {
        assertEquals(treeMap.firstEntry().getKey(), KEY_5);
        assertEquals(treeMap.firstEntry().getValue(), VALUE_5);
    }

    @Test
    public void lastEntry() {
        assertEquals(treeMap.lastEntry().getKey(), KEY_3);
        assertEquals(treeMap.lastEntry().getValue(), VALUE_3);
    }

    @Test
    public void get() {
        assertGetValue(KEY_1, VALUE_1);
        assertGetValue(KEY_3, VALUE_3);
        assertGetValue(KEY_4, VALUE_4);
    }

    @Test
    public void getNotExistKey() {
        Integer result = treeMap.get("test");
        assertNull(result);
    }

    @Test(expected = NullPointerException.class)
    public void getNullKey() {
        treeMap.get(KEY_NULL);
    }

    @Test
    public void containsKey() {
        assertTrue(treeMap.containsKey(KEY_2));
        assertFalse(treeMap.containsKey(KEY_TEST));
    }

    @Test(expected = NullPointerException.class)
    public void containsNullKey() {
        treeMap.containsKey(KEY_NULL);
    }

    @Test
    public void containsValue() {
        assertTrue(treeMap.containsValue(VALUE_5));
        assertFalse(treeMap.containsKey(KEY_TEST));
    }

    @Test
    public void remove() {
        assertEquals(treeMap.remove(KEY_5), VALUE_5);
        assertNull(treeMap.get(KEY_5));
        assertSize(4);
        assertNull(treeMap.remove(KEY_TEST));
        assertSize(4);
    }

    @Test(expected = NullPointerException.class)
    public void removeNullKey() {
        treeMap.remove(KEY_NULL);
    }

    @Test
    public void replace() {
        Integer value3 = treeMap.replace(KEY_3, VALUE_TEST);
        assertEquals(value3, VALUE_3);
        assertGetValue(KEY_3, VALUE_TEST);
        Integer notExist = treeMap.replace(KEY_TEST, VALUE_TEST);
        assertNull(notExist);
        assertSize(5);
    }

    @Test(expected = NullPointerException.class)
    public void replaceNullKey() {
        treeMap.replace(KEY_NULL, VALUE_TEST);
    }

    @Test
    public void clear() {
        assertSize(5);
        treeMap.clear();
        assertSize(0);
    }

    @Test
    public void isEmpty() {
        assertFalse(treeMap.isEmpty());
        assertSize(5);
        treeMap.clear();
        assertTrue(treeMap.isEmpty());
        assertSize(0);
    }

    @Test
    public void size() {
        assertSize(5);
        treeMap.remove(KEY_3);
        assertSize(4);
        treeMap.clear();
        assertSize(0);
        treeMap.put(KEY_TEST, VALUE_TEST);
        assertSize(1);
    }

    private void assertSize(int size) {
        assertEquals(size, treeMap.size());
    }

    private void assertGetValue(String key, Integer value) {
        assertEquals(treeMap.get(key), value);
    }
}