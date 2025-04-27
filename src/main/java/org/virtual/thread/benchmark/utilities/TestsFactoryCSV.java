package org.virtual.thread.benchmark.utilities;

import org.virtual.thread.benchmark.utilities.testing.ThreadsTestSuite;

import java.util.List;

public interface TestsFactoryCSV<P> extends TestsFactory<P, List<String[]>, String> {

    @Override
    ThreadsTestSuite<P, List<String[]>> newTestSuiteInstance(String fileNamePrefix) throws Exception;
}
