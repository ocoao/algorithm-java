package me.lwan.algorithm.lab16;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.function.Consumer;

/**
 * 图的广度优先遍历
 */
public class GraphBFS<V> implements GraphVisitor<V> {

    @Override
    public void visit(Node<V> node, Consumer<V> visitor) {
        if (node == null) {
            return;
        }
        Queue<Node<V>> queue = new LinkedList<>();
        Set<Node<V>> visited = new HashSet<>();

        queue.offer(node);
        visited.add(node);

        while (!queue.isEmpty()) {
            Node<V> n = queue.poll();
            visitor.accept(n.value);
            for (Node<V> next : n.nexts) {
                if (visited.add(next)) {
                    queue.offer(next);
                }
            }
        }
    }
}
