/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.manager;

import DAO.PlanDAO;
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
import model.Plan;
import model.User;

@WebServlet("/manager/view")
public class ViewAllPlanController extends AuthenticationServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        try {
            System.out.println("ID: "+user.getUid());
            PlanDAO planDAO = new PlanDAO();
            List<Plan> list = planDAO.getAllPlansPublicByManager(user.getEmployee().getEid());
            req.setAttribute("plans", list);
            req.getRequestDispatcher("../view/manager/viewAllPlan.jsp").forward(req, resp);
        } catch (SQLException ex) {
            Logger.getLogger(ViewAllPlanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
