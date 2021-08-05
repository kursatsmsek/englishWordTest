import javax.swing.*;

public class processForm extends JFrame {
    private JPanel processForm;
    private JTextField inputOne;
    private JLabel explanation;
    private JLabel statementOne;
    private JLabel statementTwo;
    private JLabel statementThree;
    private JLabel statementFour;
    private JTextField inputTwo;
    private JTextField inputThree;
    private JTextField inputFour;
    private JButton okeyButton;
    private JLabel processTitle;
    private Transactions transactions = new Transactions();

    public JTextField getInputOne() {
        return inputOne;
    }

    public void setInputOne(JTextField inputOne) {
        this.inputOne = inputOne;
    }

    public JTextField getInputTwo() {
        return inputTwo;
    }

    public void setInputTwo(JTextField inputTwo) {
        this.inputTwo = inputTwo;
    }

    public JTextField getInputThree() {
        return inputThree;
    }

    public void setInputThree(JTextField inputThree) {
        this.inputThree = inputThree;
    }

    public JTextField getInputFour() {
        return inputFour;
    }

    public void setInputFour(JTextField inputFour) {
        this.inputFour = inputFour;
    }

    public JLabel getProcessTitle() {
        return processTitle;
    }

    public void setProcessTitle(JLabel processTitle) {
        this.processTitle = processTitle;
    }

    public JLabel getExplanation() {
        return explanation;
    }

    public void setExplanation(JLabel explanation) {
        this.explanation = explanation;
    }

    public JLabel getStatementOne() {
        return statementOne;
    }

    public void setStatementOne(JLabel statementOne) {
        this.statementOne = statementOne;
    }

    public JLabel getStatementTwo() {
        return statementTwo;
    }

    public void setStatementTwo(JLabel statementTwo) {
        this.statementTwo = statementTwo;
    }

    public JLabel getStatementThree() {
        return statementThree;
    }

    public void setStatementThree(JLabel statementThree) {
        this.statementThree = statementThree;
    }

    public JLabel getStatementFour() {
        return statementFour;
    }

    public void setStatementFour(JLabel statementFour) {
        this.statementFour = statementFour;
    }

    public processForm() {
        add(processForm);
        setBounds(460,200,500,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("İşlem Menüsü");
        okeyButton.addActionListener(e -> {
           if (getProcessTitle().getText().equals("Kelime Silme")) {
               deleteWord deleteWord = new deleteWord();
               deleteWord.setProcessTitle(transactions.changeLabelText(deleteWord.getProcessTitle(), "Silinecek Kelimeler"));
               deleteWord.setExplanation(transactions.changeLabelText(deleteWord.getExplanation(), "Silinecek kelimeleri seçiniz."));
               deleteWord.setVisible(true);
           }
        });
    }
}
