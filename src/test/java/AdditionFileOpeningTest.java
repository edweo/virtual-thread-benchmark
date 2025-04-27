import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.virtual.thread.benchmark.test_implementations.addition_file_opening.AdditionFileOpen;
import org.virtual.thread.benchmark.utilities.TimeMeasurerProvider;
import org.virtual.thread.benchmark.utilities.time_measurement.TimeUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdditionFileOpeningTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 10, 100, 1000})
    public void testOpenFileResultCorrectValue(int threadsAmount) {
        AdditionFileOpen additionFileOpen = new AdditionFileOpen();

        List<Runnable> tasks = new ArrayList<>();
        for (int i = 0; i < threadsAmount; i++) {
            tasks.add(additionFileOpen);
        }

        TimeMeasurerProvider.measureRuntimeUsingExecutor(
                Executors.newCachedThreadPool(),
                tasks,
                TimeUnit.SECONDS
        );

        long expected = threadsAmount * 6L;

        assertEquals(expected, additionFileOpen.getCount());
    }
}
