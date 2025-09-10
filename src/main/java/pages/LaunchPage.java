package pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
import java.io.*;
import java.util.ArrayList;
import components.*;
import panels.*;

public class LaunchPage extends JPanel {
    private Style style = new Style();

    // Main panels
    NPanel nPanel = new NPanel();
    TransactionPanel tPanel = new TransactionPanel();
    NavigationBar navBarPanel = new NavigationBar();
    CenterPanel centerPanel = new CenterPanel();

    ArrayList<RoundedBorder> buttons = new ArrayList<>();

    public LaunchPage() {
        // Set up this JPanel with BorderLayout (same as the frame had)
        setLayout(new BorderLayout());
        setBackground(Color.white);

        // Create main content panel (same structure as before)
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        mainContentPanel.add(centerPanel);
        mainContentPanel.add(Box.createVerticalStrut(50));
//        mainContentPanel.add(tPanel);
        mainContentPanel.setBackground(style.white);

        // Add components in the same layout as the frame had
        add(nPanel, BorderLayout.NORTH);
        add(mainContentPanel, BorderLayout.CENTER);
        add(navBarPanel, BorderLayout.SOUTH);

        // Set the preferred size to match the original frame size
        setPreferredSize(new Dimension(420, 650));
    }
}