package org.virtual.thread.benchmark.test_implementations.addition_simulated_blocking;

import org.virtual.thread.benchmark.utilities.testing.ThreadTestResult;
import org.virtual.thread.benchmark.utilities.testing.test_suite_implementations.threads_test_suite_csv.ThreadsTestSuiteCSV;
import org.virtual.thread.benchmark.utilities.time_measurement.TimeUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.virtual.thread.benchmark.utilities.HeadersUtilities.createHeaders;

public class AdditionSimulatedBlockingTestSuite extends ThreadsTestSuiteCSV<Integer> {

    // Test suite specific fields
    private static final int SLEEP_MS = 10;
    private final List<Integer> threadsAmount;

    // Test suite required fields
    public static final String TEST_NAME = "Addition With Simulated Blocking " + SLEEP_MS + "ms";
    private static final String DIRECTORY_NAME = "addition_simulated_blocking_" + SLEEP_MS + "ms";

    private AdditionSimulatedBlockingTestSuite(
            List<Integer> threadAmounts,
            TimeUnit timeUnit,
            String[] csvDataHeaders,
            String prefixFileName
    ) throws Exception {
        super(TEST_NAME, timeUnit, csvDataHeaders, DIRECTORY_NAME, prefixFileName);
        this.threadsAmount = threadAmounts;
    }

    public static AdditionSimulatedBlockingTestSuite newInstanceWithPrefixFileName(
            List<Integer> counters,
            TimeUnit timeUnit,
            String prefixFileName) throws Exception {
        return new AdditionSimulatedBlockingTestSuite(counters, timeUnit, createHeaders(timeUnit), prefixFileName);
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

    private static List<String[]> runAllTests(List<Integer> threadAmount, Function<Integer, Double> threadTestFactory) throws InterruptedException {
        List<String[]> results = new ArrayList<>();
        for (Integer counter : threadAmount) {
            double time = threadTestFactory.apply(counter);
            results.add(new String[] {String.valueOf(counter), String.valueOf(time)});
        }
        return results;
    }

    @Override
    protected List<Runnable> testWorkloadImplementation(Integer parameter) {
        AdditionSimulatedBlocking additionSimulatedBlocking
                = new AdditionSimulatedBlocking(SLEEP_MS);

        List<Runnable> tasks = new ArrayList<>();
        for (int i = 0; i < parameter; i++) {
            tasks.add(additionSimulatedBlocking);
        }

        return tasks;
    }
}
