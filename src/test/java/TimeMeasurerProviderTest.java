import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.virtual.thread.benchmark.test_implementations.addition.Addition;
import org.virtual.thread.benchmark.test_implementations.addition_simulated_blocking.AdditionSimulatedBlocking;
import org.virtual.thread.benchmark.utilities.TimeMeasurerProvider;
import org.virtual.thread.benchmark.utilities.threads.ThreadFactoryProvider;
import org.virtual.thread.benchmark.utilities.time_measurement.TimeUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeMeasurerProviderTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100, 1000, 10000})
    public void executorWaitsForAllThreadsToFinish(int upToCount) {

        Addition addition = new Addition();
        List<Runnable> tasks = new ArrayList<>();
        for (int i = 0; i < upToCount; i++) {
            tasks.add(addition);
        }

        TimeMeasurerProvider.measureRuntimeUsingExecutor(
                Executors.newCachedThreadPool(),
                tasks,
                TimeUnit.SECONDS
        );

        assertEquals(upToCount, addition.getCount());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100, 1000, 10000})
    public void platformThreadFactoryWaitsForAllThreadsToFinish(int upToCount) {

        Addition addition = new Addition();
        List<Runnable> tasks = new ArrayList<>();
        for (int i = 0; i < upToCount; i++) {
            tasks.add(addition);
        }

        TimeMeasurerProvider.measureRuntimeUsingThreadFactory(
                ThreadFactoryProvider.platformThreadFactory(),
                tasks,
                TimeUnit.SECONDS
        );

        assertEquals(upToCount, addition.getCount());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100, 1000, 10000})
    public void virtualThreadFactoryWaitsForAllThreadsToFinish(int upToCount) {

        Addition addition = new Addition();
        List<Runnable> tasks = new ArrayList<>();
        for (int i = 0; i < upToCount; i++) {
            tasks.add(addition);
        }

        TimeMeasurerProvider.measureRuntimeUsingThreadFactory(
                ThreadFactoryProvider.virtualThreadFactory(),
                tasks,
                TimeUnit.SECONDS
        );

        assertEquals(upToCount, addition.getCount());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100, 1000})
    public void executorDelayWaitsForAllThreadsToFinish(int upToCount) {

        AdditionSimulatedBlocking addition = new AdditionSimulatedBlocking(10);
        List<Runnable> tasks = new ArrayList<>();
        for (int i = 0; i < upToCount; i++) {
            tasks.add(addition);
        }

        TimeMeasurerProvider.measureRuntimeUsingExecutor(
                Executors.newCachedThreadPool(),
                tasks,
                TimeUnit.SECONDS
        );

        assertEquals(upToCount, addition.getCount());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100, 1000})
    public void platformThreadFactoryDelayWaitsForAllThreadsToFinish(int upToCount) {

        AdditionSimulatedBlocking addition = new AdditionSimulatedBlocking(10);
        List<Runnable> tasks = new ArrayList<>();
        for (int i = 0; i < upToCount; i++) {
            tasks.add(addition);
        }

        TimeMeasurerProvider.measureRuntimeUsingThreadFactory(
                ThreadFactoryProvider.platformThreadFactory(),
                tasks,
                TimeUnit.SECONDS
        );

        assertEquals(upToCount, addition.getCount());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100, 1000})
    public void virtualThreadFactoryDelayWaitsForAllThreadsToFinish(int upToCount) {

        AdditionSimulatedBlocking addition = new AdditionSimulatedBlocking(10);
        List<Runnable> tasks = new ArrayList<>();
        for (int i = 0; i < upToCount; i++) {
            tasks.add(addition);
        }

        TimeMeasurerProvider.measureRuntimeUsingThreadFactory(
                ThreadFactoryProvider.virtualThreadFactory(),
                tasks,
                TimeUnit.SECONDS
        );

        assertEquals(upToCount, addition.getCount());
    }
}
