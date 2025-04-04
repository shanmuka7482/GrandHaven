import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SQLiteLoginApp extends Frame implements ActionListener {
    TextField unameField, pwdField;
    Label statusLabel;
    Button loginButton;
    
    public SQLiteLoginApp() {
        // Create window
        setTitle("Login");
        setSize(300, 200);
        setLayout(new GridLayout(4, 2));

        // Username field
        add(new Label("Username:"));
        unameField = new TextField();
        add(unameField);

        // Password field
        add(new Label("Password:"));
        pwdField = new TextField();
        add(pwdField);

        // Login button
        loginButton = new Button("Login");
        loginButton.addActionListener(this);
        add(loginButton);

        // Status label
        statusLabel = new Label("");
        add(statusLabel);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = unameField.getText();
        String password = pwdField.getText();

        if (checkLogin(username, password)) {
            statusLabel.setText("Login Successful!");
            openSuccessWindow();
        } else {
            statusLabel.setText("Invalid credentials");
        }
    }

    // Database connection and login validation
    private boolean checkLogin(String username, String password) {
	boolean isValid = false; 
        try {
            String path = "D:\\Btech\\Btech-3\\Sem-6\\MOBILE_APPLICATION_DEVELOPMENT_WITH_JAVA\\Desktop_dev\\app_v1\\db\\javaapp.db"; // Update with your database path
            Connection conn = DriverManager.getConnection("jdbc:sqlite:" + path);
            
            
            Statement stmt = conn.createStatement();  
            ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE uname = '" + username + "' AND pwd = '" + password + "'");  

            if (rs.next()) {  
                isValid = true;  
            }  

            rs.close();
            stmt.close(); 

        } catch (SQLException ex) {
            ex.printStackTrace();
            
        }
	return isValid;
    }

    // New Window for Successful Login
    private void openSuccessWindow() {
        Frame successFrame = new Frame("Success");
        successFrame.setSize(200, 100);
        successFrame.setLayout(new FlowLayout());
        successFrame.add(new Label("Login Successful!"));
        
        successFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                successFrame.dispose();
            }
        });

        successFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new SQLiteLoginApp();
    }
}
