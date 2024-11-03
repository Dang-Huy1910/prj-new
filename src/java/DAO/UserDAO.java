package DAO;

import model.User;
import model.Employee;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Role;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }
    public UserDAO() {
        this.connection = JDBC.getConnection();
    }
    // Create
    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO Users (eid, username, password, active, email) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user.getEmployee() != null ? user.getEmployee().getEid() : 0); // Get Employee ID
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getPassword());
            statement.setBoolean(4, user.isActive());
            statement.setString(5, user.getEmail());
            statement.executeUpdate();
        }
    }

    // Read
    public User getUser(int uid) throws SQLException {
        String sql = "SELECT * FROM Users WHERE uid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, uid);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                EmployeeDAO employeeDAO = new EmployeeDAO(connection);
                RoleDAO roleDAO = new RoleDAO(connection);
                Employee employee = employeeDAO.getEmployee(resultSet.getInt("eid"));
                Role role = roleDAO.getRoleById(resultSet.getInt("rid"));
                return new User(
                    resultSet.getInt("uid"),
                    employee,
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getBoolean("active"),
                    resultSet.getString("email"),
                        role
                );
            }
        }
        return null; // Return null if no User found
    }
        public User getUserLogin(String userName, String pass) throws SQLException {
        String sql = "SELECT * FROM Users WHERE username = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userName);
            statement.setString(2, pass);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                EmployeeDAO employeeDAO = new EmployeeDAO(connection);
                Employee employee = employeeDAO.getEmployee(resultSet.getInt("eid"));
                RoleDAO roleDAO = new RoleDAO(connection);
                Role role = roleDAO.getRoleById(resultSet.getInt("rid"));
                return new User(
                    resultSet.getInt("uid"),
                    employee,
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getBoolean("active"),
                    resultSet.getString("email"),
                        role
                );
            }
        }
        return null; // Return null if no User found
    }

    // Read all
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Users";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                EmployeeDAO employeeDAO = new EmployeeDAO(connection);
                Employee employee = employeeDAO.getEmployee(resultSet.getInt("eid")); // Get Employee by ID
                RoleDAO roleDAO = new RoleDAO(connection);
                Role role = roleDAO.getRoleById(resultSet.getInt("rid"));
                User user =  new User(
                    resultSet.getInt("uid"),
                    employee,
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getBoolean("active"),
                    resultSet.getString("email"),
                        role
                );
                users.add(user);
            }
        }
        return users;
    }

    // Update
    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE Users SET eid = ?, username = ?, password = ?, active = ?, email = ? WHERE uid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user.getEmployee() != null ? user.getEmployee().getEid() : 0);
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getPassword());
            statement.setBoolean(4, user.isActive());
            statement.setString(5, user.getEmail());
            statement.setInt(6, user.getUid());
            statement.executeUpdate();
        }
    }

    // Delete
    public void deleteUser(int uid) throws SQLException {
        String sql = "DELETE FROM Users WHERE uid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, uid);
            statement.executeUpdate();
        }
    }
}
