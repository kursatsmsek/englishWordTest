import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;

public class AppInterface extends JFrame {
    private DatabaseOperations databaseOperations = new DatabaseOperations();
    private Transactions transactions = new Transactions();
    private JButton randomWrong;
    private JButton lastLearned10;
    private JButton randomQuestion;
    private JButton randomQuestion30;
    private JButton lastWrong30;
    private JButton randomWrong30;
    private JButton lastLearned20;
    private JButton randomQuestion50;
    private JButton lastWrong50;
    private JButton randomWrong50;
    private JButton lastLearned40;
    private JButton randomQuestion100;
    private JButton addWord;
    private JButton deleteWord;
    private JButton resetWrongs;
    private JPanel homePage;
    private JButton lastWrong10;
    private JLabel upTitle;

    public AppInterface() {
        add(homePage);
        setBounds(330,200,700,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("İngilizce Kelime Testi");
        randomQuestion.addActionListener(e -> {
            Question randomQuestion = new Question();
            randomQuestion.setRandomQuestionTitle(changeLabelText(randomQuestion.getRandomQuestionTitle(), "Rastgele Soru"));
            randomQuestion.setRemainderCounter(changeLabelText(randomQuestion.getRemainderCounter(), ""));
            randomQuestion.setCorrectCounter(changeLabelText(randomQuestion.getCorrectCounter(), ""));
            randomQuestion.setWrongCounter(changeLabelText(randomQuestion.getWrongCounter(), ""));
            randomQuestion.setOkeyButton(changeButtonText(randomQuestion.getOkeyButton(), "Kontrol Et"));
            randomQuestion.setVisible(true);
        });
        randomQuestion30.addActionListener(e -> {
            Question randomQuestion = new Question();
            randomQuestion.setRandomQuestionTitle(changeLabelText(randomQuestion.getRandomQuestionTitle(), "Rastgele 30 Soru"));
            randomQuestion.setVisible(true);
        });
        randomQuestion50.addActionListener(e -> {
            Question randomQuestion = new Question();
            randomQuestion.setRandomQuestionTitle(changeLabelText(randomQuestion.getRandomQuestionTitle(), "Rastgele 50 Soru"));
            randomQuestion.setVisible(true);
        });
        randomQuestion100.addActionListener(e -> {
            Question randomQuestion = new Question();
            randomQuestion.setRandomQuestionTitle(changeLabelText(randomQuestion.getRandomQuestionTitle(), "Rastgele 100 Soru"));
            randomQuestion.setVisible(true);
        });
        lastWrong10.addActionListener(e -> {
            Question randomQuestion = new Question();
            randomQuestion.setRandomQuestionTitle(changeLabelText(randomQuestion.getRandomQuestionTitle(), "Son 10 Yanlış"));
            randomQuestion.setVisible(true);
        });
        lastWrong30.addActionListener(e -> {
            Question randomQuestion = new Question();
            randomQuestion.setRandomQuestionTitle(changeLabelText(randomQuestion.getRandomQuestionTitle(), "Son 30 Yanlış"));
            randomQuestion.setVisible(true);
        });
        lastWrong50.addActionListener(e -> {
            Question randomQuestion = new Question();
            randomQuestion.setRandomQuestionTitle(changeLabelText(randomQuestion.getRandomQuestionTitle(), "Son 50 Yanlış"));
            randomQuestion.setVisible(true);
        });
        randomWrong.addActionListener(e -> {
            Question randomQuestion = new Question();
            randomQuestion.setRandomQuestionTitle(changeLabelText(randomQuestion.getRandomQuestionTitle(), "Rastgele Yanlış"));
            randomQuestion.setRemainderCounter(changeLabelText(randomQuestion.getRemainderCounter(), ""));
            randomQuestion.setCorrectCounter(changeLabelText(randomQuestion.getCorrectCounter(), ""));
            randomQuestion.setWrongCounter(changeLabelText(randomQuestion.getWrongCounter(), ""));
            randomQuestion.setOkeyButton(changeButtonText(randomQuestion.getOkeyButton(), "Kontrol Et"));
            randomQuestion.setVisible(true);
        });
        randomWrong30.addActionListener(e -> {
            Question randomQuestion = new Question();
            randomQuestion.setRandomQuestionTitle(changeLabelText(randomQuestion.getRandomQuestionTitle(), "Rastgele 30 Yanlış"));
            randomQuestion.setVisible(true);
        });
        randomWrong50.addActionListener(e -> {
            Question randomQuestion = new Question();
            randomQuestion.setRandomQuestionTitle(changeLabelText(randomQuestion.getRandomQuestionTitle(), "Rastgele 50 Yanlış"));
            randomQuestion.setVisible(true);
        });
        lastLearned10.addActionListener(e -> {
            Question randomQuestion = new Question();
            randomQuestion.setRandomQuestionTitle(changeLabelText(randomQuestion.getRandomQuestionTitle(), "Öğrenilen Son 10"));
            randomQuestion.setVisible(true);
        });
        lastLearned20.addActionListener(e -> {
            Question randomQuestion = new Question();
            randomQuestion.setRandomQuestionTitle(changeLabelText(randomQuestion.getRandomQuestionTitle(), "Öğrenilen Son 20"));
            randomQuestion.setVisible(true);
        });
        lastLearned40.addActionListener(e -> {
            Question randomQuestion = new Question();
            randomQuestion.setRandomQuestionTitle(changeLabelText(randomQuestion.getRandomQuestionTitle(), "Öğrenilen Son 40"));
            randomQuestion.setVisible(true);
        });
        addWord.addActionListener(e -> {
            processForm processForm = new processForm();
            processForm.setProcessTitle(changeLabelText(processForm.getProcessTitle(), "Kelime Ekleme"));
            processForm.setExplanation(changeLabelText(processForm.getExplanation(), "Lütfen kelimenin Türkçe anlamını ve diğer anlamlarını giriniz."));
            processForm.setStatementOne(changeLabelText(processForm.getStatementOne(), "Türkçe Anlamı"));
            processForm.setStatementTwo(changeLabelText(processForm.getStatementTwo(), "İngilizce 1. Anlamı"));
            processForm.setStatementThree(changeLabelText(processForm.getStatementThree(), "İngilizce 2. Anlamı"));
            processForm.setStatementFour(changeLabelText(processForm.getStatementFour(), "İngilizce 3. Anlamı"));
            processForm.setVisible(true);
        });
        resetWrongs.addActionListener(e -> {
            processForm processForm = new processForm();
            processForm.setProcessTitle(changeLabelText(processForm.getProcessTitle(), "Yanlışları Sıfırla"));
            processForm.setExplanation(changeLabelText(processForm.getExplanation(), "Yanlış bilinen kelimeler tablosu sıfırlanacaktır."));
            processForm.setStatementOne(changeLabelText(processForm.getStatementOne(), "\"reset\"  >"));
            processForm.getStatementTwo().setVisible(false);
            processForm.getStatementThree().setVisible(false);
            processForm.getStatementFour().setVisible(false);
            processForm.getInputTwo().setVisible(false);
            processForm.getInputThree().setVisible(false);
            processForm.getInputFour().setVisible(false);
            processForm.setVisible(true);
        });
        deleteWord.addActionListener(e -> {
            processForm processForm = new processForm();
            processForm.setProcessTitle(changeLabelText(processForm.getProcessTitle(), "Kelime Silme"));
            processForm.setExplanation(changeLabelText(processForm.getExplanation(), "Silinecek kelimeyi aratın, sonuçlardan seçerek silin."));
            processForm.setStatementOne(changeLabelText(processForm.getStatementOne(), "Silinecek Kelime"));
            processForm.getStatementTwo().setVisible(false);
            processForm.getStatementThree().setVisible(false);
            processForm.getStatementFour().setVisible(false);
            processForm.getInputTwo().setVisible(false);
            processForm.getInputThree().setVisible(false);
            processForm.getInputFour().setVisible(false);
            processForm.setVisible(true);
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
