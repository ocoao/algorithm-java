package me.lwan.algorithm.course;

import java.util.Arrays;

/**
 * 某个数组arr中,
 * 对于每个元素arr[i],存在n个这样的元素arr[j]: j > i, arr[j] * 2 < arr[i]
 * 求所有arr[j]元素的累计个数
 */
public class BiggerThanRightTwice {

    public int getCount(int[] arr) {
        return getCount(Arrays.copyOf(arr, arr.length), arr, 0, arr.length - 1);
    }

    private int getCount(int[] src, int[] dst, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = (left + right) >> 1;
        int leftCount = getCount(dst, src, left, mid);
        int rightCount = getCount(dst, src, mid + 1, right);

        int count = 0;
        int p = left, q = mid + 1;
        while (p <= mid) {
            while (q <= right && src[p] > (src[q] * 2)) {
                q++;
            }
            count += q - mid - 1;
            p++;
        }


        // merge
        int i = left;
        p = left;
        q = mid + 1;
        while (p <= mid && q <= right) {
            if (src[p] <= src[q]) {
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

        return leftCount + rightCount + count;
    }

}
