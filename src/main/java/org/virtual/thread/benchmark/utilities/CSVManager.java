package org.virtual.thread.benchmark.utilities;

import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CSVManager {

    private static final Logger LOGGER = Logger.getLogger(CSVManager.class.getName());
    private File file;
    private PrintWriter pw;

    /**
     * Creates a CSV manager to write data to a CSV file
     * @param fileName file name without the '.csv' extension. MyFile -> MyFIle.csv
     */
    public CSVManager(String fileName) throws IOException {
        this.file = new File(fileName);
        if (!this.file.exists()) {
            this.createCSVFile(fileName);
        }
    }

    public void createCSVFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            boolean created = file.createNewFile();
            if (created) {
//                System.out.println("Successfully created file: " + fileName);
            } else {
                LOGGER.log(Level.INFO, "Failed creating new file...");
            }
        } else {
            LOGGER.log(Level.WARNING, String.format("Error creating new file. File with the name '%s' already exists.", file.getName()));
        }
    }

    public void writeToCSVFileStrings(List<String[]> data) throws FileNotFoundException {
        if (!file.exists()) {
            LOGGER.log(Level.WARNING, String.format("File with name '%s was not found.'", file.getName()));
        } else {
//            System.out.println(file.getAbsolutePath());
            this.pw = new PrintWriter(file);
            data.forEach(this::write);
            pw.close();
        }
    }

    private void write(String[] line) {
        if (this.pw != null) {
            String dataLine = String.join(",", line);
            pw.write(dataLine);
            pw.write("\n");
        }
    }
}
