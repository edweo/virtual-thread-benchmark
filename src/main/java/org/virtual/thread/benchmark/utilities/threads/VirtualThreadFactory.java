package org.virtual.thread.benchmark.utilities.threads;

public class VirtualThreadFactory implements ThreadFactory {


    @Override
    public Thread newThread(Runnable r) {
        return Thread.ofVirtual().unstarted(r);
    }
}
