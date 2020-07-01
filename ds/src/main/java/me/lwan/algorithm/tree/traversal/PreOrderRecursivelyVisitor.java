package me.lwan.algorithm.tree.traversal;

import me.lwan.algorithm.tree.Node;
import me.lwan.algorithm.tree.TreeVisitor;

import java.util.function.Consumer;

public class PreOrderRecursivelyVisitor implements TreeVisitor {
    @Override
    public <V> void visit(Node<V> root, Consumer<Node<V>> output) {
        if (root == null) {
            return;
        }
        output.accept(root);
        visit(root.left, output);
        visit(root.right, output);
    }

}
