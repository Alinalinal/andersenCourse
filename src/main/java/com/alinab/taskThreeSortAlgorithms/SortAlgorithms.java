package com.alinab.taskThreeSortAlgorithms;

import java.util.Arrays;

public class SortAlgorithms {

    public static int[] bubbleSort(int[] arr) {
        boolean sortStatus = false;

        while (!sortStatus) {
            sortStatus = true;

            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] > arr[i + 1]) {
                    sortStatus = false;
                    swapCells(arr, i, i + 1);
                }
            }
        }
        return arr;
    }

    public static int[] selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int leastValueInd = i;

            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[leastValueInd]) {
                    leastValueInd = j;
                }
            }
            swapCells(arr, i, leastValueInd);
        }
        return arr;
    }

    public static int[] insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int val = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > val) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = val;
        }
        return arr;
    }

    public static int[] mergeSort(int[] arr) {
        int arrLength = arr.length;
        if (arrLength < 2) {
            return arr;
        }
        int midIndex = arrLength / 2;
        int[] leftArr = Arrays.copyOfRange(arr, 0, midIndex);
        int[] rightArr = Arrays.copyOfRange(arr, midIndex, arrLength);

        mergeSort(leftArr);
        mergeSort(rightArr);

        merge(arr, leftArr, rightArr);
        return arr;
    }

    private static void merge(int[] arr, int[] leftArr, int[] rightArr) {
        int leftArrLength = leftArr.length;
        int rightArrLength = rightArr.length;

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < leftArrLength && j < rightArrLength) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }
        while (i < leftArrLength) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }
        while (j < rightArrLength) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }

    public static int[] quickSort(int[] arr) {
        return quickSort(arr, 0, arr.length - 1);
    }

    private static int[] quickSort(int[] arr, int lowIndex, int highIndex) {
        if (lowIndex > highIndex) {
            return arr;
        }

        int pivot = arr[highIndex];
        int leftIndex = lowIndex;
        int rightIndex = highIndex;

        while (leftIndex < rightIndex) {
            while (arr[leftIndex] <= pivot && leftIndex < rightIndex) {
                leftIndex++;
            }
            while (arr[rightIndex] >= pivot && leftIndex < rightIndex) {
                rightIndex--;
            }
            swapCells(arr, leftIndex, rightIndex);
        }
        swapCells(arr, leftIndex, highIndex);
        quickSort(arr, lowIndex, leftIndex - 1);
        quickSort(arr, leftIndex + 1, highIndex);
        return arr;
    }

    public static int[] heapSort(int[] arr) {
        int arrLength = arr.length;

        for (int i = arrLength / 2 - 1; i >=0; i--) {
            heapify(arr, i, arrLength);
        }

        for (int i = arrLength - 1; i >= 0; i--) {
            swapCells(arr, 0, i);
            heapify(arr, 0, i);
        }
        return arr;
    }

    private static void heapify(int[] arr, int i, int arrLength) {
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        int largest = i;

        if (left < arrLength && arr[left] > arr[largest]) {
            largest = left;
        }
        if (right < arrLength && arr[right] > arr[largest]) {
            largest = right;
        }
        if (i != largest) {
            swapCells(arr, i, largest);
            heapify(arr, largest, arrLength);
        }
    }

    private static void swapCells(int[] arr, int firstIndex, int secondIndex) {
        int temp = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = temp;
    }
}
