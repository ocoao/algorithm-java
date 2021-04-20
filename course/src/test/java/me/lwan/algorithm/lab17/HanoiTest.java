package me.lwan.algorithm.lab17;

import org.junit.jupiter.api.Test;

public class HanoiTest {

    private final Hanoi hanoi = new Hanoi();

    @Test
    public void moveTest() {
        hanoi.move(2);
        System.out.println("===============");
        hanoi.move(3);
        System.out.println("===============");
        hanoi.move(4);
    }

}
