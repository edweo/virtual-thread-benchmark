package org.virtual.thread.benchmark.utilities.threads;

public interface ThreadFactory {

    /**
     * Creates a new instance of an unstarted Thread
     * @param r runnable task to be executed by the thread
     * @return returns runnable Thread
     */
    Thread newThread(Runnable r);
}
