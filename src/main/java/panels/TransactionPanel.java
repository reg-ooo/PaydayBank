package panels;

import javax.swing.*;
import java.awt.*;
import components.*;

public class TransactionPanel extends JPanel{
    Style style = new Style();

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
        transactionContentPanel.add(createDateSection("Today"));
        transactionContentPanel.add(createTransactionItem("1:49 AM", "Pay via Scanned QR", style.pesoSymbol + " -100.00", false));

        transactionContentPanel.add(createDateSection("Yesterday"));
        transactionContentPanel.add(createTransactionItem("3:35 AM", "Send Money", style.pesoSymbol + " -135.00", false));

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
}
