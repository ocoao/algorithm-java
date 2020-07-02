package me.lwan.algorithm.tree.question;

import me.lwan.algorithm.tree.ExtendNode;

public class InOrderSuccessorNodeSolution {

    public <V> ExtendNode<V> getSuccessor(ExtendNode<V> node) {
        ExtendNode<V> ans;
        if (node.getRight() != null) {
            ans = node.getRight();
            while (ans.getLeft() != null) {
                ans = ans.getLeft();
            }
            return ans;
        }

        ans = node.getParent();
        while (ans != null && ans.getLeft() != node) {
            node = ans;
            ans = ans.getParent();
        }

        return ans;
    }

//    public <V> Node<V> getSuccessor(Node<V> root, Node<V> node) {
//        Node<V> ans;
//        if (node.right != null) {
//            ans = node.right;
//            while (ans.left != null) {
//                ans = ans.left;
//            }
//            return ans;
//        }
//
//
//
//        return null;
//    }

}
