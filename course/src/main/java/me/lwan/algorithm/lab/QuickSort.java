package me.lwan.algorithm.lab;

public class QuickSort {

    // quick sort 1
    public void sort1(int[] arr) {
        sort1(arr, 0, arr.length - 1);
    }

    private void sort1(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int p = partition1(arr, left, right);
        sort1(arr, left, p - 1);
        sort1(arr, p + 1, right);
    }

    private int partition1(int[] arr, int left, int right) {
        int pivot = arr[right];
        int partitionIndex = left;
        int index = left;
        while (index < right) {
            if (arr[index] <= pivot) {
                swap(arr, index, partitionIndex++);
            }
            index++;
        }
        swap(arr, partitionIndex, right);
        return partitionIndex;
    }

    // quick sort 2
    public void sort2(int[] arr) {
        sort2(arr, 0, arr.length - 1);
    }

    private void sort2(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int[] p = partition2(arr, left, right);
        sort2(arr, left, p[0] - 1);
        sort2(arr, p[1] + 1, right);
    }

    private int[] partition2(int[] arr, int left, int right) {
        int pivot = arr[right];
        int leftEdge = left;
        int rightEdge = right;
        int index = left;
        while (index <= rightEdge) {
            int value = arr[index];
            if (value < pivot) {
                swap(arr, leftEdge++, index++);
            } else if (value > pivot) {
                swap(arr, rightEdge--, index);
            } else {
                index++;
            }
        }
        return new int[] { leftEdge, rightEdge };
    }

    // quick sort 3
    public void randomQuickSort(int[] arr) {
        sort3(arr, 0, arr.length - 1);
    }

    private void sort3(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        swap(arr, right, (int) (left + Math.random() * (right - left + 1)));
        int[] p = partition2(arr, left, right);
        sort3(arr, left, p[0] - 1);
        sort3(arr, p[1] + 1, right);
    }

    private void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        arr[i] ^= arr[j];
        arr[j] ^= arr[i];
        arr[i] ^= arr[j];
    }


}
