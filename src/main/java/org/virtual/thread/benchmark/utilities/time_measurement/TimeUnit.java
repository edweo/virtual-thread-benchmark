package org.virtual.thread.benchmark.utilities.time_measurement;

/**
 * Default values are relative to seconds
 * 3 = 10^3, 6 = 10^6 ...
 */
public enum TimeUnit {
    NANO_SECONDS(9),
    MICRO_SECONDS(6),
    MILLI_SECONDS(3),
    SECONDS(0);

    public final long label;
    TimeUnit(int i) {
        this.label = i;
    }
    public static double convertTimeUnit(TimeUnit fromUnit, TimeUnit toUnit, double time) {
        int timeDifference =  (int) toUnit.label - (int) fromUnit.label;
        return Math.pow(10, timeDifference) * time;
    }

    public static String getStringTimeUnit(TimeUnit timeUnit) {
        String strTimeUnit;
        switch (timeUnit) {
            case NANO_SECONDS -> strTimeUnit = "ns";
            case MICRO_SECONDS -> strTimeUnit = "us";
            case MILLI_SECONDS -> strTimeUnit = "ms";
            default -> strTimeUnit = "s";
        }
        return strTimeUnit;
    }
}
