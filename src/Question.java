import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Question extends JFrame {
    private JPanel QuestionForm;
    private JLabel turkishMean;
    private JLabel randomQuestionTitle;
    private JTextField input;
    private JButton okeyButton;
    private JLabel remainderCounter;
    private JLabel correctCounter;
    private JLabel wrongCounter;

    public JLabel getRemainderCounter() {
        return remainderCounter;
    }

    public JLabel getCorrectCounter() {
        return correctCounter;
    }

    public void setCorrectCounter(JLabel correctCounter) {
        this.correctCounter = correctCounter;
    }

    public JLabel getWrongCounter() {
        return wrongCounter;
    }

    public void setWrongCounter(JLabel wrongCounter) {
        this.wrongCounter = wrongCounter;
    }

    public void setRemainderCounter(JLabel remainderCounter) {
        this.remainderCounter = remainderCounter;
    }

    public JLabel getTurkishMean() {
        return turkishMean;
    }

    public void setTurkishMean(JLabel turkishMean) {
        this.turkishMean = turkishMean;
    }

    public JLabel getRandomQuestionTitle() {
        return randomQuestionTitle;
    }

    public void setRandomQuestionTitle(JLabel randomQuestionTitle) {
        this.randomQuestionTitle = randomQuestionTitle;
    }

    public JTextField getInput() {
        return input;
    }

    public void setInput(JTextField input) {
        this.input = input;
    }

    public JButton getOkeyButton() {
        return okeyButton;
    }

    public void setOkeyButton(JButton okeyButton) {
        this.okeyButton = okeyButton;
    }

    public Question() {
        add(QuestionForm);
        setBounds(530,200,300,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Rastgele Soru");
        okeyButton.addActionListener(e -> {
            if (getOkeyButton().getText().equals("Kontrol Et")) {
                infoForm infoForm = new infoForm();
                infoForm.setVisible(true);
            }
        });
    }
}
