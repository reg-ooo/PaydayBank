package pages;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import components.*;
import panels.*;

public class LaunchPage extends JPanel {
    private Style style = new Style();
    private NPanel nPanel = new NPanel();
    private TransactionPanel tPanel = new TransactionPanel();
    private NavigationBar navBarPanel = new NavigationBar();
    private CenterPanel centerPanel = new CenterPanel();

    ArrayList<RoundedBorder> buttons = new ArrayList<>();

    public LaunchPage() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel mainContentPanel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        mainContentPanel.setLayout(cardLayout);
        mainContentPanel.add(centerPanel, "main");
        mainContentPanel.add(tPanel, "transaction");

        add(nPanel, BorderLayout.NORTH);
        add(mainContentPanel, BorderLayout.CENTER);
        add(navBarPanel, BorderLayout.SOUTH);

        cardLayout.show(mainContentPanel, "main");
    }
}