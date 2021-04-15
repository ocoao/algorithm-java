package me.lwan.algorithm.lab;

import java.util.Arrays;

public class ReversePairsTest extends BaseTest {

    private final ReversePairs rp = new ReversePairs();

    @Override
    public void runTest() {
        int[] nums = generateIntArray(1000, 10000, -10000, 10000);
//        int[] nums = new int[] { 7, 5, 6, 4 };

        int pairs2 = getReversePairs(nums);
        int pairs1 = rp.getReversePairs(nums);

        if (pairs1 != pairs2) {
            System.out.printf("Error... Array: %s\n Wrong Answer=%d, Right Answer=%d", Arrays.toString(nums), pairs1, pairs2);
            throw new RuntimeException();
        }
    }

    private int getReversePairs(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    count++;
                }
            }
        }
        return count;
    }



}
