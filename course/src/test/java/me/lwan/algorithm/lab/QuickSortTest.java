package me.lwan.algorithm.lab;

import java.util.Arrays;

public class QuickSortTest extends BaseTest {

    private final QuickSort qs = new QuickSort();

    @Override
    public void runTest() {
        int[] arr = generateIntArray(1000, 10000, -10000, 10000);
//        new int[] {3,2,4,1,5,7,3,3,4};//
        int[] arrBase = Arrays.copyOf(arr, arr.length);
        int[] arr1 = Arrays.copyOf(arr, arr.length);
        int[] arr2 = Arrays.copyOf(arr, arr.length);
        int[] arr3 = Arrays.copyOf(arr, arr.length);

        Arrays.sort(arrBase);
        qs.sort1(arr1);

        if (!Arrays.equals(arrBase, arr1)) {
            System.out.printf("Error... src=%s, arrBase=%s, arr1=%s\n", Arrays.toString(arr), Arrays.toString(arrBase), Arrays.toString(arr1));
            throw new RuntimeException();
        }

        qs.sort2(arr2);
        if (!Arrays.equals(arrBase, arr2)) {
            System.out.printf("Error... src=%s, arrBase=%s, arr2=%s\n", Arrays.toString(arr), Arrays.toString(arrBase), Arrays.toString(arr2));
            throw new RuntimeException();
        }

        qs.randomQuickSort(arr3);
        if (!Arrays.equals(arrBase, arr3)) {
            System.out.printf("Error... src=%s, arrBase=%s, arr3=%s\n", Arrays.toString(arr), Arrays.toString(arrBase), Arrays.toString(arr3));
            throw new RuntimeException();
        }

    }
}
