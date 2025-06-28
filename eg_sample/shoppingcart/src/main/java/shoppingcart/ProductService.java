package shoppingcart;

import java.sql.*;

public class ProductService {
    public ResultSet getProducts() throws SQLException {
        return DBConnection.stmt.executeQuery("SELECT * FROM products");
    }

    public void addProduct(String name, double price, int quantity, String imagePath) throws SQLException {
        PreparedStatement ps = DBConnection.con.prepareStatement(
            "INSERT INTO products (name, price, quantity, image_path) VALUES (?, ?, ?, ?)");
        ps.setString(1, name);
        ps.setDouble(2, price);
        ps.setInt(3, quantity);
        ps.setString(4, imagePath);
        ps.executeUpdate();
    }
}