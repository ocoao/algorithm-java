package me.lwan.algorithm.course;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 最大线段重合问题<br/>
 * 给定很多线段，每个线段都有两个数[start,end]，表示线段开始位置和结束位置，左右都是闭区间<br/>
 * 规定:<br/>
 * 1. 线段的开始和结束位置一定都是整数<br/>
 * 2. 线段重合区域的长度必须>0<br/>
 * 返回线段重合最多区域中，包含了几条线段
 */
public class LineOverlap {

    public static class Line implements Comparable<Line> {
        public final int start;
        public final int end;
        public Line(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Line o) {
            return start - o.start;
        }
    }

    public int getMaxOverlapCount(Line[] lines) {

        Arrays.sort(lines);

        int count = 0;
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (Line line : lines) {
            while (!heap.isEmpty() && heap.peek() <= line.start) {
                heap.poll();
            }
            heap.add(line.end);
            count = Math.max(count, heap.size());
        }

        return count;
    }

}
