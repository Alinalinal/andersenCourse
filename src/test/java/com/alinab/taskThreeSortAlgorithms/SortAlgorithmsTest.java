package com.alinab.taskThreeSortAlgorithms;

import org.junit.Test;

import static org.junit.Assert.*;

public class SortAlgorithmsTest {

    private final int[] resultArray1 = {-50, -1, 0, 1, 5, 5, 7, 7, 21, 33, 44, 48, 89, 93, 100};
    private final int[] testArray1 = {93, 5, 100, 1, 33, 21, 7, 7, 89, 44, 5, 0, -50, 48, -1};
    private final int[] testArray2 = {7};
    private final int[] resultArray2 = {7};

    @Test
    public void bubbleSortTest() {
        assertArrayEquals(SortAlgorithms.bubbleSort(testArray1), resultArray1);
        assertArrayEquals(SortAlgorithms.bubbleSort(testArray2), resultArray2);
    }

    @Test
    public void selectionSortTest() {
        assertArrayEquals(SortAlgorithms.selectionSort(testArray1), resultArray1);
        assertArrayEquals(SortAlgorithms.selectionSort(testArray2), resultArray2);
    }

    @Test
    public void insertionSortTest() {
        assertArrayEquals(SortAlgorithms.insertionSort(testArray1), resultArray1);
        assertArrayEquals(SortAlgorithms.insertionSort(testArray2), resultArray2);
    }

    @Test
    public void mergeSortTest() {
        assertArrayEquals(SortAlgorithms.mergeSort(testArray1), resultArray1);
        assertArrayEquals(SortAlgorithms.mergeSort(testArray2), resultArray2);
    }

    @Test
    public void quickSortTest() {
        assertArrayEquals(SortAlgorithms.quickSort(testArray1), resultArray1);
        assertArrayEquals(SortAlgorithms.quickSort(testArray2), resultArray2);
    }

    @Test
    public void heapSortTest() {
        assertArrayEquals(SortAlgorithms.heapSort(testArray1), resultArray1);
        assertArrayEquals(SortAlgorithms.heapSort(testArray2), resultArray2);
    }
}