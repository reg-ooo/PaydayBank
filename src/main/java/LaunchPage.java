import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.io.*;

public class LaunchPage extends JFrame{
    JFrame mainFrame = new JFrame();

    String pesoSymbol = "\u20B1";

    // Color
    Color gray = new Color(0xD3D3D3);
    Color pBlue = new Color(0x2D76BD);
    Color dBlue = new Color(0x163F5C);
    Color lBlue = new Color(0xC4E4FF);
    Color black = new Color(0x000000);
    Color white = new Color(0xFFFFFF);

    // Images
    ImageIcon appLogo = new ImageIcon("appLogo.png");

    public LaunchPage(){
        //Main Frame
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(420, 750);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setTitle("Payday Bank");
        mainFrame.setIconImage(appLogo.getImage());
        mainFrame.setResizable(false);
        mainFrame.setLayout(new BorderLayout());

        // Panels
        JPanel nPanel = new JPanel();
        JPanel headerPanel = new JPanel();
        JPanel upperBalancePanel = new JPanel();
        JPanel amountPanel = new JPanel();

        // Labels
        JLabel balanceText = new JLabel("Available Balance: ");
        JLabel pesoSign = new JLabel(pesoSymbol);
        JLabel amountText = new JLabel(String.format( "%s 15,000", pesoSymbol));

        // North Panel
        nPanel.setLayout(new FlowLayout());
        nPanel.setPreferredSize(new Dimension(360, 120));

        // Balance Panel
        RoundedPanel balPanel = new RoundedPanel(15, pBlue);
        balPanel.setLayout(new BorderLayout());
        balPanel.setPreferredSize(new Dimension(360, 80));

        balanceText.setFont(new Font("Open Sans", Font.BOLD, 12));
        balanceText.setForeground(gray);
        balanceText.setHorizontalTextPosition(JLabel.LEFT);

        upperBalancePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
        upperBalancePanel.setOpaque(false);
        upperBalancePanel.setPreferredSize(new Dimension(360, 30));

        amountText.setFont(loadKarlaFont(Font.PLAIN, 35f));
        amountText.setVerticalTextPosition(JLabel.CENTER);
        amountText.setForeground(white);

        amountPanel.setOpaque(false);
        amountPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 0));

        // Header Panel
        headerPanel.setPreferredSize(new Dimension(420, 15));

        balPanel.add(amountPanel);
        balPanel.add(upperBalancePanel, BorderLayout.NORTH);

        amountPanel.add(amountText);
        upperBalancePanel.add(balanceText);

        nPanel.add(headerPanel);
        nPanel.add(balPanel);

        mainFrame.add(nPanel, BorderLayout.NORTH);
        mainFrame.setVisible(true);
    }

    private Font loadKarlaFont(int style, float size) {
        try {
            InputStream fontStream = getClass().getResourceAsStream("/fonts/Quicksand-Regular.ttf");
            Font karlaFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);
            return karlaFont.deriveFont(style, size);
        } catch (Exception e) {
            System.out.println("Could not load Karla font: " + e.getMessage());

            return new Font("Arial", style, (int)size);
        }
    }

}
