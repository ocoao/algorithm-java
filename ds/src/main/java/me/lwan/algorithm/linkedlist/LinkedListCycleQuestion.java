package me.lwan.algorithm.linkedlist;

public class LinkedListCycleQuestion {

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
        // 1. Use the fast and slow pointers to find a meeting node
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
        // 2. Use a pointer to the head node,
        //    move forward with the slow pointer, and the first node that meets is the answer
        Node<E> ans = head;
        while (ans != slow) {
            ans = ans.next;
            slow = slow.next;
        }
        return ans;
    }

    /**
     * find the intersection node of head1 and head2
     * <pre>
     * case 1. no cycle linked list
     * A1 -> A2 -> A3 -> A4 -> A5
     *             ^
     * B1 -> B2 ---^
     * case 2. cycle linked list(intersect outside the cycle)
     *                     _________________
     *                    V                |
     * A1 -> A2 -> A3 -> A4 -> A5 -> A6 -> A7
     *             ^
     * B1 -> B2 ---^
     * case 3. cycle linked list(intersect at the cycle entrance)
     *               _______________________
     *              V                      |
     * A1 -> A2 -> A3 -> A4 -> A5 -> A6 -> A7
     *             ^
     * B1 -> B2 ---^
     * case 4. cycle linked list(intersect inside the cycle, A5 or A3)
     *               _______________________
     *              V                      |
     * A1 -> A2 -> A3 -> A4 -> A5 -> A6 -> A7
     *                         ^
     * B1 -> B2 -> B3 -> B4 ---^
     * </pre>
     * @param head1
     * @param head2
     * @param <E>
     * @return
     */
    public <E> Node<E> findTheIntersectionNode(Node<E> head1, Node<E> head2) {
        if (head1 == null || head2 == null) {
            return null;
        }
        Node<E> entry1 = findTheFirstNodeInCycle(head1);
        Node<E> entry2 = findTheFirstNodeInCycle(head2);
        if (entry1 == entry2) {
            // case 1
            return findTheIntersectionNodeNoCycle(head1, head2);
        }
        if (entry1 != null && entry2 != null) {
            // case 2/3/4
            return findTheIntersectionNodeCycle(head1, entry1, head2, entry2);
        }
        return null;
    }

    private <E> Node<E> findTheIntersectionNodeNoCycle(Node<E> head1, Node<E> head2) {
        return findTheIntersectionNode0(head1, head2, null);
    }

    private <E> Node<E> findTheIntersectionNode0(Node<E> head1, Node<E> head2, Node<E> end) {
        int n = 0;
        Node<E> node1 = head1;
        while (node1.next != end) {
            n++;
            node1 = node1.next;
        }
        Node<E> node2 = head2;
        while (node2.next != end) {
            n--;
            node2 = node2.next;
        }
        // the last node is different or case 3
        if (node1 != node2) {
            return end;
        }
        Node<E> longer = n > 0 ? head1 : head2;
        Node<E> shorter = n <= 0 ? head1 : head2;
        n = Math.abs(n);
        while (n-- > 0) {
            longer = longer.next;
        }
        while (longer != shorter) {
            longer = longer.next;
            shorter = shorter.next;
        }
        return longer;
    }

    private <E> Node<E> findTheIntersectionNodeCycle(Node<E> head1, Node<E> entry1, Node<E> head2, Node<E> entry2) {
        if (entry1 == entry2) {
            // case 2/3
            return findTheIntersectionNode0(head1, head2, entry1);
        }
        // case 4
        Node<E> node1 = entry1.next;
        while (entry1 != node1) {
            if (node1 == entry2) {
                return entry1;
            }
            node1 = node1.next;
        }
        return null;
    }

}
