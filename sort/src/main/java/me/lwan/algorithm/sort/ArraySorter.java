package me.lwan.algorithm.sort;

public interface ArraySorter {

    <T extends Comparable<T>> void sorting(T[] array);

    default String name() {
        return getClass().getSimpleName();
    }

    default void swap(Object[] array, int i, int j) {
        if (i == j)
            return;
        Object tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

}
