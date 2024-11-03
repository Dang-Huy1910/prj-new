package controller.auth;

import DAO.FeatureDAO;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;


@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpSession session = httpRequest.getSession();
            User account = null;
            Object obj = session.getAttribute("user");
            if (obj != null) {
                account = (User) obj;
            }
            FeatureDAO featureDAO = new FeatureDAO();
            String path = httpRequest.getRequestURI();
            
            String url = httpRequest.getScheme() + "://" + httpRequest.getServerName() + ":" + httpRequest.getServerPort()
                    + httpRequest.getContextPath();
            if (path.contains(".")&&!path.contains(".jsp")) {
                chain.doFilter(request, response);
                return;
            }
            if(featureDAO.CheckPublic(path)){
                chain.doFilter(request, response);
                return;
            }
            if (account != null) {
                try {
                    boolean result = featureDAO.CheckUrl(account.getRole().getRid(),path);
                    if(result){
                        chain.doFilter(request, response);
                    }else{
                        ((HttpServletResponse) response).sendRedirect(url+"/error404");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(AuthFilter.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                
                ((HttpServletResponse) response).sendRedirect(url+"/login");
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AuthFilter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
