package me.lwan.algorithm.lab;

import java.util.Arrays;

public class HeapSortTest extends BaseTest {

    private final HeapSort hs = new HeapSort();

    @Override
    public void runTest() {
        int[] arr = generateIntArray(1000, 10000, -100, 100);

        int[] arrBase = Arrays.copyOf(arr, arr.length);
        int[] arr1 = Arrays.copyOf(arr, arr.length);
        int[] arr2 = Arrays.copyOf(arr, arr.length);

        Arrays.sort(arrBase);
        hs.sort(arr1);
        hs.sort2(arr2);

        if (!Arrays.equals(arrBase, arr1)) {
            System.out.printf("Error... src=%s, arrBase=%s, arr1=%s\n", Arrays.toString(arr), Arrays.toString(arrBase), Arrays.toString(arr1));
            throw new RuntimeException();
        }

        if (!Arrays.equals(arrBase, arr2)) {
            System.out.printf("Error... src=%s, arrBase=%s, arr2=%s\n", Arrays.toString(arr), Arrays.toString(arrBase), Arrays.toString(arr2));
            throw new RuntimeException();
        }


    }


}
