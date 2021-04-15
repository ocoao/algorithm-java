package me.lwan.algorithm.lab;

import java.util.ArrayList;

public class BinarySearchTreeJudgeTest extends BaseTest {

    private final BinarySearchTreeJudge binarySearchTreeJudge = new BinarySearchTreeJudge();

    @Override
    public void runTest() {
        TreeNode head = generateRandomBST(6, 100);
        if (isBST1(head) != binarySearchTreeJudge.isBST(head)) {
            throw new IllegalStateException();
        }
    }

    public static boolean isBST1(TreeNode head) {
        if (head == null) {
            return true;
        }
        ArrayList<TreeNode> arr = new ArrayList<>();
        in(head, arr);
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i).val <= arr.get(i - 1).val) {
                return false;
            }
        }
        return true;
    }

    public static void in(TreeNode head, ArrayList<TreeNode> arr) {
        if (head == null) {
            return;
        }
        in(head.left, arr);
        arr.add(head);
        in(head.right, arr);
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
