package me.lwan.algorithm.course;

public class FullBinaryTreeJudgeTest extends BaseTest {

    private final FullBinaryTreeJudge fullBinaryTreeJudge = new FullBinaryTreeJudge();

    @Override
    public void runTest() {
        TreeNode head = generateRandomBST(5, 100);
        if (isFull1(head) != fullBinaryTreeJudge.isFull(head)) {
            throw new IllegalStateException();
        }
    }

    public static boolean isFull1(TreeNode head) {
        if (head == null) {
            return true;
        }
        int height = h(head);
        int TreeNodes = n(head);
        return (1 << height) - 1 == TreeNodes;
    }

    public static int h(TreeNode head) {
        if (head == null) {
            return 0;
        }
        return Math.max(h(head.left), h(head.right)) + 1;
    }

    public static int n(TreeNode head) {
        if (head == null) {
            return 0;
        }
        return n(head.left) + n(head.right) + 1;
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
