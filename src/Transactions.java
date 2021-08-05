import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class Transactions {
    DatabaseOperations databaseOperations = new DatabaseOperations();

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

    public boolean wordControl(Question question, AtomicReference<Word> word) {
        if (question.getInput().getText().equals(word.get().getEnglishMeanFirst()))
            return true;
        else if (question.getInput().getText().equals(word.get().getEnglishMeanSecond()))
            return true;
        else if (question.getInput().getText().equals(word.get().getEnglishMeanThird()))
            return true;
        else
            return false;
    }

    public void randomQuestion(AtomicInteger number) {
        AtomicReference<Word> word = new AtomicReference<>();
        AtomicInteger correctCounter = new AtomicInteger();
        AtomicInteger wrongCounter = new AtomicInteger();
        Question randomQuestion = new Question();

        randomQuestion.setVisible(true);
        randomQuestion.setRandomQuestionTitle(changeLabelText(randomQuestion.getRandomQuestionTitle(), "Rastgele " + number.get() + " Soru"));
        randomQuestion.setRemainderCounter(changeLabelText(randomQuestion.getRemainderCounter(), "Kalan: " + number.get()));

        databaseOperations.connectDatabase();
        ResultSet resultSet = databaseOperations.getRandomWords(number.get());

        try {
            resultSet.next();
            word.set(creatNewWord(resultSet));
            randomQuestion.setTurkishMean(changeLabelText(randomQuestion.getTurkishMean(), word.get().getTurkishMean()));

            randomQuestion.getOkeyButton().addActionListener(o -> {
                if (wordControl(randomQuestion, word)) {
                    correctCounter.addAndGet(1);
                    number.getAndDecrement();
                    randomQuestion.setCorrectCounter(changeLabelText(randomQuestion.getCorrectCounter(), "Doğru: " + correctCounter));
                    randomQuestion.setRemainderCounter(changeLabelText(randomQuestion.getRemainderCounter(), "Kalan: " + number.get()));
                } else {
                    wrongCounter.addAndGet(1);
                    number.getAndDecrement();
                    randomQuestion.setWrongCounter(changeLabelText(randomQuestion.getWrongCounter(), "Yanlış: " + wrongCounter));
                    randomQuestion.setRemainderCounter(changeLabelText(randomQuestion.getRemainderCounter(), "Kalan: " + number.get()));
                    databaseOperations.addToWrongList(word);
                }
                try {
                    resultSet.next();
                    word.set(creatNewWord(resultSet));
                    if (word.get().getTurkishMean().equals("boş")) {
                        randomQuestion.setTurkishMean(changeLabelText(randomQuestion.getTurkishMean(), "Sonuç"));
                        randomQuestion.getInput().setVisible(false);
                        randomQuestion.getOkeyButton().setVisible(false);
                        databaseOperations.connection.close();
                    } else {
                        randomQuestion.setTurkishMean(changeLabelText(randomQuestion.getTurkishMean(), word.get().getTurkishMean()));
                    }
                } catch (SQLException sqlException) {
                    Log.error("an error occurred in okey button " + sqlException.getMessage());
                }
            });
        } catch (SQLException sqlException) {
            Log.error("an error occured in question form " + sqlException.getMessage());
        }
    }

    public void askQuestion(String type, AtomicInteger number) {
        AtomicReference<Word> word = new AtomicReference<>();
        AtomicInteger correctCounter = new AtomicInteger();
        AtomicInteger wrongCounter = new AtomicInteger();
        Question randomQuestion = new Question();
        ResultSet resultSet;

        randomQuestion.setVisible(true);
        randomQuestion.setRemainderCounter(changeLabelText(randomQuestion.getRemainderCounter(), "Kalan: " + number.get()));

        databaseOperations.connectDatabase();

        if (type.equals("randomQuestion")) {
            randomQuestion.setRandomQuestionTitle(changeLabelText(randomQuestion.getRandomQuestionTitle(), "Rastgele " + number.get() + " Soru"));
            resultSet = databaseOperations.getRandomWords(number.get());
        }
        else if (type.equals("lastWrong")) {
            randomQuestion.setRandomQuestionTitle(changeLabelText(randomQuestion.getRandomQuestionTitle(), "Son " + number.get() + " Yanlış"));
            resultSet = databaseOperations.getWrongWords(number.get());
        }
        else if (type.equals("randomWrong")) {
            randomQuestion.setRandomQuestionTitle(changeLabelText(randomQuestion.getRandomQuestionTitle(), "Rastgele " + number.get() + " Yanlış"));
            resultSet = databaseOperations.getRandomWrongWords(number.get());
        }
        else if (type.equals("lastLearned")) {
            randomQuestion.setRandomQuestionTitle(changeLabelText(randomQuestion.getRandomQuestionTitle(), "Öğrenilen Son " + number.get()));
            resultSet = databaseOperations.getLearnedWords(number.get());
        } else {
            resultSet = null;
        }

        try {
            resultSet.next();
            word.set(creatNewWord(resultSet));
            randomQuestion.setTurkishMean(changeLabelText(randomQuestion.getTurkishMean(), word.get().getTurkishMean()));

            ResultSet finalResultSet = resultSet;
            randomQuestion.getOkeyButton().addActionListener(o -> {
                if (wordControl(randomQuestion, word)) {
                    correctCounter.addAndGet(1);
                    number.getAndDecrement();
                    randomQuestion.setCorrectCounter(changeLabelText(randomQuestion.getCorrectCounter(), "Doğru: " + correctCounter));
                    randomQuestion.setRemainderCounter(changeLabelText(randomQuestion.getRemainderCounter(), "Kalan: " + number.get()));
                } else {
                    wrongCounter.addAndGet(1);
                    number.getAndDecrement();
                    randomQuestion.setWrongCounter(changeLabelText(randomQuestion.getWrongCounter(), "Yanlış: " + wrongCounter));
                    randomQuestion.setRemainderCounter(changeLabelText(randomQuestion.getRemainderCounter(), "Kalan: " + number.get()));
                    if (type.equals("randomQuestion") && type.equals("lastLearned")) {
                        databaseOperations.addToWrongList(word);
                    }
                }
                try {
                    finalResultSet.next();
                    word.set(creatNewWord(finalResultSet));
                    if (word.get().getTurkishMean().equals("boş")) {
                        randomQuestion.setTurkishMean(changeLabelText(randomQuestion.getTurkishMean(), "Sonuç"));
                        randomQuestion.getInput().setVisible(false);
                        randomQuestion.getOkeyButton().setVisible(false);
                        databaseOperations.connection.close();
                    } else {
                        randomQuestion.setTurkishMean(changeLabelText(randomQuestion.getTurkishMean(), word.get().getTurkishMean()));
                    }
                } catch (SQLException sqlException) {
                    Log.error("an error occurred in okey button " + sqlException.getMessage());
                }
            });
        } catch (SQLException sqlException) {
            Log.error("an error occured in question form " + sqlException.getMessage());
        }
    }

    public static JLabel changeLabelText(JLabel jLabel, String newTitle) {
        jLabel.setText(newTitle);
        return jLabel;
    }
    public static JButton changeButtonText(JButton jButton, String newTitle) {
        jButton.setText(newTitle);
        return jButton;
    }
}
