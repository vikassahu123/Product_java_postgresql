package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDB {

    Connection conn = null;

    ProductDB() {
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Spring1", "postgres", "Vikassahu@123");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void save(Product p) {

        String query = "INSERT INTO product(name,type,place,warrenty) VALUES(?,?,?,?)";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, p.getName());
            st.setString(2, p.getType());
            st.setString(3, p.getPlace());
            st.setInt(4, p.getWarrenty());

            st.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        String query = "select name,type,place,warrenty from product";
        try {
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setName((rs.getString(1)));
                p.setType((rs.getString(2)));
                p.setPlace((rs.getString(3)));
                p.setWarrenty((rs.getInt(4)));

                products.add(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public Product getProductByName(String Product_name) {
        Product product = null;
        String query = "SELECT * FROM product WHERE name =?";

        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, Product_name);  // Set the input parameter

            ResultSet rs = st.executeQuery(); // Execute query

            while (rs.next()) {  // If a record is found
                product = new Product();
                product.setName(rs.getString("name"));
                product.setType(rs.getString("type"));
                product.setPlace(rs.getString("place"));
                product.setWarrenty(rs.getInt("warrenty"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return product;
    }
}
