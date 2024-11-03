/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Shift;

public class ShiftDAO {
    private Connection connection;

    // Constructor để khởi tạo DAO với kết nối tới cơ sở dữ liệu
    public ShiftDAO(Connection connection) {
        this.connection = connection;
    }
    public ShiftDAO() {
        this.connection = JDBC.getConnection();
    }
    // Phương thức thêm mới một Shift
    public boolean addShift(Shift shift) throws SQLException {
        String query = "INSERT INTO Shifts (sname, starttime, endtime) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, shift.getSname());
            stmt.setDate(2, shift.getStartTime());
            stmt.setDate(3, shift.getEndTime());
            return stmt.executeUpdate() > 0;
        }
    }

    // Phương thức cập nhật một Shift theo sid
    public boolean updateShift(Shift shift) throws SQLException {
        String query = "UPDATE Shifts SET sname = ?, starttime = ?, endtime = ? WHERE sid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, shift.getSname());
            stmt.setDate(2, shift.getStartTime());
            stmt.setDate(3, shift.getEndTime());
            stmt.setInt(4, shift.getSid());
            return stmt.executeUpdate() > 0;
        }
    }

    // Phương thức xóa một Shift theo sid
    public boolean deleteShift(int sid) throws SQLException {
        String query = "DELETE FROM Shifts WHERE sid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, sid);
            return stmt.executeUpdate() > 0;
        }
    }

    // Phương thức lấy một Shift theo sid
    public Shift getShiftById(int sid) throws SQLException {
        String query = "SELECT * FROM Shifts WHERE sid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, sid);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Shift(
                        rs.getInt("sid"),
                        rs.getString("sname"),
                        null,
                        null
                );
            }
        }
        return null;
    }

    // Phương thức lấy tất cả các Shift
    public List<Shift> getAllShifts() throws SQLException {
        List<Shift> shifts = new ArrayList<>();
        String query = "SELECT * FROM Shifts";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Shift shift = new Shift(
                        rs.getInt("sid"),
                        rs.getString("sname"),
                        rs.getDate("startTime"),
                        rs.getDate("endTime")
                );
                shifts.add(shift);
            }
        }
        return shifts;
    }
}

