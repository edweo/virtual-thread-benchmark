package org.virtual.thread.benchmark.test_implementations.addition;

import java.util.concurrent.atomic.AtomicLong;

public class Addition implements Runnable {

    private final AtomicLong count = new AtomicLong(0);

    @Override
    public void run() {
        count.incrementAndGet();
    }

    public long getCount() {
        return count.get();
    }
}
