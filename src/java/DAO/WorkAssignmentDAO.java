/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import model.WorkAssignment;
import model.PlanDetails;
import model.Employee;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkAssignmentDAO {
    private Connection connection;

    public WorkAssignmentDAO(Connection connection) {
        this.connection = connection;
    }
    public WorkAssignmentDAO() {
        this.connection = JDBC.getConnection();
    }
    // Create
    public void addWorkAssignment(WorkAssignment workAssignment) throws SQLException {
        String sql = "INSERT INTO WorkAssignments (pdid, eid, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, workAssignment.getDetails() != null ? workAssignment.getDetails().getPdid() : 0); // Get PlanDetails ID
            statement.setInt(2, workAssignment.getEmployee() != null ? workAssignment.getEmployee().getEid() : 0); // Get Employee ID
            statement.setInt(3, workAssignment.getQuantity());
            statement.executeUpdate();
        }
    }

    // Read
    public WorkAssignment getWorkAssignment(int waid) throws SQLException {
        String sql = "SELECT * FROM WorkAssignments WHERE waid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, waid);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                PlanDetailsDAO planDetailsDAO = new PlanDetailsDAO(connection);
                EmployeeDAO employeeDAO = new EmployeeDAO(connection);
                PlanDetails details = planDetailsDAO.getPlanDetails(resultSet.getInt("pdid")); // Get PlanDetails by ID
                Employee employee = employeeDAO.getEmployee(resultSet.getInt("eid")); // Get Employee by ID
                return new WorkAssignment(
                    resultSet.getInt("waid"),
                    details,
                    employee,
                    resultSet.getInt("quantity")
                );
            }
        }
        return null; // Return null if no WorkAssignment found
    }

    // Read all
    public List<WorkAssignment> getAllWorkAssignments() throws SQLException {
        List<WorkAssignment> workAssignments = new ArrayList<>();
        String sql = "SELECT * FROM WorkAssignments";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                PlanDetailsDAO planDetailsDAO = new PlanDetailsDAO(connection);
                EmployeeDAO employeeDAO = new EmployeeDAO(connection);
                PlanDetails details = planDetailsDAO.getPlanDetails(resultSet.getInt("pdid")); // Get PlanDetails by ID
                Employee employee = employeeDAO.getEmployee(resultSet.getInt("eid")); // Get Employee by ID
                WorkAssignment workAssignment = new WorkAssignment(
                    resultSet.getInt("waid"),
                    details,
                    employee,
                    resultSet.getInt("quantity")
                );
                workAssignments.add(workAssignment);
            }
        }
        return workAssignments;
    }
    // Read all
    public List<WorkAssignment> getAllWorkAssignmentsByPlanId(String date, int plid) throws SQLException {
        List<WorkAssignment> workAssignments = new ArrayList<>();
        String sql = "SELECT w.*\n" +
                    "  FROM [WorkAssignments] w join [dbo].[PlanDetails] pd on w.pdid=pd.pdid\n" +
                    "  where pd.date = ? and pd.plid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, date);
            statement.setInt(2, plid);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                PlanDetailsDAO planDetailsDAO = new PlanDetailsDAO(connection);
                EmployeeDAO employeeDAO = new EmployeeDAO(connection);
                PlanDetails details = planDetailsDAO.getPlanDetails(resultSet.getInt("pdid")); // Get PlanDetails by ID
                Employee employee = employeeDAO.getEmployee(resultSet.getInt("eid")); // Get Employee by ID
                WorkAssignment workAssignment = new WorkAssignment(
                    resultSet.getInt("waid"),
                    details,
                    employee,
                    resultSet.getInt("quantity")
                );
                workAssignments.add(workAssignment);
            }
        }
        return workAssignments;
    }
    // Update
    public void updateWorkAssignment(WorkAssignment workAssignment) throws SQLException {
        String sql = "UPDATE WorkAssignments SET pdid = ?, eid = ?, quantity = ? WHERE waid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, workAssignment.getDetails() != null ? workAssignment.getDetails().getPdid() : 0);
            statement.setInt(2, workAssignment.getEmployee() != null ? workAssignment.getEmployee().getEid() : 0);
            statement.setInt(3, workAssignment.getQuantity());
            statement.setInt(4, workAssignment.getWaid());
            statement.executeUpdate();
        }
    }

    // Delete
    public void deleteWorkAssignment(int waid) throws SQLException {
        String sql = "DELETE FROM WorkAssignments WHERE waid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, waid);
            statement.executeUpdate();
        }
    }
}

