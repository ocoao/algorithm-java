package me.lwan.algorithm.lab16;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

/**
 * 图的深度优先遍历
 */
public class GraphDFS<V> implements GraphVisitor<V> {

    @Override
    public void visit(Node<V> node, Consumer<V> visitor) {
        if (node == null) {
            return;
        }
        Set<Node<V>> visited = new HashSet<>();
        dfs(node, visited, visitor);
    }

    private void dfs(Node<V> node, Set<Node<V>> visited, Consumer<V> visitor) {
        if (!visited.add(node)) {
            return;
        }
        visitor.accept(node.value);
        for (Node<V> next : node.nexts) {
            dfs(next, visited, visitor);
        }
    }

}
