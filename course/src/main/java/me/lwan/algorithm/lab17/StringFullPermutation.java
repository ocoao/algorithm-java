package me.lwan.algorithm.lab17;

import java.util.*;

/**
 * 字符串全排列
 */
public class StringFullPermutation {

    public Collection<String> getFullPermutation(String s) {
        List<String> ans = new ArrayList<>();
        getFullPermutation(s.toCharArray(), 0, ans);
        return ans;
    }

    private void getFullPermutation(char[] arr, int index, Collection<String> ans) {
        if (index == arr.length) {
            ans.add(new String(arr));
            return;
        }
        Set<Character> visited = new HashSet<>();
        for (int i = index; i < arr.length; i++) {
            if (!visited.add(arr[i])) {
                continue;
            }
            swap(arr, i, index);
            getFullPermutation(arr, index + 1, ans);
            swap(arr, i, index);
        }
    }

    public void swap(char[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        char t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }


}
