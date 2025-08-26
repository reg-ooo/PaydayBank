import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.io.*;

public class LaunchPage extends JFrame{
    JFrame mainFrame = new JFrame();

    String pesoSymbol = "\u20B1";
    Style style = new Style();

    // Images
    private ImageIcon bankTransferImg, buyCryptoImg, cashInImg, cashOutImg,
    requestMoneyImg, sendMoneyImg;

    private ImageIcon appLogo = new ImageIcon("appLogo.png");

    //WRAPPER BUTTONS
    private JPanel payBillsWrapper, cashInWrapper, cashOutWrapper, requestMoneyWrapper,
    bankTransferWrapper, buyCryptoWrapper;

    //Buttons
    private RoundedPanel payBillsBtn = new RoundedPanel(25, style.pBlue);
    private RoundedPanel cashInBtn = new RoundedPanel(25, style.pBlue);
    private RoundedPanel cashOutBtn = new RoundedPanel(25, style.pBlue);
    private RoundedPanel requestMoneyBtn = new RoundedPanel(25, style.pBlue);
    private RoundedPanel bankTransferBtn = new RoundedPanel(25, style.pBlue);
    private RoundedPanel buyCryptoBtn = new RoundedPanel(25, style.pBlue);

    public LaunchPage(){
        //MAIN FRAME
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(420, 750);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setTitle("Payday Bank");
        mainFrame.setIconImage(appLogo.getImage());
        mainFrame.setResizable(false);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setUndecorated(true);

        //UPPER PANELS
        JPanel nPanel = new JPanel();
        JPanel headerPanel = new JPanel();
        JPanel upperBalancePanel = new JPanel();
        JPanel amountPanel = new JPanel();

        //LABELS
        JLabel balanceText = new JLabel("Available Balance: ");
        JLabel pesoSign = new JLabel(pesoSymbol);
        JLabel amountText = new JLabel(String.format( "%s 15,000", pesoSymbol));

        //PALDO KA LODS
        nPanel.setLayout(new FlowLayout());
        nPanel.setPreferredSize(new Dimension(360, 150));

        //BALANCE PANEL
        RoundedPanel balPanel = new RoundedPanel(15, style.pBlue);
        balPanel.setLayout(new BorderLayout());
        balPanel.setPreferredSize(new Dimension(360, 110));

        //BALANCE TEXT
        balanceText.setFont(new Font("Open Sans", Font.BOLD, 12));
        balanceText.setForeground(style.gray);
        balanceText.setHorizontalTextPosition(JLabel.LEFT);

        //UPPER BALANCE PANEL
        upperBalancePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
        upperBalancePanel.setOpaque(false);
        upperBalancePanel.setPreferredSize(new Dimension(360, 30));

        //BALANCE AMOUNT
        amountText.setFont(loadFont(Font.PLAIN, 40f));
        amountText.setVerticalTextPosition(JLabel.CENTER);
        amountText.setForeground(style.white);

        //AMOUNT PANEL
        amountPanel.setOpaque(false);
        amountPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 0));

        headerPanel.setPreferredSize(new Dimension(420, 15));

        //ADD COMPONENTS
        balPanel.add(amountPanel);
        balPanel.add(upperBalancePanel, BorderLayout.NORTH);

        amountPanel.add(amountText);
        upperBalancePanel.add(balanceText);

        nPanel.add(headerPanel);
        nPanel.add(balPanel);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 15));
        centerPanel.setPreferredSize(new Dimension(420, 200));
        centerPanel.setMaximumSize(new Dimension(420, 230));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 3, 25, 10));

        sendMoneyImg = resizeImage("sendMoney.png");
        cashInImg = resizeImage("cashIn.png");
        cashOutImg = resizeImage("cashOut.png");
        requestMoneyImg = resizeImage("requestMoney.png");
        bankTransferImg = resizeImage("bankTransfer.png");
        buyCryptoImg = resizeImage("buyCrypto.png");

        //Style Buttons WITH WRAPPER (ANG STYLE METHOD GINA COPY AND PASTE YUNG SAME BUTTON DESIGN)
        payBillsWrapper = styleButton(payBillsBtn, "Pay Bills", sendMoneyImg);
        cashInWrapper = styleButton(cashInBtn, "Cash In", cashInImg);
        cashOutWrapper = styleButton(cashOutBtn, "Cash Out", cashOutImg);
        requestMoneyWrapper = styleButton(requestMoneyBtn, "Request Money", requestMoneyImg);
        bankTransferWrapper = styleButton(bankTransferBtn, "Bank Transfer", bankTransferImg);
        buyCryptoWrapper = styleButton(buyCryptoBtn, "Buy Crypto", buyCryptoImg);

        //ADD BUTTONS
        addAllButtons(buttonPanel);
        centerPanel.add(buttonPanel);

        // TRANSACTION ROUNDED PANEL - Main container
        RoundedPanel transactionRoundedPanel = new RoundedPanel(15, style.white);
        transactionRoundedPanel.setLayout(new BorderLayout());
        transactionRoundedPanel.setPreferredSize(new Dimension(380, 300));
        transactionRoundedPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        // TRANSACTION HEADER PANEL (with "Transaction History" and "See all")
        JPanel transactionHeaderPanel = new JPanel(new BorderLayout());
<<<<<<< Updated upstream
        transactionHeaderPanel.setPreferredSize(new Dimension(360, 40));
        transactionHeaderPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        transactionHeaderPanel.setBackground(new Color(230, 240, 250));
=======
        transactionHeaderPanel.setPreferredSize(new Dimension(364, 35));
        transactionHeaderPanel.setOpaque(false);
>>>>>>> Stashed changes

        JLabel transactionLabel = new JLabel("Transaction History");
        transactionLabel.setFont(loadFont(Font.BOLD, 18f));
        transactionLabel.setForeground(style.dBlue);

        JLabel seeAllLabel = new JLabel("See all");
        seeAllLabel.setFont(loadFont(Font.PLAIN, 14f));
        seeAllLabel.setForeground(style.pBlue);
        seeAllLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        transactionHeaderPanel.add(transactionLabel, BorderLayout.WEST);
        transactionHeaderPanel.add(seeAllLabel, BorderLayout.EAST);

        // Create transaction history content panel
        JPanel transactionContentPanel = new JPanel();
        transactionContentPanel.setLayout(new BoxLayout(transactionContentPanel, BoxLayout.Y_AXIS));
<<<<<<< Updated upstream
        transactionContentPanel.setBackground(new Color(230, 240, 250));
=======
        transactionContentPanel.setOpaque(false);
        transactionContentPanel.setPreferredSize(new Dimension(364, 240));
>>>>>>> Stashed changes

        // Add transaction items (hardcoded)
        transactionContentPanel.add(createDateSection("Today"));
        transactionContentPanel.add(createTransactionItem("1:49 AM", "Pay via Scanned QR", pesoSymbol + " -100.00", false));

        transactionContentPanel.add(createDateSection("Yesterday"));
        transactionContentPanel.add(createTransactionItem("3:35 AM", "Send Money", pesoSymbol + " -135.00", false));

<<<<<<< Updated upstream
        transactionContentPanel.add(createDateSection("Feb 21, 2025"));
        transactionContentPanel.add(createTransactionItem("10:09 PM", "Send Money", pesoSymbol + " +2,600.00", true));

        // Make it scrollable
        JScrollPane scrollPane = new JScrollPane(transactionContentPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(new Color(230, 240, 250));
        scrollPane.setPreferredSize(new Dimension(400, 250));

        // Main transaction container that holds both header and content
        JPanel transactionContainer = new JPanel(new BorderLayout());
        transactionContainer.setBackground(Color.yellow);


        transactionContainer.setMaximumSize(new Dimension(420, 430));
        transactionContainer.add(transactionHeaderPanel, BorderLayout.NORTH);
        transactionContainer.add(scrollPane, BorderLayout.CENTER);
=======
        // Add header and content to the rounded panel
        transactionRoundedPanel.add(transactionHeaderPanel, BorderLayout.NORTH);
        transactionRoundedPanel.add(transactionContentPanel, BorderLayout.CENTER);
>>>>>>> Stashed changes

        // Transaction container wrapper
        JPanel transactionContainer = new JPanel(new FlowLayout());
        transactionContainer.setOpaque(false);
        transactionContainer.setMaximumSize(new Dimension(420, 320));
        transactionContainer.add(transactionRoundedPanel);

        // Navigation bar at the bottom
        JPanel navBarPanel = new JPanel();
        navBarPanel.setLayout(new GridLayout(1, 4));
        navBarPanel.setPreferredSize(new Dimension(420, 60));
        navBarPanel.setForeground(style.dBlue);

        // Create navigation buttons
        JPanel homeBtn = createNavButton("🏠", "Home", true);
        JPanel profileBtn = createNavButton("👤", "Profile", false);
        JPanel walletBtn = createNavButton("💳", "Wallet", false);
        JPanel notificationBtn = createNavButton("🔔", "Notifications", false);

        //ADD BUTTONS TO NAV BAR
        navBarPanel.add(homeBtn);
        navBarPanel.add(profileBtn);
        navBarPanel.add(walletBtn);
        navBarPanel.add(notificationBtn);

        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,0));

        // Create a main content panel
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        mainContentPanel.add(centerPanel);
        mainContentPanel.add(transactionContainer);



        mainFrame.add(nPanel, BorderLayout.NORTH);
        mainFrame.add(mainContentPanel, BorderLayout.CENTER);
        mainFrame.add(navBarPanel, BorderLayout.SOUTH);
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

    private JPanel createNavButton(String icon, String text, boolean isActive) {
        JPanel navButton = new JPanel();
        navButton.setLayout(new BoxLayout(navButton, BoxLayout.Y_AXIS));
        navButton.setBackground(style.pBlue);
        navButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        navButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));

        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        iconLabel.setForeground(isActive ? Color.WHITE : new Color(150, 150, 150));

        JLabel textLabel = new JLabel(text);
        textLabel.setFont(loadFont(Font.PLAIN, 10f));
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        textLabel.setForeground(isActive ? Color.WHITE : new Color(150, 150, 150));

        // Add some spacing between icon and text
        navButton.add(Box.createVerticalGlue());
        navButton.add(iconLabel);
        navButton.add(Box.createRigidArea(new Dimension(0, 2)));
        navButton.add(textLabel);
        navButton.add(Box.createVerticalGlue());

        // Add hover effect
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

    private ImageIcon resizeImage(String text) {
        //RESIZE IMAGE
        ImageIcon rawImage = new ImageIcon(text);
        Image originalImage = rawImage.getImage();
        Image resizedimage = originalImage.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        ImageIcon buttonImage = new ImageIcon(resizedimage);

        return buttonImage;
    }

    private void addAllButtons(JPanel buttonPanel) {
        buttonPanel.add(payBillsWrapper);
        buttonPanel.add(cashInWrapper);
        buttonPanel.add(cashOutWrapper);
        buttonPanel.add(requestMoneyWrapper);
        buttonPanel.add(bankTransferWrapper);
        buttonPanel.add(buyCryptoWrapper);
    }

    private JPanel styleButton(RoundedPanel button, String text, ImageIcon image) {
        //WRAPPER PANEL
        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new BorderLayout());
        wrapperPanel.setOpaque(false);
        wrapperPanel.setPreferredSize(new Dimension(95, 90));

        //BUTTON
        button.setPreferredSize(new Dimension(50, 70));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setLayout(new BorderLayout());

        //IMAGE LABEL
        JLabel imageLabel = new JLabel(image);
        imageLabel.setIcon(image);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setForeground(style.gray);

        //TEXT LABEL
        JLabel buttonLabel = new JLabel(text);
        buttonLabel.setFont(loadFont(Font.BOLD, 12f));
        buttonLabel.setForeground(style.pBlue);
        buttonLabel.setHorizontalAlignment(JLabel.CENTER);
        buttonLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        button.add(imageLabel, BorderLayout.CENTER);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(style.dBlue);
                buttonLabel.setForeground(style.dBlue);
                button.setPreferredSize(new Dimension(55, 75));

                revalidateParentContainers(button);
                button.repaint();
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(style.pBlue);
                buttonLabel.setForeground(style.pBlue);
                button.setPreferredSize(new Dimension(50, 70));

                revalidateParentContainers(button);
                button.repaint();
            }
        });

        wrapperPanel.add(button, BorderLayout.NORTH);
        wrapperPanel.add(buttonLabel, BorderLayout.CENTER);

        return wrapperPanel;
    }

    // Add this method to create transaction history items
    private JPanel createTransactionItem(String time, String description, String amount, boolean isPositive) {
        JPanel transactionPanel = new JPanel(new BorderLayout());
        transactionPanel.setBackground(new Color(230, 240, 250));
        transactionPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // Left side: Time and description
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setBackground(new Color(230, 240, 250));

        JLabel descLabel = new JLabel(description);
        descLabel.setFont(loadFont(Font.PLAIN, 14f));
        descLabel.setForeground(Color.BLACK);

        JLabel timeLabel = new JLabel(time);
        timeLabel.setFont(loadFont(Font.PLAIN, 12f));
        timeLabel.setForeground(Color.GRAY);

        leftPanel.add(descLabel);
        leftPanel.add(timeLabel);

        // Right side: Amount
        JLabel amountLabel = new JLabel(amount);
        amountLabel.setFont(loadFont(Font.BOLD, 14f));
        amountLabel.setForeground(isPositive ? new Color(0, 128, 0) : Color.RED); // Green for positive, red for negative
        amountLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        transactionPanel.add(leftPanel, BorderLayout.WEST);
        transactionPanel.add(amountLabel, BorderLayout.EAST);

        return transactionPanel;
    }

    // Add this method to create date sections
    private JPanel createDateSection(String date) {
        JPanel datePanel = new JPanel(new BorderLayout());
        datePanel.setBackground(new Color(230, 240, 250));
        datePanel.setBorder(BorderFactory.createEmptyBorder(15, 10, 5, 10));

        JLabel dateLabel = new JLabel(date);
        dateLabel.setFont(loadFont(Font.BOLD, 16f));
        dateLabel.setForeground(style.dBlue);

        datePanel.add(dateLabel, BorderLayout.WEST);

        return datePanel;
    }


    private void revalidateParentContainers(Component button) {
        Container parent = button.getParent();
        if (parent != null) {
            parent.revalidate();
            parent = parent.getParent();
        }
    }
}
