package me.lwan.algorithm.lab16;

import java.util.*;

/**
 * 拓扑排序
 */
public class GraphTopologicalSort {

    public <V> List<Node<V>> sort(Graph<V> graph) {
        // 结点入度
        Map<Node<V>, Integer> indegreeMap = new HashMap<>();
        // 0入度的结点队列
        Queue<Node<V>> zeroIndegreeQueue = new LinkedList<>();

        for (Node<V> node : graph.nodes.values()) {
            indegreeMap.put(node, node.in);
            if (node.in == 0) {
                zeroIndegreeQueue.offer(node);
            }
        }

        List<Node<V>> list = new ArrayList<>();
        while (!zeroIndegreeQueue.isEmpty()) {
            Node<V> node = zeroIndegreeQueue.poll();
            list.add(node);

            for (Node<V> next : node.nexts) {
                int in = indegreeMap.get(next) - 1;
                indegreeMap.put(next, in);
                if (in == 0) {
                    zeroIndegreeQueue.offer(next);
                }
            }
        }

        return list;
    }

}
