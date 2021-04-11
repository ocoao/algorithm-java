package me.lwan.algorithm.course;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断是否为完全二叉树
 */
public class CompleteBinaryTreeJudge {


    public boolean isCBT(TreeNode root) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        boolean checkLeaf = false;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (checkLeaf && (node.left != null || node.right != null)) {
                return false;
            }
            if (node.left == null && node.right != null) {
                // 左孩子为空但右孩子不为空，一定不是完全二叉树
                return false;
            }
            if (!checkLeaf && (node.left == null || node.right == null)) {
                // 若该结点左右孩子不全，则以后遍历的结点都应为叶子结点
                checkLeaf = true;
            }
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return true;
    }

    public boolean isCBT2(TreeNode root) {
        return dp(root).cbt;
    }

    private static class Metadata {
        // 是否为满二叉树
        boolean full;
        // 是否为完全二叉树
        boolean cbt;
        // 高度
        int height;
        Metadata(boolean full, boolean cbt, int height) {
            this.full = full;
            this.cbt = cbt;
            this.height = height;
        }
    }

    private Metadata dp(TreeNode node) {
        if (node == null) {
            return new Metadata(true, true, 0);
        }

        Metadata left = dp(node.left);
        Metadata right = dp(node.right);

        int height = Math.max(left.height, right.height) + 1;
        boolean full = left.full && right.full && left.height == right.height;
        // 4种情况:
        // 1. 左右子树都为满二叉树，且高度一样
        // 2. 左右子树都为满二叉树，且左高=右高+1
        // 3. 左子树为满二叉树，右子树为完全二叉树，且高度一样
        // 4. 左子树为完全二叉树，右子树为满二叉树，且左高=右高+1
        boolean cbt = full;
        if (cbt) {
            return new Metadata(true, true, height);
        }
        if (left.full && right.full && left.height == right.height + 1) {
            cbt = true;
        } else if (left.full && right.cbt && left.height == right.height) {
            cbt = true;
        } else if (left.cbt && right.full && left.height == right.height + 1) {
            cbt = true;
        }

        return new Metadata(full, cbt, height);
    }

}
