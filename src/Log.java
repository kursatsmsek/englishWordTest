import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Log {
    private static File logFile = new File("./log.txt");
    private static FileWriter fileWriter;
    private static BufferedWriter bufferedWriter;
    private static final LocalDateTime date = LocalDateTime.now();
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    public static boolean error(String errorMessage) {
        try {
            fileWriter = new FileWriter(logFile, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("[ERROR] [" + dateFormat.format(date) + "] " + errorMessage + "\n");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean warning(String warningMessage) {
        try {
            fileWriter = new FileWriter(logFile, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("[WARNING] [" + dateFormat.format(date) + "] " + warningMessage + "\n");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean info(String infoMessage) {
        try {
            fileWriter = new FileWriter(logFile, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("[INFO] [" + dateFormat.format(date) + "] " + infoMessage + "\n");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}