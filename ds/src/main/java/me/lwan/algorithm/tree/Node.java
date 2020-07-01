package me.lwan.algorithm.tree;

/**
 * the binary tree node definition
 * @param <V>
 */
public class Node<V> {

    public V value;
    public Node<V> left;
    public Node<V> right;

    public Node() {}

    public Node(V value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value + "->[ L:" + left + ", R:" + right + "]";
    }
}
