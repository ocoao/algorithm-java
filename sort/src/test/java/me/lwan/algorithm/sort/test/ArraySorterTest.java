package me.lwan.algorithm.sort.test;

import me.lwan.algorithm.sort.ArraySorter;
import me.lwan.algorithm.sort.BubbleSorter;
import me.lwan.algorithm.sort.InsertionSorter;
import me.lwan.algorithm.sort.SelectionSorter;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

/**
 * @see <a href="https://visualgo.net/zh/sorting">visualgo sorting</a>
 */
public class ArraySorterTest {

    private Integer[] generateArray() {
        Random rnd = new Random();
        Integer[] arr = new Integer[10000 + rnd.nextInt(9000)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rnd.nextBoolean() ? rnd.nextInt(5000) : -rnd.nextInt(5000);
        }
        return arr;
    }

    @Test
    public void batchTest() {
        doTest(generateArray(), new BubbleSorter(), new SelectionSorter(), new InsertionSorter());
    }

    @Test
    public void bubbleTest() {
        Integer[] arr = generateArray();
        doTest(arr, new BubbleSorter());
    }

    @Test
    public void selectionTest() {
        doTest(generateArray(), new SelectionSorter());
    }

    @Test
    public void insertionTest() {
        doTest(generateArray(), new InsertionSorter());
    }

    private <T extends Comparable<T>> void doTest(T[] arr, ArraySorter... sorters) {
        StringBuilder log = new StringBuilder();
        log.append("LEN=").append(arr.length);

        T[] dup = Arrays.copyOf(arr, arr.length);
        long t = System.currentTimeMillis();
        Arrays.sort(dup);
        t = System.currentTimeMillis() - t;
        log.append(", JDK COST=").append(t).append(" millis.");

        for (ArraySorter sorter : sorters) {
            T[] dup2 = Arrays.copyOf(arr, arr.length);
            t = System.currentTimeMillis();
            sorter.sorting(dup2);
            t = System.currentTimeMillis() - t;
            if (!Arrays.equals(dup, dup2)) {
                System.err.println("ERR..." + sorter.name());
                return;
            }
            log.append(" ").append(sorter.name()).append(" COST=").append(t).append(" millis.");
        }
        System.out.println(log);
    }

}
