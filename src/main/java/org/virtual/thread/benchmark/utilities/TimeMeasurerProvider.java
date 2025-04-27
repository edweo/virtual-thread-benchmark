package org.virtual.thread.benchmark.utilities;

import org.virtual.thread.benchmark.utilities.threads.ThreadFactory;
import org.virtual.thread.benchmark.utilities.time_measurement.JobTimer;
import org.virtual.thread.benchmark.utilities.time_measurement.TimeUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class TimeMeasurerProvider {

    private TimeMeasurerProvider() {}

    public static double measureRuntimeUsingExecutor(
            ExecutorService executorService,
            List<Runnable> tasks,
            TimeUnit timeUnit
    ) {
        Runnable task = () -> {
            // Run all tasks
            for (Runnable r : tasks) {
                executorService.execute(r);
            }

            // Wait for all tasks to complete
            try {
                executorService.shutdown();
                executorService.awaitTermination(Long.MAX_VALUE, java.util.concurrent.TimeUnit.NANOSECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };

        return JobTimer.measureTime(timeUnit, task);
    }

    public static double measureRuntimeUsingThreadFactory(
            ThreadFactory threadFactory,
            List<Runnable> tasks,
            TimeUnit timeUnit
    ) {
        // Create unstarted threads
        List<Thread> threads = new ArrayList<>();
        for (Runnable task : tasks) {
            threads.add(threadFactory.newThread(task));
        }

        // Start threads
        Runnable task = () -> {
            for (Thread thread : threads) {
                thread.start();
            }

            // Wait for all threads to complete
            for (Thread thread : threads) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        return JobTimer.measureTime(timeUnit, task);
    }
}
