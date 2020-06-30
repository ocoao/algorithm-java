package me.lwan.algorithm.linkedlist;

public class Node<E> {

    public E value;
    public Node<E> next;

    public Node(E value) {
        this(value, null);
    }

    public Node(E value, Node<E> next) {
        this.value = value;
        this.next = next;
    }

    @Override
    public String toString() {
        return "NODE[" + value + ']';
    }
}
