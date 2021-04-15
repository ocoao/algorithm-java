package me.lwan.algorithm.lab;


import me.lwan.algorithm.lab14.C14_IPO;

import java.util.Comparator;
import java.util.PriorityQueue;

public class C14_IPOTest extends BaseTest {

    private final C14_IPO ipo = new C14_IPO();

    @Override
    public void runTest() {
        int[] profits = generateIntArray(10, 20, 1, 100);
        int[] capital = generateIntArray(profits.length, profits.length, 1, 100);
        int k = nextInt(3, 20);
        int w = nextInt(1, 100);

        if (findMaximizedCapital(k, w, profits, capital) != ipo.findMaxCapital(k, w, profits, capital)) {
            throw new IllegalStateException();
        }
    }

    public int findMaximizedCapital(int K, int W, int[] Profits, int[] Capital) {
        PriorityQueue<Program> minCostQ = new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Program> maxProfitQ = new PriorityQueue<>(new MaxProfitComparator());
        for (int i = 0; i < Profits.length; i++) {
            minCostQ.add(new Program(Profits[i], Capital[i]));
        }
        for (int i = 0; i < K; i++) {
            while (!minCostQ.isEmpty() && minCostQ.peek().c <= W) {
                maxProfitQ.add(minCostQ.poll());
            }
            if (maxProfitQ.isEmpty()) {
                return W;
            }
            W += maxProfitQ.poll().p;
        }
        return W;
    }

    public static class Program {
        public int p;
        public int c;

        public Program(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    public static class MinCostComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o1.c - o2.c;
        }

    }

    public static class MaxProfitComparator implements Comparator<Program> {

        @Override
        public int compare(Program o1, Program o2) {
            return o2.p - o1.p;
        }

    }

}
