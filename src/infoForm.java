import javax.swing.*;

public class infoForm extends JFrame {
    private JPanel infoForm;
    private JLabel correctTitle;
    private JLabel wrongTitle;
    private JButton homePageButton;

    public JLabel getCorrectTitle() {
        return correctTitle;
    }

    public void setCorrectTitle(JLabel correctTitle) {
        this.correctTitle = correctTitle;
    }

    public JLabel getWrongTitle() {
        return wrongTitle;
    }

    public void setWrongTitle(JLabel wrongTitle) {
        this.wrongTitle = wrongTitle;
    }

    public JButton getHomePageButton() {
        return homePageButton;
    }

    public void setHomePageButton(JButton homePageButton) {
        this.homePageButton = homePageButton;
    }

    public infoForm() {
        add(infoForm);
        setBounds(560,310,230,250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Bilgi");
    }
}
