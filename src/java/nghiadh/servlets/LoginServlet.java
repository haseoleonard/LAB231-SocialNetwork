/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadh.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nghiadh.users.UsersDAO;
import nghiadh.utils.EncodeHelper;
import org.apache.log4j.Logger;

/**
 *
 * @author haseo
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class);
    private static final String LOGIN_PAGE = "login.jsp";
    private static final String ARTICLE_LIST_PAGE = "ArticleListPage.jsp";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = LOGIN_PAGE;
        boolean rs = false;
        try {
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");
            if(!username.trim().isEmpty()||!password.trim().isEmpty()){
                UsersDAO dao = new UsersDAO();
                String hashedPassword = EncodeHelper.toHexString(password);
                rs = dao.checkLogin(username, hashedPassword);
                if(rs){
                    HttpSession session = request.getSession();
                    session.setAttribute("LOGIN_USER", dao.getLoginUser());
                    Cookie usernameCookie = new Cookie("username", username);
                    usernameCookie.setMaxAge(3*60*60);
                    Cookie passwordCookie = new Cookie("password", hashedPassword);
                    passwordCookie.setMaxAge(3*60*60);
                    response.addCookie(usernameCookie);
                    response.addCookie(passwordCookie);
                    url=ARTICLE_LIST_PAGE;
                }else{
                    request.setAttribute("error", "Invalid Email or Password");
                }
            }
        } catch (SQLException | NamingException | NoSuchAlgorithmException ex) {
            LOGGER.error(ex.getMessage());
        }finally{
            if(!rs)request.getRequestDispatcher(url).forward(request, response);
            else response.sendRedirect(url);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
