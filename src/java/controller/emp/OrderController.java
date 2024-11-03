/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.emp;

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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Plan;
import model.User;
import unit.DateUtil;
import java.sql.Date;
import model.PlanDetails;
import model.WorkAssignment;
@WebServlet("/emp/order")
public class OrderController extends AuthenticationServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        try {
            int planId = Integer.parseInt(req.getParameter("id"));
            PlanDAO planDAO = new PlanDAO();
            Plan plan =  planDAO.getPlan(planId);
            List<Date> list = DateUtil.getDateRange(plan.getStartDate(), plan.getEndDate());
            PlanDetailsDAO planDetailsDAO = new PlanDetailsDAO();
            WorkAssignmentDAO workAssignmentDAO = new WorkAssignmentDAO();
            for(Date date : list){
                for(int i =1 ;i<=3;i++){
                    for(int j = 1;j<=3;j++){
                        //System.out.println(date.toString()+"-"+i+ "-" +j+" : "+req.getParameter(date.toString()+"_"+i+"_"+j));
                        String val = req.getParameter(date.toString()+"_"+i+"_"+j);
                        if(val!=null){
                            int value = Integer.parseInt(val );
                            if(value>0){
                                PlanDetails planDetails = planDetailsDAO.getPlanDetailsByDateAndPlan(date, j,planId , i);                      
                                WorkAssignment workAssignment = new WorkAssignment(0,planDetails , user.getEmployee(), value);
                                workAssignmentDAO.addWorkAssignment(workAssignment);
                            }
                        }
                        
                    }
                }
            }
            resp.sendRedirect("../home");
        } catch (SQLException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            req.getRequestDispatcher("../view/emp/order.jsp").forward(req, resp);
        } catch (SQLException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
