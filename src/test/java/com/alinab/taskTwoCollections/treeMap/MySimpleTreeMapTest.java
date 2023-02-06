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
    public void putNewValueByNewKeyReturnNull() {
        assertNull(treeMap.put(KEY_TEST, VALUE_TEST));
        assertSize(6);
        assertGetValue(KEY_TEST, VALUE_TEST);
    }

    @Test
    public void putNewValueByExistKeyReturnOldValue() {
        treeMap.put(KEY_2, VALUE_TEST);
        assertSize(5);
        assertGetValue(KEY_2, VALUE_TEST);
    }

    @Test(expected = NullPointerException.class)
    public void whenPutNewValueByNullKeyThenThrowNullPointerException() {
        treeMap.put(null, VALUE_TEST);
    }

    @Test
    public void firstEntryReturnFirstKeyValueOrNullIfEmpty() {
        assertEquals(treeMap.firstEntry().getKey(), KEY_5);
        assertEquals(treeMap.firstEntry().getValue(), VALUE_5);
    }

    @Test
    public void lastEntryReturnLastKeyValueOrNullIfEmpty() {
        assertEquals(treeMap.lastEntry().getKey(), KEY_3);
        assertEquals(treeMap.lastEntry().getValue(), VALUE_3);
    }

    @Test
    public void getReturnValueByKey() {
        assertGetValue(KEY_1, VALUE_1);
        assertGetValue(KEY_3, VALUE_3);
        assertGetValue(KEY_4, VALUE_4);
    }

    @Test
    public void getReturnNullByNotExistKey() {
        Integer result = treeMap.get("test");
        assertNull(result);
    }

    @Test(expected = NullPointerException.class)
    public void whenGetByNullKeyThenThrowNullPointerException() {
        treeMap.get(KEY_NULL);
    }

    @Test
    public void ifContainsKeyReturnTrueElseFalse() {
        assertTrue(treeMap.containsKey(KEY_2));
        assertFalse(treeMap.containsKey(KEY_TEST));
    }

    @Test(expected = NullPointerException.class)
    public void whenCheckContainsKeyByNullKeyThenThrowNullPointerException() {
        treeMap.containsKey(KEY_NULL);
    }

    @Test
    public void ifContainsValueReturnTrueElseFalse() {
        assertTrue(treeMap.containsValue(VALUE_5));
        assertFalse(treeMap.containsKey(KEY_TEST));
    }

    @Test
    public void removeValueByKeyIfExistThenReturnValueElseNull() {
        assertEquals(treeMap.remove(KEY_5), VALUE_5);
        assertNull(treeMap.get(KEY_5));
        assertSize(4);
        assertNull(treeMap.remove(KEY_TEST));
        assertSize(4);
    }

    @Test(expected = NullPointerException.class)
    public void whenRemoveByNullKeyThenThrowNullPointerException() {
        treeMap.remove(KEY_NULL);
    }

    @Test
    public void replaceValueByKeyReturnOldValueOrNullIfKeyNotExist() {
        Integer value3 = treeMap.replace(KEY_3, VALUE_TEST);
        assertEquals(value3, VALUE_3);
        assertGetValue(KEY_3, VALUE_TEST);
        Integer notExist = treeMap.replace(KEY_TEST, VALUE_TEST);
        assertNull(notExist);
        assertSize(5);
    }

    @Test(expected = NullPointerException.class)
    public void whenReplaceByNullKeyThenThrowNullPointerException() {
        treeMap.replace(KEY_NULL, VALUE_TEST);
    }

    @Test
    public void clearSetSize0() {
        assertSize(5);
        treeMap.clear();
        assertSize(0);
    }

    @Test
    public void ifIsEmptyThenReturnTrueElseFalse() {
        assertFalse(treeMap.isEmpty());
        assertSize(5);
        treeMap.clear();
        assertTrue(treeMap.isEmpty());
        assertSize(0);
    }

    @Test
    public void sizeReturnActualSize() {
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