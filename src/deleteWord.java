import javax.swing.*;

public class deleteWord extends JFrame{
    private JPanel deleteWord;
    private JLabel explanation;
    private JLabel processTitle;
    private JCheckBox erasableWordOne;
    private JButton okeyButton;
    private JCheckBox erasableWordTwo;
    private JCheckBox erasableWordThree;
    private JCheckBox erasableWordFour;
    private JCheckBox erasableWordFive;

    public JLabel getExplanation() {
        return explanation;
    }

    public void setExplanation(JLabel explanation) {
        this.explanation = explanation;
    }

    public JLabel getProcessTitle() {
        return processTitle;
    }

    public void setProcessTitle(JLabel processTitle) {
        this.processTitle = processTitle;
    }

    public JCheckBox getErasableWordOne() {
        return erasableWordOne;
    }

    public void setErasableWordOne(JCheckBox erasableWordOne) {
        this.erasableWordOne = erasableWordOne;
    }

    public JButton getOkeyButton() {
        return okeyButton;
    }

    public void setOkeyButton(JButton okeyButton) {
        this.okeyButton = okeyButton;
    }

    public JCheckBox getErasableWordTwo() {
        return erasableWordTwo;
    }

    public void setErasableWordTwo(JCheckBox erasableWordTwo) {
        this.erasableWordTwo = erasableWordTwo;
    }

    public JCheckBox getErasableWordThree() {
        return erasableWordThree;
    }

    public void setErasableWordThree(JCheckBox erasableWordThree) {
        this.erasableWordThree = erasableWordThree;
    }

    public JCheckBox getErasableWordFour() {
        return erasableWordFour;
    }

    public void setErasableWordFour(JCheckBox erasableWordFour) {
        this.erasableWordFour = erasableWordFour;
    }

    public JCheckBox getErasableWordFive() {
        return erasableWordFive;
    }

    public void setErasableWordFive(JCheckBox erasableWordFive) {
        this.erasableWordFive = erasableWordFive;
    }

    public deleteWord() {
        add(deleteWord);
        setBounds(460,200,500,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Silinecek Kelimler");
    }
}
