package me.lwan.algorithm.sort;

import java.util.Random;

/**
 * choose base element randomly
 */
public class RandomQuickSorter extends QuickSorter {

    private Random randomBase = new Random();

    @Override
    protected <T extends Comparable<T>> void quickSort(T[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        swap(array, right, left + randomBase.nextInt(right - left + 1));
        int[] range = partition(array, left, right);
        quickSort(array, left, range[0] - 1);
        quickSort(array, range[1] + 1, right);
    }
}
