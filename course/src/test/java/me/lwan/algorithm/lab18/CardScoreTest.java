package me.lwan.algorithm.lab18;

import me.lwan.algorithm.lab.BaseTest;
import me.lwan.algorithm.lab18.CardScore;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class CardScoreTest extends BaseTest {

    private final CardScore cs = new CardScore();

    @Override
    public void runTest() {

        int[] arr = generateIntArray(6, 20, 1, 13);
        Set<Integer> set = new LinkedHashSet<>();
        for (int n : arr) {
            set.add(n);
        }
        arr = new int[set.size()];
        int i = 0;
        for (Integer n : set) {
            arr[i++] = n;
        }

        int s1 = cs.getWinnerScore(arr);
        int s2 = cs.getWinnerScoreDp(arr);

        if (s1 != s2) {
            throw new IllegalStateException();
        }

        System.out.printf("arr=%s, max score=%d\n", Arrays.toString(arr), s1);
    }

//    public static int win1(int[] arr) {
//        if (arr == null || arr.length == 0) {
//            return 0;
//        }
//        int first = f1(arr, 0, arr.length - 1);
//        int second = g1(arr, 0, arr.length - 1);
//        return Math.max(first, second);
//    }
//
//    // arr[L..R]，先手获得的最好分数返回
//    public static int f1(int[] arr, int L, int R) {
//        if (L == R) {
//            return arr[L];
//        }
//        int p1 = arr[L] + g1(arr, L + 1, R);
//        int p2 = arr[R] + g1(arr, L, R - 1);
//        return Math.max(p1, p2);
//    }
//
//    // // arr[L..R]，后手获得的最好分数返回
//    public static int g1(int[] arr, int L, int R) {
//        if (L == R) {
//            return 0;
//        }
//        int p1 = f1(arr, L + 1, R); // 对手拿走了L位置的数
//        int p2 = f1(arr, L, R - 1); // 对手拿走了R位置的数
//        return Math.min(p1, p2);
//    }

}
