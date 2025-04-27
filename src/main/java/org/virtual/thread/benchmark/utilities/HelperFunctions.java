package org.virtual.thread.benchmark.utilities;

import java.util.Random;

public class HelperFunctions {

    /**
     * Function that shuffles the given int array
     * @param array integer array
     */
    public static void shuffleIntArray(int[] array) {
        Random rnd = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = array[index];
            array[index] = array[i];
            array[i] = a;
        }
    }

    /**
     * Function that creates an array of shuffled numbers, starting from 0 up to specified size - 1;
     * @param size the size of wanted array.
     * @return integer array
     */
    public static int[] createdRandomArray(int size) {
        int[] newArray = new int[size];
        shuffleIntArray(newArray);
        return newArray;
    }
}
