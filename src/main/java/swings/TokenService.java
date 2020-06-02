package swings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TokenService extends JFrame {
    private JPanel mainPanel;
    private JTextField accountNumber;
    private JLabel accountLabel;
    private JButton submitButton;
    private JLabel tokenNumber;
    private int currentToken;

    public TokenService(){
        super("Tokenizr");

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        submitButton.addActionListener(e -> {
            currentToken++;
            tokenNumber.setText("Your token No - " + tokenNumber);
        });
    }

    public static void main(String[] args) {
        JFrame jFrame = new TokenService();
        jFrame.setVisible(true);
    }
}
