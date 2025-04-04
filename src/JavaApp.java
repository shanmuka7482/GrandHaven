import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

class SQLDB {
    public static Connection conn = null;
    public static Statement stmt = null;
    public static ResultSet rset = null;

    public static void connect(String dbpath) {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + dbpath);
            stmt = conn.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void execute(String query) {
        try {
            rset = stmt.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class Login extends WindowAdapter implements ActionListener {
    Frame f = new Frame("Login");
    Label l1 = new Label("User Name");
    Label l2 = new Label("Password");
    TextField t1 = new TextField();
    TextField t2 = new TextField();
    Button b1 = new Button("Login");
    Button b2 = new Button("Reset");
    Dialog d = new Dialog(f, "Login", true);

    Login() {
        f.setSize(300, 300);
        f.setLayout(new GridLayout(3, 2));
        f.add(l1);
        f.add(t1);
        f.add(l2);
        f.add(t2);
        f.add(b1);
        f.add(b2);
        b1.addActionListener(this);
        b2.addActionListener(this);
        f.addWindowListener(this);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("Login")) {
            SQLDB.connect("D:\\Btech\\Btech-3\\Sem-6\\MOBILE_APPLICATION_DEVELOPMENT_WITH_JAVA\\Desktop_dev\\sqlite-tools-win-x64-3490100\\javaapp.db");
            String q = "SELECT * FROM users WHERE uname='" + t1.getText() + "' AND pwd='" + t2.getText() + "';";
            SQLDB.execute(q);
            String msg;
            try {
                if (SQLDB.rset.next()) {
                    msg = "Login success...";
                } else {
                    msg = "Invalid user name or password";
                }
            } catch (Exception e1) {
                e1.printStackTrace();
                msg = "Error occurred";
            }
            d.removeAll();
            d.add(new Label(msg, Label.CENTER));
            d.setSize(300, 100);
            d.setLocationRelativeTo(f);
            d.setVisible(true);
        } else if (ae.getActionCommand().equals("Reset")) {
            t1.setText("");
            t2.setText("");
        }
    }

    public void windowClosing(WindowEvent we) {
        we.getWindow().dispose();
    }
}

public class JavaApp {
    public static void main(String[] args) {
        new Login();
    }
}