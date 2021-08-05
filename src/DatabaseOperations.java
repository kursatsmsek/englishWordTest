import java.sql.*;
import java.util.concurrent.atomic.AtomicReference;

public class DatabaseOperations {

    private static final String GET_ALL_WORD_COUNT = "SELECT COUNT(id) AS count FROM wordlist";
    private static final String GET_WORD = "SELECT COUNT(turkishMean) AS count FROM wronglist WHERE wronglist.turkishMean LIKE ?";
    private static final String GET_RANDOM_WORDS = "SELECT * FROM wordlist ORDER BY RANDOM() LIMIT ?";
    private static final String GET_LEARNED_WORDS = "SELECT * FROM wordlist ORDER BY id LIMIT ?";
    private static final String ADD_WRONG_LIST = "INSERT INTO wronglist (turkishMean, englishMeanFirst, englishMeanSecond, englishMeanThird) VALUES (?,?,?,?)";

    String databaseUrl = "jdbc:sqlite:C:/Users/Kürşat/Desktop/English Word Test/wordlist.db";
    Transactions transactions;
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    ResultSet emptyResultSet;

    public ResultSet getRandomWords(Integer number) {
        try {
            preparedStatement = connection.prepareStatement(GET_RANDOM_WORDS);
            preparedStatement.setInt(1, number);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException sqlException) {
            Log.error("Random words could not be created " + sqlException.getMessage());
            return emptyResultSet;
        }
    }

    public ResultSet getLearnedWords(Integer number) {
        connectDatabase();
        try {
            preparedStatement = connection.prepareStatement(GET_LEARNED_WORDS);
            preparedStatement.setInt(1, number);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException sqlException) {
            Log.error("Learned words could not be created " + sqlException.getMessage());
            return emptyResultSet;
        }
    }

    public void addToWrongList(AtomicReference<Word> word) {
        try {
            if (!(getWord(word))) {
                preparedStatement = connection.prepareStatement(ADD_WRONG_LIST);
                preparedStatement.setString(1, word.get().getTurkishMean());
                preparedStatement.setString(2, word.get().getEnglishMeanFirst());
                preparedStatement.setString(3, word.get().getEnglishMeanSecond() != null ? word.get().getEnglishMeanSecond() : null);
                preparedStatement.setString(4, word.get().getEnglishMeanThird() != null ? word.get().getEnglishMeanThird() : null);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException sqlException) {
            Log.error("Word: " + word.get().getTurkishMean() + "could not be add to wrong list" + sqlException.getMessage());
        }
    }

    public boolean getWord(AtomicReference<Word> word) {
        try {
            preparedStatement = connection.prepareStatement(GET_WORD);
            preparedStatement.setString(1, word.get().getTurkishMean());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.getInt(1) > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException sqlException) {
            Log.error("Word: " + word.get().getTurkishMean() + "could not be add to wrong list" + sqlException.getMessage());
            return true;
        }
    }

    public void connectDatabase() {
        try {
            connection = DriverManager.getConnection(databaseUrl);
        } catch (SQLException sqlException) {
            Log.error("Can not connect to database " + sqlException.getMessage());
        }
    }

    public void closeDatabase(Connection connection) {
        try {
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
