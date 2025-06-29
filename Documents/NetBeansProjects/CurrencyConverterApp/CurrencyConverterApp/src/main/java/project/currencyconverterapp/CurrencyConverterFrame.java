package project.currencyconverterapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class CurrencyConverterFrame extends JFrame {
    private JComboBox<String> fromCurrencyBox;
    private JComboBox<String> toCurrencyBox;
    private JTextField amountField;
    private JLabel resultLabel;

    private final CurrencyConverterService converterService = new CurrencyConverterService();
    private final Image backgroundImage;

    public CurrencyConverterFrame() {
        setTitle("Currency Converter");
        setSize(850, 600);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        ImageIcon bgIcon = new ImageIcon("C:\\Users\\haffadkhan\\Documents\\NetBeansProjects\\CurrencyConverterApp\\CurrencyConverterApp\\src\\main\\java\\project\\currencyconverterapp\\Image\\\\original.gif");
        backgroundImage = bgIcon.getImage();
        initializeUI();
    }

    private void initializeUI() {
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(830, 600));

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        backgroundPanel.setBounds(0, 0, 830, 600);
        backgroundPanel.setLayout(null);
        layeredPane.add(backgroundPanel, Integer.valueOf(0));

        JLabel headingLabel = new JLabel("Currency Converter", SwingConstants.CENTER);
        headingLabel.setForeground(Color.WHITE);
        headingLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        headingLabel.setBounds(0, 90, 830, 50);
        layeredPane.add(headingLabel, Integer.valueOf(1));

        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setOpaque(true);
        inputPanel.setBackground(new Color(0, 0, 0, 100)); // semi-transparent black
        inputPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0x3B82F6), 3),
                BorderFactory.createEmptyBorder(20, 30, 20, 30)
        ));
        inputPanel.setBounds(50, 150, 730, 180);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        List<String> currencies = Arrays.asList(
                "AED", "AFN", "ALL", "AMD", "ANG", "AOA", "ARS", "AUD", "AWG", "AZN",
                "BAM", "BBD", "BDT", "BGN", "BHD", "BIF", "BMD", "BND", "BOB", "BRL",
                "BSD", "BTN", "BWP", "BYN", "BZD", "CAD", "CDF", "CHF", "CLP", "CNY",
                "COP", "CRC", "CUC", "CUP", "CVE", "CZK", "DJF", "DKK", "DOP", "DZD",
                "EGP", "ERN", "ETB", "EUR", "FJD", "FKP", "GBP", "GEL", "GHS", "GIP",
                "GMD", "GNF", "GTQ", "GYD", "HKD", "HNL", "HRK", "HTG", "HUF", "IDR",
                "ILS", "INR", "IQD", "IRR", "ISK", "JMD", "JOD", "JPY", "KES", "KGS",
                "KHR", "KMF", "KPW", "KRW", "KWD", "KYD", "KZT", "LAK", "LBP", "LKR",
                "LRD", "LSL", "LYD", "MAD", "MDL", "MGA", "MKD", "MMK", "MNT", "MOP",
                "MRU", "MUR", "MVR", "MWK", "MXN", "MYR", "MZN", "NAD", "NGN", "NIO",
                "NOK", "NPR", "NZD", "OMR", "PAB", "PEN", "PGK", "PHP", "PKR", "PLN",
                "PYG", "QAR", "RON", "RSD", "RUB", "RWF", "SAR", "SBD", "SCR", "SDG",
                "SEK", "SGD", "SHP", "SLL", "SOS", "SRD", "STN", "SYP", "SZL", "THB",
                "TJS", "TMT", "TND", "TOP", "TRY", "TTD", "TVD", "TWD", "TZS", "UAH",
                "UGX", "USD", "UYU", "UZS", "VES", "VND", "VUV", "WST", "XAF", "XCD",
                "XOF", "XPF", "YER", "ZAR", "ZMW", "ZWL"
        );

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setForeground(Color.WHITE);
        amountLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        inputPanel.add(amountLabel, gbc);

        amountField = new JTextField();
        amountField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        amountField.setOpaque(true);
        amountField.setBackground(new Color(255, 255, 255, 200)); // semi-transparent white
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.7;
        inputPanel.add(amountField, gbc);

        JLabel fromLabel = new JLabel("From Currency:");
        fromLabel.setForeground(Color.WHITE);
        fromLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        inputPanel.add(fromLabel, gbc);

        fromCurrencyBox = new JComboBox<>(currencies.toArray(new String[0]));
        fromCurrencyBox.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        fromCurrencyBox.setOpaque(true);
        fromCurrencyBox.setBackground(new Color(255, 255, 255, 200));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.7;
        inputPanel.add(fromCurrencyBox, gbc);

        JLabel toLabel = new JLabel("To Currency:");
        toLabel.setForeground(Color.WHITE);
        toLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        inputPanel.add(toLabel, gbc);

        toCurrencyBox = new JComboBox<>(currencies.toArray(new String[0]));
        toCurrencyBox.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        toCurrencyBox.setOpaque(true);
        toCurrencyBox.setBackground(new Color(255, 255, 255, 200));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.7;
        inputPanel.add(toCurrencyBox, gbc);

        layeredPane.add(inputPanel, Integer.valueOf(2));

        JPanel actionPanel = new JPanel(new GridBagLayout());
        actionPanel.setOpaque(false);
        actionPanel.setBounds(50, 350, 730, 100);

        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.insets = new Insets(10, 10, 10, 10);

        JButton convertButton = new JButton("Convert");
        convertButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        convertButton.setBackground(new Color(0x3B82F6));
        convertButton.setForeground(Color.WHITE);
        convertButton.setFocusPainted(false);
        convertButton.setBorder(BorderFactory.createEmptyBorder(15, 40, 15, 40));
        convertButton.addActionListener(this::convertCurrency);
        gbc2.gridx = 0;
        gbc2.gridy = 0;
        gbc2.anchor = GridBagConstraints.CENTER;
        actionPanel.add(convertButton, gbc2);

        JButton backButton = new JButton("Log Out");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        backButton.setBackground(new Color(0xEF4444));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
        backButton.addActionListener(event -> {
            dispose();
            new LoginFrame().setVisible(true); 
        });
        gbc2.gridx = 1;
        gbc2.gridy = 0;
        gbc2.anchor = GridBagConstraints.CENTER;
        actionPanel.add(backButton, gbc2);

        resultLabel = new JLabel("Result will appear here.");
        resultLabel.setForeground(Color.YELLOW);
        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        resultLabel.setBorder(null);
        resultLabel.setOpaque(false);
        gbc2.gridx = 0;
        gbc2.gridy = 1;
        gbc2.gridwidth = 2;
        gbc2.anchor = GridBagConstraints.CENTER;
        actionPanel.add(resultLabel, gbc2);

        layeredPane.add(actionPanel, Integer.valueOf(3));

        setLayout(new BorderLayout());
        add(layeredPane, BorderLayout.CENTER);
    }

    private void convertCurrency(ActionEvent e) {
        try {
            double amount = Double.parseDouble(amountField.getText());
            if (amount < 0) {
                resultLabel.setText("Amount must be positive.");
                return;
            }

            String from = (String) fromCurrencyBox.getSelectedItem();
            String to = (String) toCurrencyBox.getSelectedItem();

            double result = converterService.convertCurrency(from, to, amount);

            NumberFormat nf = NumberFormat.getNumberInstance(Locale.US);
            nf.setMaximumFractionDigits(2);
            nf.setMinimumFractionDigits(2);

            resultLabel.setText(String.format("%s %s = %s %s", nf.format(amount), from, nf.format(result), to));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Please enter a valid number.");
        } catch (Exception ex) {
            resultLabel.setText("Conversion error: " + ex.getMessage());
        }
    }
}
