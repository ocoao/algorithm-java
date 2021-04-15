package me.lwan.algorithm.lab14;

import java.util.PriorityQueue;

/**
 * 贪心算法<br/>
 * 切分金条问题<br/>
 * 给定一个整型数组,表示金条最终切分后的长度.每次切一刀,代价是当前切割的总长度.求切割的最小总代价<br/>
 * (哈夫曼编码)
 */
public class C14_SplitGold {

    public int getMinCost(int[] arr) {
        PriorityQueue<Integer> p = new PriorityQueue<>();
        for (int n : arr) {
            p.offer(n);
        }

        int ans = 0;
        while (p.size() > 1) {
            int c = p.poll() + p.poll();
            ans += c;
            p.offer(c);
        }

        return ans;
    }

}
