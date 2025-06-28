package shoppingcart;

import java.sql.*;

public class CartService {

    public void addToCart(int userId, int productId, int qty) throws SQLException {
        PreparedStatement ps = DBConnection.con.prepareStatement(
            "INSERT INTO cart (user_id, product_id, quantity) VALUES (?, ?, ?)");
        ps.setInt(1, userId);
        ps.setInt(2, productId);
        ps.setInt(3, qty);
        ps.executeUpdate();

        System.out.println("✅ Product added to cart.");
    }

    public void viewCart(int userId) throws SQLException {
        PreparedStatement ps = DBConnection.con.prepareStatement(
            "SELECT p.name, c.quantity, p.price FROM cart c JOIN products p ON c.product_id = p.id WHERE c.user_id = ?");
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();

        double total = 0;
        System.out.println("🛒 Your Cart:");
        while (rs.next()) {
            double price = rs.getDouble("price") * rs.getInt("quantity");
            total += price;
            System.out.println(rs.getString("name") + " | Qty: " + rs.getInt("quantity") + " | Total: ₹" + price);
        }
        System.out.println("🧾 Total: ₹" + total);
    }

    public void checkout(int userId) throws SQLException {
        ResultSet rs = DBConnection.stmt.executeQuery(
            "SELECT c.quantity, p.price FROM cart c JOIN products p ON c.product_id = p.id WHERE c.user_id = " + userId);

        double total = 0;
        while (rs.next()) {
            total += rs.getDouble("price") * rs.getInt("quantity");
        }

        PreparedStatement ps = DBConnection.con.prepareStatement("INSERT INTO orders (user_id, total) VALUES (?, ?)");
        ps.setInt(1, userId);
        ps.setDouble(2, total);
        ps.executeUpdate();

        DBConnection.stmt.execute("DELETE FROM cart WHERE user_id = " + userId);

        System.out.println("✅ Order placed! Total Bill: ₹" + total);
    }
}
