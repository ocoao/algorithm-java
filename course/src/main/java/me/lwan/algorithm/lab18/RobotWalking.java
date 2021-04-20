package me.lwan.algorithm.lab18;

/**
 * 假设有排成一行的N个位置，记为1~N(N>=2)
 * 机器人初始位置为M(1<=M<=N)，求走K步后，能来到P位置的方法有多少种
 */
public class RobotWalking {

    public int getWays(int N, int M, int P, int K) {
        if (K == 0) {
            return M == P ? 1 : 0;
        }
        if (M == 1) {
            return getWays(N, M + 1, P, K - 1);
        }
        if (M == N) {
            return getWays(N, M - 1, P, K - 1);
        }
        return getWays(N, M + 1, P, K - 1) + getWays(N, M - 1, P, K - 1);
    }

    // 动态规划
    public int getWaysDp(int N, int M, int P, int K) {
        int[][] dp = new int[N][K + 1]; // [1~N][0~K]
        dp[P - 1][0] = 1;
        for (int j = 1; j <= K; j++) {
            dp[0][j] = dp[1][j - 1];
            for (int i = 1; i < N - 1; i++) {
                dp[i][j] = dp[i + 1][j - 1] + dp[i - 1][j - 1];
            }
            dp[N - 1][j] = dp[N - 2][j - 1];
        }
        return dp[M - 1][K];
    }

}
