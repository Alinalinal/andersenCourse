package com.alinab.taskTwoCollections.hashMap;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class MySimpleHashMapTest {

    SimpleHashMap<String, Integer> hashMap;

    static final String KEY_1 = "first";
    static final String KEY_2 = "second";
    static final String KEY_3 = "third";
    static final String KEY_4 = "fourth";
    static final String KEY_5 = "a fifth";
    static final String KEY_NULL = null;
    static final String KEY_TEST = "test";

    static final Integer VALUE_1 = 1;
    static final Integer VALUE_2 = 2;
    static final Integer VALUE_3 = 3;
    static final Integer VALUE_4 = 4;
    static final Integer VALUE_5 = 5;
    static final Integer VALUE_NULL = null;
    static final Integer VALUE_TEST = 100;

    @Before
    public void initAndFill() {
        hashMap = new MySimpleHashMap<>();
        hashMap.put(KEY_1, VALUE_1);
        hashMap.put(KEY_2, VALUE_2);
        hashMap.put(KEY_3, VALUE_3);
        hashMap.put(KEY_NULL, VALUE_NULL);
        hashMap.put(KEY_4, VALUE_4);
        hashMap.put(KEY_5, VALUE_5);
    }

    @Test
    public void getReturnValueByKey() {
        assertGetValue(KEY_1, VALUE_1);
        assertGetValue(KEY_5, VALUE_5);
        assertGetValue(KEY_NULL, VALUE_NULL);
        assertGetValue(KEY_TEST, null);
    }

    @Test
    public void ifContainsKeyThenReturnTrueElseFalse() {
        assertTrue(hashMap.containsKey(KEY_1));
        assertTrue(hashMap.containsKey(KEY_5));
        assertTrue(hashMap.containsKey(KEY_NULL));
        assertFalse(hashMap.containsKey(KEY_TEST));

    }

    @Test
    public void ifContainsValueThenReturnTrueElseFalse() {
        assertTrue(hashMap.containsValue(VALUE_1));
        assertTrue(hashMap.containsValue(VALUE_5));
        assertTrue(hashMap.containsValue(VALUE_NULL));
        assertFalse(hashMap.containsValue(VALUE_TEST));
        assertFalse(hashMap.containsValue(KEY_1));
    }

    @Test
    public void putNewValueByNotExistKeyReturnNull() {
        assertNull(hashMap.put(KEY_TEST, VALUE_TEST));
        assertSize(7);
        assertGetValue(KEY_TEST, VALUE_TEST);
    }

    @Test
    public void putNewValueByExistKeyReturnOldValue() {
        assertEquals(hashMap.put(KEY_2, VALUE_TEST), VALUE_2);
        assertSize(6);
        assertGetValue(KEY_2, VALUE_TEST);
    }

    @Test
    public void removeValueByKeyIfExistThenReturnValueElseNull() {
        assertEquals(hashMap.remove(KEY_5), VALUE_5);
        assertNull(hashMap.get(KEY_5));
        assertSize(5);
        assertNull(hashMap.remove(KEY_TEST));
        assertSize(5);
        assertNull(hashMap.remove(KEY_NULL));
        assertSize(4);
    }

    @Test
    public void removeValueByKeyAndValueIfPairExistThenReturnTrueElseFalse() {
        assertTrue(hashMap.remove(KEY_5, VALUE_5));
        assertNull(hashMap.get(KEY_5));
        assertSize(5);
        assertFalse(hashMap.remove(KEY_TEST, VALUE_5));
        assertFalse(hashMap.remove(KEY_5, VALUE_TEST));
        assertSize(5);
        assertTrue(hashMap.remove(KEY_NULL, VALUE_NULL));
        assertSize(4);
    }

    @Test
    public void clearSetSize0() {
        assertSize(6);
        hashMap.clear();
        assertSize(0);
    }

    @Test
    public void ifIsEmptyThenReturnTrueElseFalse() {
        assertFalse(hashMap.isEmpty());
        assertSize(6);
        hashMap.clear();
        assertTrue(hashMap.isEmpty());
        assertSize(0);
    }

    @Test
    public void sizeReturnActualSize() {
        assertSize(6);
        hashMap.remove(KEY_3);
        assertSize(5);
        hashMap.clear();
        assertSize(0);
        hashMap.put(KEY_TEST, VALUE_TEST);
        assertSize(1);
    }

    private void assertSize(int size) {
        assertEquals(size, hashMap.size());
    }

    private void assertGetValue(String key, Integer value) {
        assertEquals(hashMap.get(key), value);
    }
}