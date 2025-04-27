package org.virtual.thread.benchmark.test_implementations.threaded_merge_sort;

import org.virtual.thread.benchmark.utilities.threads.ThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class MergeSort implements Runnable {

    private ThreadFactory threadFactory;
    private AtomicIntegerArray array;
    private ExecutorService executorService;
    private int left;
    private int right;

    /**
     * Constructor that creates the MergeSort class
     * @param threadFactory Thread factory, used to create additional threads (virtual/platform).
     * @param atomicIntegerArray atomic number Array
     * @param left position of the Leftmost boundary.
     * @param right position of Rightmost boundary.
     */
    public MergeSort(ThreadFactory threadFactory, AtomicIntegerArray atomicIntegerArray, int left, int right) {
        this.threadFactory = threadFactory;
        this.array = atomicIntegerArray;
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        if (left < right) {
            int middle = left + (right - left) / 2;

            // Divide array into 2 parts.
            MergeSort leftSide = new MergeSort( threadFactory, array, left, middle);
            MergeSort rightSide = new MergeSort(threadFactory, array, middle + 1, right);

            // Start threads
            Thread leftThread = threadFactory.newThread(leftSide);
            Thread rightThread = threadFactory.newThread(rightSide);
            leftThread.start();
            rightThread.start();

            try {
                leftThread.join();
                rightThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            merge(middle);
        }
    }


    /**
     * Merges the left and right side of the array
     * @param middle position of the middle element dividing right and left side.
     */
    private void merge(int middle) {
        int[] leftArray = new int[middle - left + 1];
        int[] rightArray = new int[right - middle];

        for (int i = 0; i < leftArray.length; i++)
            leftArray[i] = array.get(left + i);
        for (int j = 0; j < rightArray.length; j++)
            rightArray[j] = array.get(middle + 1 + j);

        int i = 0, j = 0, k = left;
        while (i < leftArray.length && j < rightArray.length) {
            if (leftArray[i] <= rightArray[j]) {
                array.set(k, leftArray[i]);
                i++;
            } else {
                array.set(k, rightArray[j]);
                j++;
            }
            k++;
        }

        while (i < leftArray.length) {
            array.set(k, leftArray[i]);
            i++;
            k++;
        }

        while (j < rightArray.length) {
            array.set(k, rightArray[j]);
            j++;
            k++;
        }
    }
}
