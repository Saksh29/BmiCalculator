package Logger;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    private static final String LOG_FOLDER = "log/";

    public static void log(String message) {
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String logFile = LOG_FOLDER + "log_" + date + ".log";
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}
