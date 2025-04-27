package org.virtual.thread.benchmark;

import io.github.cdimascio.dotenv.Dotenv;
import org.virtual.thread.benchmark.utilities.ConsoleUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class TestSuiteConfig {

    public static final String ROOT_PATH;
    public static final String DATA_PATH;
    public static final String SAVE_RESULTS_PATH;
    public static final int THREAD_AMOUNT_MACHINE = Runtime.getRuntime().availableProcessors();
    public static final String OS_MACHINE = System.getProperty("os.name").replace(" ", "-");
    public static final String PROCESSOR_FULL_NAME;

    // Needs to be manually updated for each different test system
    public static final String PROCESSOR_NAME;
    public static final String PROCESSOR_SPEED;

    static {
        // Load .env
        Dotenv dotenv = Dotenv.load();
        PROCESSOR_NAME = dotenv.get("PROCESSOR_NAME");
        PROCESSOR_SPEED = dotenv.get("PROCESSOR_SPEED");

        String absolutePath = System.getProperty("user.dir");

        // Replace path: user\java\data -> user/java/data
        ROOT_PATH = absolutePath.replace("\\", "/");

        PROCESSOR_FULL_NAME = PROCESSOR_NAME + "-" + PROCESSOR_SPEED;
        DATA_PATH = ROOT_PATH + "/data";
        SAVE_RESULTS_PATH = DATA_PATH + "/" + OS_MACHINE + "_" + PROCESSOR_FULL_NAME + "_" + "CPUs-" + THREAD_AMOUNT_MACHINE;

    }

    private TestSuiteConfig() {}

    public static String withDefaultPath(String path) {
        return SAVE_RESULTS_PATH + "/" + path;
    }

    public static void checkConfig() throws Exception {
        Objects.requireNonNull(TestSuiteConfig.PROCESSOR_SPEED, "Please provide PROCESSOR_SPEED in .env file");
        Objects.requireNonNull(TestSuiteConfig.PROCESSOR_NAME, "Please provide PROCESSOR_NAME in .env file");

        try {
            URL resource = ClassLoader.getSystemClassLoader().getResource("banner.txt");
            File file = new File(resource.getFile());
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line = reader.readLine();
            while (line != null) {
                System.out.println(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
            throw new Exception("banner.txt not found in resources folder or some error occurred reading the file");
        }

        // Create /data directory if does not exist
        Path dirDataPath = Path.of(DATA_PATH);
        if (!Files.exists(dirDataPath)) {
            Files.createDirectory(dirDataPath);
        }

        // Create /windwos-macos directory for saving empirical data specific to this machine if does not exist
        Path dirSaveResults = Path.of(SAVE_RESULTS_PATH);
        if (!Files.exists(dirSaveResults)) {
            Files.createDirectory(dirSaveResults);
        }

        System.out.println();
        System.out.println("-------------------------------------------------------");
        ConsoleUtils.printCyanTextNewLine("Test system information");
        System.out.println();

        printSystemInfoRow("Tests directory", TestSuiteConfig.DATA_PATH);
        printSystemInfoRow("Threads count", TestSuiteConfig.THREAD_AMOUNT_MACHINE+"");
        printSystemInfoRow("Processor name", TestSuiteConfig.PROCESSOR_FULL_NAME);
        printSystemInfoRow("Operating system", TestSuiteConfig.OS_MACHINE);

        System.out.println("-------------------------------------------------------");
        System.out.println();
    }

    private static void printSystemInfoRow(String key, String value) {
        System.out.print(key + ": ");
        ConsoleUtils.printGreenTextSameLine(value);
        System.out.println();
    }
}
