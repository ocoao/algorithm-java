package me.lwan.algorithm.tree;

public class ExtendNode<V> extends Node<V> {

    private ExtendNode<V> parent;

    public ExtendNode() {
    }

    public ExtendNode(V value) {
        super(value);
    }

    public ExtendNode<V> getLeft() {
        return (ExtendNode<V>) left;
    }

    public void setLeft(ExtendNode<V> left) {
        this.left = left;
        left.parent = this;
    }

    public ExtendNode<V> getRight() {
        return (ExtendNode<V>) right;
    }

    public void setRight(ExtendNode<V> right) {
        this.right = right;
        right.parent = this;
    }

    public ExtendNode<V> getParent() {
        return parent;
    }

}
