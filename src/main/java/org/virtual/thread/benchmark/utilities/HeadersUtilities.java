package org.virtual.thread.benchmark.utilities;

import org.virtual.thread.benchmark.utilities.time_measurement.TimeUnit;

public class HeadersUtilities {

    private HeadersUtilities() {}

    public static String[] createHeaders(TimeUnit timeUnit) {
        String timeHeader = "Time (" + TimeUnit.getStringTimeUnit(timeUnit) + ")";
        return new String[] {"Threads amount", timeHeader};
    }
}
