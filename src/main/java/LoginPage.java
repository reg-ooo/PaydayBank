import javax.swing.*;
import java.awt.*;

public class LoginPage extends JFrame{
    Users user = new Users();
    LaunchPage launchP =  new LaunchPage();
    public LoginPage() {
        // Panels
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new FlowLayout());

        // Text Fields
        JTextField userField = new JTextField();
        JTextField passField = new JTextField();
        userField.setPreferredSize(new Dimension(200, 30));
        passField.setPreferredSize(new Dimension(200, 30));
        JButton registerButton = new JButton("Register");
        JButton loginButton = new JButton("Login");

        // Button Actions
        registerButton.addActionListener(e -> {
            if(userField.getText().isEmpty()|| passField.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Please fill in all fields!");
                return;
            }
            user.addUser(userField.getText(), passField.getText());
            userField.setText("");
            passField.setText("");
        });

        loginButton.addActionListener(e -> {
            if(userField.getText().isEmpty()|| passField.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Please fill in all fields!");
                return;
            }
            user.loginAccount(userField.getText(), passField.getText());
            userField.setText("");
            passField.setText("");
        });



        userPanel.add(userField);
        userPanel.add(passField);
        userPanel.add(registerButton);
        userPanel.add(loginButton);
        launchP.mainFrame.add(userPanel, BorderLayout.CENTER);
    }
}
