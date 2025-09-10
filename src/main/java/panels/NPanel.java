package panels;

import javax.swing.*;
import java.awt.*;
import components.*;
import main.Payday;
import javax.swing.border.EmptyBorder;

public class NPanel extends JPanel {
    Payday db = new Payday();
    //STYLE
    Style style = new Style();

    ThemeManager themeManager = ThemeManager.getInstance();

    //BUTTON
    ImageIcon icon = resizeImage("darkModeOn.png", 50, 50);
    JButton balanceButton = new JButton(icon);


    //UPPER PANELS
    JPanel containerPanel = createPanel(new Dimension(360, 150), null, new FlowLayout());
    JPanel headerPanel = createPanel(new Dimension(420, 15), null, null);
    JPanel upperBalancePanel = createPanel(new Dimension(360, 45), null, new FlowLayout(FlowLayout.LEFT, 15, 10));
    JPanel amountPanel = createPanel(new Dimension(420, 200), null, new FlowLayout(FlowLayout.LEFT, 15, 0));

    GradientPanel balPanel = new GradientPanel(style.dvBlue, style.vBlue, 15);


    //LABELS
    JLabel balanceText = createLabel("Available Balance: ", style.loadFont(Font.BOLD, 18f, "Quicksand-Regular"), style.white);
    JLabel amountText = createLabel(String.format( "%s %.2f", style.pesoSymbol, getBalance()), style.loadFont(Font.PLAIN, 40f, "Quicksand-Regular"), style.white);

    public NPanel() {
        this.setLayout(new BorderLayout());
        this.setBackground(style.white);
        this.setOpaque(false);

        balPanel.setLayout(new BorderLayout());
        balPanel.setPreferredSize(new Dimension(360, 110));
        balPanel.setOpaque(false);

        upperBalancePanel.setOpaque(false);
        amountPanel.setOpaque(false);

        balanceText.setHorizontalTextPosition(JLabel.LEFT);
        amountText.setVerticalTextPosition(JLabel.CENTER);

        balanceText.setBorder(new EmptyBorder(0, 10, 0, 0));  // top, left, bottom, right

// Add some top padding to the button

        balPanel.add(amountPanel);
        balPanel.add(upperBalancePanel, BorderLayout.NORTH);

        amountPanel.add(amountText);
        upperBalancePanel.setLayout(new BorderLayout());

        styleDarkModeButton(balanceButton);

        upperBalancePanel.add(balanceText, BorderLayout.WEST);
        upperBalancePanel.add(balanceButton, BorderLayout.EAST);
        upperBalancePanel.add(balanceText);

        containerPanel.add(headerPanel);
        containerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        containerPanel.add(balPanel);

        this.add(containerPanel, BorderLayout.CENTER);
    }

    private void styleDarkModeButton(JButton button){
        balanceButton.setContentAreaFilled(false);
        balanceButton.setBorderPainted(false);
        balanceButton.setFocusPainted(false);
        balanceButton.setOpaque(false);

        balanceButton.setBorder(new EmptyBorder(5, 10, 0, 0)); // move down 5px, right 5px

        balanceButton.addActionListener(e -> {
            themeManager.toggleTheme();
            themeManager.applyTheme(SwingUtilities.getWindowAncestor(this));

            if (themeManager.isDarkMode()) {
                balanceButton.setIcon(resizeImage("darkModeOff.png", 50, 50)); // sun bigger
                this.setBackground(style.black);
            } else {
                balanceButton.setIcon(resizeImage("darkModeOn.png", 50, 50));  // moon bigger
                this.setBackground(style.white);
            }

            themeManager.applyTheme(this);

            // Force repaint
            this.revalidate();
            this.repaint();
        });

        balanceButton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (themeManager.isDarkMode()) {
                    balanceButton.setIcon(resizeImage("darkModeOff.png", 55, 55)); // sun bigger
                } else {
                    balanceButton.setIcon(resizeImage("darkModeOn.png", 55, 55));  // moon bigger
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (themeManager.isDarkMode()) {
                    balanceButton.setIcon(resizeImage("darkModeOff.png", 50, 50)); // sun normal
                } else {
                    balanceButton.setIcon(resizeImage("darkModeOn.png", 50, 50));  // moon normal
                }
            }
        });
    }

    private ImageIcon resizeImage(String text, int width, int height) {
        //RESIZE IMAGE

        ImageIcon rawImage = new ImageIcon(text);
        Image originalImage = rawImage.getImage();
        Image resizedimage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon buttonImage = new ImageIcon(resizedimage);

        return buttonImage;
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

    private double getBalance(){
        double balance = 0;

        String query = "select balance from  Wallets where userID = 1;";

        try{
            db.rs = db.st.executeQuery(query);
            db.rs.next();
            balance = db.rs.getDouble("balance");
        }catch (Exception e){
            e.printStackTrace();
        }

        return balance;
    }
}
