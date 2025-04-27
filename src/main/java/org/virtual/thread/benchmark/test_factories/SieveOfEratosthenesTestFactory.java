package org.virtual.thread.benchmark.test_factories;

import org.virtual.thread.benchmark.test_implementations.sieve_of_erathostenes.SieveOfEratosthenesTestSuite;
import org.virtual.thread.benchmark.utilities.TestsFactoryCSV;
import org.virtual.thread.benchmark.utilities.testing.ThreadsTestSuite;
import org.virtual.thread.benchmark.utilities.time_measurement.TimeUnit;

import java.util.List;

public class SieveOfEratosthenesTestFactory implements TestsFactoryCSV<Integer> {

    private final List<Integer> threadAmounts;
    private final TimeUnit timeUnit;

    public SieveOfEratosthenesTestFactory(List<Integer> threadAmounts, TimeUnit timeUnit) {
        this.threadAmounts = threadAmounts;
        this.timeUnit = timeUnit;
    }

    @Override
    public ThreadsTestSuite<Integer, List<String[]>> newTestSuiteInstance(String fileNamePrefix) throws Exception {
        return SieveOfEratosthenesTestSuite.newInstanceWithPrefixFileName(threadAmounts, timeUnit, fileNamePrefix);
    }

    @Override
    public String getTestName() {
        return SieveOfEratosthenesTestSuite.TEST_NAME;
    }
}
