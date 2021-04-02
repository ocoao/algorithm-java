package me.lwan.algorithm.course;

import java.util.Arrays;

public class CountSortTest extends BaseTest {

    private final CountSort cs = new CountSort();

    @Override
    public void runTest() {
        int[] src = generateIntArray(100, 1000, -1000, 1000);

        int[] arrBase = Arrays.copyOf(src, src.length);
        int[] arr1 = Arrays.copyOf(src, src.length);
        int[] arr2 = Arrays.copyOf(src, src.length);

        Arrays.sort(arrBase);
        cs.sort(arr1);
        cs.sort2(arr2);

        if (!Arrays.equals(arrBase, arr1)) {
            throw new RuntimeException(String.format("Error...\nSrc=%s\nRight=%s\nWrong1=%s", Arrays.toString(src), Arrays.toString(arrBase), Arrays.toString(arr1)));
        }

        if (!Arrays.equals(arrBase, arr2)) {
            throw new RuntimeException(String.format("Error...\nSrc=%s\nRight=%s\nWrong2=%s", Arrays.toString(src), Arrays.toString(arrBase), Arrays.toString(arr2)));
        }
    }
}
