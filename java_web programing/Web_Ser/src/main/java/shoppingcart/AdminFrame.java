package shoppingcart;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AdminFrame extends JFrame {
    public AdminFrame() {
        setTitle("🛠️ Admin Panel - Add Products");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel nameLabel = new JLabel("Product Name:");
        JTextField nameField = new JTextField(20);

        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField(10);

        JLabel qtyLabel = new JLabel("Quantity:");
        JTextField qtyField = new JTextField(10);

        JLabel imgLabel = new JLabel("Image Path:");
        JTextField imgPathField = new JTextField(20);

        JButton addBtn = new JButton("➕ Add Product");

        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        panel.add(nameLabel); panel.add(nameField);
        panel.add(priceLabel); panel.add(priceField);
        panel.add(qtyLabel); panel.add(qtyField);
        panel.add(imgLabel); panel.add(imgPathField);
        panel.add(new JLabel()); panel.add(addBtn);

        add(panel);

        addBtn.addActionListener(e -> {
            try {
                PreparedStatement ps = DBConnection.con.prepareStatement(
                    "INSERT INTO products (name, price, quantity, image_path) VALUES (?, ?, ?, ?)");
                ps.setString(1, nameField.getText());
                ps.setDouble(2, Double.parseDouble(priceField.getText()));
                ps.setInt(3, Integer.parseInt(qtyField.getText()));
                ps.setString(4, imgPathField.getText());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "✅ Product Added!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this, "❌ Failed to add product.");
            }
        });

        setVisible(true);
    }
}