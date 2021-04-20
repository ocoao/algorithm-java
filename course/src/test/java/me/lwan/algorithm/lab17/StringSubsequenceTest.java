package me.lwan.algorithm.lab17;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class StringSubsequenceTest {

    private final StringSubsequence stringSubsequence = new StringSubsequence();

    @Test
    public void getAllSubsequences() {
        Collection<String> ans = stringSubsequence.getAllSubsequences("abc");

        Collection<String> ans2 = subsNoRepeat("abc");
        if (ans.size() != ans2.size()) {
            throw new IllegalStateException();
        }
        for (String s : ans2) {
            if (!ans.contains(s)) {
                throw new IllegalStateException();
            }
        }

        for (String s : ans) {
            System.out.println(s);
        }
    }

    public static List<String> subsNoRepeat(String s) {
        char[] str = s.toCharArray();
        String path = "";
        HashSet<String> set = new HashSet<>();
        process2(str, 0, set, path);
        List<String> ans = new ArrayList<>();
        for (String cur : set) {
            ans.add(cur);
        }
        return ans;
    }

    public static void process2(char[] str, int index, HashSet<String> set, String path) {
        if (index == str.length) {
            set.add(path);
            return;
        }
        String no = path;
        process2(str, index + 1, set, no);
        String yes = path + String.valueOf(str[index]);
        process2(str, index + 1, set, yes);
    }

}
