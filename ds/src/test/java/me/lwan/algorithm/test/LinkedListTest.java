package me.lwan.algorithm.test;

import me.lwan.algorithm.linkedlist.LinkedListCycle;
import me.lwan.algorithm.linkedlist.Node;
import org.junit.jupiter.api.Test;

public class LinkedListTest {

    @Test
    public void testCycle() {
        LinkedListCycle c = new LinkedListCycle();

        Node<Integer> head = new Node<>(1);
        System.out.println(c.findTheFirstNodeInCycle(head)); // null

        Node<Integer> node2 = new Node<>(2);
        head.next = node2;
        node2.next = head;
        System.out.println(c.findTheFirstNodeInCycle(head)); // 1

        head = new Node<>(1);
        node2 = new Node<>(2);
        Node<Integer> node3 = new Node<>(3);
        Node<Integer> node4 = new Node<>(4);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        System.out.println(c.findTheFirstNodeInCycle(head)); // null

        node4.next = node2;
        System.out.println(c.findTheFirstNodeInCycle(head)); // 2

        node4.next = head;
        System.out.println(c.findTheFirstNodeInCycle(head)); // 1


    }

}
