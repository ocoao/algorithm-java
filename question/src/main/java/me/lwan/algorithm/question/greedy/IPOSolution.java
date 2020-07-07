package me.lwan.algorithm.question.greedy;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @see <a href="https://leetcode-cn.com/problems/ipo/">IPO</a>
 */
public class IPOSolution {

    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        Queue<Integer> minCapital = new PriorityQueue<>((i, j) -> Capital[i] - Capital[j]);
        Queue<Integer> maxProfit = new PriorityQueue<>((i, j) -> Profits[j] - Profits[i]);
        int len = Capital.length;
        for (int i = 0; i < len; i++) {
            minCapital.offer(i);
        }
        for (int i = 0; i < k; i++) {
            while (!minCapital.isEmpty()) {
                int index = minCapital.peek();
                if (Capital[index] > W) {
                    break;
                }
                maxProfit.offer(minCapital.poll());
            }
            if (maxProfit.isEmpty()) {
                return W;
            }
            W += Profits[maxProfit.poll()];
        }
        return W;
    }

}
