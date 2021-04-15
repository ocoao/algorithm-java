package me.lwan.algorithm.lab14;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 贪心算法<br/>
 * IPO问题<br/>
 * @see <a href="https://leetcode-cn.com/problems/ipo/">IPO</a>
 */
public class C14_IPO {

    public int findMaxCapital(int k, int w, final int[] profits, final int[] capital) {
        PriorityQueue<Integer> capitalQueue = new PriorityQueue<>(Comparator.comparingInt(i -> capital[i]));
        PriorityQueue<Integer> profitsQueue = new PriorityQueue<>((i, j) -> profits[j] - profits[i]);

        for (int i = 0; i < capital.length; i++) {
            capitalQueue.offer(i);
        }

        for (int i = 0; i < k; i++) {
            while (!capitalQueue.isEmpty() && w >= capital[capitalQueue.peek()]) {
                profitsQueue.offer(capitalQueue.poll());
            }

            if (profitsQueue.isEmpty()) {
                return w;
            }

            w += profits[profitsQueue.poll()];
        }

        return w;
    }

}
