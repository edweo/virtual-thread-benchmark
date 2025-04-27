package org.virtual.thread.benchmark.utilities;

import org.virtual.thread.benchmark.utilities.testing.ThreadsTestSuite;

/**
 * Test factory which provides the test implementation and some information about the test
 *
 * @param <P> single test parameter which affects the output of a single test result
 * @param <R> collective result after running one/multiple tests depending on intention
 * @param <M> method parameter to get specific thread suite
 */
public interface TestsFactory<P, R, M> {
    ThreadsTestSuite<P, R> newTestSuiteInstance(M parameter) throws Exception;
    String getTestName();
}
