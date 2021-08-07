import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
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
                    if (type.equals("randomQuestion") || type.equals("lastLearned")) {
                        databaseOperations.addWord(word, "wronglist");
                    }
                }
                try {
                    finalResultSet.next();
                    word.set(creatNewWord(finalResultSet));
                    if (word.get().getTurkishMean().equals("boş")) {
                        randomQuestion.setTurkishMean(changeLabelText(randomQuestion.getTurkishMean(), "Sonuç"));
                        randomQuestion.getInput().setVisible(false);
                        randomQuestion.getOkeyButton().setVisible(false);
                        databaseOperations.closeDatabase(databaseOperations.connection);
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

    public void addWordToList() {
        AtomicReference<Word> word = new AtomicReference<>();
        processForm processForm = new processForm();
        processForm.setVisible(true);
        
        processForm.setProcessTitle(changeLabelText(processForm.getProcessTitle(), "Kelime Ekleme"));
        processForm.setExplanation(changeLabelText(processForm.getExplanation(), "Lütfen kelimenin Türkçe anlamını ve diğer anlamlarını giriniz."));
        processForm.setStatementOne(changeLabelText(processForm.getStatementOne(), "Türkçe Anlamı"));
        processForm.setStatementTwo(changeLabelText(processForm.getStatementTwo(), "İngilizce 1. Anlamı"));
        processForm.setStatementThree(changeLabelText(processForm.getStatementThree(), "İngilizce 2. Anlamı"));
        processForm.setStatementFour(changeLabelText(processForm.getStatementFour(), "İngilizce 3. Anlamı"));

        processForm.getOkeyButton().addActionListener(a -> {
            databaseOperations.connectDatabase();
            if (!(processForm.getInputOne().getText().equals("")) && processForm.getInputTwo().getText().equals(""))
                processForm.setInformationText(changeLabelText(processForm.getInformationText(), "İngilizce 1. anlamı giriniz."));
            else if (processForm.getInputOne().getText().equals("")) {
                processForm.setInformationText(changeLabelText(processForm.getInformationText(), "Kelime giriniz."));
            }
            else {
                word.set(new Word(processForm.getInputOne().getText(), processForm.getInputTwo().getText(), processForm.getInputThree().getText(), processForm.getInputFour().getText()));
                if (databaseOperations.addWord(word, "wordlist")) {
                    processForm.setInformationText(changeLabelText(processForm.getInformationText(), "'" + word.get().getTurkishMean() + "' başarıyla eklendi."));
                } else
                    processForm.setInformationText(changeLabelText(processForm.getInformationText(), "Bir hata oluştu, log dosyasını kontrol ediniz."));
            }
            databaseOperations.closeDatabase(databaseOperations.connection);
        });
    }

    public void resetTable() {
        processForm processForm = new processForm();
        processForm.setVisible(true);
        processForm.setProcessTitle(changeLabelText(processForm.getProcessTitle(), "Yanlışları Sıfırla"));
        processForm.setExplanation(changeLabelText(processForm.getExplanation(), "Yanlış bilinen kelimeler tablosu sıfırlanacaktır."));
        processForm.setStatementOne(changeLabelText(processForm.getStatementOne(), "\"reset\"  >"));
        processForm.getStatementTwo().setVisible(false);
        processForm.getStatementThree().setVisible(false);
        processForm.getStatementFour().setVisible(false);
        processForm.getInputTwo().setVisible(false);
        processForm.getInputThree().setVisible(false);
        processForm.getInputFour().setVisible(false);

        processForm.getOkeyButton().addActionListener(e -> {
            databaseOperations.connectDatabase();
            if (processForm.getInputOne().getText().equals("reset")) {
                databaseOperations.resetWrongList();
                processForm.setInformationText(changeLabelText(processForm.getInformationText(), "Yanlışlar başarıyla sıfırlandı."));
            } else {
                processForm.setInformationText(changeLabelText(processForm.getInformationText(), "Bir hata oluştu, log dosyasını kontrol ediniz."));
            }
            databaseOperations.closeDatabase(databaseOperations.connection);
        });
    }

    public void deleteWord() {
        processForm processForm = new processForm();
        processForm.setVisible(true);
        processForm.setProcessTitle(changeLabelText(processForm.getProcessTitle(), "Kelime Silme"));
        processForm.setExplanation(changeLabelText(processForm.getExplanation(), "Silinecek kelimeyi aratın, sonuçlardan seçerek silin."));
        processForm.setStatementOne(changeLabelText(processForm.getStatementOne(), "Silinecek Kelime"));
        processForm.getStatementTwo().setVisible(false);
        processForm.getStatementThree().setVisible(false);
        processForm.getStatementFour().setVisible(false);
        processForm.getInputTwo().setVisible(false);
        processForm.getInputThree().setVisible(false);
        processForm.getInputFour().setVisible(false);

        processForm.getOkeyButton().addActionListener(d -> {
            databaseOperations.connectDatabase();
            String turkishMean = processForm.getInputOne().getText();
            if (databaseOperations.findWord(turkishMean)) {
                if (databaseOperations.removeWord(turkishMean)) {
                    processForm.setInformationText(changeLabelText(processForm.getInformationText(), turkishMean + " başarıyla silindi."));
                } else {
                    processForm.setInformationText(changeLabelText(processForm.getInformationText(), "Bir hata oluştu, log dosyasını kontrol ediniz."));
                }
            } else {
                processForm.setInformationText(changeLabelText(processForm.getInformationText(), "Kelime: " + turkishMean + " bulunamadı."));
            }
            databaseOperations.closeDatabase(databaseOperations.connection);
        });
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
