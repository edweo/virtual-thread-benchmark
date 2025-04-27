package org.virtual.thread.benchmark.test_factories;

import org.virtual.thread.benchmark.test_implementations.addition_file_opening.AdditionFileOpeningTestSuite;
import org.virtual.thread.benchmark.utilities.TestsFactoryCSV;
import org.virtual.thread.benchmark.utilities.testing.ThreadsTestSuite;
import org.virtual.thread.benchmark.utilities.time_measurement.TimeUnit;

import java.util.List;

public class AdditionFileOpeningTestFactory implements TestsFactoryCSV<Integer> {

    private final List<Integer> threadAmounts;
    private final TimeUnit timeUnit;

    public AdditionFileOpeningTestFactory(List<Integer> threadAmounts, TimeUnit timeUnit) {
        this.threadAmounts = threadAmounts;
        this.timeUnit = timeUnit;
    }

    @Override
    public ThreadsTestSuite<Integer, List<String[]>> newTestSuiteInstance(String fileNamePrefix) throws Exception {
        return AdditionFileOpeningTestSuite.newInstanceWithPrefixFileName(threadAmounts, timeUnit, fileNamePrefix);
    }

    @Override
    public String getTestName() {
        return AdditionFileOpeningTestSuite.TEST_NAME;
    }
}
