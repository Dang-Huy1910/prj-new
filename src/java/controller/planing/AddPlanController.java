/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.planing;

import DAO.DepartmentDAO;
import DAO.EmployeeDAO;
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
import model.Department;
import model.Employee;
import model.Plan;
import model.Status;
import model.User;

@WebServlet("/plan/add-plan")
public class AddPlanController extends AuthenticationServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        String planName = req.getParameter("planName");
        int workshop = Integer.parseInt(req.getParameter("workshop"));
        int managerId = Integer.parseInt(req.getParameter("manager"));
        java.sql.Date startDate = java.sql.Date.valueOf(req.getParameter("startDate"));
        java.sql.Date endDate = java.sql.Date.valueOf(req.getParameter("endDate"));
        PlanDAO planDAO = new PlanDAO();
        Plan plan = new Plan();
        plan.setPlname(planName);
        plan.setManager(new Employee(managerId,null,null,null,null,null));
        plan.setDepartment(new Department(workshop, "", ""));
        plan.setStartDate(startDate);
        plan.setEndDate(endDate);
        plan.setStatus(new Status(1, ""));
        try {
            planDAO.addPlan(plan);
        } catch (SQLException ex) {
            Logger.getLogger(AddPlanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            List<Plan> plans = planDAO.getAllPlans();
            resp.sendRedirect("create-detail?id="+plans.get(plans.size()-1).getPlid());
        } catch (SQLException ex) {
            Logger.getLogger(AddPlanController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        try {
            DepartmentDAO departmentDAO = new DepartmentDAO();
            EmployeeDAO employeeDAO = new EmployeeDAO();
            List<Department> listde = departmentDAO.getAllProduction();
            req.setAttribute("de", listde);
            req.setAttribute("manager", employeeDAO.getAllEmployeesByRoleId(5));
            req.getRequestDispatcher("../view/planning/createPlan.jsp").forward(req, resp);
           
        } catch (SQLException ex) {
            Logger.getLogger(AddPlanController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
