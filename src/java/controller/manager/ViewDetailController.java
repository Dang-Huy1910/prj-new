/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.manager;

import DAO.PlanDAO;
import DAO.PlanDetailsDAO;
import DAO.PlanHeaderDAO;
import DAO.ProductDAO;
import controller.auth.AuthenticationServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PlanHeader;
import model.User;

@WebServlet("/manager/view-detail")
public class ViewDetailController extends AuthenticationServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            PlanDAO planDAO = new PlanDAO();
            req.setAttribute("plan", planDAO.getPlan(id));
            PlanHeaderDAO planHeaderDAO = new PlanHeaderDAO();
            List<PlanHeader> planHeaders = planHeaderDAO.getPlanHeaderByPlanId(id);
            PlanDetailsDAO planDetailsDAO = new PlanDetailsDAO();
            req.setAttribute("productDetails", planDetailsDAO.getAllPlanDetailsByPlanId(id));
            req.setAttribute("planHeaders", planHeaders);
            
            req.getRequestDispatcher("../view/manager/viewDetail.jsp").forward(req, resp);
        } catch (SQLException ex) {
            Logger.getLogger(ViewDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
