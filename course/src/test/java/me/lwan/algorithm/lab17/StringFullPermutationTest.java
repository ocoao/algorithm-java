package me.lwan.algorithm.lab17;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringFullPermutationTest {

    private final StringFullPermutation stringFullPermutation = new StringFullPermutation();

    @Test
    public void testFullPermutation() {

        String s = "aacb";
        Collection<String> ans = stringFullPermutation.getFullPermutation(s);
        Collection<String> ans2 = permutation3(s);

        if (!ans.equals(ans2)) {
            throw new IllegalStateException();
        }

        for (String str : ans) {
            System.out.println(str);
        }

    }

    public List<String> permutation3(String s) {
        List<String> ans = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return ans;
        }
        char[] str = s.toCharArray();
        g2(str, 0, ans);
        return ans;
    }

    public void g2(char[] str, int index, List<String> ans) {
        if (index == str.length) {
            ans.add(String.valueOf(str));
        } else {
            boolean[] visited = new boolean[256];
            for (int i = index; i < str.length; i++) {
                if (!visited[str[i]]) {
                    visited[str[i]] = true;
                    stringFullPermutation.swap(str, index, i);
                    g2(str, index + 1, ans);
                    stringFullPermutation.swap(str, index, i);
                }
            }
        }
    }

}
