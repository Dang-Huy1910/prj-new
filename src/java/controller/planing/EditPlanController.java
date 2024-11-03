/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.planing;

import DAO.DepartmentDAO;
import DAO.EmployeeDAO;
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
import model.Department;
import model.Plan;
import model.PlanDetails;
import model.PlanHeader;
import model.User;
import unit.DateUtil;

@WebServlet("/plan/edit")
public class EditPlanController extends AuthenticationServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            PlanDetailsDAO planDetailsDAO = new PlanDetailsDAO();
            List<PlanDetails> list = planDetailsDAO.getAllPlanDetailsByPlanId(id);
            for(PlanDetails details : list){
                int value = Integer.parseInt(req.getParameter(details.getPdid()+""));
                planDetailsDAO.updateQuantity(details.getPdid(), value);
            }
            PlanHeaderDAO planHeaderDAO = new PlanHeaderDAO();
            for(int i=1;i<=3;i++){
                int value = Integer.parseInt(req.getParameter("total"+i));
                planHeaderDAO.updatePlanHeader(id, i, value);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EditPlanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        resp.sendRedirect("plans");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            PlanDAO planDAO = new PlanDAO();
            Plan plan =  planDAO.getPlan(id);
            List list = DateUtil.getDateRange(plan.getStartDate(), plan.getEndDate());
            req.setAttribute("list", list);
            req.setAttribute("plan", plan);
            ProductDAO productDAO = new ProductDAO();
            req.setAttribute("products", productDAO.getAllProducts());
            PlanDetailsDAO planDetailsDAO = new PlanDetailsDAO();
            req.setAttribute("productDetails", planDetailsDAO.getAllPlanDetailsByPlanId(id));
            req.getRequestDispatcher("../view/planning/edit.jsp").forward(req, resp);
           
        } catch (SQLException ex) {
            Logger.getLogger(AddPlanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
