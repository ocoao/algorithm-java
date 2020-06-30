package me.lwan.algorithm.linkedlist;

public class LinkedListCycle {

    /**
     * find the first node in the cycle, if there is no cycle, return null
     * @param head
     * @param <E>
     * @return
     */
    public <E> Node<E> findTheFirstNodeInCycle(Node<E> head) {
        if (head == null || head.next == null) {
            return null;
        }
        Node<E> slow = head.next;
        Node<E> fast = head.next.next;
        while (slow != null && fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }
        if (slow == head) {
            // only 2 nodes
            return head;
        }
        if (fast == null || fast.next == null) {
            return null;
        }
        Node<E> ans = head;
        while (ans != slow) {
            ans = ans.next;
            slow = slow.next;
        }
        return ans;
    }

}
