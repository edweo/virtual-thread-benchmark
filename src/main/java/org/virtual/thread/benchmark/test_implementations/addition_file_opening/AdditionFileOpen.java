package org.virtual.thread.benchmark.test_implementations.addition_file_opening;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.net.URL;
import java.util.concurrent.atomic.AtomicLong;

public class AdditionFileOpen implements Runnable {
    private final AtomicLong count = new AtomicLong(0);

    @Override
    public void run() {
        long sum = 0;

        try {
            sum += openFileAndGetNumber("num1.txt");
            sum += openFileAndGetNumber("num2.txt");
            sum += openFileAndGetNumber("num3.txt");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        count.addAndGet(sum);
    }

    public long getCount() {
        return count.get();
    }

    private static long openFileAndGetNumber(String filename) throws Exception {
        URL resource = ClassLoader.getSystemClassLoader().getResource("numbers/" + filename);

        try {
            File file = new File(resource.getFile());
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            return Integer.parseInt(line);
        } catch (NullPointerException | FileNotFoundException e) {
            throw new FileNotFoundException("File not found: " + filename);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("First line is not a number in " + resource.getFile());
        }
    }
}
