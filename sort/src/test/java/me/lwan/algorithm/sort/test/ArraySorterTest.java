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

import java.util.Arrays;
import java.util.Random;

/**
 * @see <a href="https://visualgo.net/zh/sorting">visualgo sorting</a>
 */
public class ArraySorterTest {

    private Integer[] generateArray() {
        return generateArray(10000);
    }

    private Integer[] generateArray(int count) {
        Random rnd = new Random();
//        Integer[] arr = new Integer[10 + rnd.nextInt(9)];
        Integer[] arr = new Integer[count];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rnd.nextBoolean() ? rnd.nextInt(5000) : -rnd.nextInt(5000);
        }
        return arr;
    }

    @Test
    public void batchTest() {
        int batchTimes = 17;
        ArraySorter[] sorters = {
//                new BubbleSorter(), new SelectionSorter(), new InsertionSorter(),
                new MergeSorter(), new MergeSorter2(),
                new QuickSorter(), new RandomQuickSorter(),
                new HeapSorter()
        };
        Table table = createTable(batchTimes, sorters);
        int row = 2;
        for (int i = 0; i < batchTimes; i++) {
            Integer[] array = generateArray(100 << i);
            long[] times = doTest(array, sorters);
            if (times == null) {
                return;
            }
            addRow(table, row++, array.length, times);
            System.out.println("batch ok -> " + (i + 1));
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

    @Test
    public void quickTest() {
        doTest(new QuickSorter());
    }

    @Test
    public void randomQuickTest() {
        doTest(new RandomQuickSorter());
    }

    @Test
    public void heapTest() {
        doTest(new HeapSorter());
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
        long[] times = new long[sorters.length + 1];
        int i = 0;

        T[] dup = Arrays.copyOf(arr, arr.length);
        long t = System.currentTimeMillis();
        Arrays.sort(dup);
        t = System.currentTimeMillis() - t;
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
            times[i++] = t;
            System.out.println(arr.length + " -> " + sorter.name() + " OK");
        }
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

    public int reversePairs(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        return reversePairs(nums, 0, nums.length - 1);
    }

    private int reversePairs(int[] nums, int left, int right) {
        if (left == right) {
            return 0;
        }
        int mid = (left + right) >> 1;
        int countLeft = reversePairs(nums, left, mid);
        int countRight = reversePairs(nums, mid + 1, right);

        int count = 0;
        int p = mid;
        int q = right;
        int[] tmp = new int[right - left + 1];
        int i = tmp.length - 1;
        while (p >= left && q > mid) {
            if (nums[p] > nums[q]) {
                count += q - mid;
                tmp[i--] = nums[p--];
            } else {
                tmp[i--] = nums[q--];
            }
        }
        while (p >= left) {
            tmp[i--] = nums[p--];
        }
        while (q > mid) {
            tmp[i--] = nums[q--];
        }
        for (i = 0; i < tmp.length; i++) {
            nums[left + i] = tmp[i];
        }

        return countLeft + countRight + count;
    }

    @Test
    public void testReversePairs() {
        int n = reversePairs(new int[] { 7, 5, 6, 4 });
        System.out.println(n);
    }

    public int countRangeSum(int[] nums, int lower, int upper) {
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                long sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += nums[k];
                }
                if (sum >= lower && sum <= upper) {
                    ans++;
                    System.out.println(i + ", " + j + " = " + sum);
                }
            }
        }
        return ans;
    }

    @Test
    public void countRangeSum() {
        System.out.println(countRangeSum(new int[] { -2147483647,0,-2147483647,2147483647 }, -564, 3864));
    }

}
