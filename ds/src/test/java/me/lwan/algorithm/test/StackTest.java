package me.lwan.algorithm.test;

import me.lwan.algorithm.stack.MinimumStack;
import org.junit.jupiter.api.Test;

import java.util.Stack;

public class StackTest {

    private static class MinimumStack2<E extends Comparable<E>> extends Stack<E> {
        public E getMinimum() {
            E m = null;
            for (int i = size() - 1; i >= 0; i--) {
                E e = elementAt(i);
                if (m == null || e.compareTo(m) < 0) {
                    m = e;
                }
            }
            return m;
        }
    }

    @Test
    public void testMinimumStack() {
        MinimumStack<Integer> ms = new MinimumStack<>();
        MinimumStack2<Integer> ms2 = new MinimumStack2<>();

        int count = 10000;
        while (count-- > 0) {
            if (ms.size() != ms2.size()) {
                System.err.println("SIZE ERR...");
                return;
            }
            if (Math.random() < 0.5 && !ms.isEmpty()) {
                if (!ms.pop().equals(ms2.pop())) {
                    System.err.println("POP ERR...");
                    return;
                }
                continue;
            }
            if (ms.getMinimum() != null && !ms.getMinimum().equals(ms2.getMinimum())) {
                System.err.println("MIN ERR...");
                return;
            }
            Integer n = (int) (Math.random() * 1000);
            ms.push(n);
            ms2.push(n);
        }
        System.out.println("OK");
    }


}
