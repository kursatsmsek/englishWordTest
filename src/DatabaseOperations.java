import java.sql.*;
import java.util.concurrent.atomic.AtomicReference;

public class DatabaseOperations {

    private static final String GET_ALL_WORD_COUNT = "SELECT COUNT(id) AS count FROM wordlist";
    private static final String GET_WORD = "SELECT COUNT(turkishMean) AS count FROM wronglist WHERE turkishMean LIKE ?";
    private static final String GET_RANDOM_WORDS = "SELECT * FROM wordlist ORDER BY RANDOM() LIMIT ?";
    private static final String GET_LEARNED_WORDS = "SELECT * FROM wordlist ORDER BY id DESC LIMIT ?";
    private static final String ADD_WRONG_LIST = "INSERT INTO wronglist (turkishMean, englishMeanFirst, englishMeanSecond, englishMeanThird) VALUES (?,?,?,?)";
    private static final String GET_WRONG_WORDS = "SELECT * FROM wronglist ORDER BY id DESC LIMIT ?";
    private static final String GET_RANDOM_WRONG_WORDS = "SELECT * FROM wronglist ORDER BY RANDOM() LIMIT ?";
    private static final String ADD_WORD = "INSERT INTO wordlist (turkishMean, englishMeanFirst, englishMeanSecond, englishMeanThird) VALUES (?,?,?,?)";
    private static final String DELETE_WRONG_WORDS = "DELETE FROM wronglist";
    private static final String RESET_ID = "UPDATE SQLITE_SEQUENCE SET SEQ=0 WHERE NAME='wronglist'";
    private static final String DELETE_WORD = "DELETE FROM wordlist WHERE turkishMean = ?";
    private static final String FIND_WORD = "SELECT COUNT(turkishMean) AS count FROM wordlist WHERE turkishMean LIKE ?";

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

    public ResultSet getWrongWords(Integer number) {
        try {
            preparedStatement = connection.prepareStatement(GET_WRONG_WORDS);
            preparedStatement.setInt(1, number);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException sqlException) {
            Log.error("wrong words could not be fetched " + sqlException.getMessage());
            return emptyResultSet;
        }
    }

    public ResultSet getRandomWrongWords(Integer number) {
        try {
            preparedStatement = connection.prepareStatement(GET_RANDOM_WRONG_WORDS);
            preparedStatement.setInt(1, number);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException sqlException) {
            Log.error("random wrong words could not be fetched " + sqlException.getMessage());
            return emptyResultSet;
        }
    }

    public boolean addWord(AtomicReference<Word> word, String target) {
        try {
            if (!(getWord(word.get().getTurkishMean()))) {
                preparedStatement = connection.prepareStatement(target.equals("wordlist") ? ADD_WORD : ADD_WRONG_LIST);
                preparedStatement.setString(1, word.get().getTurkishMean());
                preparedStatement.setString(2, word.get().getEnglishMeanFirst());
                preparedStatement.setString(3, word.get().getEnglishMeanSecond() != null ? word.get().getEnglishMeanSecond() : null);
                preparedStatement.setString(4, word.get().getEnglishMeanThird() != null ? word.get().getEnglishMeanThird() : null);
                preparedStatement.executeUpdate();
            }
            return true;
        } catch (SQLException sqlException) {
            Log.error("Word: " + word.get().getTurkishMean() + "could not be add to " + target + sqlException.getMessage());
            return false;
        }
    }

    public boolean resetWrongList() {
        connectDatabase();
        try {
            preparedStatement = connection.prepareStatement(DELETE_WRONG_WORDS);
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(RESET_ID);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException sqlException) {
            Log.error("Wrong list table could not be reset " + sqlException.getMessage());
            return false;
        }
    }

    public boolean getWord(String turkishMean) {
        try {
            if (connection.isClosed())
                connectDatabase();
            preparedStatement = connection.prepareStatement(GET_WORD);
            preparedStatement.setString(1, turkishMean);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.getInt(1) > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException sqlException) {
            Log.error("Word: " + turkishMean + " could not be find" + sqlException.getMessage());
            return false;
        }
    }

    public boolean findWord(String turkishMean) {
        connectDatabase();
        try {
            preparedStatement = connection.prepareStatement(FIND_WORD);
            preparedStatement.setString(1, turkishMean);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.getInt(1) > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException sqlException) {
            Log.error("Word: " + turkishMean + " could not be find" + sqlException.getMessage());
            return false;
        } finally {
            closeDatabase(connection);
        }
    }

    public boolean removeWord(String turkishMean) {
        connectDatabase();
        try {
            preparedStatement = connection.prepareStatement(DELETE_WORD);
            preparedStatement.setString(1, turkishMean);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException sqlException) {
            Log.error("Word: " + turkishMean + " couldn not be delete" + sqlException.getMessage());
            return false;
        } finally {
            closeDatabase(connection);
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
            if (!(connection.isClosed()))
                connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
