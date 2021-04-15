package me.lwan.algorithm.lab;

import java.util.Arrays;

public class BiggerThanRightTwiceTest extends BaseTest {

    private final BiggerThanRightTwice inst = new BiggerThanRightTwice();

    @Override
    public void runTest() {
        int[] nums = generateIntArray(1000, 10000, -10000, 10000);

        int count2 = getCount2(nums);
        int count1 = inst.getCount(nums);

        if (count1 != count2) {
            System.out.printf("Error... Array: %s\n Wrong Answer=%d, Right Answer=%d", Arrays.toString(nums), count1, count2);
            throw new RuntimeException();
        }
    }

    private int getCount(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((nums[j] << 1) < nums[i]) {
                    count++;
                }
            }
        }
        return count;
    }

    private int getCount2(int[] arr) {
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > (arr[j] << 1)) {
                    ans++;
                }
            }
        }
        return ans;
    }

}
