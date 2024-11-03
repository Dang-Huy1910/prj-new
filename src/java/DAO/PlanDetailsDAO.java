package DAO;

import model.PlanDetails;
import model.PlanHeader;
import model.Shift;
import model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Plan;

public class PlanDetailsDAO {
    private Connection connection;

    public PlanDetailsDAO(Connection connection) {
        this.connection = connection;
    }

    public PlanDetailsDAO() {
        this.connection = JDBC.getConnection();
    }

    // Create
    public void addPlanDetails(PlanDetails planDetails) throws SQLException {
        String sql = "INSERT INTO PlanDetails (plid, sid, date, quantity, pid) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, planDetails.getPlan() != null ? planDetails.getPlan().getPlid(): 0); // Get Plan ID
            statement.setInt(2, planDetails.getShift() != null ? planDetails.getShift().getSid() : 0); // Get Shift ID
            statement.setDate(3, planDetails.getDate());
            statement.setInt(4, planDetails.getQuantity());
            statement.setInt(5, planDetails.getProduct() != null ? planDetails.getProduct().getPid(): 0); // Get Product ID
            statement.executeUpdate();
        }
    }

    // Read
    public PlanDetails getPlanDetails(int pdid) throws SQLException {
        String sql = "SELECT * FROM PlanDetails WHERE pdid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, pdid);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                PlanDAO planHeaderDAO = new PlanDAO(connection);
                ShiftDAO shiftDAO = new ShiftDAO(connection);
                ProductDAO productDAO = new ProductDAO(connection); // Create a DAO for Product
                Plan planHeader = planHeaderDAO.getPlan(resultSet.getInt("plid")); // Get PlanHeader by ID
                Shift shift = shiftDAO.getShiftById(resultSet.getInt("sid")); // Get Shift by ID
                Product product = productDAO.getProduct(resultSet.getInt("pid")); // Get Product by ID
                return new PlanDetails(
                    resultSet.getInt("pdid"),
                    planHeader,
                    shift,
                    resultSet.getDate("date"),
                    product,
                    resultSet.getInt("quantity")
                );
            }
        }
        return null; // Return null if no PlanDetails found
    }
        public PlanDetails getPlanDetailsByDateAndPlan(Date date,int productId,int planId,int sid) throws SQLException {
        String sql = "SELECT [pdid]\n" +
                "      ,[plid]\n" +
                "      ,[pid]\n" +
                "      ,[sid]\n" +
                "      ,[date]\n" +
                "      ,[quantity]\n" +
                "  FROM [dbo].[PlanDetails]\n" +
                "  where date = ? and pid= ? and plid = ? and sid =?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDate(1, date);
            statement.setInt(2, productId);
            statement.setInt(3, planId);
            statement.setInt(4, sid);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                PlanDAO planHeaderDAO = new PlanDAO(connection);
                ShiftDAO shiftDAO = new ShiftDAO(connection);
                ProductDAO productDAO = new ProductDAO(connection); // Create a DAO for Product
                Plan planHeader = planHeaderDAO.getPlan(resultSet.getInt("plid")); // Get PlanHeader by ID
                Shift shift = shiftDAO.getShiftById(resultSet.getInt("sid")); // Get Shift by ID
                Product product = productDAO.getProduct(resultSet.getInt("pid")); // Get Product by ID
                return new PlanDetails(
                    resultSet.getInt("pdid"),
                    planHeader,
                    shift,
                    resultSet.getDate("date"),
                    product,
                    resultSet.getInt("quantity")
                );
            }
        }
        return null; // Return null if no PlanDetails found
    }

    // Read all
    public List<PlanDetails> getAllPlanDetailsByPlanId(int id) throws SQLException {
        List<PlanDetails> planDetailsList = new ArrayList<>();
        String sql = "SELECT * FROM PlanDetails   where plid = ?";
       try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
               PlanDAO planHeaderDAO = new PlanDAO(connection);
                ShiftDAO shiftDAO = new ShiftDAO(connection);
                ProductDAO productDAO = new ProductDAO(connection); // Create a DAO for Product
                Plan planHeader = planHeaderDAO.getPlan(resultSet.getInt("plid")); // Get PlanHeader by ID
                Shift shift = shiftDAO.getShiftById(resultSet.getInt("sid")); // Get Shift by ID
                Product product = productDAO.getProduct(resultSet.getInt("pid")); // Get Product by ID
                PlanDetails details = new PlanDetails(
                    resultSet.getInt("pdid"),
                    planHeader,
                    shift,
                    resultSet.getDate("date"),
                    product,
                    resultSet.getInt("quantity")
                );
                planDetailsList.add(details);
            }
        }
        return planDetailsList;
    }
        public List<PlanDetails> getAllPlanDetailsByPlanIdAndDay(int id,String date) throws SQLException {
        List<PlanDetails> planDetailsList = new ArrayList<>();
        String sql = "SELECT * FROM PlanDetails   where plid = ? and date = ?";
       try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.setString(2, date);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
               PlanDAO planHeaderDAO = new PlanDAO(connection);
                ShiftDAO shiftDAO = new ShiftDAO(connection);
                ProductDAO productDAO = new ProductDAO(connection); // Create a DAO for Product
                Plan planHeader = planHeaderDAO.getPlan(resultSet.getInt("plid")); // Get PlanHeader by ID
                Shift shift = shiftDAO.getShiftById(resultSet.getInt("sid")); // Get Shift by ID
                Product product = productDAO.getProduct(resultSet.getInt("pid")); // Get Product by ID
                PlanDetails details = new PlanDetails(
                    resultSet.getInt("pdid"),
                    planHeader,
                    shift,
                    resultSet.getDate("date"),
                    product,
                    resultSet.getInt("quantity")
                );
                planDetailsList.add(details);
            }
        }
        return planDetailsList;
    }

    // Update
    public void updatePlanDetails(PlanDetails planDetails) throws SQLException {
        String sql = "UPDATE PlanDetails SET plid = ?, sid = ?, date = ?, quantity = ?, pid = ? WHERE pdid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, planDetails.getPlan() != null ? planDetails.getPlan().getPlid(): 0);
            statement.setInt(2, planDetails.getShift() != null ? planDetails.getShift().getSid() : 0);
            statement.setDate(3, planDetails.getDate());
            statement.setInt(4, planDetails.getQuantity());
            statement.setInt(5, planDetails.getProduct() != null ? planDetails.getProduct().getPid(): 0);
            statement.setInt(6, planDetails.getPdid());
            statement.executeUpdate();
        }
    }
     public void updateQuantity(int pdid , int quantityNew) throws SQLException {
        String sql = "UPDATE PlanDetails SET  quantity = ? WHERE pdid = ? and quantity <> ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, quantityNew);
            statement.setInt(2, pdid);
            statement.setInt(3, quantityNew);
            statement.executeUpdate();
        }
    }

    // Delete
    public void deletePlanDetails(int pdid) throws SQLException {
        String sql = "DELETE FROM PlanDetails WHERE pdid = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, pdid);
            statement.executeUpdate();
        }
    }
}
