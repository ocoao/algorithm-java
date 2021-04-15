package me.lwan.algorithm.lab;

/**
 * 判断是否为平衡二叉树
 */
public class BalanceBinaryTreeJudge {

    public boolean isBalance(TreeNode root) {
        if (root == null) {
            return true;
        }
        return dp(root).balance;
    }

    private static class Metadata {
        int height;
        boolean balance;
        Metadata(int height, boolean balance) {
            this.height = height;
            this.balance = balance;
        }
    }

    private Metadata dp(TreeNode node) {
        if (node == null) {
            return new Metadata(0, true);
        }
        Metadata left = dp(node.left);
        Metadata right = dp(node.right);

        int height = Math.max(left.height, right.height) + 1;
        boolean balance = left.balance && right.balance && Math.abs(left.height - right.height) <= 1;
        return new Metadata(height, balance);
    }

}
