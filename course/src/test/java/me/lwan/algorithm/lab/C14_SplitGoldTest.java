package me.lwan.algorithm.lab;

import me.lwan.algorithm.lab14.C14_SplitGold;

public class C14_SplitGoldTest extends BaseTest {

    private final C14_SplitGold splitGold = new C14_SplitGold();

    @Override
    public void runTest() {
        int[] arr = generateIntArray(1, 8, 1, 1000);
        if (getMinCost(arr) != splitGold.getMinCost(arr)) {
            throw new IllegalStateException();
        }
    }

    private int getMinCost(int[] arr) {
        return getMinCost(arr, 0);
    }

    // preCost: 之前的合并代价
    private int getMinCost(int[] arr, int preCost) {
        if (arr.length == 1) {
            return preCost;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                ans = Math.min(ans, getMinCost(merge(arr, i, j), preCost + arr[i] + arr[j]));
            }
        }
        return ans;
    }

    private int[] merge(int[] arr, int i, int j) {
        int[] dump = new int[arr.length - 1];
        int n = 0;
        for (int index = 0; index < arr.length; index++) {
            if (index != i && index != j) {
                dump[n++] = arr[index];
            }
        }
        dump[n] = arr[i] + arr[j];
        return dump;
    }

}























