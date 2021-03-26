package me.lwan.algorithm.course;

import java.util.Arrays;

/**
 * 数组中的单调和(小和)
 * <pre>
 * 现定义数组单调和为所有元素i的f(i)值之和。
 * 这里的f(i)函数定义为元素i左边(不包括其自身)小于等于它的数字之和。
 * 请设计一个高效算法，计算数组的单调和。
 * </pre>
 * @see <a href="https://www.nowcoder.com/questionTerminal/8397609ba7054da382c4599d42e494f3">数组单调和(小和)</a>
 */
public class SmallSum {

    public int getSmallSum(int[] nums) {
        int[] dump = Arrays.copyOf(nums, nums.length);
        return getSmallSum(dump, nums, 0, nums.length - 1);
    }

    private int getSmallSum(int[] src, int[] dst, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = (left + right) >> 1;
        int leftSum = getSmallSum(dst, src, left, mid);
        int rightSum = getSmallSum(dst, src, mid + 1, right);

        int i = left;
        int p = left, q = mid + 1;
        int sum = 0;
        while (p <= mid && q <= right) {
            if (src[p] <= src[q]) {
                sum += src[p] * (right - q + 1);
                dst[i++] = src[p++];
            } else {
                dst[i++] = src[q++];
            }
        }
        while (p <= mid) {
            dst[i++] = src[p++];
        }
        while (q <= right) {
            dst[i++] = src[q++];
        }
        return sum + leftSum + rightSum;
    }

}
