package org.virtual.thread.benchmark.utilities;

public class TestRunner {

    private TestRunner() {}

    /**
     * Calculate empirical data from a test n-amount of times to get multiple test which could be used to determine average score
     *
     * @param testsFactory factory which provides test suites
     * @param testAmount tests amount
     * @param <P> single test parameter which affects the output of a single test result
     * @throws Exception error
     */
    public static <P> void runTestWithFilePrefix(TestsFactoryCSV<P> testsFactory, int testAmount) throws Exception {
        ConsoleUtils.printCurrentRunningTest(testsFactory.getTestName());
        ConsoleUtils.printYellowTextSameLine(" (" + testAmount + " test rounds)");

        for (int i = 1; i <= testAmount; i++) {

            final int number = i;
            Runnable test = () -> {
                // Add prefix before each test run: 1_my_test.csv, 2_my_test.csv...
                //
                // Create and start the test
                try {
                    testsFactory
                            .newTestSuiteInstance(number+"")
                            .runTests();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            };

            Thread thread = Thread.ofPlatform().start(test);
            thread.join();
        }

        ConsoleUtils.printTestDone();
    }
}
