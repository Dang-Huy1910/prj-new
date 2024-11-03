/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Department;

public class DepartmentDAO {

    private Connection connection;

    // Constructor để khởi tạo DAO với kết nối tới cơ sở dữ liệu
    public DepartmentDAO(Connection connection) {
        this.connection = connection;
    }

    public DepartmentDAO() {
        this.connection = JDBC.getConnection();
    }

    // Phương thức thêm mới một Department
    public boolean addDepartment(Department department) throws SQLException {
        String query = "INSERT INTO Departments (dname, type) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, department.getDname());
            stmt.setString(2, department.getType());
            return stmt.executeUpdate() > 0;
        }
    }

    // Phương thức cập nhật một Department theo did
    public boolean updateDepartment(Department department) throws SQLException {
        String query = "UPDATE Departments SET dname = ?, type = ? WHERE did = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, department.getDname());
            stmt.setString(2, department.getType());
            stmt.setInt(3, department.getDid());
            return stmt.executeUpdate() > 0;
        }
    }

    // Phương thức xóa một Department theo did
    public boolean deleteDepartment(int did) throws SQLException {
        String query = "DELETE FROM Departments WHERE did = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, did);
            return stmt.executeUpdate() > 0;
        }
    }

    // Phương thức lấy một Department theo did
    public Department getDepartmentById(int did) throws SQLException {
        String query = "SELECT * FROM Departments WHERE did = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, did);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Department(
                        rs.getInt("did"),
                        rs.getString("dname"),
                        rs.getString("type")
                );
            }
        }
        return null;
    }

    // Phương thức lấy tất cả các Department
    public List<Department> getAllDepartments() throws SQLException {
        List<Department> departments = new ArrayList<>();
        String query = "SELECT * FROM Departments";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Department department = new Department(
                        rs.getInt("did"),
                        rs.getString("dname"),
                        rs.getString("type")
                );
                departments.add(department);
            }
        }
        return departments;
    }
        // Phương thức lấy tất cả các Department
    public List<Department> getAllProduction() throws SQLException {
        List<Department> departments = new ArrayList<>();
        String query = "SELECT * FROM Departments   where type = 'Production'";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Department department = new Department(
                        rs.getInt("did"),
                        rs.getString("dname"),
                        rs.getString("type")
                );
                departments.add(department);
            }
        }
        return departments;
    }
}
