package org.virtual.thread.benchmark.utilities.threads;

public class ThreadFactoryProvider {

    private ThreadFactoryProvider() {}

    public static ThreadFactory virtualThreadFactory() {
        return new VirtualThreadFactory();
    }

    public static ThreadFactory platformThreadFactory() {
        return new PlatformThreadFactory();
    }
}
