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
    private ImageIcon imageIcon = new ImageIcon("./icon.png");

    public AppInterface() {
        add(homePage);
        setBounds(330,200,700,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("English Word Test");
        setIconImage(imageIcon.getImage());

        databaseOperations.connectDatabase();

        randomQuestion.addActionListener(e -> {
            AtomicInteger number = new AtomicInteger(1);
            transactions.askQuestion("randomQuestion", number);
        });
        randomQuestion30.addActionListener(e -> {
            AtomicInteger number = new AtomicInteger(30);
            transactions.askQuestion("randomQuestion", number);
        });
        randomQuestion50.addActionListener(e -> {
            AtomicInteger number = new AtomicInteger(50);
            transactions.askQuestion("randomQuestion", number);
        });
        randomQuestion100.addActionListener(e -> {
            AtomicInteger number = new AtomicInteger(100);
            transactions.askQuestion("randomQuestion", number);
        });
        lastWrong10.addActionListener(e -> {
            AtomicInteger number = new AtomicInteger(10);
            transactions.askQuestion("lastWrong", number);
        });
        lastWrong30.addActionListener(e -> {
            AtomicInteger number = new AtomicInteger(30);
            transactions.askQuestion("lastWrong", number);
        });
        lastWrong50.addActionListener(e -> {
            AtomicInteger number = new AtomicInteger(50);
            transactions.askQuestion("lastWrong", number);
        });
        randomWrong.addActionListener(e -> {
            AtomicInteger number = new AtomicInteger(1);
            transactions.askQuestion("randomWrong", number);
        });
        randomWrong30.addActionListener(e -> {
            AtomicInteger number = new AtomicInteger(30);
            transactions.askQuestion("randomWrong", number);
        });
        randomWrong50.addActionListener(e -> {
            AtomicInteger number = new AtomicInteger(50);
            transactions.askQuestion("randomWrong", number);
        });
        lastLearned10.addActionListener(e -> {
            AtomicInteger number = new AtomicInteger(10);
            transactions.askQuestion("lastLearned", number);
        });
        lastLearned20.addActionListener(e -> {
            AtomicInteger number = new AtomicInteger(20);
            transactions.askQuestion("lastLearned", number);
        });
        lastLearned40.addActionListener(e -> {
            AtomicInteger number = new AtomicInteger(40);
            transactions.askQuestion("lastLearned", number);
        });
        addWord.addActionListener(e -> {
            transactions.addWordToList();
        });
        resetWrongs.addActionListener(e -> {
            transactions.resetTable();
        });
        deleteWord.addActionListener(e -> {
            transactions.deleteWord();
        });
    }
}
