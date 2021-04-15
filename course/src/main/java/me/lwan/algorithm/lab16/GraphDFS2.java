package me.lwan.algorithm.lab16;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.function.Consumer;

/**
 * 非递归深度优先遍历图
 * @param <V>
 */
public class GraphDFS2<V> implements GraphVisitor<V> {
    @Override
    public void visit(Node<V> node, Consumer<V> visitor) {
        Stack<Node<V>> stack = new Stack<>();
        Set<Node<V>> visited = new HashSet<>();

        stack.add(node);
        visited.add(node);

        while (!stack.isEmpty()) {
            Node<V> n = stack.pop();
            visitor.accept(n.value);

            for (int i = n.nexts.size() - 1; i >= 0; i--) {
                Node<V> next = n.nexts.get(i);
                if (!visited.add(next)) {
                    stack.push(next);
                }
            }
        }

    }
}
