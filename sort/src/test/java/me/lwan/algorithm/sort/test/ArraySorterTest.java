package me.lwan.algorithm.sort.test;

import com.indvd00m.ascii.render.Render;
import com.indvd00m.ascii.render.api.ICanvas;
import com.indvd00m.ascii.render.api.IContextBuilder;
import com.indvd00m.ascii.render.api.IRender;
import com.indvd00m.ascii.render.elements.Label;
import com.indvd00m.ascii.render.elements.Table;
import com.indvd00m.ascii.render.elements.Text;
import me.lwan.algorithm.sort.*;
import org.junit.jupiter.api.Test;
import sun.security.x509.X400Address;

import java.nio.file.NotLinkException;
import java.util.Arrays;
import java.util.Random;

/**
 * @see <a href="https://visualgo.net/zh/sorting">visualgo sorting</a>
 */
public class ArraySorterTest {

    private Integer[] generateArray() {
        Random rnd = new Random();
//        Integer[] arr = new Integer[10 + rnd.nextInt(9)];
        Integer[] arr = new Integer[10000 + rnd.nextInt(9000)];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rnd.nextBoolean() ? rnd.nextInt(5000) : -rnd.nextInt(5000);
        }
        return arr;
    }

    @Test
    public void batchTest() {
        int batchTimes = 10;
        ArraySorter[] sorters = {
                new BubbleSorter(), new SelectionSorter(), new InsertionSorter(),
                new MergeSorter(), new MergeSorter2()
        };
        Table table = createTable(batchTimes, sorters);
        int row = 2;
        for (int i = 0; i < batchTimes; i++) {
            Integer[] array = generateArray();
            long[] times = doTest(array, sorters);
            if (times == null) {
                return;
            }
            addRow(table, row++, array.length, times);
        }
        render(table, 15 * (sorters.length + 2), 4 * (batchTimes + 1));
    }

    @Test
    public void bubbleTest() {
        doTest(new BubbleSorter());
    }

    @Test
    public void selectionTest() {
        doTest(new SelectionSorter());
    }

    @Test
    public void insertionTest() {
        doTest(new InsertionSorter());
    }

    @Test
    public void mergeTest() {
        doTest(new MergeSorter());
    }

    @Test
    public void mergeTest2() {
        doTest(new MergeSorter2());
    }

    private void doTest(ArraySorter sorter) {
        Integer[] arr = generateArray();

        long[] times = doTest(arr, sorter);
        if (times == null) {
            return;
        }
        Table table = createTable(1, sorter);
        addRow(table, 2, arr.length, times);
        render(table, 40, 8);
    }

    private <T extends Comparable<T>> long[] doTest(T[] arr, ArraySorter... sorters) {
//        StringBuilder log = new StringBuilder();
//        log.append("LEN=").append(arr.length);
//        int col = 1;
//        render.setElement(col++, 2, new Text(String.valueOf(arr.length)));

        long[] times = new long[sorters.length + 1];
        int i = 0;

        T[] dup = Arrays.copyOf(arr, arr.length);
        long t = System.currentTimeMillis();
        Arrays.sort(dup);
        t = System.currentTimeMillis() - t;
//        log.append(", JDK COST=").append(t).append(" millis.");
//        render.setElement(col++, 2, new Text(t + " millis."));
        times[i++] = t;

        for (ArraySorter sorter : sorters) {
            T[] dup2 = Arrays.copyOf(arr, arr.length);
            t = System.currentTimeMillis();
            sorter.sorting(dup2);
            t = System.currentTimeMillis() - t;
            if (!Arrays.equals(dup, dup2)) {
                System.err.println("ERR..." + sorter.name());
                return null;
            }
//            log.append(" ").append(sorter.name()).append(" COST=").append(t).append(" millis.");
//            render.setElement(col++, 2, new Text(t + " millis"));
            times[i++] = t;
        }
//        System.out.println(log);
        return times;
    }

    private static Table createTable(int totalTimes, ArraySorter... sorters) {
        Table table = new Table(sorters.length + 2, totalTimes + 1);
        int col = 1;
        table.setElement(col++, 1, new Label("ARRAY LEN"));
        table.setElement(col++, 1, new Label("JDK"));
        for (ArraySorter sorter : sorters) {
            table.setElement(col++, 1, new Label(sorter.name()));
        }
        return table;
    }

    private static void addRow(Table table, int row, int arrayLen, long[] times) {
        int col = 1;
        table.setElement(col++, row, new Text(String.valueOf(arrayLen)));
        for (long t : times) {
            table.setElement(col++, row, new Text(t + " millis."));
        }
    }

    private static void render(Table table, int width, int height) {
        IRender render = new Render();
        IContextBuilder builder = render.newBuilder();
        builder.width(width).height(height);

        builder.element(table);

        ICanvas canvas = render.render(builder.build());
        System.out.println(canvas.getText());
    }

}
