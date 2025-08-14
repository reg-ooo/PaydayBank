import javax.swing.*;
import java.awt.*;


public class LaunchPage extends JFrame{
    Users user = new Users();
    JFrame mainFrame = new JFrame();

    public LaunchPage(){
        //Main Frame
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(500, 500);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setTitle("Payday Bank");
        mainFrame.setResizable(false);
        mainFrame.setLayout(new BorderLayout());
//        Panels
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new FlowLayout());


//        Text Field
        JTextField userField = new JTextField();
        JTextField passField = new JTextField();
        userField.setPreferredSize(new Dimension(200, 30));
        passField.setPreferredSize(new Dimension(200, 30));
        JButton registerButton = new JButton("Register");
        JButton loginButton = new JButton("Login");

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
        mainFrame.add(userPanel, BorderLayout.CENTER);
        mainFrame.setVisible(true);
    }
}
