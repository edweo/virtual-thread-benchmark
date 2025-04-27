package org.virtual.thread.benchmark;

import org.virtual.thread.benchmark.utilities.ConsoleUtils;

import static org.virtual.thread.benchmark.TestParameters.runEmpiricalCustomTests;
import static org.virtual.thread.benchmark.TestParameters.runEmpiricalTests;

public class Main {
    public static void main(String[] args) throws Exception {
        TestSuiteConfig.checkConfig();
        runEmpiricalTests();
        runEmpiricalCustomTests();

        System.out.println();
        ConsoleUtils.printGreenBackground("Finished running all empirical thread tests");
    }
}
