package org.virtual.thread.benchmark.test_implementations.addition_simulated_blocking;

import java.util.concurrent.atomic.AtomicLong;

public class AdditionSimulatedBlocking implements Runnable {

    private final AtomicLong count = new AtomicLong(0);
    private final int sleepInMS;

    /**
     * Create ExpensiveAddition with thread sleeping to simulate blocking behaviour of threads
     * @param sleepInMS sleep amount in milliseconds for every new thread
     */
    public AdditionSimulatedBlocking(int sleepInMS) {
        this.sleepInMS = sleepInMS;
    }


    @Override
    public void run() {
        count.incrementAndGet();
        try {
            Thread.sleep(sleepInMS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public long getCount() {
        return count.get();
    }
}
