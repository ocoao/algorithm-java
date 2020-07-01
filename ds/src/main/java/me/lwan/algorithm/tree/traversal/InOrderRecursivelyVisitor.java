package me.lwan.algorithm.tree.traversal;

import me.lwan.algorithm.tree.Node;
import me.lwan.algorithm.tree.TreeVisitor;

import java.util.function.Consumer;

public class InOrderRecursivelyVisitor implements TreeVisitor {
    @Override
    public <V> void visit(Node<V> root, Consumer<Node<V>> output) {
        if (root == null) {
            return;
        }
        visit(root.left, output);
        output.accept(root);
        visit(root.right, output);
    }
}
