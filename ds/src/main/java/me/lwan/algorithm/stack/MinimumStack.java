package me.lwan.algorithm.stack;

import java.util.Stack;

/**
 * Implement a stack that can get the minimum value, and time complexity is O(1).
 * @param <E>
 */
public class MinimumStack<E extends Comparable<E>> {

    protected Stack<E> data = new Stack<>();
    protected Stack<E> minimum = new Stack<>();

    public void push(E e) {
        data.push(e);
//        if (minimum.isEmpty() || e.compareTo(minimum.peek()) < 0) {
//            minimum.push(e);
//        } else {
//            minimum.push(minimum.peek());
//        }
        if (!minimum.isEmpty() && e.compareTo(minimum.peek()) > 0) {
            return;
        }
        minimum.push(e);
    }

    public E pop() {
//        minimum.pop();
        if (!minimum.isEmpty() && minimum.peek() == data.peek()) {
            minimum.pop();
        }
        return data.pop();
    }

    public E peek() {
        return data.peek();
    }

    public E getMinimum() {
        return minimum.empty() ? null : minimum.peek();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public int size() {
        return data.size();
    }

}
