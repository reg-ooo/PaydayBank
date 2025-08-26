import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class LoginPage extends JFrame{
    Style style = new Style();
    Users user = new Users();
    JFrame loginFrame = new JFrame();
    JFrame registerFrame = new JFrame();
    ImageIcon appLogo = new ImageIcon("appLogo.png");
    boolean userValid = false, passValid = false;

    public LoginPage() {
        loginFrame.setSize(420, 750);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setTitle("Payday Bank");
        loginFrame.setIconImage(appLogo.getImage());
        loginFrame.setResizable(false);
        loginFrame.setBackground(Color.white);

        // Panels
        JPanel nPanel = new JPanel();
        JPanel loginPanel = new JPanel();
        JPanel registerPanel = new JPanel();
        RoundedPanel rPanelLogin = new RoundedPanel(15, Color.white);
        RoundedPanel rPanelRegister = new RoundedPanel(15, Color.white);
        JPanel cLoginPanel = new JPanel();
        JPanel cRegisterPanel = new JPanel();
        JPanel inputPanel = new JPanel();

        // North Panel
        nPanel.setPreferredSize(new Dimension(420, 50));
        nPanel.setLayout(new GridLayout(1,2));
        nPanel.setOpaque(false);

        loginPanel.setLayout(new BorderLayout());
        loginPanel.setPreferredSize(new Dimension(210, 50));

        registerPanel.setLayout(new BorderLayout());
        registerPanel.setPreferredSize(new Dimension(210, 50));

        rPanelLogin.setPreferredSize(new Dimension(170, 5));
        rPanelLogin.setBackground(style.pBlue);

        rPanelRegister.setPreferredSize(new Dimension(170, 5));
        rPanelRegister.setBackground(style.transparent);

        // North Bottom Panels
        JPanel loginBottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        loginBottomPanel.setOpaque(false);
        loginBottomPanel.setPreferredSize(new Dimension(210, 5));

        JPanel registerBottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        registerBottomPanel.setOpaque(false);
        registerBottomPanel.setPreferredSize(new Dimension(210, 5));

        // Label Container
        JPanel loginLabelContainer = new JPanel();
        JPanel registerLabelContainer = new JPanel();

        loginLabelContainer.setPreferredSize(new Dimension(60, 30));
        loginLabelContainer.setOpaque(false);
        loginLabelContainer.setLayout(new FlowLayout(FlowLayout.RIGHT, 60, 20));

        registerLabelContainer.setPreferredSize(new Dimension(60, 30));
        registerLabelContainer.setOpaque(false);
        registerLabelContainer.setLayout(new FlowLayout(FlowLayout.LEFT, 50, 20));

        // North Labels
        JLabel loginLabel = new JLabel("Login");
        JLabel registerLabel = new JLabel("Sign Up");

        loginLabel.setFont(style.loadFont(Font.BOLD, 18f, "Quicksand-Regular"));
        loginLabel.setForeground(style.dBlue);
        registerLabel.setFont(style.loadFont(Font.BOLD, 18f, "Quicksand-Regular"));
        registerLabel.setForeground(new Color(178, 177, 177));

        // Top Area
        loginLabelContainer.add(loginLabel);
        registerLabelContainer.add(registerLabel);
        loginBottomPanel.add(rPanelLogin);
        registerBottomPanel.add(rPanelRegister);
        loginPanel.add(loginBottomPanel, BorderLayout.SOUTH);
        loginPanel.add(loginLabelContainer, BorderLayout.CENTER);
        registerPanel.add(registerLabelContainer, BorderLayout.CENTER);
        registerPanel.add(registerBottomPanel, BorderLayout.SOUTH);
        nPanel.add(loginPanel);
        nPanel.add(registerPanel);

        // Center Area Login
        cLoginPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 30));
        cLoginPanel.setBackground(Color.white);

        inputPanel.setPreferredSize(new Dimension(350, 160));
        inputPanel.setBackground(Color.white);
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));

        // Center Text Fields
        RoundedTextField userField = new RoundedTextField(13, new Color(234,232,228), style.transparent, 3);
        RoundedTextField passField = new RoundedTextField(13, new Color(234,232,228), style.transparent, 3);

        userField.setPreferredSize(new Dimension(350, 50));
        userField.setMaximumSize(new Dimension(350, 50));

        passField.setPreferredSize(new Dimension(350, 50));
        passField.setMaximumSize(new Dimension(350, 50));

        // Center Labels
        JLabel userLabel = new JLabel("Your Username");
        JLabel passLabel = new JLabel("Your Password");

        userLabel.setFont(style.loadFont(Font.BOLD, 12f, "Quicksand-Bold"));
        userLabel.setHorizontalAlignment(JLabel.LEFT);
        passLabel.setFont(style.loadFont(Font.BOLD, 12f, "Quicksand-Bold"));

        userLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        passLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        userField.setAlignmentX(Component.LEFT_ALIGNMENT);
        passField.setAlignmentX(Component.LEFT_ALIGNMENT);

        inputPanel.add(Box.createVerticalStrut(10));
        inputPanel.add(userLabel);
        inputPanel.add(Box.createVerticalStrut(5));
        inputPanel.add(userField);
        inputPanel.add(Box.createVerticalStrut(15));
        inputPanel.add(passLabel);
        inputPanel.add(Box.createVerticalStrut(5));
        inputPanel.add(passField);
        inputPanel.add(Box.createVerticalGlue());

        // Buttons
        RoundedButton loginButton = new RoundedButton("Log In", 15, style.pBlue);
        loginButton.setPreferredSize(new Dimension(350, 50));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(style.loadFont(Font.BOLD, 14f, "Quicksand-Bold"));

        loginButton.addActionListener(e -> {
            if(userField.getText().isEmpty()){
                userField.setBorderColor(Color.red);
                userValid = false;
            }
            else{
                userValid = true;
            }
            if(passField.getText().isEmpty()){
                passField.setBorderColor(Color.red);
                passValid = false;
            }
            else{
                passValid = true;
            }

            if(userValid){
                userField.setBorderColor(new Color(234,232,228));
            }
            if(passValid){
                passField.setBorderColor(new Color(234,232,228));
            }
            loginFrame.repaint();
            System.out.println("press");
            if(userValid && passValid){
                user.loginAccount(userField.getText(), passField.getText());
            }

            this.dispose();
            new LaunchPage();
        });

        cLoginPanel.add(inputPanel);
        cLoginPanel.add(loginButton);

        // Center Area Register
        cRegisterPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 30));
        cRegisterPanel.setBackground(Color.white);

        loginFrame.add(nPanel, BorderLayout.NORTH);
        loginFrame.add(cLoginPanel, BorderLayout.CENTER);

        // loginPanel
        loginPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                rPanelLogin.setBackground(style.pBlue);
                rPanelRegister.setBackground(style.transparent);

                loginLabel.setForeground(style.dBlue);
                registerLabel.setForeground(new Color(178, 177, 177));

                loginFrame.remove(cRegisterPanel);
                loginFrame.add(cLoginPanel, BorderLayout.CENTER);
                loginFrame.revalidate();
                loginFrame.repaint();
            }
        });

        // registerPanel
        registerPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e){
                rPanelRegister.setBackground(style.pBlue);
                rPanelLogin.setBackground(style.transparent);

                loginLabel.setForeground(new Color(178, 177, 177));
                registerLabel.setForeground(style.dBlue);

                loginFrame.remove(cLoginPanel);
                loginFrame.add(cRegisterPanel, BorderLayout.CENTER);
                loginFrame.revalidate();
                loginFrame.repaint();
            }
        });

        loginFrame.setVisible(true);
    }
}