package org.virtual.thread.benchmark.utilities;

import org.virtual.thread.benchmark.utilities.time_measurement.TimeUnit;

import java.util.List;

import static org.virtual.thread.benchmark.utilities.TestRunner.runTestWithFilePrefix;

public class TestRunnersProvider {

    private TestRunnersProvider() {}

    public static void runAdditionTests(List<Integer> threadAmounts, int testAmount, TimeUnit timeUnit) throws Exception {
        runTestWithFilePrefix(TestFactoryProvider.getAdditionTestFactory(threadAmounts, timeUnit), testAmount);
    }

    public static void runAdditionWithDelayTests(List<Integer> threadAmounts, int testAmount, TimeUnit timeUnit) throws Exception {
        runTestWithFilePrefix(TestFactoryProvider.getAdditionSimulatedBlockingTestFactory(threadAmounts, timeUnit), testAmount);
    }

    public static void runAdditionFileOpeningTests(List<Integer> threadAmounts, int testAmount, TimeUnit timeUnit) throws Exception {
        runTestWithFilePrefix(TestFactoryProvider.getAdditionFileOpeningTestFactory(threadAmounts, timeUnit), testAmount);
    }

    public static void runArraySortTests(List<Integer> threadAmounts, int testAmount, TimeUnit timeUnit) throws Exception {
        runTestWithFilePrefix(TestFactoryProvider.getArraySortTestFactory(threadAmounts, timeUnit), testAmount);
    }

    public static void runSieveOfEratosthenesTests(List<Integer> threadAmounts, int testAmount, TimeUnit timeUnit) throws Exception {
        runTestWithFilePrefix(TestFactoryProvider.getSieveOfEratosthenesTestFactory(threadAmounts, timeUnit), testAmount);
    }
}
