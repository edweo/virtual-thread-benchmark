package org.virtual.thread.benchmark.utilities;

import org.virtual.thread.benchmark.test_factories.*;
import org.virtual.thread.benchmark.utilities.time_measurement.TimeUnit;

import java.util.List;

public class TestFactoryProvider {

    private TestFactoryProvider() {}

    public static AdditionTestFactory getAdditionTestFactory(List<Integer> threadAmounts, TimeUnit timeUnit) {
        return new AdditionTestFactory(threadAmounts, timeUnit);
    }

    public static AdditionSimulatedBlockingTestFactory getAdditionSimulatedBlockingTestFactory(List<Integer> threadAmounts, TimeUnit timeUnit) {
        return new AdditionSimulatedBlockingTestFactory(threadAmounts, timeUnit);
    }

    public static AdditionFileOpeningTestFactory getAdditionFileOpeningTestFactory(List<Integer> threadAmounts, TimeUnit timeUnit) {
        return new AdditionFileOpeningTestFactory(threadAmounts, timeUnit);
    }

    public static ArraySortTestFactory getArraySortTestFactory(List<Integer> threadAmounts, TimeUnit timeUnit) {
        return new ArraySortTestFactory(threadAmounts, timeUnit);
    }

    public static SieveOfEratosthenesTestFactory getSieveOfEratosthenesTestFactory(List<Integer> threadAmounts, TimeUnit timeUnit) {
        return new SieveOfEratosthenesTestFactory(threadAmounts, timeUnit);
    }
}
