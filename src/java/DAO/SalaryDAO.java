/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import model.Salary;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SalaryDAO {
    private Connection connection;

    public SalaryDAO(Connection connection) {
        this.connection = connection;
    }
    public SalaryDAO() {
        this.connection = JDBC.getConnection();
    }
    // Create
    public void addSalary(Salary salary) throws SQLException {
        String sql = "INSERT INTO Salaries (slevel, salary) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, salary.getSlevel());
            statement.setDouble(2, salary.getSalary());
            statement.executeUpdate();
        }
    }

    // Read
    public Salary getSalary(int sid) throws SQLException {
        String sql = "SELECT * FROM Salaries WHERE sid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, sid);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Salary(resultSet.getInt("sid"),
                                  resultSet.getString("slevel"),
                                  resultSet.getDouble("salary"));
            }
        }
        return null; // return null if no Salary found
    }

    // Read all
    public List<Salary> getAllSalaries() throws SQLException {
        List<Salary> salaries = new ArrayList<>();
        String sql = "SELECT * FROM Salaries";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Salary salary = new Salary(resultSet.getInt("sid"),
                                           resultSet.getString("slevel"),
                                           resultSet.getDouble("salary"));
                salaries.add(salary);
            }
        }
        return salaries;
    }

    // Update
    public void updateSalary(Salary salary) throws SQLException {
        String sql = "UPDATE Salaries SET slevel = ?, salary = ? WHERE sid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, salary.getSlevel());
            statement.setDouble(2, salary.getSalary());
            statement.setInt(3, salary.getSid());
            statement.executeUpdate();
        }
    }

    // Delete
    public void deleteSalary(int sid) throws SQLException {
        String sql = "DELETE FROM Salaries WHERE sid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, sid);
            statement.executeUpdate();
        }
    }
}
