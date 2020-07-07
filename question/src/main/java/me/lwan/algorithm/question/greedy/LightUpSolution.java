package me.lwan.algorithm.question.greedy;

/**
 * 给定一个字符串str，只由'X'和'.'两种字符构成
 * 'X'表示墙，不能放灯，也不需要点亮
 * '.'表示居民点，可以放灯，需要点亮
 * 如果灯放在i位置，可以让i-1，i和i+1三个位置被点亮
 * 返回如果点亮str中所有需要点亮的位置，至少需要几盏灯
 */
public class LightUpSolution {

    public int getMinimumLights(String str) {
        int ans = 0;
        int index = 0;
        while (index < str.length()) {
            char c = str.charAt(index);
            if (c == 'X') {
                index++;
                continue;
            }
            ans++;
            if (index + 1 >= str.length()) {
                break;
            }
            if (str.charAt(index + 1) == 'X') {
                index += 2;
                continue;
            }
            index += 3;
        }
        return ans;
    }

}
