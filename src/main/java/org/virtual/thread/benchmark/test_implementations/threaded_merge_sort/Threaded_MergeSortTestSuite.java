package org.virtual.thread.benchmark.test_implementations.threaded_merge_sort;

import org.virtual.thread.benchmark.utilities.HelperFunctions;
import org.virtual.thread.benchmark.utilities.threads.ThreadFactory;
import org.virtual.thread.benchmark.utilities.time_measurement.TimeUnit;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class Threaded_MergeSortTestSuite {

    // Test suite required fields
    public static final String TEST_NAME = "Merge Sort";
    private static final String DIRECTORY_NAME = "merge_sort";


    private Threaded_MergeSortTestSuite(
            int arraySize,
            String testName,
            TimeUnit timeUnit,
            String[] csvDataHeaders,
            String prefixFileName) throws Exception {
    }


    private static String[] createHeaders(TimeUnit timeUnit) {
        String timeHeader = "Time (" + TimeUnit.getStringTimeUnit(timeUnit) + ")";
        return new String[] {timeHeader, "Array size", "JVM CPU Usage"};
    }

    /**
     * Test that creates, shuffles and sorts the array using the merge sort.
     * @param arraySize number defining how big the testing array is going to be.
     * @param  tf A thread factory that will be used to spawn threads
     */
    public static void Threaded_MergeSortTest_WithPrintouts(int arraySize, ThreadFactory tf) {

        int[] numArray = new int[arraySize];
        for (int i = 0; i < numArray.length; i++) {
            numArray[i] = i;
        }

        // Shuffle the numbers randomly
        HelperFunctions.shuffleIntArray(numArray);

        for (int i = 0; i < numArray.length; i++) {
            System.out.println(numArray[i]);
        }

        AtomicIntegerArray arrayToSort = new AtomicIntegerArray(numArray);

        MergeSort sort;
        sort = new MergeSort(tf, arrayToSort, 0, arrayToSort.length() - 1);
        System.out.println();
        sort.run();

        for (int i = 0; i < numArray.length; i++) {
            System.out.println(arrayToSort.get(i));
        }
    }

    /**
     * Test that creates, shuffles and sorts the array using the merge sort.
     * @param numberArray Array to be sorted
     * @param  tf A thread factory that will be used to spawn threads
     */
    public static void MergeSortTest_WithoutPrintouts(AtomicIntegerArray numberArray, ThreadFactory tf) {

        MergeSort sort;
        sort = new MergeSort(tf, numberArray, 0, numberArray.length() - 1);

        sort.run();
    }

}
