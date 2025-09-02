package panels;

import javax.swing.*;
import java.awt.*;
import components.*;

public class TransactionPanel extends JPanel {
    Style style = new Style();

    RoundedPanel transactionRoundedPanel = new RoundedPanel(15, style.sBlue);
    JPanel transactionHeaderPanel = createPanel(new Dimension(364, 35), null, new BorderLayout());
    JPanel transactionContentPanel = createPanel(null, null, null); // Removed fixed height

    JLabel transactionLabel = createLabel("Transaction History", style.loadFont(Font.BOLD, 20f, "Quicksand-Regular"), style.dBlue);
    JLabel seeAllLabel = createLabel("See all", style.loadFont(Font.PLAIN, 14f, "Quicksand-Regular"), style.pBlue);

    public TransactionPanel() {
        this.setOpaque(false);
        this.setBackground(style.white);

        transactionRoundedPanel.setLayout(new BorderLayout());
        transactionRoundedPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

        transactionHeaderPanel.setOpaque(false);
        seeAllLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        transactionHeaderPanel.add(transactionLabel, BorderLayout.WEST);
        transactionHeaderPanel.add(seeAllLabel, BorderLayout.EAST);

        transactionContentPanel.setLayout(new BoxLayout(transactionContentPanel, BoxLayout.Y_AXIS));
        transactionContentPanel.setBackground(style.sBlue);
        transactionContentPanel.setOpaque(false);

        transactionContentPanel.add(createDateSection("Today"));
        transactionContentPanel.add(createTransactionItem("1:49 AM", "Pay via Scanned QR", style.pesoSymbol + " -100.00", false));
        transactionContentPanel.add(createDateSection("Yesterday"));
        transactionContentPanel.add(createTransactionItem("3:35 AM", "Send Money", style.pesoSymbol + " -135.00", false));

        transactionRoundedPanel.add(transactionHeaderPanel, BorderLayout.NORTH);
        transactionRoundedPanel.add(transactionContentPanel, BorderLayout.CENTER);

        RoundedBorder transactionContainer = new RoundedBorder(15, style.vBlue, 2);
        transactionContainer.setLayout(new FlowLayout());
        transactionContainer.setOpaque(false);
        transactionContainer.setMaximumSize(new Dimension(390, 300)); // Increased height to accommodate more content
        transactionContainer.setPreferredSize(new Dimension(390, 300));
        transactionContainer.add(transactionRoundedPanel);

        this.add(transactionContainer, BorderLayout.CENTER);
    }

    private JPanel createTransactionItem(String time, String description, String amount, boolean isPositive) {
        JPanel transactionPanel = createPanel(null, new Color(230, 240, 250), new BorderLayout());
        transactionPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

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

        JLabel amountLabel = new JLabel(amount);
        amountLabel.setFont(style.loadFont(Font.BOLD, 16f, "Quicksand-Regular"));
        amountLabel.setForeground(isPositive ? new Color(0, 128, 0) : Color.RED);
        amountLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        transactionPanel.add(leftPanel, BorderLayout.WEST);
        transactionPanel.add(amountLabel, BorderLayout.EAST);

        return transactionPanel;
    }

    private JPanel createPanel(Dimension dim, Color color, LayoutManager layout) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(dim);
        panel.setBackground(color);
        panel.setLayout(layout);
        return panel;
    }

    private JLabel createLabel(String text, Font font, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        return label;
    }

    private JPanel createDateSection(String date) {
        JPanel datePanel = createPanel(null, new Color(230, 240, 250), new BorderLayout());
        datePanel.setBorder(BorderFactory.createEmptyBorder(15, 10, 5, 10));

        JLabel dateLabel = createLabel(date, style.loadFont(Font.BOLD, 16f, "Quicksand-Bold"), style.dBlue);
        datePanel.add(dateLabel, BorderLayout.WEST);

        return datePanel;
    }
}