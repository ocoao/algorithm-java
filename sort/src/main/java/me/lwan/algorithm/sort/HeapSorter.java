package me.lwan.algorithm.sort;

public class HeapSorter implements ArraySorter {
    @Override
    public <T extends Comparable<T>> void sorting(T[] array) {
        if (array == null || array.length < 2) {
            return;
        }
        // build the heap
        for (int i = getParentIndex(array.length - 1); i >= 0; i--) {
            heapify(array, i, array.length);
        }
        // swap the first element(the biggest value) and the last element, and then rebuild the heap
        int size = array.length;
        while (size > 0) {
            swap(array, 0, --size);
            heapify(array, 0, size);
        }
    }

    private <T extends Comparable<T>> void heapify(T[] array, int index, int size) {
        int left;
        while ((left = (index << 1) + 1) < size) {
            // get the max child
            int largest = left + 1 < size && array[left + 1].compareTo(array[left]) > 0 ? left + 1 : left;
            if (array[index].compareTo(array[largest]) >= 0) {
                // if the value is not less than the child
                break;
            }
            swap(array, index, largest);
            index = largest;
        }
    }

    private static int getParentIndex(int index) {
        return (index - 1) >> 1;
    }

}
