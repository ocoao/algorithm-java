package me.lwan.algorithm.course;

import java.util.Arrays;

/**
 * 字符串数组最小字典序组合
 */
public class C13_LowestLexicography {

    public String getLowestString(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        Arrays.sort(strs, (a, b) -> (a + b).compareTo(b + a));
        StringBuilder buf = new StringBuilder();
        for (String s : strs) {
            buf.append(s);
        }
        return buf.toString();
    }

}
