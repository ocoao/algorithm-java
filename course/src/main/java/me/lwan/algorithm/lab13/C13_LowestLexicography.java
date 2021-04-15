package me.lwan.algorithm.lab13;

import java.util.Arrays;

/**
 * 贪心算法<br/>
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
