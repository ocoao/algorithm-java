package me.lwan.algorithm.course;

/**
 * 寻找最大二叉搜索子树,返回其结点数量
 */
public class MaxSubBinarySearchTree {

    public int getMaxSubBSTSize(TreeNode root) {
        return dp(root).maxSubBSTSize;
    }


    private static class Metadata {
        // 该结点中的子树最大二叉搜索树的结点数量
        int maxSubBSTSize;
        // 该子树的结点数量(若与maxSubBSTSize相等，则说明这整棵树为二叉搜索树)
        int size;
        // 该子树中最大的值
        int maxVal;
        // 该子树中最小的值
        int minVal;

        Metadata(int maxSubBSTSize, int size, int maxVal, int minVal) {
            this.maxSubBSTSize = maxSubBSTSize;
            this.size = size;
            this.maxVal = maxVal;
            this.minVal = minVal;
        }

        boolean isBST() {
            return maxSubBSTSize == size;
        }

    }

    private Metadata dp(TreeNode node) {
        if (node == null) {
            return new Metadata(0, 0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        Metadata left = dp(node.left);
        Metadata right = dp(node.right);

        int size = left.size + right.size + 1;
        int maxVal = Math.max(node.val, right.maxVal);
        int minVal = Math.min(node.val, left.minVal);

        boolean isBST = left.isBST() && right.isBST() && left.maxVal < node.val && right.minVal > node.val;
        int maxSubBSTSize = isBST ? size : Math.max(left.maxSubBSTSize, right.maxSubBSTSize);

        return new Metadata(maxSubBSTSize, size, maxVal, minVal);
    }


}
