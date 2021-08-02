import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class Transactions {
    public ArrayList produceRandomList(Integer number, Integer total) {
        ArrayList randomNumberList = new ArrayList();
        Random random = new Random();
        while (randomNumberList.size() < number + 1) {
            Integer randomNumber = random.nextInt(total);
            if (!randomNumberList.contains(randomNumber)) {
                randomNumberList.add(randomNumber);
            }
        }
        return randomNumberList;
    }

    public Word creatNewWord(ResultSet resultSet) {
        try {
            Word word;
            if (resultSet.getString("englishMeanSecond") == null)
                word = new Word(resultSet.getString("turkishMean"), resultSet.getString("englishMeanFirst"));
            else if (!(resultSet.getString("englishMeanThird") == null))
                word = new Word(resultSet.getString("turkishMean"),
                        resultSet.getString("englishMeanFirst"),
                        resultSet.getString("englishMeanSecond"),
                        resultSet.getString("englishMeanThird"));
            else
                word = new Word(resultSet.getString("turkishMean"),
                        resultSet.getString("englishMeanFirst"),
                        resultSet.getString("englishMeanSecond"));
            return word;
        } catch (SQLException sqlException) {
            Log.error("Word could not be create " + sqlException.getMessage());
            Word emptyWord = new Word("boş", "boş");
            return emptyWord;
        }
    }

}
