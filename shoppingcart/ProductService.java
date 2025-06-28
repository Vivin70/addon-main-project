package shoppingcart;

import java.sql.*;

public class ProductService {

    public void showProducts() throws SQLException {
        ResultSet rs = DBConnection.stmt.executeQuery("SELECT * FROM products");

        System.out.println("🛍️ Available Products:");
        while (rs.next()) {
            System.out.println(
                rs.getInt("id") + " | " +
                rs.getString("name") + " | ₹" +
                rs.getDouble("price") + " | Qty: " +
                rs.getInt("quantity"));
        }
    }
}
