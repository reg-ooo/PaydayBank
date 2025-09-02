package pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
import java.io.*;
import java.util.ArrayList;
import components.*;
import panels.*;

public class LaunchPage extends JFrame {
    private Style style = new Style();
    RoundedFrame mainFrame = new RoundedFrame(30);
    final ImageIcon appLogo = new ImageIcon("appLogo.png");

    NPanel nPanel = new NPanel();
    TransactionPanel tPanel = new TransactionPanel();
    NavigationBar navBarPanel = new NavigationBar();
    CenterPanel centerPanel = new CenterPanel();

    ArrayList<RoundedBorder> buttons = new ArrayList<>();
    public LaunchPage(){
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(420, 650);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setTitle("Payday Bank");
        mainFrame.setIconImage(appLogo.getImage());
        mainFrame.setResizable(false);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setUndecorated(true);
        mainFrame.setBackground(Color.WHITE);
        mainFrame.getContentPane().setBackground(Color.WHITE);

        JPanel mainContentPanel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        mainContentPanel.setLayout(cardLayout);
        mainContentPanel.add(centerPanel, "main");
        mainContentPanel.add(tPanel, "transaction");

        mainFrame.add(nPanel, BorderLayout.NORTH);
        mainFrame.add(mainContentPanel, BorderLayout.CENTER);
        mainFrame.add(navBarPanel, BorderLayout.SOUTH);

        cardLayout.show(mainContentPanel, "main");

        mainFrame.setVisible(true);
    }
}