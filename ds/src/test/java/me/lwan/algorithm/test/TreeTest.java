package me.lwan.algorithm.test;

import me.lwan.algorithm.tree.Node;
import me.lwan.algorithm.tree.TreeVisitor;
import me.lwan.algorithm.tree.traversal.*;
import org.junit.jupiter.api.Test;

public class TreeTest {

    private Node<Integer> buildTree() {
        return buildNode(1, 5, 1);
    }

    private Node<Integer> buildNode(int value, int depth, float probability) {
        if (depth <= 0 || Math.random() > probability) {
            return null;
        }
        Node<Integer> node = new Node<>(value);
        node.left = buildNode(value << 1, depth - 1, 0.7f);
        node.right = buildNode((value << 1) + 1, depth - 1, 0.7f);
        return node;
    }

    @Test
    public void visitTest() {
        Node<Integer> root = buildTree();
        System.out.println(root);

        System.out.println("\nPRE-ORDER:");
        TreeVisitor v = new PreOrderRecursivelyVisitor();
        v.visit(root);
        System.out.println();
        v = new PreOrderVisitor();
        v.visit(root);

        System.out.println("\nIN-ORDER:");
        v = new InOrderRecursivelyVisitor();
        v.visit(root);
        System.out.println();
        v = new InOrderVisitor();
        v.visit(root);

        System.out.println("\nPOST-ORDER:");
        v = new PostOrderRecursivelyVisitor();
        v.visit(root);
        System.out.println();
        v = new PostOrderVisitor();
        v.visit(root);

        System.out.println("\nLEVEL-ORDER:");
        v = new LevelOrderVisitor();
        v.visit(root);
    }

}
