package org.virtual.thread.benchmark.utilities.testing.empirical_data_processors;

import org.virtual.thread.benchmark.utilities.CSVManager;
import org.virtual.thread.benchmark.utilities.testing.EmpiricalDataProcessor;
import org.virtual.thread.benchmark.utilities.testing.FinalThreadTestResult;

import java.io.IOException;
import java.util.List;

public class CustomEmpiricalDataSaverCSV implements EmpiricalDataProcessor<List<String[]>> {

    private final String threadFactoryVirtualThreadsCsvPath;
    private final String threadFactoryPlatformThreadsCsvPath;
    private final String[] csvDataHeaders;

    public CustomEmpiricalDataSaverCSV(
            String threadFactoryVirtualThreadsCsvPath,
            String threadFactoryPlatformThreadsCsvPath,
            String[] csvDataHeaders) {
        this.threadFactoryVirtualThreadsCsvPath = threadFactoryVirtualThreadsCsvPath;
        this.threadFactoryPlatformThreadsCsvPath = threadFactoryPlatformThreadsCsvPath;
        this.csvDataHeaders = csvDataHeaders;
    }

    @Override
    public void processEmpiricalData(FinalThreadTestResult<List<String[]>> data) throws Exception {
        writeToCSV(data.virtualThreadsResult().threadFactoryResult(), this.threadFactoryVirtualThreadsCsvPath, csvDataHeaders);
        writeToCSV(data.platformThreadsResult().threadFactoryResult(), this.threadFactoryPlatformThreadsCsvPath, csvDataHeaders);
    }

    private static void writeToCSV(List<String[]> data, String fullPath, String[] csvDataHeaders) throws IOException {
        CSVManager csvManager = new CSVManager(fullPath);
        data.addFirst(csvDataHeaders);
        csvManager.writeToCSVFileStrings(data);
    }
}
