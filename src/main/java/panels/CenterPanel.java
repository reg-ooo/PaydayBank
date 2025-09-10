package panels;

import components.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import components.*;


public class CenterPanel extends JPanel {
    Style style = new Style();

    public final JPanel payBillsWrapper,cashInWrapper, cashOutWrapper, requestMoneyWrapper, bankTransferWrapper, buyCryptoWrapper;

    JPanel centerPanel = createPanel(new Dimension(420, 200), null, new FlowLayout(FlowLayout.CENTER, 0, 2));

    ArrayList<RoundedBorder> buttons = new ArrayList<>();

    public CenterPanel() {
        //CENTER PANEL
        this.setOpaque(false);
        this.setBackground(style.white);

        centerPanel.setMaximumSize(new Dimension(420, 230));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0,0));

        //BUTTON PANELS
        JPanel buttonPanel = createPanel(null, null, new GridLayout(2, 3, 25, 10));
        for(int i = 0; i<6; i++){
            buttons.add(new RoundedBorder(25, style.vBlue, 2));
        }
        //RESIZE BUTTON IMAGES
        ImageIcon sendMoneyImg = resizeImage("sendMoney.png", 60, 60);
        ImageIcon cashInImg = resizeImage("cashIn.png", 60, 60);
        ImageIcon cashOutImg = resizeImage("cashOut.png", 60, 60);
        ImageIcon requestMoneyImg = resizeImage("requestMoney.png", 60, 60);
        ImageIcon bankTransferImg = resizeImage("bankTransfer.png", 60, 60);
        ImageIcon buyCryptoImg = resizeImage("buyCrypto.png", 60, 60);

        //Style Buttons WITH WRAPPER (ANG STYLE METHOD GINA COPY AND PASTE YUNG SAME BUTTON DESIGN)
        payBillsWrapper = styleButton(buttons.get(0), "Pay Bills", sendMoneyImg);
        cashInWrapper = styleButton(buttons.get(1), "Cash In", cashInImg);
        cashOutWrapper = styleButton(buttons.get(2), "Cash Out", cashOutImg);
        requestMoneyWrapper = styleButton(buttons.get(3), "Request Money", requestMoneyImg);
        bankTransferWrapper = styleButton(buttons.get(4), "Bank Transfer", bankTransferImg);
        buyCryptoWrapper = styleButton(buttons.get(5), "Buy Crypto", buyCryptoImg);

        //ADD BUTTONS
        addAllButtons(buttonPanel);
        centerPanel.add(buttonPanel);

        this.add(centerPanel, BorderLayout.CENTER);
    }

    private JPanel styleButton(RoundedBorder button, String text, ImageIcon image) {
        //WRAPPER PANEL
        JPanel wrapperPanel = createPanel(new Dimension(95, 90), null, new BorderLayout());

        //BUTTON
        button.setPreferredSize(new Dimension(50, 70));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setLayout(new BorderLayout());

        //IMAGE LABEL
        JLabel imageLabel =  new JLabel(image);
        imageLabel.setIcon(image);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setForeground(style.sBlue);

        //TEXT LABEL
        JLabel buttonLabel = createLabel(text, style.loadFont(Font.BOLD, 12f, "Quicksand-Regular"), style.pBlue);
        buttonLabel.setHorizontalAlignment(JLabel.CENTER);
        buttonLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        button.add(imageLabel, BorderLayout.CENTER);

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(style.dBlue);
                buttonLabel.setForeground(style.dBlue);
                button.setPreferredSize(new Dimension(55, 75));

                revalidateParentContainers(button);
                button.repaint();
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(style.vBlue);
                buttonLabel.setForeground(style.pBlue);
                button.setPreferredSize(new Dimension(50, 70));

                revalidateParentContainers(button);
                button.repaint();
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JOptionPane.showMessageDialog(centerPanel, "Button Pressed!");
            }
        });

        wrapperPanel.add(button, BorderLayout.NORTH);
        wrapperPanel.add(buttonLabel, BorderLayout.CENTER);

        return wrapperPanel;
    }

    private ImageIcon resizeImage(String text, int width, int height) {
        //RESIZE IMAGE
        ImageIcon rawImage = new ImageIcon(text);
        Image originalImage = rawImage.getImage();
        Image resizedimage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon buttonImage = new ImageIcon(resizedimage);

        return buttonImage;
    }

    private void addAllButtons(JPanel buttonPanel) {
        buttonPanel.add(payBillsWrapper);
        buttonPanel.add(cashInWrapper);
        buttonPanel.add(cashOutWrapper);
        buttonPanel.add(requestMoneyWrapper);
        buttonPanel.add(bankTransferWrapper);
        buttonPanel.add(buyCryptoWrapper);
    }

    private void revalidateParentContainers(Component button) {
        Container parent = button.getParent();
        if (parent != null) {
            parent.revalidate();
//            parent = parent.getParent();
        }
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
