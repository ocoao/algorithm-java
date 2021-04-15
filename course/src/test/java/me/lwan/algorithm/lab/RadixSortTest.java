package me.lwan.algorithm.lab;

import java.util.Arrays;

public class RadixSortTest extends BaseTest {

    private final RadixSort rs = new RadixSort();

    @Override
    public void runTest() {
        int[] src = generateIntArray(100, 1000, -1000, 1000);

        int[] arrBase = Arrays.copyOf(src, src.length);
        int[] arr1 = Arrays.copyOf(src, src.length);
        int[] arr2 = Arrays.copyOf(src, src.length);

        Arrays.sort(arrBase);
        rs.sort(arr1);
        rs.sort2(arr2);

        if (!Arrays.equals(arrBase, arr1)) {
            throw new RuntimeException(String.format("Error...\nSrc=%s\nRight=%s\nWrong1=%s", Arrays.toString(src), Arrays.toString(arrBase), Arrays.toString(arr1)));
        }

//        if (!Arrays.equals(arrBase, arr2)) {
//            throw new RuntimeException(String.format("Error...\nSrc=%s\nRight=%s\nWrong2=%s", Arrays.toString(src), Arrays.toString(arrBase), Arrays.toString(arr2)));
//        }
    }
}
