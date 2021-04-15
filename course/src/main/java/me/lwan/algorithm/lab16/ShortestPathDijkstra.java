package me.lwan.algorithm.lab16;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 计算某个顶点到其他顶点的最短路径,使用Dijkstra算法
 */
public class ShortestPathDijkstra {

    public <V> Map<Node<V>, Integer> shortestPath(Node<V> start) {
        Map<Node<V>, Integer> distanceMapping = new HashMap<>();
        distanceMapping.put(start, 0);

        Set<Node<V>> visited = new HashSet<>();

        Node<V> min = start;
        while (min != null) {
            int distance = distanceMapping.get(min);
            for (Edge<V> e : min.edges) {
                Node<V> dest = e.to;
                Integer d = distanceMapping.get(dest);
                if (d == null || d > distance + e.weight) {
                    distanceMapping.put(dest, distance + e.weight);
                }
            }
            visited.add(min);
            min = getMinPathNode(distanceMapping, visited);
        }

        return distanceMapping;
    }

    private <V> Node<V> getMinPathNode(Map<Node<V>, Integer> distanceMapping, Set<Node<V>> visited) {
        Map.Entry<Node<V>, Integer> min = null;

        for (Map.Entry<Node<V>, Integer> e : distanceMapping.entrySet()) {
            if (visited.contains(e.getKey())) {
                continue;
            }

            if (min == null || e.getValue() < min.getValue()) {
                min = e;
            }
        }

        return min != null ? min.getKey() : null;
    }

//    private static class Path<V> implements Comparable<Path<V>> {
//        Node<V> dest;
//        int distance;
//        Path(Node<V> dest, int distance) {
//            this.dest = dest;
//            this.distance = distance;
//        }
//
//        @Override
//        public int compareTo(Path<V> o) {
//            return distance - o.distance;
//        }
//    }

}
