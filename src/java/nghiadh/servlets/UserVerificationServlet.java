/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadh.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nghiadh.userauthentication.UserAuthenticationDAO;
import nghiadh.users.UsersDAO;
import nghiadh.users.UsersDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author haseo
 */
@WebServlet(name = "UserVerificationServlet", urlPatterns = {"/UserVerificationServlet"})
public class UserVerificationServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(UserVerificationServlet.class);
    private static final int ACTIVATED_ID = 2;
    private static final String VERIFICATION_PAGE = "AccountVerifyPage.jsp";

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
        try {
            String authCode = request.getParameter("txtAuthCode");
            if (authCode != null && !authCode.isEmpty()) {
                authCode=authCode.trim().toUpperCase();
                HttpSession session = request.getSession(false);
                UsersDAO usersDAO = new UsersDAO();
                if (session != null) {
                    UsersDTO loginUser = (UsersDTO) session.getAttribute("LOGIN_USER");
                    UserAuthenticationDAO dao = new UserAuthenticationDAO();
                    boolean rs = dao.checkAuthCode(loginUser.getEmail(), authCode);
                    if(rs){
                        usersDAO.updateStatusAccount(loginUser.getEmail(), ACTIVATED_ID);
                        if(usersDAO.reloadLoginUser(loginUser.getEmail())){
                            loginUser = usersDAO.getLoginUser();
                            session.setAttribute("LOGIN_USER", loginUser);
                        }
                    }else{
                        request.setAttribute("FAILED", "FAILED");
                    }
                }
            }
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.fatal(ex.getMessage());
        } finally {
            request.getRequestDispatcher(VERIFICATION_PAGE).forward(request, response);
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
