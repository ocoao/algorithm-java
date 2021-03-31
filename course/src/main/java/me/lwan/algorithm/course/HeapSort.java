package me.lwan.algorithm.course;

public class HeapSort {

    public void sort(int[] arr) {
        // build heap
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }
        // sort
        int size = arr.length;
        while (size > 1) {
            swap(arr, 0, --size);
            heapify(arr, 0, size);
        }
    }

    public void sort2(int[] arr) {
        // build heap by heapify
        for (int i = arr.length - 1; i >= 0; i--) {
            heapify(arr, i, arr.length);
        }
        // sort
        int size = arr.length;
        while (size > 1) {
            swap(arr, 0, --size);
            heapify(arr, 0, size);
        }
    }

    private void heapInsert(int[] arr, int index) {
        while (arr[index] > arr[(index - 1) / 2]) { // compare with parent
            swap(arr, index, (index - 1) / 2);
            index = (index - 1) / 2;
        }
    }

    private void heapify(int[] arr, int index, int size) {
        int left; // left child index
        while ((left = index * 2 + 1) < size) {
            int largeIndex = left + 1 < size && arr[left + 1] > arr[left] ? left + 1 : left;
            if (arr[index] >= arr[largeIndex]) {
                break;
            }
            swap(arr, index, largeIndex);
            index = largeIndex;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

}
