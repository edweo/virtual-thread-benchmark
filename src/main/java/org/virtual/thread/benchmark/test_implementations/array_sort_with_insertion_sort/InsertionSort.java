package org.virtual.thread.benchmark.test_implementations.array_sort_with_insertion_sort;

public class InsertionSort {

    public static void sort(int[] numberArray) {

        for(int i = 0; i < numberArray.length; i++) {

            int currentValue = numberArray[i];
            int j = i - 1;

            while (j >= 0 && currentValue < numberArray[j]) {
                numberArray[j + 1] = numberArray[j];
                j -= 1;
            }

            numberArray[j + 1] = currentValue;
        }
    }
}
