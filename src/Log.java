import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
    private static File logFile;
    private static FileWriter fileWriter;
    private static BufferedWriter bufferedWriter;
    private static final LocalDateTime date = LocalDateTime.now();
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static boolean error(String errorMessage) {
        try {
            logFile = new File("log.txt");
            fileWriter = new FileWriter(logFile, false);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("[ERROR] [" + dateFormat.format(date) + "] " + errorMessage);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean warning(String warningMessage) {
        try {
            logFile = new File("log.txt");
            fileWriter = new FileWriter(logFile, false);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("[WARNING] [" + dateFormat.format(date) + "] " + warningMessage);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static boolean info(String infoMessage) {
        try {
            logFile = new File("log.txt");
            fileWriter = new FileWriter(logFile, false);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("[INFO] [" + dateFormat.format(date) + "] " + infoMessage);
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}