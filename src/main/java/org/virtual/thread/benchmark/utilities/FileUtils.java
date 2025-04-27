package org.virtual.thread.benchmark.utilities;

import org.virtual.thread.benchmark.utilities.time_measurement.TimeUnit;

public class FileUtils {

    private FileUtils() {}

    public static String createVirtualTestFileNameCSV(String directoryName, TimeUnit timeUnit) {
        return createFileNameCSV(formatDirectoryName(directoryName) + "_virtual_" + TimeUnit.getStringTimeUnit(timeUnit));
    }

    public static String createPlatformTestFileNameCSV(String directoryName, TimeUnit timeUnit) {
        return createFileNameCSV(formatDirectoryName(directoryName) + "_platform_" + TimeUnit.getStringTimeUnit(timeUnit));
    }

    private static String createFileNameCSV(String fileName) {
        return fileName + ".csv";
    }

    /**
     * Converts any '_' to '-' symbols in the directory name to make it easier to split the file name
     * with semantic values describing the file name, e.g., timeunit, file name, test thread type
     * @param directoryName directory name
     * @return formatted directory name
     */
    private static String formatDirectoryName(String directoryName) {
        return directoryName.replace("_", "-");
    }
}
