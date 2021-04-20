package me.lwan.algorithm.lab18;

import me.lwan.algorithm.lab18.RobotWalking;
import org.junit.jupiter.api.Test;

public class RobotWalkingTest {

    private final RobotWalking robotWalking = new RobotWalking();

    @Test
    public void test() {
        int N = 6;
        int M = 2;
        int P = 5;
        int K = 11;

        int ways1 = robotWalking.getWays(N, M, P, K);
        int ways2 = robotWalking.getWaysDp(N, M, P, K);

        if (ways1 != ways2) {
            throw new IllegalStateException();
        }

        System.out.printf("N=%d, M=%d, P=%d, K=%d, ways=%d\n", N, M, P, K, ways1);
//        System.out.println(ways1(N, M, P, K));
    }

//    public static int ways1(int N, int start, int aim, int K) {
//        if (N < 2 || start < 1 || start > N || aim < 1 || aim > N || K < 1) {
//            return -1;
//        }
//        return process1(start, K, aim, N);
//    }
//
//    // 机器人当前来到的位置是cur，
//    // 机器人还有rest步需要去走，
//    // 最终的目标是aim，
//    // 有哪些位置？1~N
//    // 返回：机器人从cur出发，走过rest步之后，最终停在aim的方法数，是多少？
//    public static int process1(int cur, int rest, int aim, int N) {
//        if (rest == 0) { // 如果已经不需要走了，走完了！
//            return cur == aim ? 1 : 0;
//        }
//        // (cur, rest)
//        if (cur == 1) { // 1 -> 2
//            return process1(2, rest - 1, aim, N);
//        }
//        // (cur, rest)
//        if (cur == N) { // N-1 <- N
//            return process1(N - 1, rest - 1, aim, N);
//        }
//        // (cur, rest)
//        return process1(cur - 1, rest - 1, aim, N) + process1(cur + 1, rest - 1, aim, N);
//    }

}
