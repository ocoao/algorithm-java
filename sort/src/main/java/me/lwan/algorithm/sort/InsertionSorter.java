package me.lwan.algorithm.sort;

public class InsertionSorter implements ArraySorter {
    @Override
    public <T extends Comparable<T>> void sorting(T[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0 && array[j].compareTo(array[j - 1]) < 0; j--) {
                swap(array, j, j - 1);
            }
        }
    }
}
