package me.lwan.algorithm.lab14;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 并查集
 */
public class C14_UnionFindSet {

    private static class Node<V> {
        V value;
        Node(V value) {
            this.value = value;
        }
    }

    public interface UnionFind<V> {
        boolean isSameSet(V a, V b);
        void union(V a, V b);
        int size();
    }

    public static class UnionFind1<V> implements UnionFind<V> {

        private final Map<V, Node<V>> nodeMapping = new HashMap<>();
        // key: 子集合结点, value: 父集合结点
        private final Map<Node<V>, Node<V>> parentMapping = new HashMap<>();
        // key: 集合结点, value: 结点数量  有子结点的才加入到该map
        private final Map<Node<V>, Integer> sizeMapping = new HashMap<>();

        public UnionFind1(List<V> values) {
            for (V v : values) {
                Node<V> node = new Node<>(v);
                nodeMapping.put(v, node);
                parentMapping.put(node, node);
                sizeMapping.put(node, 1);
            }
        }

        private Node<V> findAncestor(V v) {
            Node<V> node = nodeMapping.get(v);
            if (node == null) {
                return null;
            }
            Node<V> tmp;
            List<Node<V>> list = new LinkedList<>();
            while (node != (tmp = parentMapping.get(node))) {
                list.add(node);
                node = tmp;
            }
            // 优化之后的查找性能
            for (Node<V> n : list) {
                parentMapping.put(n, node);
            }
            return node;
        }

        @Override
        public boolean isSameSet(V a, V b) {
            return findAncestor(a) == findAncestor(b);
        }

        @Override
        public void union(V a, V b) {
            Node<V> ancestorA = findAncestor(a);
            Node<V> ancestorB = findAncestor(b);
            if (ancestorA == ancestorB) {
                return;
            }
            int sizeA = sizeMapping.get(ancestorA);
            int sizeB = sizeMapping.get(ancestorB);

            Node<V> ancestorBig = sizeA >= sizeB ? ancestorA : ancestorB;
            Node<V> ancestorSmall = ancestorBig == ancestorA ? ancestorB : ancestorA;

            parentMapping.put(ancestorSmall, ancestorBig);
            sizeMapping.put(ancestorBig, sizeA + sizeB);
            sizeMapping.remove(ancestorSmall);
        }

        @Override
        public int size() {
            return sizeMapping.size();
        }
    }

    public static class UnionFind2<V> implements UnionFind<V> {

        private static class Node2<V> extends Node<V> {
            Node2<V> parent;
            int size;
            Node2(V value) {
                super(value);
                this.parent = this;
                this.size = 1;
            }
        }

        private final Map<V, Node2<V>> nodeMapping = new HashMap<>();
        private int size;

        public UnionFind2(List<V> values) {
            for (V v : values) {
                Node2<V> node = new Node2<>(v);
                nodeMapping.put(v, node);
            }
            this.size = values.size();
        }

        private Node2<V> findAncestor(V v) {
            Node2<V> node = nodeMapping.get(v);
            if (node == null) {
                return null;
            }
            while (node != node.parent) {
                node = node.parent;
            }
            return node;
        }

        @Override
        public boolean isSameSet(V a, V b) {
            return findAncestor(a) == findAncestor(b);
        }

        @Override
        public void union(V a, V b) {
            Node2<V> ancestorA = findAncestor(a);
            Node2<V> ancestorB = findAncestor(b);
            if (ancestorA == ancestorB) {
                return;
            }

            Node2<V> ancestorBig = ancestorA.size >= ancestorB.size ? ancestorA : ancestorB;
            Node2<V> ancestorSmall = ancestorBig == ancestorA ? ancestorB : ancestorA;

            ancestorSmall.parent = ancestorBig;
            ancestorBig.size += ancestorSmall.size;
            ancestorSmall.size = 0;
            size--;
        }

        @Override
        public int size() {
            return size;
        }
    }

}
