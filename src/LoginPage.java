import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginPage extends JPanel implements ActionListener {
    JTextField unameField;
    JPasswordField pwdField;
    JLabel statusLabel;
    JButton loginButton;
    CardLayout cardLayout;
    JPanel mainPanel;

    public LoginPage(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        setLayout(new BorderLayout());

        // Use default background from main panel
        setBackground(mainPanel.getBackground());

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBackground(mainPanel.getBackground());

        // Username
        JLabel unameLabel = new JLabel("Username:");
        unameField = new JTextField();
        formPanel.add(unameLabel);
        formPanel.add(unameField);

        // Password
        JLabel pwdLabel = new JLabel("Password:");
        pwdField = new JPasswordField();
        formPanel.add(pwdLabel);
        formPanel.add(pwdField);

        // Login button
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        formPanel.add(loginButton);

        // Status label
        statusLabel = new JLabel("", SwingConstants.CENTER);

        add(formPanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = unameField.getText();
        String password = new String(pwdField.getPassword());

        if (checkLogin(username, password)) {
            statusLabel.setText("Login Successful!");
            cardLayout.show(mainPanel, "home");
        } else {
            statusLabel.setText("Invalid credentials");
        }
    }

    private boolean checkLogin(String username, String password) {
        boolean isValid = false;
        try {
            String path = "./db/javaapp.db";
            Connection conn = DriverManager.getConnection("jdbc:sqlite:" + path);
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE uname = '" + username + "' AND pwd = '" + password + "'");

            if (rs.next()) isValid = true;

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return isValid;
    }
}
 