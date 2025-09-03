package panels;

import javax.swing.*;
import java.awt.*;
import components.*;

public class NPanel extends JPanel {

    //STYLE
    Style style = new Style();

    //UPPER PANELS
    JPanel containerPanel = createPanel(new Dimension(360, 150), null, new FlowLayout());
    JPanel headerPanel = createPanel(new Dimension(420, 15), null, null);
    JPanel upperBalancePanel = createPanel(new Dimension(360, 30), null, new FlowLayout(FlowLayout.LEFT, 15, 10));
    JPanel amountPanel = createPanel(new Dimension(420, 200), null, new FlowLayout(FlowLayout.LEFT, 15, 0));

    GradientPanel balPanel = new GradientPanel(style.dvBlue, style.vBlue, 15);


    //LABELS
    JLabel balanceText = createLabel("Available Balance: ", style.loadFont(Font.BOLD, 18f, "Quicksand-Regular"), style.white);
    JLabel amountText = createLabel(String.format( "%s 15,000", style.pesoSymbol), style.loadFont(Font.PLAIN, 40f, "Quicksand-Regular"), style.white);

    public NPanel() {
        this.setLayout(new BorderLayout());
        this.setBackground(style.white);
        this.setOpaque(false);

        balPanel.setLayout(new BorderLayout());
        balPanel.setPreferredSize(new Dimension(360, 110));

        upperBalancePanel.setOpaque(false);
        amountPanel.setOpaque(false);

        balanceText.setHorizontalTextPosition(JLabel.LEFT);
        amountText.setVerticalTextPosition(JLabel.CENTER);

        balPanel.add(amountPanel);
        balPanel.add(upperBalancePanel, BorderLayout.NORTH);

        amountPanel.add(amountText);
        upperBalancePanel.add(balanceText);

        containerPanel.add(headerPanel);
        containerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        containerPanel.add(balPanel);

        this.add(containerPanel, BorderLayout.CENTER);
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
