package org.virtual.thread.benchmark.utilities.testing;

import java.util.List;

public record ThreadTestResult<T>(
        T threadFactoryResult,
        T executorResult
) {

}
