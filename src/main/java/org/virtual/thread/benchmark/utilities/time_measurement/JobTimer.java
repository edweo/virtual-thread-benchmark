package org.virtual.thread.benchmark.utilities.time_measurement;

import java.util.Objects;

public class JobTimer {

    private static final String JOB_PARAMETER_ERROR = "ERROR: Parameter 'job' cannot be null.";

    private JobTimer() {}

    /**
     * Measures time to complete a job.
     * Main thread blocking occurs if not run in a separate thread.
     *
     * @param outputTimeUnit desired output of time
     * @param job function which is measured by time it takes to complete it
     * @return elapsed time to tun the runnable function
     */
    public static double measureTime(TimeUnit outputTimeUnit, Runnable job) {
        Objects.requireNonNull(outputTimeUnit, JOB_PARAMETER_ERROR);

        try {
            Thread thread = Thread.ofPlatform().unstarted(job);

            long start = System.nanoTime();
            thread.start();
            thread.join();
            long end = System.nanoTime();

            return TimeUnit.convertTimeUnit(TimeUnit.NANO_SECONDS, outputTimeUnit, (double) end - start);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
