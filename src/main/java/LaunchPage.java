import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.io.*;

public class LaunchPage extends JFrame{
    JFrame mainFrame = new JFrame();
    String pesoSymbol = "\u20B1";

    Color gray = new Color(0xD3D3D3);
    Color pBlue = new Color(0x2D76BD);
    Color dBlue = new Color(0x163F5C);
    Color lBlue = new Color(0xC4E4FF);
    Color black = new Color(0x000000);
    Color white = new Color(0xFFFFFF);

    ImageIcon appLogo = new ImageIcon("appLogo.png");

    public LaunchPage(){
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(420, 750);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setTitle("Payday Bank");
        mainFrame.setIconImage(appLogo.getImage());
        mainFrame.setResizable(false);
        mainFrame.setLayout(new BorderLayout());

        JPanel nPanel = new JPanel();
        JPanel headerPanel = new JPanel();
        JPanel upperBalancePanel = new JPanel();
        JPanel amountPanel = new JPanel();

        JLabel balanceText = new JLabel("Available Balance: ");
        JLabel pesoSign = new JLabel(pesoSymbol);
        JLabel amountText = new JLabel(String.format( "%s 15,000", pesoSymbol));

        nPanel.setLayout(new FlowLayout());
        nPanel.setPreferredSize(new Dimension(360, 120));

        RoundedPanel balPanel = new RoundedPanel(15, pBlue);
        balPanel.setLayout(new BorderLayout());
        balPanel.setPreferredSize(new Dimension(360, 80));

        balanceText.setFont(new Font("Open Sans", Font.BOLD, 12));
        balanceText.setForeground(gray);
        balanceText.setHorizontalTextPosition(JLabel.LEFT);

        upperBalancePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
        upperBalancePanel.setOpaque(false);
        upperBalancePanel.setPreferredSize(new Dimension(360, 30));

        amountText.setFont(loadFont(Font.PLAIN, 35f));
        amountText.setVerticalTextPosition(JLabel.CENTER);
        amountText.setForeground(white);

        amountPanel.setOpaque(false);
        amountPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 0));

        headerPanel.setPreferredSize(new Dimension(420, 15));

        balPanel.add(amountPanel);
        balPanel.add(upperBalancePanel, BorderLayout.NORTH);

        amountPanel.add(amountText);
        upperBalancePanel.add(balanceText);

        nPanel.add(headerPanel);
        nPanel.add(balPanel);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
        centerPanel.setPreferredSize(new Dimension(420, 180));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 3, 15, 15));
        buttonPanel.setPreferredSize(new Dimension(360, 140));

        RoundedPanel payBillsBtn = new RoundedPanel(15, pBlue);
        payBillsBtn.setPreferredSize(new Dimension(80, 60));
        payBillsBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        payBillsBtn.setLayout(new BorderLayout());
        JLabel payBillsLabel = new JLabel("Pay Bills");
        payBillsLabel.setFont(loadFont(Font.PLAIN, 12f));
        payBillsLabel.setForeground(white);
        payBillsLabel.setHorizontalAlignment(JLabel.CENTER);
        payBillsBtn.add(payBillsLabel, BorderLayout.CENTER);
        payBillsBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                payBillsBtn.setBackground(dBlue);
                payBillsBtn.repaint();
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                payBillsBtn.setBackground(pBlue);
                payBillsBtn.repaint();
            }
        });

        RoundedPanel cashInBtn = new RoundedPanel(15, pBlue);
        cashInBtn.setPreferredSize(new Dimension(80, 60));
        cashInBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cashInBtn.setLayout(new BorderLayout());
        JLabel cashInLabel = new JLabel("Cash In");
        cashInLabel.setFont(loadFont(Font.PLAIN, 12f));
        cashInLabel.setForeground(white);
        cashInLabel.setHorizontalAlignment(JLabel.CENTER);
        cashInBtn.add(cashInLabel, BorderLayout.CENTER);
        cashInBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cashInBtn.setBackground(dBlue);
                cashInBtn.repaint();
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cashInBtn.setBackground(pBlue);
                cashInBtn.repaint();
            }
        });

        RoundedPanel cashOutBtn = new RoundedPanel(15, pBlue);
        cashOutBtn.setPreferredSize(new Dimension(80, 60));
        cashOutBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cashOutBtn.setLayout(new BorderLayout());
        JLabel cashOutLabel = new JLabel("Cash Out");
        cashOutLabel.setFont(loadFont(Font.PLAIN, 12f));
        cashOutLabel.setForeground(white);
        cashOutLabel.setHorizontalAlignment(JLabel.CENTER);
        cashOutBtn.add(cashOutLabel, BorderLayout.CENTER);
        cashOutBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cashOutBtn.setBackground(dBlue);
                cashOutBtn.repaint();
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cashOutBtn.setBackground(pBlue);
                cashOutBtn.repaint();
            }
        });

        RoundedPanel requestMoneyBtn = new RoundedPanel(15, pBlue);
        requestMoneyBtn.setPreferredSize(new Dimension(80, 60));
        requestMoneyBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        requestMoneyBtn.setLayout(new BorderLayout());
        JLabel requestMoneyLabel = new JLabel("Request Money");
        requestMoneyLabel.setFont(loadFont(Font.PLAIN, 12f));
        requestMoneyLabel.setForeground(white);
        requestMoneyLabel.setHorizontalAlignment(JLabel.CENTER);
        requestMoneyBtn.add(requestMoneyLabel, BorderLayout.CENTER);
        requestMoneyBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                requestMoneyBtn.setBackground(dBlue);
                requestMoneyBtn.repaint();
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                requestMoneyBtn.setBackground(pBlue);
                requestMoneyBtn.repaint();
            }
        });

        RoundedPanel bankTransferBtn = new RoundedPanel(15, pBlue);
        bankTransferBtn.setPreferredSize(new Dimension(80, 60));
        bankTransferBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        bankTransferBtn.setLayout(new BorderLayout());
        JLabel bankTransferLabel = new JLabel("Bank Transfer");
        bankTransferLabel.setFont(loadFont(Font.PLAIN, 12f));
        bankTransferLabel.setForeground(white);
        bankTransferLabel.setHorizontalAlignment(JLabel.CENTER);
        bankTransferBtn.add(bankTransferLabel, BorderLayout.CENTER);
        bankTransferBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bankTransferBtn.setBackground(dBlue);
                bankTransferBtn.repaint();
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bankTransferBtn.setBackground(pBlue);
                bankTransferBtn.repaint();
            }
        });

        RoundedPanel buyCryptoBtn = new RoundedPanel(15, pBlue);
        buyCryptoBtn.setPreferredSize(new Dimension(80, 60));
        buyCryptoBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buyCryptoBtn.setLayout(new BorderLayout());
        JLabel buyCryptoLabel = new JLabel("Buy Crypto");
        buyCryptoLabel.setFont(loadFont(Font.PLAIN, 12f));
        buyCryptoLabel.setForeground(white);
        buyCryptoLabel.setHorizontalAlignment(JLabel.CENTER);
        buyCryptoBtn.add(buyCryptoLabel, BorderLayout.CENTER);
        buyCryptoBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                buyCryptoBtn.setBackground(dBlue);
                buyCryptoBtn.repaint();
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                buyCryptoBtn.setBackground(pBlue);
                buyCryptoBtn.repaint();
            }
        });

        buttonPanel.add(payBillsBtn);
        buttonPanel.add(cashInBtn);
        buttonPanel.add(cashOutBtn);
        buttonPanel.add(requestMoneyBtn);
        buttonPanel.add(bankTransferBtn);
        buttonPanel.add(buyCryptoBtn);

        centerPanel.add(buttonPanel);

        JPanel transactionPanel = new JPanel();
        transactionPanel.setLayout(new BorderLayout());
        transactionPanel.setPreferredSize(new Dimension(360, 40));
        transactionPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        JLabel transactionLabel = new JLabel("Transaction History");
        transactionLabel.setFont(loadFont(Font.BOLD, 18f));
        transactionLabel.setForeground(dBlue);

        JLabel seeAllLabel = new JLabel("See all");
        seeAllLabel.setFont(loadFont(Font.PLAIN, 14f));
        seeAllLabel.setForeground(pBlue);
        seeAllLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        transactionPanel.add(transactionLabel, BorderLayout.WEST);
        transactionPanel.add(seeAllLabel, BorderLayout.EAST);

        centerPanel.add(transactionPanel);

        mainFrame.add(nPanel, BorderLayout.NORTH);
        mainFrame.add(centerPanel, BorderLayout.CENTER);
        mainFrame.setVisible(true);
    }

    private Font loadFont(int style, float size) {
        try {
            InputStream fontStream = getClass().getResourceAsStream("/fonts/Quicksand-Regular.ttf");
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            return customFont.deriveFont(style, size);
        } catch (Exception e) {
            System.out.println("Could not load font: " + e.getMessage());
            return new Font("Arial", style, (int)size);
        }
    }
}