package me.lwan.algorithm.lab18;

/**
 * <pre>
 * 给定一个数组arr,表示一系列纸牌的数值
 * 玩家A和B依次拿每张纸牌，每个玩家每次只能拿最左或最右的纸牌
 * 玩家A和B都绝顶聪明，求最后获胜者的分数
 * </pre>
 */
public class CardScore {

    public int getWinnerScore(int[] arr) {
        int s1 = firsthand(arr, 0, arr.length - 1);
        int s2 = lasthand(arr, 0, arr.length - 1);
        return Math.max(s1, s2);
    }

    // 先手拿牌
    private int firsthand(int[] arr, int i, int j) {
        if (i == j) {
            return arr[i];
        }

        int s1 = arr[i] + lasthand(arr, i + 1, j);
        int s2 = arr[j] + lasthand(arr, i, j - 1);
        return Math.max(s1, s2);
    }

    // 后手拿牌
    private int lasthand(int[] arr, int i, int j) {
        if (i == j) {
            return 0;
        }
        int s1 = firsthand(arr, i, j - 1);
        int s2 = firsthand(arr, i + 1, j);
        return Math.min(s1, s2);
    }

    // 动态规划
    public int getWinnerScoreDp(int[] arr) {
        int n = arr.length;
        int[][] fdp = new int[n][n];
        int[][] ldp = new int[n][n];

        for (int i = 0; i < n; i++) {
            fdp[i][i] = arr[i];
//            ldp[i][i] = 0;
        }

        for (int col = 1; col < n; col++) {
            int i = 0, j = col;
            while (j < n) {
                fdp[i][j] = Math.max(arr[i] + ldp[i + 1][j], arr[j] + ldp[i][j - 1]);
                ldp[i][j] = Math.min(fdp[i + 1][j], fdp[i][j - 1]);
                i++;
                j++;
            }
        }

        return Math.max(fdp[0][n - 1], ldp[0][n - 1]);
    }

}
