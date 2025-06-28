package shoppingcart;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class ProductFrame extends JFrame {
    ProductService productService = new ProductService();
    CartService cartService = new CartService();
    int userId;

    public ProductFrame(int userId) {
        this.userId = userId;

        setTitle("Product Catalog");
        setSize(500, 400);
        setLayout(new BorderLayout());

        JPanel productPanel = new JPanel();
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));
        JScrollPane scroll = new JScrollPane(productPanel);
        add(scroll, BorderLayout.CENTER);

        try {
            ResultSet rs = productService.getProducts();
            while (rs.next()) {
                int pid = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int qty = rs.getInt("quantity");

                JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
                p.add(new JLabel(name + " ₹" + price + " | Qty: " + qty));

                JTextField qtyField = new JTextField("1", 3);
                JButton addBtn = new JButton("Add to Cart");

                p.add(qtyField);
                p.add(addBtn);

                addBtn.addActionListener(e -> {
                    try {
                        int q = Integer.parseInt(qtyField.getText());
                        cartService.addToCart(userId, pid, q);
                        JOptionPane.showMessageDialog(this, "Product added to cart.");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });

                productPanel.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JButton viewCart = new JButton("View Cart");
        JButton checkout = new JButton("Checkout");
        JPanel bottom = new JPanel();
        bottom.add(viewCart);
        bottom.add(checkout);
        add(bottom, BorderLayout.SOUTH);

        viewCart.addActionListener(e -> viewCartDialog());
        checkout.addActionListener(e -> checkoutDialog());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void viewCartDialog() {
        try {
            ResultSet rs = cartService.getCartItems(userId);
            StringBuilder sb = new StringBuilder("🛒 Your Cart:\n");
            double total = 0;
            while (rs.next()) {
                int qty = rs.getInt("quantity");
                double price = rs.getDouble("price") * qty;
                sb.append(rs.getString("name")).append(" | Qty: ").append(qty)
                        .append(" | Total: ₹").append(price).append("\n");
                total += price;
            }
            sb.append("\nTotal: ₹").append(total);
            JOptionPane.showMessageDialog(this, sb.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void checkoutDialog() {
        try {
            double total = cartService.checkout(userId);
            JOptionPane.showMessageDialog(this, "✅ Order placed! Total Bill: ₹" + total);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
