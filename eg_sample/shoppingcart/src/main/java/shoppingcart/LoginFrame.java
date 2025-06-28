package shoppingcart;

import javax.swing.*;
import java.sql.*;

public class LoginFrame extends JFrame {
    JTextField userField;
    JPasswordField passField;
    JButton loginBtn, registerBtn;
    UserService userService = new UserService();

    public LoginFrame() {
        setTitle("Login / Register");
        setSize(300, 200);
        setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(20, 20, 80, 25);
        add(userLabel);

        userField = new JTextField();
        userField.setBounds(100, 20, 150, 25);
        add(userField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(20, 60, 80, 25);
        add(passLabel);

        passField = new JPasswordField();
        passField.setBounds(100, 60, 150, 25);
        add(passField);

        loginBtn = new JButton("Login");
        loginBtn.setBounds(40, 100, 80, 25);
        add(loginBtn);

        registerBtn = new JButton("Register");
        registerBtn.setBounds(140, 100, 100, 25);
        add(registerBtn);

        loginBtn.addActionListener(e -> {
            try {
                String username = userField.getText();
                String password = new String(passField.getPassword());
                int id = userService.login(username, password);
                boolean isAdmin = userService.isAdmin(id);
                if (id != -1) {
                    dispose();
                    if (isAdmin) new AdminFrame();
                    else new ProductFrame(id);
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid credentials");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        registerBtn.addActionListener(e -> {
            try {
                String username = userField.getText();
                String password = new String(passField.getPassword());
                userService.register(username, password);
                JOptionPane.showMessageDialog(this, "Registered successfully!");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}