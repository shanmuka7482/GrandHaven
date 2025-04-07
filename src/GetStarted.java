import javax.swing.*;
import java.awt.*;

public class GetStarted extends JPanel {
    private Image backgroundImage;

    public GetStarted(CardLayout cardLayout, JPanel mainPanel) {
        // Load background image
        backgroundImage = new ImageIcon("res/bg1.jpeg").getImage(); // Make sure the path is correct

        setLayout(new BorderLayout());

        // Content panel (center)
        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false); // So background shows through
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(80, 20, 80, 20)); // Padding

        JLabel heading = new JLabel("PropertyPro", JLabel.CENTER);
        heading.setAlignmentX(Component.CENTER_ALIGNMENT);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 36));
        heading.setForeground(Color.WHITE);

        JLabel subtitle = new JLabel("Find your perfect property match with ease", JLabel.CENTER);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        subtitle.setForeground(Color.WHITE);

        contentPanel.add(heading);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(subtitle);

        // Button panel (bottom)
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 30));

        JButton getStartedBtn = new JButton("Get Started") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
                // Background
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40); // 40 = corner radius
        
                super.paintComponent(g);
                g2.dispose();
            }
        
            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 40, 40);
                g2.dispose();
            }
        
            @Override
            public boolean isOpaque() {
                return false;
            }
        };
        
        getStartedBtn.setPreferredSize(new Dimension(600, 60));
        getStartedBtn.setFont(new Font("Segoe UI", Font.BOLD, 20));
        getStartedBtn.setBackground(new Color(52, 108, 255));
        getStartedBtn.setForeground(Color.WHITE);
        getStartedBtn.setFocusPainted(false);
        getStartedBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        getStartedBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        getStartedBtn.setBorder(BorderFactory.createLineBorder(new Color(52, 108, 255), 1, true));
        getStartedBtn.addActionListener(e -> cardLayout.show(mainPanel, "loginType"));

        buttonPanel.add(getStartedBtn);

        add(contentPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            Graphics2D g2d = (Graphics2D) g.create();

            // Draw the background image scaled
            g2d.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

            // Draw dark transparent overlay
            g2d.setColor(new Color(0, 0, 0, 150)); // Semi-transparent black
            g2d.fillRect(0, 0, getWidth(), getHeight());

            g2d.dispose();
        }
    }
}
