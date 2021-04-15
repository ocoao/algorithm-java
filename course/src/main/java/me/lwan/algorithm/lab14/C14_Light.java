package me.lwan.algorithm.lab14;

/**
 * 贪心算法<br/>
 * 点灯问题<br/>
 * 给定一个由字符'X','.'组成的字符串,X表示墙,.表示街道,只有.位置可以放置灯,一颗灯可以照亮本身位置及相邻两个位置.<br/>
 * 求照亮所有.位置,最少需要多少灯
 */
public class C14_Light {

    public int getMinLightCount(String road) {
        int ans = 0;
        int i = 0;
        while (i < road.length()) {
            if (road.charAt(i) == 'X') {
                i++;
            } else {
                ans++;
                if (i + 1 >= road.length()) {
                    break;
                }
                if (road.charAt(i + 1) == 'X') {
                    i += 2;
                } else {
                    i += 3;
                }
            }
        }
        return ans;
    }

}
