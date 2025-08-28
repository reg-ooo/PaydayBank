import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
import java.io.*;
import java.util.ArrayList;

public class LaunchPage extends JFrame {
    private Style style = new Style();
    RoundedFrame mainFrame = new RoundedFrame(30);
    final String pesoSymbol = "\u20B1";
    final ImageIcon appLogo = new ImageIcon("appLogo.png");
    final ImageIcon appLogo2 = new ImageIcon("appLogo2.png");
    private final ImageIcon navAppLogo = new ImageIcon("navAppLogo.png");

    //WRAPPER BUTTONS
    private final JPanel payBillsWrapper;
    private final JPanel cashInWrapper;
    private final JPanel cashOutWrapper;
    private final JPanel requestMoneyWrapper;
    private final JPanel bankTransferWrapper;
    private final JPanel buyCryptoWrapper;

    //Buttons
    ArrayList<RoundedPanel> buttons = new ArrayList<>();
    public LaunchPage(){
        //MAIN FRAME
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(420, 650);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setTitle("Payday Bank");
        mainFrame.setIconImage(appLogo.getImage());
        mainFrame.setResizable(false);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setUndecorated(true);

        //UPPER PANELS
        JPanel nPanel = createPanel(new Dimension(360, 150), null, new FlowLayout());
        JPanel headerPanel = createPanel(new Dimension(420, 15), null, null);
        JPanel upperBalancePanel = createPanel(new Dimension(360, 30), null, new FlowLayout(FlowLayout.LEFT, 15, 10));
        JPanel amountPanel = createPanel(new Dimension(420, 200), null, new FlowLayout(FlowLayout.LEFT, 15, 0));

        //LABELS
        JLabel pesoSign = new JLabel(pesoSymbol);

        //BALANCE PANEL
        RoundedBorder balPanel = new RoundedBorder(15, style.pBlue, 3);
        balPanel.setLayout(new BorderLayout());
        balPanel.setPreferredSize(new Dimension(360, 110));

        //BALANCE TEXT
        JLabel balanceText = createLabel("Available Balance: ", style.loadFont(Font.BOLD, 18f, "Quicksand-Regular"), style.dBlue);
        balanceText.setHorizontalTextPosition(JLabel.LEFT);

        //UPPER BALANCE PANEL
        upperBalancePanel.setOpaque(false);

        //BALANCE AMOUNT
        JLabel amountText = createLabel(String.format( "%s 15,000", pesoSymbol), style.loadFont(Font.PLAIN, 40f, "Quicksand-Regular"), style.pBlue);
        amountText.setVerticalTextPosition(JLabel.CENTER);

        //AMOUNT PANEL
        amountPanel.setOpaque(false);

        //ADD COMPONENTS
        balPanel.add(amountPanel);
        balPanel.add(upperBalancePanel, BorderLayout.NORTH);

        amountPanel.add(amountText);
        upperBalancePanel.add(balanceText);

        nPanel.add(headerPanel);
        nPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        nPanel.add(balPanel);

        //CENTER PANEL
        JPanel centerPanel = createPanel(new Dimension(420, 200), null, new FlowLayout(FlowLayout.CENTER, 0, 15));
        centerPanel.setMaximumSize(new Dimension(420, 230));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,0));

        //BUTTON PANELS
        JPanel buttonPanel = createPanel(null, null, new GridLayout(2, 3, 25, 10));
        for(int i = 0; i<6; i++){
            buttons.add(new RoundedPanel(25, style.pBlue));
        }
        String[] buttonNames = {"sendMoney.png", "cashIn.png", "cashOut.png", "requestMoney.png", "bankTransfer.png", "buyCrypto.png"};
        //RESIZE BUTTON IMAGES
        ImageIcon sendMoneyImg = resizeImage("sendMoney.png", 60, 60);
        ImageIcon cashInImg = resizeImage("cashIn.png", 60, 60);
        ImageIcon cashOutImg = resizeImage("cashOut.png", 60, 60);
        ImageIcon requestMoneyImg = resizeImage("requestMoney.png", 60, 60);
        ImageIcon bankTransferImg = resizeImage("bankTransfer.png", 60, 60);
        ImageIcon buyCryptoImg = resizeImage("buyCrypto.png", 60, 60);

        //Style Buttons WITH WRAPPER (ANG STYLE METHOD GINA COPY AND PASTE YUNG SAME BUTTON DESIGN)
            payBillsWrapper = styleButton(buttons.get(0), "Pay Bills", sendMoneyImg);
            cashInWrapper = styleButton(buttons.get(1), "Cash In", cashInImg);
            cashOutWrapper = styleButton(buttons.get(2), "Cash Out", cashOutImg);
            requestMoneyWrapper = styleButton(buttons.get(3), "Request Money", requestMoneyImg);
            bankTransferWrapper = styleButton(buttons.get(4), "Bank Transfer", bankTransferImg);
            buyCryptoWrapper = styleButton(buttons.get(5), "Buy Crypto", buyCryptoImg);
        //ADD BUTTONS
        addAllButtons(buttonPanel);
        centerPanel.add(buttonPanel);

        // TRANSACTION HEADER PANEL (with "Transaction History" and "See all")
// TRANSACTION ROUNDED PANEL - Main container
        RoundedPanel transactionRoundedPanel = new RoundedPanel(15, style.sBlue);
        transactionRoundedPanel.setLayout(new BorderLayout());
        transactionRoundedPanel.setPreferredSize(new Dimension(380, 150));
        transactionRoundedPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

// TRANSACTION HEADER PANEL (with "Transaction History" and "See all")
        JPanel transactionHeaderPanel = createPanel(new Dimension(364, 35), null, new BorderLayout());
        transactionHeaderPanel.setOpaque(false);

        JLabel transactionLabel = createLabel("Transaction History", style.loadFont(Font.BOLD, 20f, "Quicksand-Regular"), style.dBlue);

        JLabel seeAllLabel = createLabel("See all", style.loadFont(Font.PLAIN, 14f, "Quicksand-Regular"), style.pBlue);
        seeAllLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        transactionHeaderPanel.add(transactionLabel, BorderLayout.WEST);
        transactionHeaderPanel.add(seeAllLabel, BorderLayout.EAST);

// Create transaction history content panel
        JPanel transactionContentPanel = createPanel(new Dimension(364, 240), null, null);
        transactionContentPanel.setLayout(new BoxLayout(transactionContentPanel, BoxLayout.Y_AXIS));
        transactionContentPanel.setBackground(style.sBlue);
        transactionContentPanel.setOpaque(false);

// Add transaction items (hardcoded)
        transactionContentPanel.add(createDateSection("Today"));
        transactionContentPanel.add(createTransactionItem("1:49 AM", "Pay via Scanned QR", pesoSymbol + " -100.00", false));

        transactionContentPanel.add(createDateSection("Yesterday"));
        transactionContentPanel.add(createTransactionItem("3:35 AM", "Send Money", pesoSymbol + " -135.00", false));

// Add header and content to the rounded panel
        transactionRoundedPanel.add(transactionHeaderPanel, BorderLayout.NORTH);
        transactionRoundedPanel.add(transactionContentPanel, BorderLayout.CENTER);

// Transaction container wrapper
        RoundedBorder transactionContainer = new RoundedBorder(15, style.pBlue, 2);
        transactionContainer.setLayout(new FlowLayout());
        transactionContainer.setOpaque(false);
        transactionContainer.setMaximumSize(new Dimension(390, 160));
        transactionContainer.setPreferredSize(new Dimension(390, 160));
        transactionContainer.add(transactionRoundedPanel);

        // Navigation bar at the bottom
        JPanel navBarPanel = createPanel(new Dimension(420, 60), null, new GridLayout(1, 3));
        navBarPanel.setForeground(style.dBlue);

        navBarPanel.setForeground(style.dBlue);

        // Create navigation buttons
        JPanel homeBtn = createNavButton("🏠", "Home", true, false);
        JPanel logoBtn =  createNavButton("❌", "Exit", false, true);
        JPanel profileBtn = createNavButton("👤", "Profile", false, false);

        //ADD BUTTONS TO NAV BAR
        navBarPanel.add(homeBtn);
        navBarPanel.add(logoBtn);
        navBarPanel.add(profileBtn);


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

    private JPanel createNavButton(String icon, String text, boolean isActive, boolean exit) {
        JPanel navButton = createPanel(null, style.pBlue, null);
        navButton.setLayout(new BoxLayout(navButton, BoxLayout.Y_AXIS));
        navButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        navButton.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        navButton.setPreferredSize(new Dimension(80, 45)); // Fixed height


        JLabel iconLabel = createLabel(icon, new Font("Segoe UI Emoji", Font.PLAIN, 30), null);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        iconLabel.setForeground(isActive ? Color.WHITE : new Color(150, 150, 150));

        JLabel textLabel = new JLabel(text);
        textLabel.setFont(style.loadFont(Font.PLAIN, 10f, "Quicksand-Regular"));
        textLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        textLabel.setForeground(isActive ? Color.WHITE : new Color(150, 150, 150));

        // Add some spacing between icon and text
        navButton.add(Box.createVerticalGlue());
        navButton.add(iconLabel);
        navButton.add(Box.createRigidArea(new Dimension(0, 2)));
        navButton.add(textLabel);

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

            @Override
            public void mouseClicked(MouseEvent e) {
                if (exit) {
                    System.exit(0);
                } else {
                    JOptionPane.showMessageDialog(mainFrame, "Nav Button Pressed!");
                }
            }
        });
        return navButton;
    }

    private JPanel createLogoNavButton() {
        JPanel navButton = createPanel(null, style.pBlue, null);
        navButton.setLayout(new BoxLayout(navButton, BoxLayout.Y_AXIS));
        navButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        navButton.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        navButton.setPreferredSize(new Dimension(80, 75)); // Fixed height


        Image originalImage = appLogo.getImage();
        Image resizedImage = originalImage.getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        ImageIcon resizedLogo = new ImageIcon(resizedImage);

        JLabel logoLabel = new JLabel(resizedLogo);
        logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        JLabel dummyTextLabel = new JLabel(" ");
        dummyTextLabel.setFont(style.loadFont(Font.PLAIN, 10f, "Quicksand-Regular"));
        dummyTextLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dummyTextLabel.setForeground(style.pBlue);

        navButton.add(Box.createVerticalGlue());
        navButton.add(logoLabel);
        navButton.add(Box.createRigidArea(new Dimension(0, 20)));
        navButton.add(dummyTextLabel);

        return navButton;
    }

    private ImageIcon resizeImage(String text, int width, int height) {
        //RESIZE IMAGE
        ImageIcon rawImage = new ImageIcon(text);
        Image originalImage = rawImage.getImage();
        Image resizedimage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
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
        JPanel wrapperPanel = createPanel(new Dimension(95, 90), null, new BorderLayout());

        //BUTTON
        button.setPreferredSize(new Dimension(50, 70));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setLayout(new BorderLayout());

        //IMAGE LABEL
        JLabel imageLabel =  new JLabel(image);
        imageLabel.setIcon(image);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setForeground(style.gray);

        //TEXT LABEL
        JLabel buttonLabel = createLabel(text, style.loadFont(Font.BOLD, 12f, "Quicksand-Bold"), style.dBlue);
        buttonLabel.setHorizontalAlignment(JLabel.CENTER);
        buttonLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        button.add(imageLabel, BorderLayout.CENTER);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(style.dBlue);
                buttonLabel.setForeground(style.pBlue);
                button.setPreferredSize(new Dimension(55, 75));

                revalidateParentContainers(button);
                button.repaint();
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(style.pBlue);
                buttonLabel.setForeground(style.dBlue);
                button.setPreferredSize(new Dimension(50, 70));

                revalidateParentContainers(button);
                button.repaint();
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JOptionPane.showMessageDialog(mainFrame, "Button Pressed!");
            }
        });

        wrapperPanel.add(button, BorderLayout.NORTH);
        wrapperPanel.add(buttonLabel, BorderLayout.CENTER);

        return wrapperPanel;
    }

    // Add this method to create transaction history items
    private JPanel createTransactionItem(String time, String description, String amount, boolean isPositive) {
        JPanel transactionPanel = createPanel(null, new Color(230, 240, 250), new BorderLayout());
        transactionPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // Left side: Time and description
        JPanel leftPanel = createPanel(null, new Color(230, 240, 250), null);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JLabel descLabel = new JLabel(description);
        descLabel.setFont(style.loadFont(Font.PLAIN, 14f, "Quicksand-Regular"));
        descLabel.setForeground(Color.BLACK);

        JLabel timeLabel = new JLabel(time);
        timeLabel.setFont(style.loadFont(Font.PLAIN, 12f, "Quicksand-Regular"));
        timeLabel.setForeground(Color.GRAY);

        leftPanel.add(descLabel);
        leftPanel.add(timeLabel);

        // Right side: Amount
        JLabel amountLabel = new JLabel(amount);
        amountLabel.setFont(style.loadFont(Font.BOLD, 16f, "Quicksand-Regular"));
        amountLabel.setForeground(isPositive ? new Color(0, 128, 0) : Color.RED); // Green for positive, red for negative
        amountLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        transactionPanel.add(leftPanel, BorderLayout.WEST);
        transactionPanel.add(amountLabel, BorderLayout.EAST);

        return transactionPanel;
    }

    // Add this method to create date sections
    private JPanel createDateSection(String date) {
        JPanel datePanel = createPanel(null, new Color(230, 240, 250), new BorderLayout());
        datePanel.setBorder(BorderFactory.createEmptyBorder(15, 10, 5, 10));

        JLabel dateLabel = createLabel(date, style.loadFont(Font.BOLD, 12f, "Quicksand-BOLD"), style.dBlue);
        dateLabel.setFont(style.loadFont(Font.BOLD, 16f, "Quicksand-Bold"));

        datePanel.add(dateLabel, BorderLayout.WEST);

        return datePanel;
    }

    private void revalidateParentContainers(Component button) {
        Container parent = button.getParent();
        if (parent != null) {
            parent.revalidate();
//            parent = parent.getParent();
        }
    }

    private JPanel createPanel(Dimension dim, Color color, LayoutManager layout){
        JPanel panel = new JPanel();
        panel.setPreferredSize(dim);
        panel.setBackground(color);
        panel.setLayout(layout);

        return panel;
    }

    private JLabel createLabel(String text, Font font, Color color){
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);

        return label;
    }
}

