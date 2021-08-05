import javax.swing.*;
import java.util.concurrent.atomic.AtomicInteger;

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
            AtomicInteger number = new AtomicInteger(1);
            transactions.askQuestion("randomQuestion", number);
        });
        randomQuestion30.addActionListener(e -> {
            AtomicInteger number = new AtomicInteger(3);
            transactions.askQuestion("randomQuestion", number);
        });
        randomQuestion50.addActionListener(e -> {
            AtomicInteger number = new AtomicInteger(5);
            transactions.askQuestion("randomQuestion", number);
        });
        randomQuestion100.addActionListener(e -> {
            AtomicInteger number = new AtomicInteger(10);
            transactions.askQuestion("randomQuestion", number);
        });
        lastWrong10.addActionListener(e -> {
            AtomicInteger number = new AtomicInteger(1);
            transactions.askQuestion("lastWrong", number);
        });
        lastWrong30.addActionListener(e -> {
            AtomicInteger number = new AtomicInteger(3);
            transactions.askQuestion("lastWrong", number);
        });
        lastWrong50.addActionListener(e -> {
            AtomicInteger number = new AtomicInteger(5);
            transactions.askQuestion("lastWrong", number);
        });
        randomWrong.addActionListener(e -> {
            AtomicInteger number = new AtomicInteger(1);
            transactions.askQuestion("randomWrong", number);
        });
        randomWrong30.addActionListener(e -> {
            AtomicInteger number = new AtomicInteger(3);
            transactions.askQuestion("randomWrong", number);
        });
        randomWrong50.addActionListener(e -> {
            AtomicInteger number = new AtomicInteger(5);
            transactions.askQuestion("randomWrong", number);
        });
        lastLearned10.addActionListener(e -> {
            AtomicInteger number = new AtomicInteger(1);
            transactions.askQuestion("lastLearned", number);
        });
        lastLearned20.addActionListener(e -> {
            AtomicInteger number = new AtomicInteger(2);
            transactions.askQuestion("lastLearned", number);
        });
        lastLearned40.addActionListener(e -> {
            AtomicInteger number = new AtomicInteger(4);
            transactions.askQuestion("lastLearned", number);
        });
        addWord.addActionListener(e -> {
            processForm processForm = new processForm();
            processForm.setProcessTitle(transactions.changeLabelText(processForm.getProcessTitle(), "Kelime Ekleme"));
            processForm.setExplanation(transactions.changeLabelText(processForm.getExplanation(), "Lütfen kelimenin Türkçe anlamını ve diğer anlamlarını giriniz."));
            processForm.setStatementOne(transactions.changeLabelText(processForm.getStatementOne(), "Türkçe Anlamı"));
            processForm.setStatementTwo(transactions.changeLabelText(processForm.getStatementTwo(), "İngilizce 1. Anlamı"));
            processForm.setStatementThree(transactions.changeLabelText(processForm.getStatementThree(), "İngilizce 2. Anlamı"));
            processForm.setStatementFour(transactions.changeLabelText(processForm.getStatementFour(), "İngilizce 3. Anlamı"));
            processForm.setVisible(true);
        });
        resetWrongs.addActionListener(e -> {
            processForm processForm = new processForm();
            processForm.setProcessTitle(transactions.changeLabelText(processForm.getProcessTitle(), "Yanlışları Sıfırla"));
            processForm.setExplanation(transactions.changeLabelText(processForm.getExplanation(), "Yanlış bilinen kelimeler tablosu sıfırlanacaktır."));
            processForm.setStatementOne(transactions.changeLabelText(processForm.getStatementOne(), "\"reset\"  >"));
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
            processForm.setProcessTitle(transactions.changeLabelText(processForm.getProcessTitle(), "Kelime Silme"));
            processForm.setExplanation(transactions.changeLabelText(processForm.getExplanation(), "Silinecek kelimeyi aratın, sonuçlardan seçerek silin."));
            processForm.setStatementOne(transactions.changeLabelText(processForm.getStatementOne(), "Silinecek Kelime"));
            processForm.getStatementTwo().setVisible(false);
            processForm.getStatementThree().setVisible(false);
            processForm.getStatementFour().setVisible(false);
            processForm.getInputTwo().setVisible(false);
            processForm.getInputThree().setVisible(false);
            processForm.getInputFour().setVisible(false);
            processForm.setVisible(true);
        });
    }

}
