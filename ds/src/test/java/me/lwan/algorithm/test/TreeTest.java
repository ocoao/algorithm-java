package me.lwan.algorithm.test;

import me.lwan.algorithm.tree.Node;
import me.lwan.algorithm.tree.TreeVisitor;
import me.lwan.algorithm.tree.traversal.*;
import org.junit.jupiter.api.Test;
import sun.reflect.generics.tree.Tree;

public class TreeTest {

    private Node<Integer> buildTree() {
        return buildNode(1, 5, 1);
    }

    private Node<Integer> buildNode(int value, int depth, float probability) {
        if (depth <= 0 || Math.random() > probability) {
            return null;
        }
        Node<Integer> node = new Node<>(value);
        node.left = buildNode(value << 1, depth - 1, 0.7f);
        node.right = buildNode((value << 1) + 1, depth - 1, 0.7f);
        return node;
    }

    @Test
    public void visitTest() {
//        Node<Integer> root = buildTree();
        Node<Integer> root = new Node<>(1);
        root.left = new Node<>(2);
        root.right = new Node<>(3);
        root.left.left = new Node<>(4);
        root.left.right = new Node<>(5);
        root.right.right = new Node<>(6);
        System.out.println(root);

        System.out.println("\nPRE-ORDER:");
        TreeVisitor v = new PreOrderRecursivelyVisitor();
        v.visit(root);
        System.out.println();
        v = new PreOrderVisitor();
        v.visit(root);

        System.out.println("\nIN-ORDER:");
        v = new InOrderRecursivelyVisitor();
        v.visit(root);
        System.out.println();
        v = new InOrderVisitor();
        v.visit(root);

        System.out.println("\nPOST-ORDER:");
        v = new PostOrderRecursivelyVisitor();
        v.visit(root);
        System.out.println();
        v = new PostOrderVisitor();
        v.visit(root);

        System.out.println("\nLEVEL-ORDER:");
        v = new LevelOrderVisitor();
        v.visit(root);
    }

     public class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode(int x) { val = x; }
     }
    static class Solution {
        public int diameterOfBinaryTree(TreeNode root) {
            int[] dp = new int[2];
            diameterOfBinaryTree(root, dp);
            return dp[1] - 1;
        }

        private void diameterOfBinaryTree(TreeNode node, int[] dp) {
            if (node == null) {
                dp[0] = dp[1] = 0;
                return;
            }
            diameterOfBinaryTree(node.left, dp);
            int leftHeight = dp[0];
            int leftDiameter = dp[1];
            diameterOfBinaryTree(node.right, dp);
            int rightHeight = dp[0];
            int rightDiameter = dp[1];

            dp[0] = Math.max(leftHeight, rightHeight) + 1;
            dp[1] = Math.max(Math.max(leftDiameter, rightDiameter), leftHeight + rightHeight + 1);
        }

    }

    @Test
    public void testDp() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        int n = new Solution().diameterOfBinaryTree(root);
        System.out.println(n);
    }

}
