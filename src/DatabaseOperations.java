import java.sql.*;

public class DatabaseOperations {

    private static final String GET_ALL_WORD_COUNT = "SELECT COUNT(id) AS count FROM wordlist";
    private static final String GET_WORD = "SELECT * FROM wordlist WHERE id = ?";
    private static final String GET_RANDOM_WORDS = "SELECT * FROM wordlist ORDER BY RANDOM() LIMIT ?";
    private static final String GET_LEARNED_WORDS = "SELECT * FROM wordlist ORDER BY id LIMIT ?";

    String databaseUrl = "jdbc:sqlite:C:/Users/Kürşat/Desktop/English Word Test/wordlist.db";
    Transactions transactions;
    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    ResultSet emptyResultSet;

    public ResultSet getRandomWords(Integer number) {
        connectDatabase();
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

    public void connectDatabase() {
        try {
            connection = DriverManager.getConnection(databaseUrl);
        } catch (SQLException sqlException) {
            Log.error("Can not connect to database " + sqlException.getMessage());
        }
    }
}
