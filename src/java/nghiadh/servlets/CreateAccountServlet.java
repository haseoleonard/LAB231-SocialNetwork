/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadh.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.mail.MessagingException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nghiadh.userauthentication.UserAuthenticationDAO;
import nghiadh.users.UsersDAO;
import nghiadh.users.UsersError;
import nghiadh.utils.EncodeHelper;
import nghiadh.utils.MailHelpers;
import org.apache.log4j.Logger;

/**
 *
 * @author haseo
 */
@WebServlet(name = "CreateAccountServlet", urlPatterns = {"/CreateAccountServlet"})
public class CreateAccountServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(CreateAccountServlet.class);
    private static final String CREATE_ACCOUNT_PAGE = "createAccountPage.jsp";

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
        String url = CREATE_ACCOUNT_PAGE;
        try {
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            String name = request.getParameter("txtName");
            UsersDAO dao = new UsersDAO();
            String hashedPassword = EncodeHelper.toHexString(password);
            boolean rs = dao.createNewAccount(email, hashedPassword, name);
            if (rs) {
                UserAuthenticationDAO authenticationDAO = new UserAuthenticationDAO();
                String authCode = EncodeHelper.generateNewVerificationCode();
                if(authenticationDAO.addTimeOutAuthCode(email, authCode)){
                    Timestamp timeOutDate = authenticationDAO.getAuthCodeTimeOutDate(email);
                    MailHelpers.sendVerificationEmail(email, authCode, timeOutDate);
                    request.setAttribute("CREATE_SUCCESS", rs);    
                }                
            }
        } catch (SQLException ex) {
            if (ex.getMessage().contains("duplicate")) {
                UsersError error = new UsersError();
                error.setEmailExistedErr("Email Existed!!!");
                request.setAttribute("ERROR", error);
            }
        } catch (NamingException ex) {
            LOGGER.fatal(ex.getMessage());
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.fatal(ex.getMessage());
        } catch (MessagingException ex) {
            LOGGER.error(ex.getMessage());
        } catch (UnsupportedEncodingException ex) {
            LOGGER.error(ex.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
