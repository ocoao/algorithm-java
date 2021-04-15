package me.lwan.algorithm.lab;

import me.lwan.algorithm.lab13.C13_EmployeeMaxHappy;

public class C13_EmployeeMaxHappyTest extends BaseTest {

    private final C13_EmployeeMaxHappy employeeMaxHappy = new C13_EmployeeMaxHappy();

    @Override
    public void runTest() {
        C13_EmployeeMaxHappy.Employee boss = genarateBoss(4, 7, 100);
        if (maxHappy1(boss) != employeeMaxHappy.getMaxHappy(boss)) {
            throw new IllegalStateException();
        }
    }

    public static int maxHappy1(C13_EmployeeMaxHappy.Employee boss) {
        if (boss == null) {
            return 0;
        }
        return process1(boss, false);
    }

    // 当前来到的节点叫cur，
    // up表示cur的上级是否来，
    // 该函数含义：
    // 如果up为true，表示在cur上级已经确定来，的情况下，cur整棵树能够提供最大的快乐值是多少？
    // 如果up为false，表示在cur上级已经确定不来，的情况下，cur整棵树能够提供最大的快乐值是多少？
    public static int process1(C13_EmployeeMaxHappy.Employee cur, boolean up) {
        if (up) { // 如果cur的上级来的话，cur没得选，只能不来
            int ans = 0;
            for (C13_EmployeeMaxHappy.Employee next : cur.members) {
                ans += process1(next, false);
            }
            return ans;
        } else { // 如果cur的上级不来的话，cur可以选，可以来也可以不来
            int p1 = cur.happy;
            int p2 = 0;
            for (C13_EmployeeMaxHappy.Employee next : cur.members) {
                p1 += process1(next, true);
                p2 += process1(next, false);
            }
            return Math.max(p1, p2);
        }
    }

    // for test
    public static C13_EmployeeMaxHappy.Employee genarateBoss(int maxLevel, int maxNexts, int maxHappy) {
        if (Math.random() < 0.02) {
            return null;
        }
        C13_EmployeeMaxHappy.Employee boss = new C13_EmployeeMaxHappy.Employee((int) (Math.random() * (maxHappy + 1)));
        genarateNexts(boss, 1, maxLevel, maxNexts, maxHappy);
        return boss;
    }

    // for test
    public static void genarateNexts(C13_EmployeeMaxHappy.Employee e, int level, int maxLevel, int maxNexts, int maxHappy) {
        if (level > maxLevel) {
            return;
        }
        int nextsSize = (int) (Math.random() * (maxNexts + 1));
        for (int i = 0; i < nextsSize; i++) {
            C13_EmployeeMaxHappy.Employee next = new C13_EmployeeMaxHappy.Employee((int) (Math.random() * (maxHappy + 1)));
            e.members.add(next);
            genarateNexts(next, level + 1, maxLevel, maxNexts, maxHappy);
        }
    }

}
