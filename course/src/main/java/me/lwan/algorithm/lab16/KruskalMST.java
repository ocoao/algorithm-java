package me.lwan.algorithm.lab16;

import java.util.*;

/**
 * Kruskal算法实现的最小生成树(Minimum spanning tree)
 */
public class KruskalMST {

    public <V> Set<Edge<V>> mst(Graph<V> graph) {
        Set<Edge<V>> edges = new HashSet<>();

        UnionFind<V> uf = new UnionFind<>(graph);
        Queue<Edge<V>> queue = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        for (Edge<V> edge : graph.edges) {
            queue.offer(edge);
        }

        while (!queue.isEmpty()) {
            Edge<V> e = queue.poll();
            if (!uf.union(e.from, e.to)) {
                continue;
            }
            edges.add(e);
            if (uf.size() == 1) {
                break;
            }
        }

        return edges;
    }

    private static class UnionFind<V> {

        private Map<Node<V>, Node<V>> parentMapping = new HashMap<>();
        private Map<Node<V>, Integer> sizeMapping = new HashMap<>();

        public UnionFind(Graph<V> graph) {
            for (Node<V> node : graph.nodes.values()) {
                parentMapping.put(node, node);
                sizeMapping.put(node, 1);
            }
        }

        public int size() {
            return sizeMapping.size();
        }

        public boolean union(Node<V> a, Node<V> b) {
            Node<V> ancestorA = findAncestor(a);
            Node<V> ancestorB = findAncestor(b);
            if (ancestorA == ancestorB) {
                return false;
            }

            int sizeA = sizeMapping.get(ancestorA);
            int sizeB = sizeMapping.get(ancestorB);

            // big
            a = sizeA >= sizeB ? ancestorA : ancestorB;
            // small
            b = a == ancestorA ? ancestorB : ancestorA;

            parentMapping.put(b, a);
            sizeMapping.put(a, sizeA + sizeB);
            sizeMapping.remove(b);
            return true;
        }

        private Node<V> findAncestor(Node<V> node) {
            List<Node<V>> list = new LinkedList<>();
            Node<V> tmp;
            while (node != (tmp = parentMapping.get(node))) {
                list.add(node);
                node = tmp;
            }

            // optimize
            for (Node<V> n : list) {
                parentMapping.put(n, node);
            }

            return node;
        }

    }

}
