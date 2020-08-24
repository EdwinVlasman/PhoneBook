package phonebook;

import java.util.ArrayList;

public final class Sort {
    // Method stops if it takes more then 10*argument time to complete
    public static long bubbleSort(ArrayList<String> array, long time) {

        long start = System.currentTimeMillis();
        for (int i = 0; i < array.size() - 1; i++) {
            for (int j = 0; j < array.size() - i - 1; j++) {
                if (array.get(j).replaceAll("\\d+", "").compareTo(array.get(j + 1).replaceAll("\\d+", "")) > 0) {
                    String temp = array.get(j);
                    array.set(j, array.get(j + 1));
                    array. set(j + 1, temp);
                }
                if ((System.currentTimeMillis() - start) >= (time * 10)) {
                    return (System.currentTimeMillis() - start);
                }
            }
        }

        return (System.currentTimeMillis() - start);
    }

    public static long quickSort(ArrayList<String> array, int left, int right) {
        long start = System.currentTimeMillis();
        if (left < right) {
            int pivotIndex = partition(array, left, right); // the pivot is already on its place
            quickSort(array, left, pivotIndex - 1);  // sort the left subarray
            quickSort(array, pivotIndex + 1, right); // sort the right subarray
        }
        return (System.currentTimeMillis() - start);
    }

    private static int partition(ArrayList<String> array, int left, int right) {
        String pivot = array.get(right).replaceAll("\\d+", "");  // choose the rightmost element as the pivot
        int partitionIndex = left; // the first element greater than the pivot

        /* move large values into the right side of the array */
        for (int i = left; i < right; i++) {
            if (array.get(i).replaceAll("\\d+", "").compareTo(pivot) <= 0) { // may be used '<' as well
                swap(array, i, partitionIndex);
                partitionIndex++;
            }
        }

        swap(array, partitionIndex, right); // put the pivot on a suitable position

        return partitionIndex;
    }

    private static void swap(ArrayList<String> array, int i, int j) {
        String temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }


}
