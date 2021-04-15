package me.lwan.algorithm.lab16;

import java.util.ArrayList;
import java.util.List;

/**
 * node of the graph
 * @param <V>
 */
public class Node<V> {

    public V value;
    public int in;
    public int out;
    public List<Node<V>> nexts = new ArrayList<>();
    public List<Edge<V>> edges = new ArrayList<>();

}
