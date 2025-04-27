package org.virtual.thread.benchmark.test_implementations.sieve_of_erathostenes;

import org.virtual.thread.benchmark.utilities.testing.ThreadTestResult;
import org.virtual.thread.benchmark.utilities.testing.test_suite_implementations.threads_test_suite_csv.ThreadsTestSuiteCSV;
import org.virtual.thread.benchmark.utilities.time_measurement.TimeUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.virtual.thread.benchmark.utilities.HeadersUtilities.createHeaders;

public class SieveOfEratosthenesTestSuite extends ThreadsTestSuiteCSV<Integer> {

    // Test suite required fields
    public static final String TEST_NAME = "Sieve of Eratosthenes 10_000_000 Numbers";
    private static final String DIRECTORY_NAME = "sieve_of_eratosthenes_10million_numbers";

    // Test suite specific fields
    private static final int UP_TO_NUMBER = 10_000_000;
    private final List<Integer> threadsAmount;

    protected SieveOfEratosthenesTestSuite(
            List<Integer> threadAmounts,
            TimeUnit timeUnit,
            String[] csvDataHeaders,
            String prefixFileName
    ) throws Exception {
        super(TEST_NAME, timeUnit, csvDataHeaders, DIRECTORY_NAME, prefixFileName);
        this.threadsAmount = threadAmounts;
    }

    public static SieveOfEratosthenesTestSuite newInstance(
            List<Integer> counters,
            TimeUnit timeUnit) throws Exception {
        return new SieveOfEratosthenesTestSuite(counters, timeUnit, createHeaders(timeUnit), "");
    }

    public static SieveOfEratosthenesTestSuite newInstanceWithPrefixFileName(
            List<Integer> counters,
            TimeUnit timeUnit,
            String prefixFileName) throws Exception {
        return new SieveOfEratosthenesTestSuite(counters, timeUnit, createHeaders(timeUnit), prefixFileName);
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
            Runnable task = () -> SieveOfErathostenes.findPrimeNumbers(UP_TO_NUMBER);
            tasks.add(task);
        }
        return tasks;
    }
}
