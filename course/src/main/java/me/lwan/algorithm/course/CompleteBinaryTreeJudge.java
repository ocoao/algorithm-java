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

}
