package project.currencyconverterapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Set;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    private static final String BACKGROUND_IMAGE = "C:\\Users\\haffadkhan\\Documents\\NetBeansProjects\\CurrencyConverterApp\\CurrencyConverterApp\\src\\main\\java\\project\\currencyconverterapp\\Image\\background1.jpg";
    private static final String LEFT_LOGO = "C:\\Users\\haffadkhan\\Documents\\NetBeansProjects\\CurrencyConverterApp\\CurrencyConverterApp\\src\\main\\java\\project\\currencyconverterapp\\Image\\left_logo.png";
    private static final String RIGHT_LOGO = "C:\\Users\\haffadkhan\\Documents\\NetBeansProjects\\CurrencyConverterApp\\CurrencyConverterApp\\src\\main\\java\\project\\currencyconverterapp\\Image\\right_logo.png";

    public LoginFrame() {
        setTitle("Login - Currency Converter");
        setSize(850, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setContentPane(new BackgroundPanel());
        initializeUI();
    }

    private void initializeUI() {
        getContentPane().setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        JLabel leftLogo = new JLabel(new ImageIcon(new ImageIcon(LEFT_LOGO).getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH)));
        leftLogo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.add(leftLogo, BorderLayout.WEST);

        JLabel headingLabel = new JLabel("Real Time Currency Converter  ", SwingConstants.CENTER);
        headingLabel.setForeground(Color.WHITE);
        headingLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        headingLabel.setBorder(BorderFactory.createEmptyBorder(30, 10, 10, 10));
        topPanel.add(headingLabel, BorderLayout.CENTER);

        JLabel rightLogo = new JLabel(new ImageIcon(new ImageIcon(RIGHT_LOGO).getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH)));
        rightLogo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.add(rightLogo, BorderLayout.EAST);

        getContentPane().add(topPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f));
                g2d.setColor(new Color(0, 0, 0, 120));
                g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2d.dispose();
            }
        };
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(userLabel, gbc);

        usernameField = new JTextField(15);
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        usernameField.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(usernameField, gbc);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.WHITE);
        passLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(passLabel, gbc);

        passwordField = new JPasswordField(15);
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        passwordField.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(passwordField, gbc);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 22));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(new Color(0, 123, 255));
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginButton.setPreferredSize(new Dimension(150, 45));

        loginButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                loginButton.setBackground(new Color(0, 100, 210));
            }

            public void mouseExited(MouseEvent e) {
                loginButton.setBackground(new Color(0, 123, 255));
            }
        });

        loginButton.addActionListener(this::handleLogin);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(loginButton, gbc);

        getContentPane().add(formPanel, BorderLayout.CENTER);
    }

    private void handleLogin(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        Set<String> validUsernames = Set.of("2024F-BSE-103","2024F-BSE-113","2024F-BSE-124","2024F-BSE-145");
        if (validUsernames.contains(username) && "123456789".equals(password)) {
            CurrencyConverterFrame dashboard = new CurrencyConverterFrame();
            dashboard.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            try {
                backgroundImage = new ImageIcon(BACKGROUND_IMAGE).getImage();
            } catch (Exception e) {
                System.err.println("Background image not found.");
            }
            setLayout(new BorderLayout());
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}