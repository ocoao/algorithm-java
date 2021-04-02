package me.lwan.algorithm.course;

import java.util.Arrays;

/**
 * 计数排序
 */
public class CountSort {

    public void sort(int[] arr) {
        int min = arr[0], max = arr[0];
        for (int n : arr) {
            min = Math.min(n, min);
            max = Math.max(n, max);
        }

        // bucket[i]存储arr中每个元素(i+min)的个数
        int[] bucket = new int[max - min + 1];
        for (int n : arr) {
            bucket[n - min]++;
        }

        int i = 0;
        for (int j = 0; j < bucket.length; j++) {
            while (bucket[j]-- > 0) {
                arr[i++] = j + min;
            }
        }
    }

    public void sort2(int[] arr) {
        int min = arr[0], max = arr[0];
        for (int n : arr) {
            min = Math.min(n, min);
            max = Math.max(n, max);
        }

        int[] bucket = new int[max - min + 1];
        for (int n : arr) {
            bucket[n - min]++; // 此时的bucket[i]表示元素i+min的个数
        }

        for (int i = 1; i < bucket.length; i++) {
            bucket[i] += bucket[i - 1]; // 此时的bucket[i]表示元素i+min在排序后的数组中的位置上限
        }

        int[] dump = Arrays.copyOf(arr, arr.length);
        for (int i = dump.length - 1; i >= 0; i--) {
            int index = --bucket[dump[i] - min];
            arr[index] = dump[i];
        }
    }

}
