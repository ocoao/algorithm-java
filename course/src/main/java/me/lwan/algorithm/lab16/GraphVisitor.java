package me.lwan.algorithm.lab16;

import java.util.function.Consumer;

public interface GraphVisitor<V> {

    void visit(Node<V> node, Consumer<V> visitor);

    default void visit(Node<V> node) {
        visit(node, System.out::println);
    }

}
