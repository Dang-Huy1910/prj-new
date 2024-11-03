/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import model.Status;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StatusDAO {
    private Connection connection;

    public StatusDAO(Connection connection) {
        this.connection = connection;
    }
    public StatusDAO() {
        this.connection = JDBC.getConnection();
    }
    // Create
    public void addStatus(Status status) throws SQLException {
        String sql = "INSERT INTO Status (statusname) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status.getName());
            statement.executeUpdate();
        }
    }

    // Read
    public Status getStatus(int statusId) throws SQLException {
        String sql = "SELECT * FROM Status WHERE statusid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, statusId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Status(resultSet.getInt("statusid"), resultSet.getString("statusname"));
            }
        }
        return null; // Return null if no Status found
    }

    // Read all
    public List<Status> getAllStatuses() throws SQLException {
        List<Status> statuses = new ArrayList<>();
        String sql = "SELECT * FROM Status";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Status status = new Status(resultSet.getInt("statusid"), resultSet.getString("statusname"));
                statuses.add(status);
            }
        }
        return statuses;
    }

    // Update
    public void updateStatus(Status status) throws SQLException {
        String sql = "UPDATE Status SET statusname = ? WHERE statusid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, status.getName());
            statement.setInt(2, status.getStatusId());
            statement.executeUpdate();
        }
    }

    // Delete
    public void deleteStatus(int statusId) throws SQLException {
        String sql = "DELETE FROM Status WHERE statusid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, statusId);
            statement.executeUpdate();
        }
    }
}