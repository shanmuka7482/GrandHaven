import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginTypePage extends JPanel {

    public LoginTypePage(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new BorderLayout());
        setBackground(new Color(245, 245, 245));

        JLabel title = new JLabel("Choose Login Type", JLabel.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setBorder(BorderFactory.createEmptyBorder(40, 0, 30, 0));
        add(title, BorderLayout.NORTH);

        // Create main box panel
        JPanel boxPanel = new JPanel();
        boxPanel.setLayout(new BoxLayout(boxPanel, BoxLayout.Y_AXIS));
        boxPanel.setOpaque(false);

        boxPanel.add(createLoginOption("Customer Login", "Browse and book properties", new Color(52, 108, 255), "👤", cardLayout, mainPanel, "customerLogin"));
        boxPanel.add(Box.createVerticalStrut(40));
        boxPanel.add(createLoginOption("Owner Login", "Manage your property listings", new Color(0, 170, 90), "🏠", cardLayout, mainPanel, "ownerLogin"));

        // Add Guest link
        JLabel guest = new JLabel("<HTML><U>Continue as Guest</U></HTML>", JLabel.CENTER);
        guest.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        guest.setForeground(Color.DARK_GRAY);
        guest.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        guest.setBorder(BorderFactory.createEmptyBorder(30, 0, 20, 0));

        guest.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(mainPanel, "guest");
            }
        });

        add(boxPanel, BorderLayout.CENTER);
        add(guest, BorderLayout.SOUTH);
    }

    private JPanel createLoginOption(String title, String subtitle, Color iconColor, String iconText,
                                     CardLayout cardLayout, JPanel mainPanel, String nextPage) {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(600, 100));
        panel.setMaximumSize(new Dimension(700, 100));
        panel.setBackground(Color.WHITE);
        // panel.setBorder(BorderFactory.createCompoundBorder(
        //         BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
        //         BorderFactory.createEmptyBorder(20, 20, 20, 20)
        // ));
        panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Rounded border
        panel.setOpaque(false);
        JPanel roundedPanel = new RoundedPanel(20, Color.WHITE,1);
        roundedPanel.setLayout(new BorderLayout());
        roundedPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Create a rounded icon panel
        JPanel iconWrapper = new RoundedPanel(100, new Color(iconColor.getRed(), iconColor.getGreen(), iconColor.getBlue(), 30),0);
        iconWrapper.setPreferredSize(new Dimension(60, 50));
        iconWrapper.setLayout(new GridBagLayout()); // Center the icon

        // Icon
        JLabel icon = new JLabel(iconText, JLabel.CENTER);
        icon.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 28));
        icon.setForeground(iconColor);
        icon.setPreferredSize(new Dimension(60, 50));
        icon.setHorizontalAlignment(JLabel.CENTER);
        icon.setVerticalAlignment(JLabel.CENTER);
        icon.setMaximumSize(new Dimension(60, 50));

        iconWrapper.add(icon); // Add icon inside the rounded panel

        JPanel textPanel = new JPanel(new GridLayout(2, 1,0,1));
        textPanel.setOpaque(false);

        JLabel titleLabel = new JLabel(title, JLabel.LEFT);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JLabel subtitleLabel = new JLabel(subtitle, JLabel.LEFT);
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setForeground(Color.GRAY);

        textPanel.add(titleLabel);
        textPanel.add(subtitleLabel);


        // Assemble
        roundedPanel.add(iconWrapper, BorderLayout.WEST);
        roundedPanel.add(Box.createHorizontalStrut(40), BorderLayout.CENTER);
        roundedPanel.add(textPanel, BorderLayout.EAST);

        panel.add(roundedPanel, BorderLayout.CENTER);

        // Action on click
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.show(mainPanel, nextPage);
            }
        });

        return panel;
    }

    // Custom rounded panel
    class RoundedPanel extends JPanel {
        private int cornerRadius;
        private Color backgroundColor;
        private Color borderColor = new Color(200, 200, 200); // Light gray border
        private int borderThickness;
    
        public RoundedPanel(int radius, Color bgColor,int bt) {
            super();
            cornerRadius = radius;
            backgroundColor = bgColor;
            borderThickness = bt;
            setOpaque(false);
        }
    
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
            // Fill background
            g2.setColor(backgroundColor);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
    
            // Draw border
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(borderThickness));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
    
            g2.dispose();
            super.paintComponent(g);
        }
    }
    
}