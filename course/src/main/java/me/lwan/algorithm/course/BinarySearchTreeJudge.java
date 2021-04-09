package me.lwan.algorithm.course;

/**
 * 判断是否为二叉搜索树
 */
public class BinarySearchTreeJudge {

    public boolean isBST(TreeNode root) {
        return root == null || dp(root).bst;
    }

    private static class Metadata {
        int minVal;
        int maxVal;
        boolean bst;
        Metadata(int minVal, int maxVal, boolean bst) {
            this.minVal = minVal;
            this.maxVal = maxVal;
            this.bst = bst;
        }
    }

    private Metadata dp(TreeNode node) {
        if (node == null) {
            return null;
        }
        Metadata left = dp(node.left);
        Metadata right = dp(node.right);

        boolean bst = (left == null || left.bst) && (right == null || right.bst);
        if (left != null && left.maxVal >= node.val) {
            bst = false;
        } else if (right != null && right.minVal <= node.val) {
            bst = false;
        }
        int minVal = left != null ? left.minVal : node.val;
        int maxVal = right != null ? right.maxVal : node.val;
        return new Metadata(minVal, maxVal, bst);
    }

}
