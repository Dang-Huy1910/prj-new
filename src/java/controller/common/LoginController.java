package controller.common;

import DAO.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res){
        try {
            req.getRequestDispatcher("view/public/login.jsp").forward(req,res);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        try {
            String userName = req.getParameter("username");
            String passWord = req.getParameter("password");
            String warningLogin;
            String url;
            UserDAO userDAO = new UserDAO();
            User user = userDAO.getUserLogin(userName, passWord);
            if(user == null){
                url = "view/public/login.jsp";
                warningLogin = "username or password incorrect";
                req.setAttribute("warningLogin", warningLogin);
                req.getRequestDispatcher(url).forward(req, res);
            }else {
                if (!user.isActive()) {
                    url = "view/public/login.jsp";
                    warningLogin = "Account need to be activated";
                    req.setAttribute("warningLogin", warningLogin);
                    req.getRequestDispatcher(url).forward(req, res);
                }
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                
                Cookie c_username = new Cookie("userName", userName);
                c_username.setMaxAge(3600 * 24 * 7);
                
                Cookie c_password = new Cookie("passWord", passWord);
                c_password.setMaxAge(3600 * 24 * 7);                     
                res.addCookie(c_username);
                res.addCookie(c_password);
                res.sendRedirect("home");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
