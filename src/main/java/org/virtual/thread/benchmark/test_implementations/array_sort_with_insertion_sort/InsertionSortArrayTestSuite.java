package org.virtual.thread.benchmark.test_implementations.array_sort_with_insertion_sort;

import org.virtual.thread.benchmark.utilities.testing.ThreadTestResult;
import org.virtual.thread.benchmark.utilities.testing.test_suite_implementations.threads_test_suite_csv.ThreadsTestSuiteCSV;
import org.virtual.thread.benchmark.utilities.time_measurement.TimeUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;

import static org.virtual.thread.benchmark.utilities.HeadersUtilities.createHeaders;

public class InsertionSortArrayTestSuite extends ThreadsTestSuiteCSV<Integer> {

    // Test suite required fields
    public static final String TEST_NAME = "Insertion sorting Arrays with 100000 Elements";
    private static final String DIRECTORY_NAME = "insertion_sort_arrays_with_100000_elements";

    // Test suite specific fields
    private static final int SEED_RANDOM_GENERATOR = 123;
    private static final int MIN_NUMBER_ARRAY = 1;
    private static final int MAX_NUMBER_ARRAY = 100_000;
    private static final int ARRAY_SIZE = 100_000;
    private static final int[] TEMPLATE_ARRAY;
    private final List<Integer> threadsAmount;

    static {
        // Create random array with same seed to amke all tests fair
        Random random = new Random(SEED_RANDOM_GENERATOR);
        TEMPLATE_ARRAY = IntStream
                .generate(() -> (int) (random.nextDouble() * (MAX_NUMBER_ARRAY - MIN_NUMBER_ARRAY + 1) + MIN_NUMBER_ARRAY))
                .limit(ARRAY_SIZE).toArray();
    }

    protected InsertionSortArrayTestSuite(
            List<Integer> threadAmounts,
            TimeUnit timeUnit,
            String[] csvDataHeaders,
            String prefixFileName
    ) throws Exception {
        super(TEST_NAME, timeUnit, csvDataHeaders, DIRECTORY_NAME, prefixFileName);
        this.threadsAmount = threadAmounts;
    }

    public static InsertionSortArrayTestSuite newInstance(
            List<Integer> counters,
            TimeUnit timeUnit) throws Exception {
        return new InsertionSortArrayTestSuite(counters, timeUnit, createHeaders(timeUnit), "");
    }

    public static InsertionSortArrayTestSuite newInstanceWithPrefixFileName(
            List<Integer> counters,
            TimeUnit timeUnit,
            String prefixFileName) throws Exception {
        return new InsertionSortArrayTestSuite(counters, timeUnit, createHeaders(timeUnit), prefixFileName);
    }

    private static List<String[]> runAllTests(List<Integer> threadAmount, Function<Integer, Double> threadTestFactory) throws InterruptedException {
        List<String[]> results = new ArrayList<>();
        for (Integer counter : threadAmount) {
            double time = threadTestFactory.apply(counter);
            results.add(new String[] {String.valueOf(counter), String.valueOf(time)});
        }
        return results;
    }

    @Override
    protected ThreadTestResult<List<String[]>> runAllVirtualThreadTests() throws InterruptedException {
        List<String[]> threadFactoryResult = runAllTests(threadsAmount, this::plainVirtualThreadTestFactory);
        List<String[]> executorResult = runAllTests(threadsAmount, this::executorVirtualThreadTestFactory);
        return new ThreadTestResult<>(threadFactoryResult, executorResult);
    }

    @Override
    protected ThreadTestResult<List<String[]>> runAllPlatformThreadTests() throws InterruptedException {
        List<String[]> threadFactoryResult = runAllTests(threadsAmount, this::plainPlatformThreadsTestFactory);
        List<String[]> executorResult = runAllTests(threadsAmount, this::executorPlatformThreadsTestFactory);
        return new ThreadTestResult<>(threadFactoryResult, executorResult);
    }

    @Override
    protected List<Runnable> testWorkloadImplementation(Integer parameter) {
        List<Runnable> tasks = new ArrayList<>();
        for (int i = 0; i < parameter; i++) {
            int[] copiedArray = TEMPLATE_ARRAY.clone();
            Runnable task = () -> InsertionSort.sort(copiedArray);
            tasks.add(task);
        }
        return tasks;
    }
}
