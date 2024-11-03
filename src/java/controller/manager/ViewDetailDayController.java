/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.manager;

import DAO.EmployeeDAO;
import DAO.PlanDAO;
import DAO.PlanDetailsDAO;
import DAO.ProductDAO;
import DAO.WorkAssignmentDAO;
import controller.auth.AuthenticationServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Plan;
import model.PlanDetails;
import model.PlanHeader;
import model.Product;
import model.User;
import model.WorkAssignment;

@WebServlet("/manager/view-day")
public class ViewDetailDayController extends AuthenticationServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        try {           
            int id = Integer.parseInt(req.getParameter("id"));
            String date= req.getParameter("date");
            PlanDetailsDAO planDetailsDAO = new PlanDetailsDAO();
            List<PlanDetails> list = planDetailsDAO.getAllPlanDetailsByPlanIdAndDay(id, date);
            PlanDetails planDetails = list.get(0);
            req.setAttribute("planDetails", list);
            req.setAttribute("planDetail", planDetails);
            EmployeeDAO employeeDAO = new EmployeeDAO();
//            ProductDAO productDAO = new ProductDAO();
//            LinkedHashMap<Integer,PlanHeader> report = new LinkedHashMap<>();
//            for(Product product : productDAO.getAllProducts()){
//                PlanHeader planHeader1 = new PlanHeader();
//                planHeader1.setProduct(product);
//                int quantity = 0;
//                for(PlanDetails planDetails1 : list){
//                    if(planDetails1.getProduct().getPid() == product.getPid()) quantity+= planDetails1.getQuantity();
//                }
//                planHeader1.setQuantity(quantity);
//                report.put(0, planHeader1);
//            }         
//            
//            //report.put(id, id)
            WorkAssignmentDAO workAssignmentDAO = new WorkAssignmentDAO();
            req.setAttribute("work", workAssignmentDAO.getAllWorkAssignmentsByPlanId(date, id));
            req.setAttribute("emps", employeeDAO.getEmployeeByDeId(planDetails.getPlan().getDepartment().getDid()));
            req.getRequestDispatcher("../view/manager/viewDetailDay.jsp").forward(req, resp);
        } catch (SQLException ex) {
            Logger.getLogger(ViewAllPlanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
