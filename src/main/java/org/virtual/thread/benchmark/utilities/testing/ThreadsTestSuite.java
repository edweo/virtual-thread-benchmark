package org.virtual.thread.benchmark.utilities.testing;

import org.virtual.thread.benchmark.utilities.TimeMeasurerProvider;
import org.virtual.thread.benchmark.utilities.threads.ThreadFactory;
import org.virtual.thread.benchmark.utilities.threads.ThreadFactoryProvider;
import org.virtual.thread.benchmark.utilities.time_measurement.TimeUnit;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Thread suite implementation to measure platform and virtual threads
 *
 * @param <P> single test parameter which affects the output of a single test result
 * @param <R> collective result after running one/multiple tests depending on intention
 */
abstract public class ThreadsTestSuite<P, R> implements TestsRunnable {

    protected String testName;
    protected TimeUnit timeUnit;

    protected ThreadsTestSuite(String testName, TimeUnit timeUnit) throws Exception {
        Objects.requireNonNull(testName, "Test name not set");
        Objects.requireNonNull(timeUnit, "TimeUnit not set");

        this.testName = testName;
        this.timeUnit = timeUnit;
    }

    /**
     * Run all virtual thread tests and compile 2 different results
     * - using plain threads
     * - using executor service
     *
     * @return compiled list of test results, could be only a single test result or multiple
     * @throws InterruptedException error
     */
    abstract protected ThreadTestResult<R> runAllVirtualThreadTests() throws InterruptedException;

    /**
     * Run all platform thread tests and compile results
     * @return compiled list of test results, could be only a single test result or multiple
     * @throws InterruptedException error
     */
    abstract protected ThreadTestResult<R> runAllPlatformThreadTests() throws InterruptedException;

    /**
     * Implementation of a single round full off runnables ready to be run
     *
     * @param parameter parameter which changes the test in some way which could affect the test result
     * @return test completion time as a result
     */
    abstract protected List<Runnable> testWorkloadImplementation(P parameter);

    /**
     * Return a processor object which will do something with the empirical data,
     * e.g., print to console, savo to file or any other needs
     *
     * @return object which will process the gathered empirical data result
     * @throws Exception error
     */
    abstract protected EmpiricalDataProcessor<R> getEmpiricalDataProcessor() throws Exception;

    protected double plainVirtualThreadTestFactory(P p) {
        ThreadFactory threadFactory = ThreadFactoryProvider.virtualThreadFactory();
        List<Runnable> tasks = testWorkloadImplementation(p);

        try {
            return measureRuntimeUsingThreadFactory(threadFactory, tasks);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected double plainPlatformThreadsTestFactory(P p) {
        ThreadFactory threadFactory = ThreadFactoryProvider.platformThreadFactory();
        List<Runnable> tasks = testWorkloadImplementation(p);

        try {
            return measureRuntimeUsingThreadFactory(threadFactory, tasks);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected double executorVirtualThreadTestFactory(P p) {
        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
        List<Runnable> tasks = testWorkloadImplementation(p);
        return measureRuntimeUsingExecutor(executorService, tasks);
    }

    protected double executorPlatformThreadsTestFactory(P p) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Runnable> tasks = testWorkloadImplementation(p);
        return measureRuntimeUsingExecutor(executorService, tasks);
    }

    protected double measureRuntimeUsingExecutor(ExecutorService executorService, List<Runnable> tasks) {
        return TimeMeasurerProvider.measureRuntimeUsingExecutor(executorService, tasks, this.timeUnit);
    }

    protected double measureRuntimeUsingThreadFactory(ThreadFactory threadFactory, List<Runnable> tasks) throws InterruptedException {
        return TimeMeasurerProvider.measureRuntimeUsingThreadFactory(threadFactory, tasks, this.timeUnit);
    };

    /**
     * Default implementation to run all tests and save empirical data to CSV using abstract methods
     * @throws Exception error
     */
    protected void runTestsAndApplyProcessingFunctionAfter() throws Exception {
        FinalThreadTestResult<R> empiricalData = new FinalThreadTestResult<>(runAllVirtualThreadTests(), runAllPlatformThreadTests());
        getEmpiricalDataProcessor().processEmpiricalData(empiricalData);
    }

    @Override
    public void runTests() throws Exception {
        runTestsAndApplyProcessingFunctionAfter();
    }


}
