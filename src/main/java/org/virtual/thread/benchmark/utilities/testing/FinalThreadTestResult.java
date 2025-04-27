package org.virtual.thread.benchmark.utilities.testing;

public record FinalThreadTestResult<T>(
        ThreadTestResult<T> virtualThreadsResult,
        ThreadTestResult<T> platformThreadsResult
) {
}
