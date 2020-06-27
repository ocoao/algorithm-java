package me.lwan.algorithm.sort;

public class SelectionSorter implements ArraySorter {
    @Override
    public <T extends Comparable<T>> void sorting(T[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            int minIdx = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[minIdx].compareTo(array[j]) > 0) {
                    minIdx = j;
                }
            }
            swap(array, i, minIdx);
        }
    }
}
