package components;
import panels.CenterPanel;
import panels.GradientPanel;
import panels.NPanel;
import panels.TransactionPanel;

import javax.swing.*;
import java.awt.*;

public class ThemeManager {
    private static ThemeManager instance;
    private static boolean isDarkMode = false;

    // Light theme colors
    private static final Color lightStartColor = new Color(0x123499);
    private static final Color lightEndColor = new Color(0x1A43BF);
    private static final Color lightTransacColor = new Color(45, 55, 72);

    // Dark theme colors
    private static final Color darkStartColor = new Color(20, 30, 70);  // navy
    private static final Color darkEndColor = new Color(40, 90, 160); // deep blue

    public Color getDarkStartColor() { return darkStartColor; }
    public Color getDarkEndColor() { return darkEndColor; }

    private ThemeManager() {}

    public static ThemeManager getInstance() {
        if (instance == null) {
            instance = new ThemeManager();
        }
        return instance;
    }

    public void toggleTheme() {
        isDarkMode = !isDarkMode;
        // You can also fire an event here to notify all components to update
    }

    public boolean isDarkMode() {
        return isDarkMode;
    }

    public void applyTheme(Component comp) {
        if (comp instanceof GradientPanel gp) {
            // Gradient panels (backgrounds)
            if (isDarkMode) {
                gp.setGradientColors(getDarkStartColor(), getDarkEndColor());
            } else {
                gp.setGradientColors(lightStartColor, lightEndColor);
            }
        }
        else if (comp instanceof NavigationBar nb) {
            // NavigationBar (uses GradientPanel inside)
            if (isDarkMode) {
                nb.navBarPanel.setGradientColors(getDarkStartColor(), getDarkEndColor());
                nb.navBarPanel.setBackground(Color.black);
            } else {
                nb.navBarPanel.setGradientColors(lightStartColor, lightEndColor);
                nb.navBarPanel.setBackground(Color.white);
            }
        }
        else if (comp instanceof NPanel np) {
            np.setBackground(isDarkMode ? Color.BLACK : Color.WHITE);
        }
        else if (comp instanceof CenterPanel cp) {
            // CenterPanel background only
            cp.setBackground(isDarkMode ? Color.BLACK : Color.WHITE);
            cp.centerPanel.setBackground(isDarkMode ? Color.BLACK : Color.WHITE);
        }
        else if (comp instanceof TransactionPanel tp) {
            tp.applyTheme(isDarkMode);
        }

        // Recurse for children
        if (comp instanceof Container container) {
            for (Component child : container.getComponents()) {
                if (child instanceof JComponent jc) {
                    applyTheme(jc);
                }
            }
        }

        comp.revalidate();
        comp.repaint();
    }


}
