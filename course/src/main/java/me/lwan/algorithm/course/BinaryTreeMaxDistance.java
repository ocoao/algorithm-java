package me.lwan.algorithm.course;

/**
 * 计算二叉树中的最大距离(两个结点之间的路径)
 */
public class BinaryTreeMaxDistance {

    public int getMaxDistance(TreeNode root) {
        return dp(root).maxDistance;
    }

    private static class Metadata {
        int maxDistance;
        int height;
        Metadata(int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }
    }

    private Metadata dp(TreeNode node) {
        if (node == null) {
            return new Metadata(0, 0);
        }
        Metadata left = dp(node.left);
        Metadata right = dp(node.right);

        int height = Math.max(left.height, right.height) + 1;
        int maxDistance = Math.max(Math.max(left.maxDistance, right.maxDistance), left.height + right.height + 1);

        return new Metadata(maxDistance, height);
    }

}
