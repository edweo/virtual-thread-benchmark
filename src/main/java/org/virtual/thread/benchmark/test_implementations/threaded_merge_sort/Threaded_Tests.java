package org.virtual.thread.benchmark.test_implementations.threaded_merge_sort;

import org.virtual.thread.benchmark.TestSuiteConfig;
import org.virtual.thread.benchmark.utilities.CSVManager;
import org.virtual.thread.benchmark.utilities.testing.test_suite_implementations.threads_test_suite_csv.ThreadsTestSuiteCSV;
import org.virtual.thread.benchmark.utilities.threads.ThreadFactory;
import org.virtual.thread.benchmark.utilities.threads.ThreadFactoryProvider;
import org.virtual.thread.benchmark.utilities.time_measurement.TimeMeasurement;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class Threaded_Tests {

    public static void run_virtualVSplatforming_test(List<int[]> preferableArraySizes, String fileNamePrefix) throws FileNotFoundException {
        String Test_Suit_Folder_Name = "merge_sort";
        String PathToDataSaver = TestSuiteConfig.SAVE_RESULTS_PATH + "/" + Test_Suit_Folder_Name; // Path where we will save data

        String[] headers = {"Array Size","Time (ms)"};

        List<String[]> data_platform = new ArrayList<>();
        List<String[]> data_virtual = new ArrayList<>();

        data_platform.add(headers);
        data_virtual.add(headers);

        for (int[] array : preferableArraySizes) {
            String[] dataRowVirtual = new String[2];
            String[] dataRowPlatform = new String[2];
            dataRowVirtual[0] = String.valueOf(array.length);
            dataRowPlatform[0] = String.valueOf(array.length);

            AtomicIntegerArray tempAtomicArray_Platform = new AtomicIntegerArray(array);
            AtomicIntegerArray tempAtomicArray_Virtual = new AtomicIntegerArray(array);

            double timeVirtual = measureTimeTest(tempAtomicArray_Virtual, ThreadFactoryProvider.virtualThreadFactory());
            dataRowVirtual[1] = String.valueOf(timeVirtual);

            double timePlatform = measureTimeTest(tempAtomicArray_Platform, ThreadFactoryProvider.platformThreadFactory());
            dataRowPlatform[1] = String.valueOf(timePlatform);

            data_virtual.add(dataRowVirtual);
            data_platform.add(dataRowPlatform);
        }

        String threadFactoryPathDir = PathToDataSaver + "/thread_factory";

        ThreadsTestSuiteCSV.checkAndCreateTestDirectory(Test_Suit_Folder_Name);
        ThreadsTestSuiteCSV.checkAndCreateTestDirectory(Test_Suit_Folder_Name + "/thread_factory");

        // Fake data to make it easier for python script
        ThreadsTestSuiteCSV.checkAndCreateTestDirectory(Test_Suit_Folder_Name + "/executor_service");
        List<String[]> fakeData = new ArrayList<>();
        fakeData.add(headers);
        fakeData.add(new String[] {0+"", 0+"" ,0+"", 0+""});
        saveData(fakeData, PathToDataSaver + "/executor_service", "FAKE_merge_sort_virtual.csv");
        saveData(fakeData, PathToDataSaver + "/executor_service", "FAKE_merge_sort_platform.csv");

        String fileNameVirtual = fileNamePrefix + "_merge_sort_virtual.csv";
        String fileNamePlatform = fileNamePrefix + "_merge_sort_platform.csv";

        saveData(data_platform, threadFactoryPathDir, fileNamePlatform);
        saveData(data_virtual, threadFactoryPathDir, fileNameVirtual);
    }

    private static double measureTimeTest(AtomicIntegerArray arr, ThreadFactory tf) {
        TimeMeasurement timer = new TimeMeasurement();
        timer.startTimer();
        Threaded_MergeSortTestSuite.MergeSortTest_WithoutPrintouts(arr, tf);
        timer.stopTimer();
        return timer.getResult();
    }

    private static void saveData(List<String[]> data, String threadFactoryPathDir, String fileName) {
        CSVManager csvManager;
        try {
            csvManager = new CSVManager(threadFactoryPathDir + "/" + fileName);
        } catch (Exception e) {
            System.out.println(e);
            return;
        }

        try {
            csvManager.writeToCSVFileStrings(data);
        } catch (FileNotFoundException e) {
            System.out.println(e);
            return;
        }
    }
}
