import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.io.*;

public class LaunchPage extends JFrame{
    JFrame mainFrame = new JFrame();

    String pesoSymbol = "\u20B1";
    ColorPalette color = new ColorPalette();

    // Images
    ImageIcon appLogo = new ImageIcon("appLogo.png");

    //Buttons
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

        JPanel nPanel = new JPanel();
        JPanel headerPanel = new JPanel();
        JPanel upperBalancePanel = new JPanel();
        JPanel amountPanel = new JPanel();

        JLabel balanceText = new JLabel("Available Balance: ");
        JLabel pesoSign = new JLabel(pesoSymbol);
        JLabel amountText = new JLabel(String.format( "%s 15,000", pesoSymbol));

        nPanel.setLayout(new FlowLayout());
        nPanel.setPreferredSize(new Dimension(360, 120));

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
        buttonPanel.setLayout(new GridLayout(2, 3, 25, 25));
        buttonPanel.setPreferredSize(new Dimension(360, 140));


        //Style Buttons (ANG STYLE METHOD GINA COPY AND PASTE YUNG SAME BUTTON DESIGN)
        styleButton(payBillsBtn, "Pay Bills");
        styleButton(cashInBtn, "Cash In");
        styleButton(cashOutBtn, "Cash Out");
        styleButton(requestMoneyBtn, "Request Money");
        styleButton(bankTransferBtn, "Bank Transfer");
        styleButton(buyCryptoBtn, "Buy Crypto");

        //ADD BUTTONS
        addAllButtons(buttonPanel);
        centerPanel.add(buttonPanel);

        JPanel transactionPanel = new JPanel();
        transactionPanel.setLayout(new BorderLayout());
        transactionPanel.setPreferredSize(new Dimension(360, 40));
        transactionPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        JLabel transactionLabel = new JLabel("Transaction History");
        transactionLabel.setFont(loadFont(Font.BOLD, 18f));
        transactionLabel.setForeground(color.dBlue);

        JLabel seeAllLabel = new JLabel("See all");
        seeAllLabel.setFont(loadFont(Font.PLAIN, 14f));
        seeAllLabel.setForeground(color.pBlue);
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
