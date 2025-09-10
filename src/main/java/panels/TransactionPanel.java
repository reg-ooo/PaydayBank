package panels;

import javax.swing.*;
import java.awt.*;
import components.*;
import data.Transaction;
import main.Payday;

public class TransactionPanel extends JPanel{
    Transaction trans;
    Style style = new Style();
    Payday db = new Payday();

    RoundedPanel transactionRoundedPanel = new RoundedPanel(15, style.sBlue);
    JPanel transactionHeaderPanel = createPanel(new Dimension(364, 35), null, new BorderLayout());
    JPanel transactionContentPanel = createPanel(new Dimension(364, 240), null, null);

    JLabel transactionLabel = createLabel("Transaction History", style.loadFont(Font.BOLD, 20f, "Quicksand-Regular"), style.dBlue);
    JLabel seeAllLabel = createLabel("See all", style.loadFont(Font.PLAIN, 14f, "Quicksand-Regular"), style.pBlue);

    public TransactionPanel() {
        this.setOpaque(false);
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

