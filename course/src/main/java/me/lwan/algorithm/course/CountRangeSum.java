package me.lwan.algorithm.course;

import java.util.Arrays;

/**
 * 区间和的个数
 * @see <a href="https://leetcode-cn.com/problems/count-of-range-sum/">区间和的个数</a>
 */
public class CountRangeSum {

    public int countRangeSum(int[] arr, int lower, int upper) {
        int[] preSum = new int[arr.length];
        preSum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            preSum[i] = preSum[i - 1] + arr[i];
        }
        return count(Arrays.copyOf(preSum, preSum.length), preSum, 0, preSum.length - 1, lower, upper);
    }

    private int count(int[] preSum, int[] preSumDst, int left, int right, int lower, int upper) {
        if (left == right) {
            return preSum[left] >= lower && preSum[left] <= upper ? 1 : 0;
        }
        int mid = (left + right) >> 1;
        int countLeft = count(preSumDst, preSum, left, mid, lower, upper);
        int countRight = count(preSumDst, preSum, mid + 1, right, lower, upper);

        int count = 0;
        int windowLeft = left, windowRight = left;
        for (int i = mid + 1; i <= right; i++) {
            int preSumLower = preSum[i] - upper;
            int preSumUpper = preSum[i] - lower;
            while (windowLeft <= mid && preSum[windowLeft] < preSumLower) {
                windowLeft++;
            }
            while (windowRight <= mid && preSum[windowRight] <= preSumUpper) {
                windowRight++;
            }
            count += windowRight - windowLeft;
        }

        // merge
        int i = left;
        int p = left, q = mid + 1;
        while (p <= mid && q <= right) {
            preSumDst[i++] = preSum[p] <= preSum[q] ? preSum[p++] : preSum[q++];
        }
        while (p <= mid) {
            preSumDst[i++] = preSum[p++];
        }
        while (q <= right) {
            preSumDst[i++] = preSum[q++];
        }

        return count + countLeft + countRight;
    }

}
