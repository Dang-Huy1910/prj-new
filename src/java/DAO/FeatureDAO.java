/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Department;
import model.Employee;
import model.Plan;
import model.Role;
import model.Status;


public class FeatureDAO {
     private Connection connection;

    // Constructor to initialize DAO with a specific database connection
    public FeatureDAO(Connection connection) {
        this.connection = connection;
    }

    // Default constructor that uses JDBC.getConnection() to establish a connection
    public FeatureDAO() {
        this.connection = JDBC.getConnection();
    }

    // Method to add a new Role
    public boolean CheckUrl(int roleId, String path) throws SQLException {
       String sql = "SELECT *\n" +
        "  FROM [dbo].[RoleFeatures] rf\n" +
        "  join [dbo].[Features] f on rf.fid = f.fid\n" +
        "  where f.url = ? and rf.rid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(2, roleId);
            statement.setString(1, path);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
            return false;
        }
    }
    
    public boolean CheckPublic(String path) throws SQLException {
        String sql = "SELECT *\n" +
        "  FROM [dbo].[RoleFeatures] rf\n" +
        "  join [dbo].[Features] f on rf.fid = f.fid\n" +
        "  where f.url = ? and rf.rid = 6";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, path);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
            return false;
        }
    }
}
