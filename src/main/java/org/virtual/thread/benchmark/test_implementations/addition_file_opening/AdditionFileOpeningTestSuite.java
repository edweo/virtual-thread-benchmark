package org.virtual.thread.benchmark.test_implementations.addition_file_opening;

import org.virtual.thread.benchmark.utilities.testing.ThreadTestResult;
import org.virtual.thread.benchmark.utilities.testing.test_suite_implementations.threads_test_suite_csv.ThreadsTestSuiteCSV;
import org.virtual.thread.benchmark.utilities.time_measurement.TimeUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.virtual.thread.benchmark.utilities.HeadersUtilities.createHeaders;

public class AdditionFileOpeningTestSuite extends ThreadsTestSuiteCSV<Integer> {

    // Test suite required fields
    public static final String TEST_NAME = "Addition File Opening";
    private static final String DIRECTORY_NAME = "addition_file_opening";

    // Test suite specific fields
    private final List<Integer> threadsAmount;

    private AdditionFileOpeningTestSuite(
            List<Integer> threadAmounts,
            TimeUnit timeUnit,
            String[] csvDataHeaders,
            String prefixFileName
    ) throws Exception {
        super(TEST_NAME, timeUnit, csvDataHeaders, DIRECTORY_NAME, prefixFileName);
        this.threadsAmount = threadAmounts;
    }

    public static AdditionFileOpeningTestSuite newInstanceWithPrefixFileName(
            List<Integer> counters,
            TimeUnit timeUnit,
            String prefixFileName) throws Exception {
        return new AdditionFileOpeningTestSuite(counters, timeUnit, createHeaders(timeUnit), prefixFileName);
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
        AdditionFileOpen addition = new AdditionFileOpen();

        List<Runnable> tasks = new ArrayList<>();
        for (int i = 0; i < parameter; i++) {
            tasks.add(addition);
        }

        return tasks;
    }
}
