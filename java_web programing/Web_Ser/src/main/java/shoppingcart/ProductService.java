package shoppingcart;

import java.sql.*;

public class ProductService {
    public ResultSet getProducts() throws SQLException {
        return DBConnection.stmt.executeQuery("SELECT * FROM products");
    }
}
