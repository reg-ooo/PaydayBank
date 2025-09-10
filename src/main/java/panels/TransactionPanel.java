package panels;

import javax.swing.*;
import java.awt.*;
import components.*;
import data.Transaction;
import main.Payday;

public class TransactionPanel extends JPanel{
    Transaction trans;
    public Style style = new Style();
    Payday db = new Payday();

    public RoundedPanel transactionRoundedPanel = new RoundedPanel(15, style.sBlue);
    JPanel transactionHeaderPanel = createPanel(new Dimension(364, 35), null, new BorderLayout());
    JPanel transactionContentPanel = createPanel(new Dimension(364, 240), null, null);

    public JLabel transactionLabel = createLabel("Transaction History", style.loadFont(Font.BOLD, 20f, "Quicksand-Regular"), style.dBlue);
    public JLabel seeAllLabel = createLabel("See all", style.loadFont(Font.PLAIN, 14f, "Quicksand-Regular"), style.pBlue);

    public TransactionPanel() {
        this.setOpaque(true);
        this.setBackground(style.white);

        // TRANSACTION HEADER PANEL (with "Transaction History" and "See all")
// TRANSACTION ROUNDED PANEL - Main container;
        transactionRoundedPanel.setLayout(new BorderLayout());
        transactionRoundedPanel.setPreferredSize(new Dimension(380, 150));
        transactionRoundedPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

// TRANSACTION HEADER PANEL (with "Transaction History" and "See all")
        transactionHeaderPanel.setOpaque(false);


        seeAllLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        transactionHeaderPanel.add(transactionLabel, BorderLayout.WEST);
        transactionHeaderPanel.add(seeAllLabel, BorderLayout.EAST);

// Create transaction history content panel
        transactionContentPanel.setLayout(new BoxLayout(transactionContentPanel, BoxLayout.Y_AXIS));
        transactionContentPanel.setBackground(style.sBlue);
        transactionContentPanel.setOpaque(false);

// Add transaction items (hardcoded)
        String time = getTransaction().getDate().substring(getTransaction().getDate().indexOf(" "), getTransaction().getDate().length() - 3);
        String validatedTime = checkTime(time);
        transactionContentPanel.add(createDateSection(getTransaction().getDate().substring(0, getTransaction().getDate().indexOf(" "))));
        transactionContentPanel.add(createTransactionItem(validatedTime, getTransaction().getType(), style.pesoSymbol + getTransaction().getAmount(), !getTransaction().getType().equals("send")));

// Add header and content to the rounded panel
        transactionRoundedPanel.add(transactionHeaderPanel, BorderLayout.NORTH);
        transactionRoundedPanel.add(transactionContentPanel, BorderLayout.CENTER);

// Transaction container wrapper
        RoundedBorder transactionContainer = new RoundedBorder(15, style.vBlue, 2);
        transactionContainer.setLayout(new FlowLayout());
        transactionContainer.setOpaque(false);
        transactionContainer.setMaximumSize(new Dimension(390, 160));
        transactionContainer.setPreferredSize(new Dimension(390, 160));
        transactionContainer.add(transactionRoundedPanel);

        this.add(transactionContainer, BorderLayout.CENTER);
    }

    // Add this method to create transaction history items
    private JPanel createTransactionItem(String time, String description, String amount, boolean isPositive) {
        JPanel transactionPanel = createPanel(null, null, new BorderLayout()); // no hardcoded bg
        transactionPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JPanel leftPanel = createPanel(null, null, null); // no bg here either
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        JLabel descLabel = new JLabel(description);
        descLabel.setFont(style.loadFont(Font.PLAIN, 14f, "Quicksand-Regular"));
        descLabel.setForeground(Color.BLACK);

        JLabel timeLabel = new JLabel(time);
        timeLabel.setFont(style.loadFont(Font.PLAIN, 12f, "Quicksand-Regular"));
        timeLabel.setForeground(Color.GRAY);

        leftPanel.add(descLabel);
        leftPanel.add(timeLabel);

        JLabel amountLabel = new JLabel(amount);
        amountLabel.setFont(style.loadFont(Font.BOLD, 16f, "Quicksand-Regular"));
        amountLabel.setForeground(isPositive ? new Color(0, 128, 0) : Color.RED);
        amountLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        transactionPanel.add(leftPanel, BorderLayout.WEST);
        transactionPanel.add(amountLabel, BorderLayout.EAST);

        return transactionPanel;
    }

    public void applyTheme(boolean isDarkMode) {
        // Background of the whole panel
        this.setBackground(isDarkMode ? Color.BLACK : style.white);

        // Card backgrounds
        Color lightCard = new Color(230, 240, 250);   // sky blue
        Color darkCard  = new Color(45, 55, 72);      // dark slate blue
        transactionRoundedPanel.setBackground(isDarkMode ? darkCard : lightCard);

        // Header labels
        transactionLabel.setForeground(isDarkMode ? Color.WHITE : style.dBlue);
        seeAllLabel.setForeground(isDarkMode ? new Color(150, 180, 250) : style.pBlue);

        // Update children inside content panel
        for (Component comp : transactionContentPanel.getComponents()) {
            if (comp instanceof JPanel panel) {
                panel.setBackground(isDarkMode ? darkCard : lightCard);

                for (Component sub : panel.getComponents()) {
                    if (sub instanceof JLabel lbl) {
                        String text = lbl.getText();

                        // Date labels
                        if (text.matches("\\d{4}-\\d{2}-\\d{2}")) {
                            lbl.setForeground(isDarkMode ? new Color(180, 200, 250) : style.dBlue);
                        }
                        // Time labels
                        else if (lbl.getForeground().equals(Color.GRAY)) {
                            lbl.setForeground(isDarkMode ? new Color(200, 200, 200) : Color.GRAY);
                        }
                        // Description labels
                        else if (lbl.getForeground().equals(Color.BLACK)) {
                            lbl.setForeground(isDarkMode ? Color.WHITE : Color.BLACK);
                        }
                        // Amount stays green/red, no change
                    }
                }
            }
        }

        revalidate();
        repaint();
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

    // Add this method to create date sections
    private JPanel createDateSection(String date) {
        JPanel datePanel = createPanel(null, new Color(230, 240, 250), new BorderLayout());
        datePanel.setBorder(BorderFactory.createEmptyBorder(15, 10, 5, 10));

        JLabel dateLabel = createLabel(date, style.loadFont(Font.BOLD, 12f, "Quicksand-BOLD"), style.dBlue);
        dateLabel.setFont(style.loadFont(Font.BOLD, 16f, "Quicksand-Bold"));

        datePanel.add(dateLabel, BorderLayout.WEST);

        return datePanel;
    }

    public Transaction getTransaction() {
        String query1 = "select * from Transactions where transactionID = 1;";
        try {
            db.rs = db.st.executeQuery(query1);
            db.rs.next();
            trans = new Transaction(db.rs.getString("transactionType"), db.rs.getDouble("amount"), db.rs.getString("transactionDate"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return trans;
    }

    public String checkTime(String time){
        int hour = Integer.parseInt(time.substring(0, 3).trim());
        System.out.println(hour);
        if(hour >= 12){
            return hour - 12 + time.substring(3) + "PM";
        }
        else{
            return hour + time.substring(3) + "AM";
        }
    }
}

