/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.auth;

import DAO.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;


public abstract class AuthenticationServlet extends HttpServlet {

    private User getAuthentication(HttpServletRequest req) {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            Cookie[] cookies = req.getCookies();
            if (cookies != null) {
                String userName = null;
                String passWord = null;
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("userName")) {
                        userName = cookie.getValue();
                    }
                    if (cookie.getName().equals("passWord")) {
                        passWord = cookie.getValue();
                    }
                    if (userName != null && passWord != null) {
                        break;
                    }
                }
                if (userName == null || passWord == null) {
                    return null;
                } else {
                    User test = null;
                    UserDAO accountDAO = new UserDAO();
                    try {
                        test = accountDAO.getUserLogin(userName,passWord);
                    } catch (SQLException ex) {
                        Logger.getLogger(AuthenticationServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (test != null) {
                        session.setAttribute("user", test);
                    }
                    return test;
                }
            }
        }
        return user;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getAuthentication(req);
        if (user != null) {
            doPost(req, resp, user);
        } else {
         
            String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                    + req.getContextPath();

            resp.sendRedirect(url + "/login");
        }
    }

    protected abstract void doPost(HttpServletRequest req, HttpServletResponse resp, User user)
            throws ServletException, IOException;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = getAuthentication(req);
        if (user != null) {
            doGet(req, resp, user);
        } else {
            String url = req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort()
                    + req.getContextPath();

            resp.sendRedirect(url + "/login");
        }

    }

    protected abstract void doGet(HttpServletRequest req, HttpServletResponse resp, User user)
            throws ServletException, IOException;

}
