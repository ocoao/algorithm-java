package me.lwan.algorithm.sort;

import java.util.Arrays;

/**
 * recursive merge sorting
 */
public class MergeSorter implements ArraySorter {
    @Override
    public <T extends Comparable<T>> void sorting(T[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        sorting(Arrays.copyOf(array, array.length), array, 0, array.length - 1);
    }

    private <T extends Comparable<T>> void sorting(T[] src, T[] dest, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = left + ((right - left) >> 1);
        sorting(dest, src, left, mid);
        sorting(dest, src, mid + 1, right);
        merge(src, dest, left, mid, right);
    }

    protected <T extends Comparable<T>> void merge(T[] src, T[] dest, int left, int mid, int right) {
        int i = left;
        int p = left, q = mid + 1;
        while (p <= mid && q <= right) {
            if (src[p].compareTo(src[q]) <= 0) {
                dest[i++] = src[p++];
            } else {
                dest[i++] = src[q++];
            }
        }
        while (p <= mid) {
            dest[i++] = src[p++];
        }
        while (q <= right) {
            dest[i++] = src[q++];
        }
    }

}
