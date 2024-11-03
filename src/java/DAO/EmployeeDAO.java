/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import model.Employee;
import model.Department;
import model.Salary;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    private Connection connection;

    public EmployeeDAO(Connection connection) {
        this.connection = connection;
    }

    public EmployeeDAO() {
        this.connection = JDBC.getConnection();
    }

    // Create
    public void addEmployee(Employee employee) throws SQLException {
        String sql = "INSERT INTO Employees (ename, did, phonenumber, address, sid) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, employee.getEname());
            statement.setInt(2, employee.getDepartment() != null ? employee.getDepartment().getDid() : null); // Assuming Department has getDid method
            statement.setString(3, employee.getPhoneNumber());
            statement.setString(4, employee.getAddress());
            statement.setInt(5, employee.getSalary() != null ? employee.getSalary().getSid() : null); // Assuming Salary has getSid method
            statement.executeUpdate();
        }
    }

    // Read
    public Employee getEmployee(int eid) throws SQLException {
        String sql = "SELECT * FROM Employees WHERE eid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, eid);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Department department = getDepartmentById(resultSet.getInt("did"));
                Salary salary = getSalaryById(resultSet.getInt("sid"));
                return new Employee(resultSet.getInt("eid"),
                        resultSet.getString("ename"),
                        department,
                        resultSet.getString("phonenumber"),
                        resultSet.getString("address"),
                        salary);
            }
        }
        return null; // Return null if no Employee found
    }
    
        public List<Employee> getEmployeeByDeId(int did) throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT e.eid, e.ename, e.did, e.phonenumber, e.address, e.sid\n" +
                    "FROM [ProductionSchedulingSystem_DB].[dbo].[Employees] AS e\n" +
                    "JOIN [ProductionSchedulingSystem_DB].[dbo].[Users] AS u ON e.eid = u.eid\n" +
                    "WHERE e.did = ? AND u.rid = 7;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, did);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Department department = getDepartmentById(resultSet.getInt("did"));
                Salary salary = getSalaryById(resultSet.getInt("sid"));
                Employee employee = new Employee(resultSet.getInt("eid"),
                        resultSet.getString("ename"),
                        department,
                        resultSet.getString("phonenumber"),
                        resultSet.getString("address"),
                        salary);
                employees.add(employee);
            }
        }
        return employees;
    }

    // Read all
    public List<Employee> getAllEmployees() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM Employees";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Department department = getDepartmentById(resultSet.getInt("did"));
                Salary salary = getSalaryById(resultSet.getInt("sid"));
                Employee employee = new Employee(resultSet.getInt("eid"),
                        resultSet.getString("ename"),
                        department,
                        resultSet.getString("phonenumber"),
                        resultSet.getString("address"),
                        salary);
                employees.add(employee);
            }
        }
        return employees;
    }

    public List<Employee> getAllEmployeesByRoleId(int role) throws SQLException {
        List<Employee> employees = new ArrayList<>();
        String sql = "select e.* from [dbo].[Employees] e \n"
                + "join [dbo].[Users] u on e.eid=u.eid\n"
                + "where u.rid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql);) {
            statement.setInt(1, role);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Department department = getDepartmentById(resultSet.getInt("did"));
                Salary salary = getSalaryById(resultSet.getInt("sid"));
                Employee employee = new Employee(resultSet.getInt("eid"),
                        resultSet.getString("ename"),
                        department,
                        resultSet.getString("phonenumber"),
                        resultSet.getString("address"),
                        salary);
                employees.add(employee);
            }
        }
        return employees;
    }

    // Update
    public void updateEmployee(Employee employee) throws SQLException {
        String sql = "UPDATE Employees SET ename = ?, did = ?, phonenumber = ?, address = ?, sid = ? WHERE eid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, employee.getEname());
            statement.setInt(2, employee.getDepartment() != null ? employee.getDepartment().getDid() : null);
            statement.setString(3, employee.getPhoneNumber());
            statement.setString(4, employee.getAddress());
            statement.setInt(5, employee.getSalary() != null ? employee.getSalary().getSid() : null);
            statement.setInt(6, employee.getEid());
            statement.executeUpdate();
        }
    }

    // Delete
    public void deleteEmployee(int eid) throws SQLException {
        String sql = "DELETE FROM Employees WHERE eid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, eid);
            statement.executeUpdate();
        }
    }

    // Get Department by ID
    private Department getDepartmentById(int did) throws SQLException {
        String sql = "SELECT * FROM Departments WHERE did = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, did);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Department(resultSet.getInt("did"), resultSet.getString("dname"), resultSet.getString("type")); // Adjust based on Department class
            }
        }
        return null;
    }

    // Get Salary by ID
    private Salary getSalaryById(int sid) throws SQLException {
        String sql = "SELECT * FROM Salaries WHERE sid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, sid);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Salary(resultSet.getInt("sid"), resultSet.getString("slevel"), resultSet.getDouble("salary")); // Adjust based on Salary class
            }
        }
        return null;
    }
}
