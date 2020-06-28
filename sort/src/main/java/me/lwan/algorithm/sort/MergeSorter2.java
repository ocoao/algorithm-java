package me.lwan.algorithm.sort;

import java.util.Arrays;

/**
 * iterative merge sorting
 */
public class MergeSorter2 extends MergeSorter {
    @Override
    public <T extends Comparable<T>> void sorting(T[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        T[] copy = Arrays.copyOf(array, array.length);
        int mergeLen = 1;
        while (mergeLen > 0 && mergeLen < array.length) {
            int left = 0;
            while (left < array.length) {
                int mid = left + mergeLen - 1;
                if (mid >= array.length) {
                    break;
                }
                int right = Math.min(mid + mergeLen, array.length - 1);
                System.arraycopy(array, left, copy, left, right - left + 1);
                merge(copy, array, left, mid, right);
                left = right + 1;
            }
            mergeLen <<= 1;

//            T[] tmp = copy;
//            array = copy;
//            copy = tmp;
        }
    }

}
