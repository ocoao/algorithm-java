package me.lwan.algorithm.lab17;

import org.junit.jupiter.api.Test;

import java.util.Stack;

public class ReverseStackWithoutDataStructureTest {

    private final ReverseStackWithoutDataStructure rs = new ReverseStackWithoutDataStructure();

    @Test
    public void reverseStackTest() {
        Stack<Integer> s = new Stack<>();
        s.push(1);
        s.push(2);
        s.push(3);
        s.push(4);

        System.out.println(s);
        System.out.println("===============");

        rs.reverse(s);

        System.out.println(s);

    }

}
