/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.admin;

import DAO.PlanDAO;
import controller.auth.AuthenticationServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

@WebServlet("/admin/edit")
public class EditPlanController extends AuthenticationServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        int check = Integer.parseInt(req.getParameter("check"));
        PlanDAO planDAO = new PlanDAO();
        if(check == 1){
            try {
                planDAO.updatePlanStaus(2,id);
            } catch (SQLException ex) {
                Logger.getLogger(EditPlanController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else {
            try {
                planDAO.updatePlanStaus(4,id);
            } catch (SQLException ex) {
                Logger.getLogger(EditPlanController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        resp.sendRedirect("view");
    }
    
}
