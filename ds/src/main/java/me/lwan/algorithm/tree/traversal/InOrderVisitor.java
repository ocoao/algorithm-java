package me.lwan.algorithm.tree.traversal;

import me.lwan.algorithm.tree.Node;
import me.lwan.algorithm.tree.TreeVisitor;

import java.util.Stack;
import java.util.function.Consumer;

public class InOrderVisitor implements TreeVisitor {
    @Override
    public <V> void visit(Node<V> root, Consumer<Node<V>> output) {
        if (root == null) {
            return;
        }
        Stack<Node<V>> help = new Stack<>();
        Node<V> current = root;
        while (current != null || !help.isEmpty()) {
            if (current != null) {
                help.push(current);
                current = current.left;
            } else {
                current = help.pop();
                output.accept(current);
                current = current.right;
            }
        }
    }
}
