package me.lwan.algorithm.lab16;

import java.util.*;

/**
 * Prim算法实现的最小生成树
 */
public class PrimMST {

    public <V> Set<Edge<V>> mst(Graph<V> graph) {
        Set<Edge<V>> edges = new HashSet<>();

        // 随机取一个顶点(若原图为森林,则需要遍历所有顶点)
        Node<V> node = graph.nodes.values().iterator().next();

        Set<Node<V>> visited = new HashSet<>();
        visited.add(node);
        Queue<Edge<V>> queue = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        while (node != null) {
            visited.add(node);
            // 将当前顶点的所有边加入小顶堆
            for (Edge<V> e : node.edges) {
                queue.offer(e);
            }
            node = null;

            while (!queue.isEmpty()) {
                Edge<V> e = queue.poll();
                // 取最小边,若该边的另一顶点未访问过则加入最小生成树,并解锁该顶点的所有边
                if (visited.contains(e.to)) {
                    continue;
                }
                edges.add(e);

                node = e.to;
                break;
            }
        }

        return edges;
    }

}
