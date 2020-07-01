package me.lwan.algorithm.tree;

import java.util.function.Consumer;

public interface TreeVisitor {
    <V> void visit(Node<V> root, Consumer<Node<V>> output);

    default <V> void visit(Node<V> root) {
        visit(root, node -> System.out.print(node.value + " "));
    }
}
