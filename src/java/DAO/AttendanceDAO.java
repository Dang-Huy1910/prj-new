package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import model.Attendance;
import model.Employee;
import model.SalaryUnit;

public class AttendanceDAO {
    private Connection connection;
    
    public AttendanceDAO() {
        this.connection = JDBC.getConnection();
    }
    
    public AttendanceDAO(Connection connection) {
        this.connection = connection;
    }

    // Phương thức thêm một Attendance mới
    public boolean addAttendance(Attendance attendance) throws SQLException {
        String query = "INSERT INTO Attendance (waid, actualquantity, alpha, note) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setObject(1, attendance.getWaid().getWaid(), Types.INTEGER);
            stmt.setInt(2, attendance.getActualQuantity());
            stmt.setFloat(3, attendance.getAlpha());
            stmt.setString(4, attendance.getNote());
            return stmt.executeUpdate() > 0;
        }
    }

    // Phương thức cập nhật một Attendance theo atid
    public boolean updateAttendance(Attendance attendance) throws SQLException {
        String query = "UPDATE Attendance SET waid = ?, actualquantity = ?, alpha = ?, note = ? WHERE atid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setObject(1, attendance.getWaid().getWaid(), Types.INTEGER);
            stmt.setInt(2, attendance.getActualQuantity());
            stmt.setFloat(3, attendance.getAlpha());
            stmt.setString(4, attendance.getNote());
            stmt.setInt(5, attendance.getAtid());
            return stmt.executeUpdate() > 0;
        }
    }

    // Phương thức xóa một Attendance theo atid
    public boolean deleteAttendance(int atid) throws SQLException {
        String query = "DELETE FROM Attendance WHERE atid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, atid);
            return stmt.executeUpdate() > 0;
        }
    }

    // Phương thức lấy một Attendance theo atid
    public Attendance getAttendanceById(int atid) throws SQLException {
        String query = "SELECT * FROM Attendance WHERE atid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, atid);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                WorkAssignmentDAO workAssignmentDAO = new WorkAssignmentDAO(connection);
                return new Attendance(
                        rs.getInt("atid"),
                        workAssignmentDAO.getWorkAssignment(rs.getInt("waid")),
                        rs.getInt("actualquantity"),
                        rs.getFloat("alpha"),
                        rs.getString("note")
                );
            }
        }
        return null;
    }

    // Phương thức lấy tất cả các Attendance
    public List<Attendance> getAllAttendances() throws SQLException {
        List<Attendance> attendances = new ArrayList<>();
        String query = "SELECT * FROM Attendance";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                WorkAssignmentDAO workAssignmentDAO = new WorkAssignmentDAO(connection);
                Attendance attendance = new Attendance(
                        rs.getInt("atid"),
                        workAssignmentDAO.getWorkAssignment(rs.getInt("waid")),
                        rs.getInt("actualquantity"),
                        rs.getFloat("alpha"),
                        rs.getString("note")
                );
                attendances.add(attendance);
            }
        }
        return attendances;
    }
    
        public List<SalaryUnit> getAllAttendancesByPlanId(int plid) throws SQLException {
        List<SalaryUnit> attendances = new ArrayList<>();
        String query = "select w.eid, a.alpha , pd.date\n" +
                        "from [dbo].[Attendance] a join [dbo].[WorkAssignments] w on a.waid = w.waid\n" +
                        "join [dbo].[PlanDetails] pd on pd.pdid = w.pdid\n" +
                        "where pd.plid = ?\n" +
                        "group by w.eid,a.alpha , pd.date";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, plid);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                EmployeeDAO employeeDAO = new EmployeeDAO();
                SalaryUnit attendance = new SalaryUnit(
                            employeeDAO.getEmployee(rs.getInt(1)),
                            rs.getFloat(2),
                            rs.getDate(3)
                );
                attendances.add(attendance);
            }
        }
        return attendances;
    }
}
