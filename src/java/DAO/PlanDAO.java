package DAO;

import model.Plan;
import model.Department;
import model.Status;
import model.Employee;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlanDAO {
    private Connection connection;

    public PlanDAO(Connection connection) {
        this.connection = connection;
    }

    public PlanDAO() {
        this.connection = JDBC.getConnection();
    }

    // Create
    public void addPlan(Plan plan) throws SQLException {
        String sql = "INSERT INTO Plans (plname, startdate, enddate, did, statusid, eid) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, plan.getPlname());
            statement.setDate(2, plan.getStartDate());
            statement.setDate(3, plan.getEndDate());
            statement.setInt(4, plan.getDepartment() != null ? plan.getDepartment().getDid() : null);
            statement.setInt(5, plan.getStatus() != null ? plan.getStatus().getStatusId() : null);
            statement.setInt(6, plan.getManager() != null ? plan.getManager().getEid() : null);
            statement.executeUpdate();
        }
    }

    // Read
    public Plan getPlan(int plid) throws SQLException {
        String sql = "SELECT * FROM Plans WHERE plid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, plid);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                DepartmentDAO departmentDAO = new DepartmentDAO(connection);
                StatusDAO statusDAO = new StatusDAO(connection);
                EmployeeDAO employeeDAO = new EmployeeDAO(connection);
                Department department = departmentDAO.getDepartmentById(resultSet.getInt("did"));
                Status status = statusDAO.getStatus(resultSet.getInt("statusid"));
                Employee manager = employeeDAO.getEmployee(resultSet.getInt("eid"));
                return new Plan(
                    resultSet.getInt("plid"),
                    resultSet.getString("plname"),
                    resultSet.getDate("startdate"),
                    resultSet.getDate("enddate"),
                    department,
                    status,
                    manager
                );
            }
        }
        return null; // Return null if no Plan found
    }

    // Read all
    public List<Plan> getAllPlans() throws SQLException {
        List<Plan> plans = new ArrayList<>();
        String sql = "SELECT * FROM Plans";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                DepartmentDAO departmentDAO = new DepartmentDAO(connection);
                StatusDAO statusDAO = new StatusDAO(connection);
                EmployeeDAO employeeDAO = new EmployeeDAO(connection);
                Department department = departmentDAO.getDepartmentById(resultSet.getInt("did"));
                Status status = statusDAO.getStatus(resultSet.getInt("statusid"));
                Employee manager = employeeDAO.getEmployee(resultSet.getInt("eid"));
                Plan plan =  new Plan(
                    resultSet.getInt("plid"),
                    resultSet.getString("plname"),
                    resultSet.getDate("startdate"),
                    resultSet.getDate("enddate"),
                    department,
                    status,
                    manager
                );
                plans.add(plan);
            }
        }
        return plans;
    }
    public List<Plan> getAllPlansPublic() throws SQLException {
        List<Plan> plans = new ArrayList<>();
        String sql = "SELECT * FROM Plans where [statusid] <> 1 and [statusid] <> 4";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                DepartmentDAO departmentDAO = new DepartmentDAO(connection);
                StatusDAO statusDAO = new StatusDAO(connection);
                EmployeeDAO employeeDAO = new EmployeeDAO(connection);
                Department department = departmentDAO.getDepartmentById(resultSet.getInt("did"));
                Status status = statusDAO.getStatus(resultSet.getInt("statusid"));
                Employee manager = employeeDAO.getEmployee(resultSet.getInt("eid"));
                Plan plan =  new Plan(
                    resultSet.getInt("plid"),
                    resultSet.getString("plname"),
                    resultSet.getDate("startdate"),
                    resultSet.getDate("enddate"),
                    department,
                    status,
                    manager
                );
                plans.add(plan);
            }
        }
        return plans;
    }
        public List<Plan> getAllPlansPublicByManager(int eid) throws SQLException {
        List<Plan> plans = new ArrayList<>();
        String sql = "SELECT * FROM Plans where [statusid] <> 1 and [statusid] <> 4 and eid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
             statement.setInt(1, eid);
             ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                DepartmentDAO departmentDAO = new DepartmentDAO(connection);
                StatusDAO statusDAO = new StatusDAO(connection);
                EmployeeDAO employeeDAO = new EmployeeDAO(connection);
                Department department = departmentDAO.getDepartmentById(resultSet.getInt("did"));
                Status status = statusDAO.getStatus(resultSet.getInt("statusid"));
                Employee manager = employeeDAO.getEmployee(resultSet.getInt("eid"));
                Plan plan =  new Plan(
                    resultSet.getInt("plid"),
                    resultSet.getString("plname"),
                    resultSet.getDate("startdate"),
                    resultSet.getDate("enddate"),
                    department,
                    status,
                    manager
                );
                plans.add(plan);
            }
        }
        return plans;
    }
                public List<Plan> getAllPlansPublicByDeID(int did) throws SQLException {
        List<Plan> plans = new ArrayList<>();
        String sql = "SELECT * FROM Plans where [statusid] <> 1 and [statusid] <> 4 and did = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)){
             statement.setInt(1, did);
             ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                DepartmentDAO departmentDAO = new DepartmentDAO(connection);
                StatusDAO statusDAO = new StatusDAO(connection);
                EmployeeDAO employeeDAO = new EmployeeDAO(connection);
                Department department = departmentDAO.getDepartmentById(resultSet.getInt("did"));
                Status status = statusDAO.getStatus(resultSet.getInt("statusid"));
                Employee manager = employeeDAO.getEmployee(resultSet.getInt("eid"));
                Plan plan =  new Plan(
                    resultSet.getInt("plid"),
                    resultSet.getString("plname"),
                    resultSet.getDate("startdate"),
                    resultSet.getDate("enddate"),
                    department,
                    status,
                    manager
                );
                plans.add(plan);
            }
        }
        return plans;
    }

    public void updatePlan(Plan plan) throws SQLException {
        String sql = "UPDATE Plans SET plname = ?, startdate = ?, enddate = ?, did = ?, statusid = ?, eid = ? WHERE plid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, plan.getPlname());
            statement.setDate(2, plan.getStartDate());
            statement.setDate(3, plan.getEndDate());
            statement.setInt(4, plan.getDepartment() != null ? plan.getDepartment().getDid() : null);
            statement.setInt(5, plan.getStatus() != null ? plan.getStatus().getStatusId() : null);
            statement.setInt(6, plan.getManager() != null ? plan.getManager().getEid() : null);
            statement.setInt(7, plan.getPlid());
            statement.executeUpdate();
        }
    }

    public void deletePlan(int plid) throws SQLException {
        String sql = "DELETE FROM Plans WHERE plid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, plid);
            statement.executeUpdate();
        }
    }
    public void updatePlanStaus(int status,int plid) throws SQLException {
        String sql = "UPDATE Plans SET statusid = ?WHERE plid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, status);
            statement.setInt(2, plid);
            statement.executeUpdate();
        }
    }
}
