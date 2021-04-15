package me.lwan.algorithm.lab;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 基数排序
 */
public class RadixSort {

    public void sort(int[] arr) {
        int minNum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            minNum = Math.min(minNum, arr[i]);
        }

        // 确保为非负数
        int offset = -minNum;
        if (offset > 0) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] += offset;
            }
        }

        int figure = getMaxFigure(arr);
        LinkedList<Integer>[] buckets = new LinkedList[10]; // 某位数为0~9的桶
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }
        for (int f = 1; f <= figure; f *= 10) { // 从低到高(个位/十位/百位...)依次处理每一位数
            for (int n : arr) {
                int digit = n / f % 10;
                buckets[digit].add(n);
            }
            int i = 0;
            for (LinkedList<Integer> bucket : buckets) {
                while (!bucket.isEmpty()) {
                    arr[i++] = bucket.removeFirst();
                }
            }
        }

        if (offset > 0) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] -= offset;
            }
        }
    }

    public void sort2(int[] arr) {
        int minNum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            minNum = Math.min(minNum, arr[i]);
        }

        // 确保为非负数
        int offset = -minNum;
        if (offset > 0) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] += offset;
            }
        }

        int figure = getMaxFigure(arr);
        int[] dump = new int[arr.length];
        int[] bucket = new int[10];
        for (int f = 1; f <= figure; f *= 10) { // 从低到高(个位/十位/百位...)依次处理每一位数
            System.arraycopy(arr, 0, dump, 0, dump.length);
            Arrays.fill(bucket, 0);
            for (int n : dump) {
                int digit = n / f % 10;
                bucket[digit]++; // 该位数的个数
            }
            for (int i = 1; i < bucket.length; i++) {
                bucket[i] += bucket[i - 1]; // 该位数的索引上限
            }
            for (int i = dump.length - 1; i >= 0; i--) {
                int digit = dump[i] / f % 10;
                int index = --bucket[digit];
                arr[index] = dump[i];
            }
        }

        if (offset > 0) {
            for (int i = 0; i < arr.length; i++) {
                arr[i] -= offset;
            }
        }
    }

    // 获取最大位数 0~9->1 10~99->10 100~999->100
    private static int getMaxFigure(int[] arr) {
        int maxNum = arr[0];
        for (int n : arr) {
            maxNum = Math.max(maxNum, n);
        }

        int figure = 1;
        while (maxNum > 0) {
            figure *= 10;
            maxNum /= 10;
        }
        return figure;
    }

}
