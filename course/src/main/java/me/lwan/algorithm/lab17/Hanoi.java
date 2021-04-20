package me.lwan.algorithm.lab17;

/**
 * 汉诺塔
 */
public class Hanoi {

    public void move(int n) {
        move(n, "L", "R", "M");
    }

    private void move(int n, String left, String right, String middle) {
        if (n == 1) {
            System.out.printf("MOVE 1: %s -> %s\n", left, right);
            return;
        }
        move(n - 1, left, middle, right);
        System.out.printf("MOVE %d: %s -> %s\n", n, left, right);
        move(n - 1, middle, right, left);
    }

}
