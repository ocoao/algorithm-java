package me.lwan.algorithm.course;

import java.util.Arrays;

public class CountRangeSumTest extends BaseTest {

    private final CountRangeSum crs = new CountRangeSum();

    @Override
    public void runTest() {
        int[] arr = generateIntArray(1000, 10000, -10000, 10000);
        int lower = nextInt(-10000, 10000);
        int upper = nextInt(lower + 10, lower + 100);

        int c1 = countRangeSum(arr, lower, upper);
        int c2 = crs.countRangeSum(arr, lower, upper);

        if (c1 != c2) {
            System.out.printf("Error... Arr=%s,\n Lower=%d, Upper=%d, Right Answer=%d, Wrong Answer=%d",
                    Arrays.toString(arr), lower, upper, c1, c2);
            throw new RuntimeException();
        }
    }

    private int countRangeSum(int[] arr, int lower, int upper) {
        int[] preSum = new int[arr.length];
        preSum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            preSum[i] = preSum[i - 1] + arr[i];
        }

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            int pre = i > 0 ? preSum[i - 1] : 0;
            for (int j = i; j < arr.length; j++) {
                int sum = preSum[j] - pre;
                if (sum >= lower && sum <= upper) {
                    count++;
                }
            }
        }
        return count;
    }

}
