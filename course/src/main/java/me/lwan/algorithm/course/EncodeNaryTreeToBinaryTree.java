package me.lwan.algorithm.course;

import java.util.ArrayList;
import java.util.List;

/**
 * @see <a href="https://leetcode-cn.com/problems/encode-n-ary-tree-to-binary-tree">LeetCode 431</a>
 */
public class EncodeNaryTreeToBinaryTree {

    public TreeNode encode(Node root) {
        if (root == null) {
            return null;
        }
        TreeNode treeRoot = new TreeNode(root.val);
        treeRoot.left = encodeChildren(root.children);
        return treeRoot;
    }

    private TreeNode encodeChildren(List<Node> children) {
        if (children == null || children.isEmpty()) {
            return null;
        }
        TreeNode root = null;
        TreeNode pre = null;
        for (Node child : children) {
            TreeNode current = new TreeNode(child.val);
            current.left = encodeChildren(child.children);
            if (root == null) {
                root = current;
            } else {
                pre.right = current;
            }
            pre = current;
        }
        return root;
    }

    public Node decode(TreeNode root) {
        if (root == null) {
            return null;
        }
        return new Node(root.val, decodeChildren(root.left));
    }

    public List<Node> decodeChildren(TreeNode node) {
        if (node == null) {
            return null;
        }
        List<Node> children = new ArrayList<>();
        while (node != null) {
            children.add(new Node(node.val, decodeChildren(node.left)));
            node = node.right;
        }
        return children;
    }

}

class Node {
    public int val;
    public List<Node> children;
    public Node(int val, List<Node> children) {
        this.val = val;
        this.children = children;
    }
}

class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int val) {
        this.val = val;
    }
}