package me.lwan.algorithm.lab;

import org.junit.jupiter.api.Test;

import java.util.Random;

public abstract class BaseTest {

    public int[] generateIntArray(int minLength, int maxLength, int minNum, int maxNum) {
        Random rnd = new Random();
        int len = nextInt(rnd, minLength, maxLength);
        int[] nums = new int[len];
        for (int i = nums.length - 1; i >= 0; i--) {
            nums[i] = nextInt(rnd, minNum, maxNum);
        }
        return nums;
    }

    /**
     * generate a random number [min, max]
     * @param rnd
     * @param min
     * @param max
     * @return
     */
    private int nextInt(Random rnd, int min, int max) {
        if (min == max) {
            return min;
        }
        return rnd.nextInt(max + 1 - min) + min;
    }

    protected int nextInt(int min, int max) {
        return nextInt(new Random(), min, max);
    }

    @Test
    public void runBatch1000() {
        runBatchTest(1000);
        System.out.println("OK");
    }

    public void runBatchTest(int count) {
        while (count-- > 0) {
            runTest();
        }
    }

    @Test
    public abstract void runTest();

}
