package org.virtual.thread.benchmark.utilities.testing;

/**
 * Interface which processes/saves data in some file
 * format or shows it in some UI such as console
 */
@FunctionalInterface
public interface EmpiricalDataProcessor<T> {
    void processEmpiricalData(FinalThreadTestResult<T> data) throws Exception;
}
