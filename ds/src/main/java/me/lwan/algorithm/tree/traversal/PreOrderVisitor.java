package me.lwan.algorithm.tree.traversal;

import me.lwan.algorithm.tree.Node;
import me.lwan.algorithm.tree.TreeVisitor;

import java.util.Stack;
import java.util.function.Consumer;

/**
 * use stack to visit the tree nodes
 * 1. push root to the stack
 * 2. pop the top element, visit it
 * 3. push the right/left child of the top element
 * 4. repeat step 2~3 until the stack is empty
 */
public class PreOrderVisitor implements TreeVisitor {
    @Override
    public <V> void visit(Node<V> root, Consumer<Node<V>> output) {
        if (root == null) {
            return;
        }
        Stack<Node<V>> help = new Stack<>();
        help.push(root);
        while (!help.isEmpty()) {
            Node<V> node = help.pop();
            output.accept(node);
            if (node.right != null) {
                help.push(node.right);
            }
            if (node.left != null) {
                help.push(node.left);
            }
        }
    }
}
