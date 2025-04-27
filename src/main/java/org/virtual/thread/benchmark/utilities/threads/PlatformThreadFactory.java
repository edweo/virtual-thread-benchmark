package org.virtual.thread.benchmark.utilities.threads;

public class PlatformThreadFactory implements ThreadFactory {

    @Override
    public Thread newThread(Runnable r) {
        return Thread.ofPlatform().unstarted(r);
    }
}
