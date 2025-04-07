// HomePage.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomePage extends JPanel {

    public HomePage(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Home Page", JLabel.CENTER);
        JButton goToLogin = new JButton("Go to Login");

        goToLogin.addActionListener(e -> cardLayout.show(mainPanel, "login"));

        add(label, BorderLayout.CENTER);
        add(goToLogin, BorderLayout.SOUTH);
    }
}
