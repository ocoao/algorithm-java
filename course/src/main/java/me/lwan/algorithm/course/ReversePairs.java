package me.lwan.algorithm.course;

import java.util.Arrays;

/**
 * 数组中的逆序对<br/>
 * <pre>
 *     在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 * </pre>
 * @see <a href="https://leetcode-cn.com/problems/shu-zu-zhong-de-ni-xu-dui-lcof/">数组中的逆序对</a>
 */
public class ReversePairs {

    public int getReversePairs(int[] nums) {
        return getReversePairs(Arrays.copyOf(nums, nums.length), nums, 0, nums.length - 1);
    }

    private int getReversePairs(int[] src, int[] dst, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = (left + right) >> 1;
        int leftPairs = getReversePairs(dst, src, left, mid);
        int rightPairs = getReversePairs(dst, src, mid + 1, right);

        int pairs = 0;
        int i = right;
        int p = mid, q = right;
        while (p >= left && q > mid) {
            if (src[p] > src[q]) {
                pairs += q - mid;
                dst[i--] = src[p--];
            } else {
                dst[i--] = src[q--];
            }
        }
        while (p >= left) {
            dst[i--] = src[p--];
        }
        while (q > mid) {
            dst[i--] = src[q--];
        }

        return pairs + leftPairs + rightPairs;
    }

}
