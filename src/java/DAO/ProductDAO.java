/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private Connection connection;

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }
    public ProductDAO() {
        this.connection = JDBC.getConnection();
    }
    // Create
    public void addProduct(Product product) throws SQLException {
        String sql = "INSERT INTO Products (pname, estimation, description) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getPname());
            statement.setFloat(2, product.getEstimation());
            statement.setString(3, product.getDescription());
            statement.executeUpdate();
        }
    }

    // Read
    public Product getProduct(int pid) throws SQLException {
        String sql = "SELECT * FROM Products WHERE pid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, pid);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Product(
                    resultSet.getInt("pid"),
                    resultSet.getString("pname"),
                    resultSet.getFloat("estimation"),
                    resultSet.getString("description")
                );
            }
        }
        return null; // Return null if no Product found
    }

    // Read all
    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM Products";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Product product = new Product(
                    resultSet.getInt("pid"),
                    resultSet.getString("pname"),
                    resultSet.getFloat("estimation"),
                    resultSet.getString("description")
                );
                products.add(product);
            }
        }
        return products;
    }

    // Update
    public void updateProduct(Product product) throws SQLException {
        String sql = "UPDATE Products SET pname = ?, estimation = ?, description = ? WHERE pid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getPname());
            statement.setFloat(2, product.getEstimation());
            statement.setString(3, product.getDescription());
            statement.setInt(4, product.getPid());
            statement.executeUpdate();
        }
    }

    // Delete
    public void deleteProduct(int pid) throws SQLException {
        String sql = "DELETE FROM Products WHERE pid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, pid);
            statement.executeUpdate();
        }
    }
}