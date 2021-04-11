package me.lwan.algorithm.course;

/**
 * 查找二叉树中两个结点的最低公共祖先
 * @see <a href="https://leetcode-cn.com/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/">二叉树最近公共祖先</a>
 */
public class C13_LowestCommonAncestor {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return dp(root, p, q).ans;
    }

    private static class Metadata {
        boolean hasP;
        boolean hasQ;
        // 最低公共祖先结点
        TreeNode ans;
        Metadata(boolean hasP, boolean hasQ, TreeNode ans) {
            this.hasP = hasP;
            this.hasQ = hasQ;
            this.ans = ans;
        }
    }

    private Metadata dp(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null) {
            return new Metadata(false, false, null);
        }
        Metadata left = dp(node.left, p, q);
        if (left.ans != null) {
            return left;//new Metadata(true, true, left.ans);
        }
        Metadata right = dp(node.right, p, q);
        if (right.ans != null) {
            return right;//new Metadata(true, true, right.ans);
        }

        boolean hasP = left.hasP || right.hasP || node == p;
        boolean hasQ = left.hasQ || right.hasQ || node == q;
        TreeNode ans = hasP && hasQ ? node : null;
        return new Metadata(hasP, hasQ, ans);
    }

}
