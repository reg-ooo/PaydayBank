import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.io.*;

public class LaunchPage extends JFrame{
    JFrame mainFrame = new JFrame();

    String pesoSymbol = "\u20B1";
    ColorPalette color = new ColorPalette();

    ImageIcon appLogo = new ImageIcon("appLogo.png");

    RoundedPanel payBillsBtn = new RoundedPanel(15, color.pBlue);
    RoundedPanel cashInBtn = new RoundedPanel(15, color.pBlue);
    RoundedPanel cashOutBtn = new RoundedPanel(15, color.pBlue);
    RoundedPanel requestMoneyBtn = new RoundedPanel(15, color.pBlue);
    RoundedPanel bankTransferBtn = new RoundedPanel(15, color.pBlue);
    RoundedPanel buyCryptoBtn = new RoundedPanel(15, color.pBlue);

    public LaunchPage(){
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(420, 750);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setTitle("Payday Bank");
        mainFrame.setIconImage(appLogo.getImage());
        mainFrame.setResizable(false);
        mainFrame.setLayout(new BorderLayout());

        JPanel topWhitePanel = new JPanel();
        topWhitePanel.setLayout(new BorderLayout());
        topWhitePanel.setBackground(Color.WHITE);

        JPanel nPanel = new JPanel();
        JPanel headerPanel = new JPanel();
        JPanel upperBalancePanel = new JPanel();
        JPanel amountPanel = new JPanel();

        JLabel balanceText = new JLabel("Available Balance: ");
        JLabel pesoSign = new JLabel(pesoSymbol);
        JLabel amountText = new JLabel(String.format( "%s 15,000", pesoSymbol));

        nPanel.setLayout(new FlowLayout());
        nPanel.setPreferredSize(new Dimension(360, 120));
        nPanel.setBackground(Color.WHITE);

        RoundedPanel balPanel = new RoundedPanel(15, color.pBlue);
        balPanel.setLayout(new BorderLayout());
        balPanel.setPreferredSize(new Dimension(360, 80));

        balanceText.setFont(new Font("Open Sans", Font.BOLD, 12));
        balanceText.setForeground(color.gray);
        balanceText.setHorizontalTextPosition(JLabel.LEFT);

        upperBalancePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
        upperBalancePanel.setOpaque(false);
        upperBalancePanel.setPreferredSize(new Dimension(360, 30));

        amountText.setFont(loadFont(Font.PLAIN, 35f));
        amountText.setVerticalTextPosition(JLabel.CENTER);
        amountText.setForeground(color.white);

        amountPanel.setOpaque(false);
        amountPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 0));

        headerPanel.setPreferredSize(new Dimension(420, 15));
        headerPanel.setBackground(Color.WHITE);

        balPanel.add(amountPanel);
        balPanel.add(upperBalancePanel, BorderLayout.NORTH);

        amountPanel.add(amountText);
        upperBalancePanel.add(balanceText);

        nPanel.add(headerPanel);
        nPanel.add(balPanel);

        JPanel buttonsPanelContainer = new JPanel();
        buttonsPanelContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        buttonsPanelContainer.setPreferredSize(new Dimension(420, 180));
        buttonsPanelContainer.setBackground(Color.WHITE);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 3, 25, 25));
        buttonPanel.setPreferredSize(new Dimension(360, 140));
        buttonPanel.setOpaque(false);

        styleButton(payBillsBtn, "Pay Bills");
        styleButton(cashInBtn, "Cash In");
        styleButton(cashOutBtn, "Cash Out");
        styleButton(requestMoneyBtn, "Request Money");
        styleButton(bankTransferBtn, "Bank Transfer");
        styleButton(buyCryptoBtn, "Buy Crypto");

        addAllButtons(buttonPanel);
        buttonsPanelContainer.add(buttonPanel);

        topWhitePanel.add(nPanel, BorderLayout.NORTH);
        topWhitePanel.add(buttonsPanelContainer, BorderLayout.CENTER);

        JPanel bottomGrayPanel = new JPanel();
        bottomGrayPanel.setLayout(new BorderLayout());
        bottomGrayPanel.setBackground(new Color(230, 240, 250));

        JPanel transactionPanel = new JPanel();
        transactionPanel.setLayout(new BorderLayout());
        transactionPanel.setPreferredSize(new Dimension(360, 45));
        transactionPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 0, 30));
        transactionPanel.setBackground(new Color(230, 240, 250));

        JLabel transactionLabel = new JLabel("Transaction History");
        transactionLabel.setFont(loadFont(Font.BOLD, 18f));
        transactionLabel.setForeground(color.dBlue);

        JLabel seeAllLabel = new JLabel("See all");
        seeAllLabel.setFont(loadFont(Font.PLAIN, 14f));
        seeAllLabel.setForeground(color.pBlue);
        seeAllLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        transactionPanel.add(transactionLabel, BorderLayout.WEST);
        transactionPanel.add(seeAllLabel, BorderLayout.EAST);

        JPanel spacerPanel = new JPanel();
        spacerPanel.setBackground(new Color(230, 240, 250));

        bottomGrayPanel.add(transactionPanel, BorderLayout.NORTH);
        bottomGrayPanel.add(spacerPanel, BorderLayout.CENTER);

        JPanel navBarPanel = new JPanel();
        navBarPanel.setLayout(new GridLayout(1, 4));
        navBarPanel.setPreferredSize(new Dimension(420, 70));
        navBarPanel.setBackground(new Color(52, 88, 130));

        JPanel homeBtn = createNavButton("🏠", "Home", true);
        JPanel profileBtn = createNavButton("👤", "Profile", false);
        JPanel walletBtn = createNavButton("💳", "Wallet", false);
        JPanel notificationBtn = createNavButton("🔔", "Notifications", false);

        navBarPanel.add(homeBtn);
        navBarPanel.add(profileBtn);
        navBarPanel.add(walletBtn);
        navBarPanel.add(notificationBtn);

        mainFrame.add(topWhitePanel, BorderLayout.NORTH);
        mainFrame.add(bottomGrayPanel, BorderLayout.CENTER);
        mainFrame.add(navBarPanel, BorderLayout.SOUTH);
        mainFrame.setVisible(true);
    }

    private JPanel createNavButton(String icon, String text, boolean isActive) {
        JPanel navButton = new JPanel();
        navButton.setLayout(new BoxLayout(navButton, BoxLayout.Y_AXIS));
        navButton.setBackground(new Color(52, 88, 130));
        navButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        navButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        iconLabel.setForeground(isActive ? Color.WHITE : new Color(150, 150, 150));

        JLabel textLabel = new JLabel(text);
        textLabel.setFont(loadFont(Font.PLAIN, 10f));
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        textLabel.setForeground(isActive ? Color.WHITE : new Color(150, 150, 150));

        navButton.add(Box.createVerticalGlue());
        navButton.add(iconLabel);
        navButton.add(Box.createRigidArea(new Dimension(0, 2)));
        navButton.add(textLabel);
        navButton.add(Box.createVerticalGlue());

        navButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (!isActive) {
                    iconLabel.setForeground(Color.WHITE);
                    textLabel.setForeground(Color.WHITE);
                }
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (!isActive) {
                    iconLabel.setForeground(new Color(150, 150, 150));
                    textLabel.setForeground(new Color(150, 150, 150));
                }
            }
        });

        return navButton;
    }

    private Font loadFont(int style, float size) {
        try {
            InputStream fontStream = getClass().getResourceAsStream("/fonts/Quicksand-Regular.ttf");
            Font karlaFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            return karlaFont.deriveFont(style, size);
        } catch (Exception e) {
            System.out.println("Could not load Karla font: " + e.getMessage());

            return new Font("Arial", style, (int)size);
        }
    }

    private void addHoverEffect() {

    }

    private void addAllButtons(JPanel buttonPanel) {
        buttonPanel.add(payBillsBtn);
        buttonPanel.add(cashInBtn);
        buttonPanel.add(cashOutBtn);
        buttonPanel.add(requestMoneyBtn);
        buttonPanel.add(bankTransferBtn);
        buttonPanel.add(buyCryptoBtn);
    }

    private void styleButton(RoundedPanel button, String text) {
        button.setPreferredSize(new Dimension(10, 30));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setLayout(new BorderLayout());

        JLabel buttonLabel = new JLabel(text);
        buttonLabel.setFont(loadFont(Font.PLAIN, 12f));
        buttonLabel.setForeground(color.white);
        buttonLabel.setHorizontalAlignment(JLabel.CENTER);

        button.add(buttonLabel, BorderLayout.CENTER);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.dBlue);
                button.repaint();
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color.pBlue);
                button.repaint();
            }
        });
    }

}