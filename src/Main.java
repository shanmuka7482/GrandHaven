import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame {
    CardLayout cardLayout;
    JPanel mainPanel;

    public Main() {
        setTitle("PropertyPro");
        setSize(800, 550);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Card layout for the main panel
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // mainPanel.setBackground(new Color(230, 230, 255));

        // Pages
        JPanel getStarted = new GetStarted(cardLayout, mainPanel);
        JPanel homePage = new HomePage(cardLayout, mainPanel);
        LoginPage loginPage = new LoginPage(cardLayout, mainPanel);
        LoginTypePage loginTypePage = new LoginTypePage(cardLayout, mainPanel);

        // Add pages to main panel
        mainPanel.add(getStarted, "hero");
        mainPanel.add(loginTypePage, "loginType");
        mainPanel.add(loginPage, "login");
        mainPanel.add(homePage, "home");

        add(mainPanel, BorderLayout.CENTER);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                dispose();
            }
        });
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public static void main(String[] args) {
        new Main();
    }
}
