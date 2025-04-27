package org.virtual.thread.benchmark.test_factories;

import org.virtual.thread.benchmark.test_implementations.addition.AdditionTestSuite;
import org.virtual.thread.benchmark.utilities.TestsFactoryCSV;
import org.virtual.thread.benchmark.utilities.time_measurement.TimeUnit;
import org.virtual.thread.benchmark.utilities.testing.test_suite_implementations.threads_test_suite_csv.ThreadsTestSuiteCSV;

import java.util.List;

public class AdditionTestFactory implements TestsFactoryCSV<Integer> {

    private final List<Integer> threadAmounts;
    private final TimeUnit timeUnit;

    public AdditionTestFactory(List<Integer> threadAmounts, TimeUnit timeUnit) {
        this.threadAmounts = threadAmounts;
        this.timeUnit = timeUnit;
    }

    @Override
    public ThreadsTestSuiteCSV<Integer> newTestSuiteInstance(String fileNamePrefix) throws Exception {
        return AdditionTestSuite.newInstanceWithPrefixFileName(
                threadAmounts,
                timeUnit,
                fileNamePrefix
        );
    }

    @Override
    public String getTestName() {
        return AdditionTestSuite.TEST_NAME;
    }
}
