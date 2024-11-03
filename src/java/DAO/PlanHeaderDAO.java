/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import model.PlanHeader;
import model.Plan;
import model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlanHeaderDAO {
    private Connection connection;

    public PlanHeaderDAO(Connection connection) {
        this.connection = connection;
    }
    public PlanHeaderDAO() {
        this.connection = JDBC.getConnection();
    }
    // Create
    public void addPlanHeader(PlanHeader planHeader) throws SQLException {
        String sql = "INSERT INTO PlanHeaders (plid, pid, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, planHeader.getPlan() != null ? planHeader.getPlan().getPlid() : null); // Get plan ID
            statement.setInt(2, planHeader.getProduct() != null ? planHeader.getProduct().getPid() : null); // Get product ID
            statement.setInt(3, planHeader.getQuantity());
            statement.executeUpdate();
        }
    }

    // Read
    public PlanHeader getPlanHeader(int phid) throws SQLException {
        String sql = "SELECT * FROM PlanHeaders WHERE phid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, phid);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                PlanDAO planDAO = new PlanDAO(connection);
                ProductDAO productDAO = new ProductDAO(connection);
                Plan plan = planDAO.getPlan(resultSet.getInt("pid"));
                Product product = productDAO.getProduct(resultSet.getInt("pid"));
                return new PlanHeader(
                    resultSet.getInt("phid"),
                    plan,
                    product,
                    resultSet.getInt("quantity")
                );
            }
        }
        return null; // Return null if no PlanHeader found
    }
    public List<PlanHeader> getPlanHeaderByPlanId(int id) throws SQLException {
        List<PlanHeader> planHeaders = new ArrayList<>();
        String sql = "SELECT * FROM PlanHeaders WHERE [plid] = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                PlanDAO planDAO = new PlanDAO(connection);
                ProductDAO productDAO = new ProductDAO(connection);
                Plan plan = planDAO.getPlan(resultSet.getInt("pid"));
                Product product = productDAO.getProduct(resultSet.getInt("pid"));
                PlanHeader planHeader = new PlanHeader(
                    resultSet.getInt("phid"),
                    plan,
                    product,
                    resultSet.getInt("quantity")
                );
                planHeaders.add(planHeader);
            }
        }
        return planHeaders; // Return null if no PlanHeader found
    }

    // Read all
    public List<PlanHeader> getAllPlanHeaders() throws SQLException {
        List<PlanHeader> planHeaders = new ArrayList<>();
        String sql = "SELECT * FROM PlanHeaders";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                PlanDAO planDAO = new PlanDAO(connection);
                ProductDAO productDAO = new ProductDAO(connection);
                Plan plan = planDAO.getPlan(resultSet.getInt("pid"));
                Product product = productDAO.getProduct(resultSet.getInt("pid"));
                PlanHeader planHeader = new PlanHeader(
                    resultSet.getInt("phid"),
                    plan,
                    product,
                    resultSet.getInt("quantity")
                );
                planHeaders.add(planHeader);
            }
        }
        return planHeaders;
    }

    // Update
    public void updatePlanHeader(int planId,int productId,int quantity) throws SQLException {
        String sql = "UPDATE PlanHeaders SET  quantity = ? WHERE plid = ?&& pid = ? && quantity <> ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, quantity);
            statement.setInt(2, planId);
            statement.setInt(3, productId);
            statement.setInt(4, quantity);
            statement.executeUpdate();
        }
    }

    // Delete
    public void deletePlanHeader(int phid) throws SQLException {
        String sql = "DELETE FROM PlanHeaders WHERE phid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, phid);
            statement.executeUpdate();
        }
    }
}
