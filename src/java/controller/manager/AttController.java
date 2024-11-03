/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.manager;

import DAO.AttendanceDAO;
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
import model.Attendance;
import model.User;
import model.WorkAssignment;

@WebServlet("/manager/att")
public class AttController extends AuthenticationServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String date= req.getParameter("date");
            WorkAssignmentDAO workAssignmentDAO = new WorkAssignmentDAO();
            List<WorkAssignment> list = workAssignmentDAO.getAllWorkAssignmentsByPlanId(date, id);
            AttendanceDAO attendanceDAO = new AttendanceDAO();
            WorkAssignment temp = list.get(0);
            float alpha = Float.parseFloat(req.getParameter("a_"+temp.getWaid()));
            String note = req.getParameter("n_"+temp.getWaid());
            for(WorkAssignment workAssignment : list){
                int quantity = Integer.parseInt(req.getParameter("w_"+workAssignment.getWaid()));
                if(temp.getEmployee().getEid()!=workAssignment.getEmployee().getEid()){
                    alpha = Float.parseFloat(req.getParameter("a_"+workAssignment.getWaid()));
                    note = req.getParameter("n_"+workAssignment.getWaid());
                    temp=workAssignment;
                }
                attendanceDAO.addAttendance(new Attendance(0, workAssignment, quantity, alpha, note));               
            }
            resp.sendRedirect("view");
        } catch (SQLException ex) {
            Logger.getLogger(AttController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String date= req.getParameter("date");
            WorkAssignmentDAO workAssignmentDAO = new WorkAssignmentDAO();
            List<WorkAssignment> list = workAssignmentDAO.getAllWorkAssignmentsByPlanId(date, id);
            req.setAttribute("work", list);
            if(list.size()>0)
                req.setAttribute("planDetail", list.get(0).getDetails());
            req.getRequestDispatcher("../view/manager/att.jsp").forward(req, resp);
        } catch (SQLException ex) {
            Logger.getLogger(AttController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
