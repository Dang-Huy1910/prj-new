/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.manager;

import DAO.AttendanceDAO;
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
import model.Plan;
import model.User;
import unit.DateUtil;

@WebServlet("/manager/viewAtt")
public class ViewAllAttControler extends AuthenticationServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
            AttendanceDAO attendanceDAO = new AttendanceDAO();
            EmployeeDAO employeeDAO = new EmployeeDAO();
            req.setAttribute("att", attendanceDAO.getAllAttendancesByPlanId(id));
            req.setAttribute("emps", employeeDAO.getEmployeeByDeId(plan.getDepartment().getDid()));
            req.getRequestDispatcher("../view/manager/viewAllAtt.jsp").forward(req, resp);
        } catch (SQLException ex) {
            Logger.getLogger(ViewAllAttControler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
