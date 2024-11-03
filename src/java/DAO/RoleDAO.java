package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Role;

public class RoleDAO {

    private Connection connection;

    // Constructor to initialize DAO with a specific database connection
    public RoleDAO(Connection connection) {
        this.connection = connection;
    }

    // Default constructor that uses JDBC.getConnection() to establish a connection
    public RoleDAO() {
        this.connection = JDBC.getConnection();
    }

    // Method to add a new Role
    public boolean addRole(Role role) throws SQLException {
        String query = "INSERT INTO Roles (rname) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, role.getRname());
            return stmt.executeUpdate() > 0;
        }
    }

    // Method to update an existing Role by rid
    public boolean updateRole(Role role) throws SQLException {
        String query = "UPDATE Roles SET rname = ? WHERE rid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, role.getRname());
            stmt.setInt(2, role.getRid());
            return stmt.executeUpdate() > 0;
        }
    }

    // Method to delete a Role by rid
    public boolean deleteRole(int rid) throws SQLException {
        String query = "DELETE FROM Roles WHERE rid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, rid);
            return stmt.executeUpdate() > 0;
        }
    }

    // Method to retrieve a Role by rid
    public Role getRoleById(int rid) throws SQLException {
        String query = "SELECT * FROM Roles WHERE rid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, rid);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Role(
                        rs.getInt("rid"),
                        rs.getString("rname")
                );
            }
        }
        return null;
    }

    // Method to retrieve all Roles
    public List<Role> getAllRoles() throws SQLException {
        List<Role> roles = new ArrayList<>();
        String query = "SELECT * FROM Roles";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Role role = new Role(
                        rs.getInt("rid"),
                        rs.getString("rname")
                );
                roles.add(role);
            }
        }
        return roles;
    }
}
