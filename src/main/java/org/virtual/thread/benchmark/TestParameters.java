package org.virtual.thread.benchmark;

import org.virtual.thread.benchmark.test_implementations.threaded_merge_sort.Threaded_Tests;
import org.virtual.thread.benchmark.utilities.ConsoleUtils;
import org.virtual.thread.benchmark.utilities.time_measurement.TimeUnit;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static org.virtual.thread.benchmark.utilities.TestRunnersProvider.*;

public class TestParameters {

    private static final List<Integer> fastTest;
    private static final List<Integer> slowerTest;
    private static final List<Integer> mediumTest;
    private static final List<Integer> intensiveTest;

    private static final int TEST_RUN_COUNT = 5;

    static {
        fastTest = List.of(32, 64, 128, 256, 512, 1024, 2048);
        slowerTest = mergeLists(fastTest, List.of(4096, 8192, 16384, 32768, 65536, 131072));
        mediumTest = mergeLists(slowerTest, List.of(262144, 524288, 1048576));
        intensiveTest = mergeLists(mediumTest, List.of(2097152));
    }

    private TestParameters() {}

    /**
     * Configure empirical tests
     * @throws Exception error
     */
    public static void runEmpiricalTests() throws Exception {
        List<Integer> customTest = List.of(32, 64, 128, 256);

        runAdditionTests(slowerTest, TEST_RUN_COUNT, TimeUnit.SECONDS);
        System.gc();
        runAdditionWithDelayTests(customTest, TEST_RUN_COUNT, TimeUnit.MILLI_SECONDS);
        System.gc();
        runAdditionFileOpeningTests(customTest, TEST_RUN_COUNT, TimeUnit.MILLI_SECONDS);
        System.gc();
        runArraySortTests(List.of(2, 4, 8), TEST_RUN_COUNT, TimeUnit.SECONDS);
        System.gc();
        runSieveOfEratosthenesTests(List.of(2, 4, 8, 16, 32), TEST_RUN_COUNT, TimeUnit.SECONDS);
        System.gc();
    }

    public static void runEmpiricalCustomTests() throws FileNotFoundException {
        ConsoleUtils.printCurrentRunningTest("Recursive Merge Sort");
        ConsoleUtils.printYellowTextSameLine(" (" + TEST_RUN_COUNT + " test rounds)");

        List<int[]> testArrays = generateTestArrays();
        for (int i = 1; i <= TEST_RUN_COUNT; i++) {
            Threaded_Tests.run_virtualVSplatforming_test(testArrays, String.valueOf(i));
        }

        ConsoleUtils.printTestDone();
    }

    private static List<int[]> generateTestArrays() {
        int SEED_RANDOM_GENERATOR = 123;
        int MIN_NUMBER_ARRAY = 1;
        int MAX_NUMBER_ARRAY = 20_000;

        List<int[]> allArrays = new ArrayList<>();

        Random random = new Random(SEED_RANDOM_GENERATOR);

        int[] arraySizes = new int[] {32, 64, 128, 256, 516};

        for (int arraySize: arraySizes) {
            int[] newArr = IntStream
                    .generate(() -> (int) (random.nextDouble() * (MAX_NUMBER_ARRAY - MIN_NUMBER_ARRAY + 1) + MIN_NUMBER_ARRAY))
                    .limit(arraySize).toArray();

            allArrays.add(newArr);
        }

        return allArrays;
    }

    private static List<Integer> mergeLists(List<Integer> list1, List<Integer> list2) {
        List<Integer> result = new ArrayList<>();
        result.addAll(list1);
        result.addAll(list2);
        return List.copyOf(result);
    }
}
