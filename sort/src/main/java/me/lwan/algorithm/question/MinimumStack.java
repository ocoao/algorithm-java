package me.lwan.algorithm.question;

import java.util.Stack;

public class MinimumStack<E extends Comparable<E>> {

    private Stack<E> data = new Stack<>();
    private Stack<E> minimum = new Stack<>();

    public void push(E e) {
        data.push(e);
        if (minimum.isEmpty() || e.compareTo(minimum.peek()) < 0) {
            minimum.push(e);
        } else {
            minimum.push(minimum.peek());
        }
    }

    public E pop() {
        minimum.pop();
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
