package org.virtual.thread.benchmark.utilities.testing;

/**
 * Measures empirically thread performance of virtual and platform threads in the JVM
 */
@FunctionalInterface
public interface TestsRunnable {

    /**
     * Method to run the full test suite which can include gathering data and processing the data
     * @throws Exception error while running test
     */
    void runTests() throws Exception;
}
