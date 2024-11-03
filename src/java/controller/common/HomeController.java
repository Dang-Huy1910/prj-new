/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.common;

import controller.auth.AuthenticationServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.User;

@WebServlet("/home")
public class HomeController extends AuthenticationServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp, User user) throws ServletException, IOException {
        if(user.getRole().getRid() == 3)
            req.getRequestDispatcher("view/planning/home.jsp").forward(req, resp);
        else if(user.getRole().getRid() == 5)
            req.getRequestDispatcher("view/manager/home.jsp").forward(req, resp);
        else if(user.getRole().getRid() == 7)
            req.getRequestDispatcher("view/emp/home.jsp").forward(req, resp);
        else if(user.getRole().getRid() == 1)
            req.getRequestDispatcher("view/admin/home.jsp").forward(req, resp);
    }
    
}
