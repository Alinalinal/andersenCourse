package com.alinab.taskTwoCollections.arrayList;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class MySimpleArrayListTest {

    SimpleArrayList<String> arrayList;

    static final String VALUE_1 = "first";
    static final String VALUE_2 = "second";
    static final String VALUE_3 = "third";
    static final String VALUE_4 = "fourth";
    static final String VALUE_NULL = null;
    static final String VALUE_TEST = "test";
    static final Integer ILLEGAL_VALUE = 1;

    @Before
    public void initAndFill() {
        arrayList = new MySimpleArrayList<>();
        arrayList.add(VALUE_3);
        arrayList.add(VALUE_1);
        arrayList.add(VALUE_4);
        arrayList.add(VALUE_NULL);
        arrayList.add(VALUE_2);
    }

    @Test
    public void getReturnElementByIndex() {
        assertGetElement(0, VALUE_3);
        assertGetElement(3, null);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetByIllegalArrayIndexThenThrowIndexOutOfBoundException() {
        arrayList.get(-1);
    }

    @Test
    public void addNewElementToTheEndReturnTrue() {
        assertTrue(arrayList.add(VALUE_TEST));
        arrayList.add(VALUE_1);
        assertTrue(arrayList.add(VALUE_3));
        assertGetElement(0, VALUE_3);
        assertGetElement(3, VALUE_NULL);
        assertGetElement(7, VALUE_3);
        assertSize(8);
    }

    @Test
    public void addNewElementByIndexShiftElementsFromThisIndexToTheRight() {
        assertGetElement(3, VALUE_NULL);
        assertSize(5);
        arrayList.add(3, VALUE_TEST);
        assertGetElement(3, VALUE_TEST);
        assertGetElement(4, VALUE_NULL);
        assertSize(6);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddByIllegalArrayIndexThenThrowIndexOutOfBoundsException() {
        arrayList.add(-1, VALUE_TEST);
    }

    @Test
    public void setNewElementByIndexRemoveAndReturnOldElement() {
        String oldElement = arrayList.set(1, VALUE_TEST);
        assertNotEquals(oldElement, arrayList.get(1));
        arrayList.set(4, VALUE_2);
        assertGetElement(4, VALUE_2);
        assertSize(5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetByIllegalArrayIndexThenThrowIndexOutOfBoundsException() {
        arrayList.set(-1, VALUE_TEST);
    }

    @Test
    public void removeElementByIndexReturnIt() {
        assertGetElement(3, VALUE_NULL);
        assertSize(5);
        assertEquals(arrayList.remove(3), VALUE_NULL);
        assertGetElement(3, VALUE_2);
        assertEquals(arrayList.remove(0), VALUE_3);
        assertGetElement(0, VALUE_1);
        assertSize(3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenRemoveByIllegalArrayIndexThenThrowIndexOutOfBoundsException() {
        arrayList.remove(-1);
    }

    @Test
    public void removeByElementValueReturnTrueElseReturnFalse() {
        assertGetElement(3, VALUE_NULL);
        assertSize(5);
        assertTrue(arrayList.remove(VALUE_NULL));
        assertGetElement(3, VALUE_2);
        assertGetElement(0, VALUE_3);
        assertTrue(arrayList.remove(VALUE_3));
        assertGetElement(0, VALUE_1);
        assertFalse(arrayList.remove(VALUE_TEST));
        assertSize(3);
    }

    @Test
    public void ifContainElementThenReturnTrueElseFalse() {
        assertTrue(arrayList.contains(VALUE_2));
        assertTrue(arrayList.contains(VALUE_NULL));
        assertFalse(arrayList.contains(VALUE_TEST));
        assertFalse(arrayList.contains(ILLEGAL_VALUE));
    }

    @Test
    public void clearSetSize0() {
        assertSize(5);
        arrayList.clear();
        assertSize(0);
    }

    @Test
    public void ifIsEmptyThenReturnTrueElseFalse() {
        assertFalse(arrayList.isEmpty());
        assertSize(5);
        arrayList.clear();
        assertTrue(arrayList.isEmpty());
        assertSize(0);
    }

    @Test
    public void sizeReturnActualSize() {
        assertSize(5);
        arrayList.remove(VALUE_1);
        assertSize(4);
        arrayList.clear();
        assertSize(0);
        arrayList.add(VALUE_1);
        assertSize(1);
    }

    private void assertSize(int size) {
        assertEquals(size, arrayList.size());
    }

    private void assertGetElement(int index, String element) {
        assertEquals(arrayList.get(index), element);
    }
}