/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.planing;

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
import model.Plan;
import model.PlanHeader;
import model.Product;
import model.User;
import unit.DateUtil;
import java.sql.Date;
import model.PlanDetails;
import model.Shift;
@WebServlet("/plan/create-detail")
public class PlanDetailController extends AuthenticationServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            int total1 = Integer.parseInt( req.getParameter("total1"));
            int total2 = Integer.parseInt( req.getParameter("total2"));           
            int total3 = Integer.parseInt( req.getParameter("total3"));
            PlanDAO planDAO = new PlanDAO();
            Plan planId = planDAO.getPlan(id);
            PlanHeaderDAO planHeaderDAO = new PlanHeaderDAO();
            try {
                planHeaderDAO.addPlanHeader(new PlanHeader(0, planId, new Product(1, null, 0, null), total1));
                planHeaderDAO.addPlanHeader(new PlanHeader(0, planId, new Product(2, null, 0, null), total2));
                planHeaderDAO.addPlanHeader(new PlanHeader(0, planId, new Product(3, null, 0, null), total3));
            } catch (SQLException ex) {
                Logger.getLogger(PlanDetailController.class.getName()).log(Level.SEVERE, null, ex);
            }
            PlanDetailsDAO planDetailsDAO = new PlanDetailsDAO();
            List<Date> list = DateUtil.getDateRange(planId.getStartDate(), planId.getEndDate());
            for(Date day : list){
                for(int i=1;i<=3;i++){
                    for(int j=1;j<=3;j++){
                        int value = Integer.parseInt( req.getParameter(day.toString()+"_"+i+"_"+j));
                        Product product = new Product();
                        product.setPid(j);
                        PlanDetails planDetails = new PlanDetails();
                        planDetails.setPlan(planId);
                        planDetails.setShift(new Shift(i,"",null,null));  
                        planDetails.setDate(day);
                        planDetails.setProduct(product);
                        planDetails.setQuantity(value);
                        planDetailsDAO.addPlanDetails(planDetails);
                    }
                }
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(PlanDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
        resp.sendRedirect("../home");
        
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
            req.getRequestDispatcher("../view/planning/planDetail.jsp").forward(req, resp);
        } catch (SQLException ex) {
            Logger.getLogger(PlanDetailController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
