package me.lwan.algorithm.lab17;

import java.util.Stack;

/**
 * 不借助任何数据结构 逆序栈
 */
public class ReverseStackWithoutDataStructure {

    public <T> void reverse(Stack<T> stack) {
        if (stack.isEmpty()) {
            return;
        }

        T t = popBottom(stack);
        reverse(stack);
        stack.push(t);
    }

    private <T> T popBottom(Stack<T> stack) {
        T t = stack.pop();
        if (stack.isEmpty()) {
            return t;
        }
        T bottom = popBottom(stack);
        stack.push(t);
        return bottom;
    }

}
