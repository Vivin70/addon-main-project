package shoppingcart;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AdminFrame extends JFrame {
    ProductService productService = new ProductService();

    public AdminFrame() {
        setTitle("Admin - Product Management");
        setSize(500, 400);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));

        JTextField nameField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField qtyField = new JTextField();
        JTextField imagePathField = new JTextField();

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Price:"));
        panel.add(priceField);
        panel.add(new JLabel("Quantity:"));
        panel.add(qtyField);
        panel.add(new JLabel("Image Path:"));
        panel.add(imagePathField);

        JButton addButton = new JButton("Add Product");
        panel.add(addButton);

        addButton.addActionListener(e -> {
            try {
                String name = nameField.getText();
                double price = Double.parseDouble(priceField.getText());
                int qty = Integer.parseInt(qtyField.getText());
                String path = imagePathField.getText();
                productService.addProduct(name, price, qty, path);
                JOptionPane.showMessageDialog(this, "✅ Product added!");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        add(panel, BorderLayout.CENTER);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}