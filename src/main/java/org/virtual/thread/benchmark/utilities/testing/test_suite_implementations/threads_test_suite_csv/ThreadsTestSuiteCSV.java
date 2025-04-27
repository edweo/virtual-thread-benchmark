package org.virtual.thread.benchmark.utilities.testing.test_suite_implementations.threads_test_suite_csv;

import org.virtual.thread.benchmark.TestSuiteConfig;
import org.virtual.thread.benchmark.utilities.FileUtils;
import org.virtual.thread.benchmark.utilities.testing.EmpiricalDataProcessor;
import org.virtual.thread.benchmark.utilities.testing.ThreadsTestSuite;
import org.virtual.thread.benchmark.utilities.testing.empirical_data_processors.EmpiricalDataSaverCSV;
import org.virtual.thread.benchmark.utilities.time_measurement.TimeUnit;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Thread test suite which saves the empirical data to a CSV file
 *
 * @param <P> single test parameter which affects the output of a single test result
 */
abstract public class ThreadsTestSuiteCSV<P>
        extends ThreadsTestSuite<P, List<String[]>> {

    // CSV specific fields
    protected final String threadFactoryVirtualThreadsCsvPath;
    protected final String threadFactoryPlatformThreadsCsvPath;
    protected final String executorVirtualThreadsCsvPath;
    protected final String executorPlatformThreadsCsvPath;
    protected final String[] csvDataHeaders;
    protected static final String DIRECTORY_THREAD_FACTORY = "thread_factory";
    protected static final String DIRECTORY_EXECUTOR_SERVICE = "executor_service";

    // Could be not specific to CSV, e.g, in some other abstracted class, should be fine for now
    private final String fileNameVirtualTest;
    private final String fileNamePlatformTest;
    private final String testDirectoryName;

    public ThreadsTestSuiteCSV(
            String testName,
            TimeUnit timeUnit,
            String[] csvDataHeaders,
            String directoryName,
            String prefixFileName) throws Exception {
        super(testName, timeUnit);
        this.csvDataHeaders = csvDataHeaders;
        this.testDirectoryName = directoryName;

        String prefixToBeAdded = prefixFileName.isEmpty() ? prefixFileName : prefixFileName + "_";

        this.fileNameVirtualTest = prefixToBeAdded + FileUtils.createVirtualTestFileNameCSV(directoryName, timeUnit);
        this.fileNamePlatformTest = prefixToBeAdded + FileUtils.createPlatformTestFileNameCSV(directoryName, timeUnit);

        this.threadFactoryVirtualThreadsCsvPath = createFullPathFileCSV(directoryName + "/" + DIRECTORY_THREAD_FACTORY, fileNameVirtualTest);
        this.threadFactoryPlatformThreadsCsvPath = createFullPathFileCSV(directoryName + "/" + DIRECTORY_THREAD_FACTORY, fileNamePlatformTest);

        this.executorVirtualThreadsCsvPath = createFullPathFileCSV(directoryName + "/" + DIRECTORY_EXECUTOR_SERVICE, fileNameVirtualTest);
        this.executorPlatformThreadsCsvPath = createFullPathFileCSV(directoryName + "/" + DIRECTORY_EXECUTOR_SERVICE, fileNamePlatformTest);
    }

    @Override
    protected EmpiricalDataProcessor<List<String[]>> getEmpiricalDataProcessor() throws Exception {
        checkAndCreateTestDirectory(testDirectoryName);
        checkAndCreateTestDirectory(testDirectoryName + "/" + DIRECTORY_EXECUTOR_SERVICE);
        checkAndCreateTestDirectory(testDirectoryName + "/" + DIRECTORY_THREAD_FACTORY);

        return new EmpiricalDataSaverCSV(
                threadFactoryVirtualThreadsCsvPath,
                threadFactoryPlatformThreadsCsvPath,
                executorVirtualThreadsCsvPath,
                executorPlatformThreadsCsvPath,
                csvDataHeaders);
    }

    // Could be not specific to CSV, e.g, in some other abstracted class, should be fine for now
    private static String createFullPathFileCSV(String directoryName, String fileName) {
        return TestSuiteConfig.withDefaultPath(directoryName + "/" + fileName);
    }

    public static void checkAndCreateTestDirectory(String testDirectoryName) {
        if (!Files.exists(Path.of(TestSuiteConfig.withDefaultPath(testDirectoryName)))) {
            File dir = new File(TestSuiteConfig.withDefaultPath(testDirectoryName));
            dir.mkdir();
        }
    }
}
