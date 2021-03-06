package me.lwan.algorithm.lab;

import java.util.ArrayList;

public class MaxSubBinarySearchTreeTest extends BaseTest {

    private final MaxSubBinarySearchTree maxSubBinarySearchTree = new MaxSubBinarySearchTree();

    @Override
    public void runTest() {
        TreeNode head = generateRandomBST(5, 100);
        if (maxSubBSTSize1(head) != maxSubBinarySearchTree.getMaxSubBSTSize(head)) {
            throw new IllegalStateException("getMaxSubBSTSize Error");
        }

        if (maxSubBSTHead1(head) != maxSubBinarySearchTree.getMaxSubBST(head)) {
//            TreeNode n1 = maxSubBSTHead1(head);
//            TreeNode n2 = maxSubBinarySearchTree.getMaxSubBST(head);
//            throw new IllegalStateException("getMaxSubBST Error: " + n1.val + ", " + n2.val);
            throw new IllegalStateException("getMaxSubBST Error");
        }

    }

    public static TreeNode maxSubBSTHead1(TreeNode head) {
        if (head == null) {
            return null;
        }
        if (getBSTSize(head) != 0) {
            return head;
        }
        TreeNode leftAns = maxSubBSTHead1(head.left);
        TreeNode rightAns = maxSubBSTHead1(head.right);
        return getBSTSize(leftAns) >= getBSTSize(rightAns) ? leftAns : rightAns;
    }

    public static int getBSTSize(TreeNode head) {
        if (head == null) {
            return 0;
        }
        ArrayList<TreeNode> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).val <= arr.get(i - 1).val) {
                return 0;
            }
        }
        return arr.size();
    }

    public static void in(TreeNode head, ArrayList<TreeNode> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
    }

    public static int maxSubBSTSize1(TreeNode head) {
        if (head == null) {
            return 0;
        }
        int h = getBSTSize(head);
        if (h != 0) {
            return h;
        }
        return Math.max(maxSubBSTSize1(head.left), maxSubBSTSize1(head.right));
    }

    // for test
    public static TreeNode generateRandomBST(int maxLevel, int maxValue) {
        return generate(1, maxLevel, maxValue);
    }

    // for test
    public static TreeNode generate(int level, int maxLevel, int maxValue) {
        if (level > maxLevel || Math.random() < 0.5) {
            return null;
        }
        TreeNode head = new TreeNode((int) (Math.random() * maxValue));
        head.left = generate(level + 1, maxLevel, maxValue);
        head.right = generate(level + 1, maxLevel, maxValue);
        return head;
    }

}
