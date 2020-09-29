/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadh.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.mail.MessagingException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nghiadh.userauthentication.UserAuthenticationDAO;
import nghiadh.users.UsersDTO;
import nghiadh.utils.EncodeHelper;
import nghiadh.utils.MailHelpers;
import org.apache.log4j.Logger;

/**
 *
 * @author haseo
 */
@WebServlet(name = "UserVerifyCodeResendServlet", urlPatterns = {"/UserVerifyCodeResendServlet"})
public class UserVerifyCodeResendServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(UserVerifyCodeResendServlet.class); 
    private static final String VERIFY_PAGE = "AccountVerifyPage.jsp";
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
            HttpSession session = request.getSession(false);
            if (session != null) {
                UsersDTO loginUser = (UsersDTO) session.getAttribute("LOGIN_USER");
                UserAuthenticationDAO userAuthenticationDAO = new UserAuthenticationDAO();
                Timestamp expTime = userAuthenticationDAO.getAuthCodeTimeOutDate(loginUser.getEmail());
                String authCode = null;
                if (expTime == null) {
                    if (userAuthenticationDAO.deleteTimeOutAuthCode()) {
                        authCode = EncodeHelper.generateNewVerificationCode();
                        userAuthenticationDAO.addTimeOutAuthCode(loginUser.getEmail(), authCode);
                        expTime = userAuthenticationDAO.getAuthCodeTimeOutDate(loginUser.getEmail());
                    }
                }
                authCode = userAuthenticationDAO.getAuthCode();
                MailHelpers.sendVerificationEmail(loginUser.getEmail(), authCode, expTime);
            }
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.fatal(ex.getMessage());
        } catch (MessagingException ex) {
            LOGGER.error(ex.getMessage());
        } catch (UnsupportedEncodingException ex) {
            LOGGER.fatal(ex.getMessage());
        } finally {
            response.sendRedirect(VERIFY_PAGE);
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
