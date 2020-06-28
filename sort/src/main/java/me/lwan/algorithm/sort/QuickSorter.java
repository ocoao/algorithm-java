package me.lwan.algorithm.sort;

public class QuickSorter implements ArraySorter {
    @Override
    public <T extends Comparable<T>> void sorting(T[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        quickSort(array, 0, array.length - 1);
    }

    protected <T extends Comparable<T>> void quickSort(T[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int[] range = partition(array, left, right);
        quickSort(array, left, range[0] - 1);
        quickSort(array, range[1] + 1, right);
    }

    protected <T extends Comparable<T>> int[] partition(T[] array, int left, int right) {
        T base = array[right];
        int less = left - 1;
        int more = right + 1;
        int i = left;
        while (i < more) {
            int c = array[i].compareTo(base);
            if (c < 0) {
                swap(array, ++less, i++);
            } else if (c > 0) {
                swap(array, --more, i);
            } else {
                i++;
            }
        }
        return new int[] { less + 1, more - 1 };
    }

}
