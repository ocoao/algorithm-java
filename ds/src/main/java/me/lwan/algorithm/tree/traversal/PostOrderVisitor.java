package me.lwan.algorithm.tree.traversal;

import me.lwan.algorithm.tree.Node;
import me.lwan.algorithm.tree.TreeVisitor;

import java.util.Stack;
import java.util.function.Consumer;

public class PostOrderVisitor implements TreeVisitor {
    @Override
    public <V> void visit(Node<V> root, Consumer<Node<V>> output) {
        if (root == null) {
            return;
        }
        Stack<Node<V>> help = new Stack<>();
        help.push(root);
        Node<V> top = null;
        Node<V> visited = root;
        while (!help.isEmpty()) {
            top = help.peek();
            if (top.left != null && top.left != visited && top.right != visited) {
                help.push(top.left);
            } else if (top.right != null && top.right != visited) {
                help.push(top.right);
            } else {
                output.accept(help.pop());
                visited = top;
            }
        }
    }
}
