package me.lwan.algorithm.course;

import java.util.Arrays;

public class SmallSumTest extends BaseTest {

    private final SmallSum summer = new SmallSum();

    @Override
    public void runTest() {
        int[] nums = generateIntArray(1000, 10000, -10000, 10000);

        int sum2 = getSmallSum(nums);
        int sum1 = summer.getSmallSum(nums);

        if (sum1 != sum2) {
            System.out.printf("Error... Array: %s\n Wrong Answer=%d, Right Answer=%d", Arrays.toString(nums), sum1, sum2);
            throw new RuntimeException();
        }
    }

    private int getSmallSum(int[] nums) {
        int sum = 0;
        for (int i = 1; i < nums.length; i++) {
            int n = nums[i];
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] <= n) {
                    sum += nums[j];
                }
            }
        }
        return sum;
    }

}
