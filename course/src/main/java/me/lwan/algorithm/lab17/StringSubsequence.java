package me.lwan.algorithm.lab17;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 获取字符串全部子序列
 */
public class StringSubsequence {

    public Collection<String> getAllSubsequences(String s) {
        if (s == null || s.isEmpty()) {
            return Collections.emptyList();
        }
        Set<String> ans = new LinkedHashSet<>();
        getSubsequence(s, 0, new StringBuilder(), ans);
        return ans;
    }

    private void getSubsequence(String s, int index, StringBuilder buf, Collection<String> ans) {
        if (index == s.length()) {
            ans.add(buf.toString());
            return;
        }
        getSubsequence(s, index + 1, buf, ans);
        buf.append(s.charAt(index));
        getSubsequence(s, index + 1, buf, ans);
        buf.deleteCharAt(buf.length() - 1);
    }


}
