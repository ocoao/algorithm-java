package me.lwan.algorithm.lab;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class LeetCodeTest {

    @Test
    public void longestSubstring() {
        System.out.println(lengthOfLongestSubstring("abba"));
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        Map<Character, Integer> indexMapping = new HashMap<>();
        int left = 0, right = 0;
        int max = 0;
        while (right < s.length()) {
            Character c = s.charAt(right);
            Integer index = indexMapping.get(c);
            if (index != null) {
                left = index + 1;
            }

            indexMapping.put(c, right++);
            max = Math.max(right - left, max);
        }
        return max;
    }

}
