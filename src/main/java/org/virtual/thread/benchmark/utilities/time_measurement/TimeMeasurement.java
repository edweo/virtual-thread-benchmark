package org.virtual.thread.benchmark.utilities.time_measurement;

public class TimeMeasurement {
    private long startTime;

    private long endTime;

    public TimeMeasurement() {}

    public void startTimer() {
        startTime = System.nanoTime();
    }

    public void stopTimer() {
        endTime = System.nanoTime();
    }

    /**
     * Returns the duration from starting the timer and ending the timer in ms.
     * @return a float indcating how many ms passed from start to end.
     */
    public float getResult() {
        return (float)(endTime - startTime) / 1000000;
    }

    public void resetTimer() {
        startTime = 0;
        endTime = 0;
    }
}
