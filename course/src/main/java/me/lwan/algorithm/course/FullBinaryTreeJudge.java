package me.lwan.algorithm.course;

public class FullBinaryTreeJudge {

    public boolean isFull(TreeNode root) {
        if (root == null) {
            return true;
        }
        Metadata metadata = dp(root);
        return (1 << metadata.height) - 1 == metadata.size;
    }

    private static class Metadata {
        int height; // 树的高度
        int size; // 结点数量
        Metadata(int height, int size) {
            this.height = height;
            this.size = size;
        }
    }

    private Metadata dp(TreeNode node) {
        if (node == null) {
            return new Metadata(0, 0);
        }
        Metadata left = dp(node.left);
        Metadata right = dp(node.right);

        return new Metadata(Math.max(left.height, right.height) + 1, left.size + right.size + 1);
    }

}
