package me.lwan.algorithm.lab16;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Graph<V> {

    public Map<V, Node<V>> nodes = new HashMap<>();
    public Set<Edge<V>> edges = new HashSet<>();

}
