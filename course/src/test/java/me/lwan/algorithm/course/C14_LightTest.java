package me.lwan.algorithm.course;

import java.util.HashSet;

public class C14_LightTest extends BaseTest {

    private final C14_Light light = new C14_Light();

    @Override
    public void runTest() {
        char[] arr = new char[nextInt(1, 20)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Math.random() < 0.5 ? 'X' : '.';
        }
        String road = new String(arr);
        if (getMinLightCount(road) != light.getMinLightCount(road)) {
            throw new IllegalStateException();
        }
    }

    private int getMinLightCount(String road) {
        if (road == null || road.isEmpty()) {
            return 0;
        }
        return process(road.toCharArray(), 0, new HashSet<>());
    }

    // str[index....]位置，自由选择放灯还是不放灯
    // str[0..index-1]位置呢？已经做完决定了，那些放了灯的位置，存在lights里
    // 要求选出能照亮所有.的方案，并且在这些有效的方案中，返回最少需要几个灯
    private int process(char[] str, int index, HashSet<Integer> lights) {
        if (index == str.length) { // 结束的时候
            for (int i = 0; i < str.length; i++) {
                if (str[i] != 'X') { // 当前位置是点的话
                    if (!lights.contains(i - 1) && !lights.contains(i) && !lights.contains(i + 1)) {
                        return Integer.MAX_VALUE;
                    }
                }
            }
            return lights.size();
        } else { // str还没结束
            // i X .
            int no = process(str, index + 1, lights);
            int yes = Integer.MAX_VALUE;
            if (str[index] == '.') {
                lights.add(index);
                yes = process(str, index + 1, lights);
                lights.remove(index);
            }
            return Math.min(no, yes);
        }
    }

}
