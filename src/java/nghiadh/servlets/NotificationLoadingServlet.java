/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadh.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nghiadh.notifications.NotificationsDAO;
import nghiadh.notifications.NotificationsDTO;
import nghiadh.posts.PostsDAO;
import nghiadh.users.UsersDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author haseo
 */
@WebServlet(name = "NotificationLoadingServlet", urlPatterns = {"/NotificationLoadingServlet"})
public class NotificationLoadingServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(NotificationLoadingServlet.class);
    private static final String NOTIFICATION_PAGE = "notificationsPage.jsp";
    private static final int DEFAULT_PAGE_NUMBER = 1;

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
                NotificationsDAO notificationsDAO = new NotificationsDAO();
                PostsDAO postsDAO = new PostsDAO();
                int page = DEFAULT_PAGE_NUMBER;
                UsersDTO loginUser = (UsersDTO) session.getAttribute("LOGIN_USER");
                String txtPage = request.getParameter("page");
                request.setAttribute("LOADED", "LOADED");
                request.setAttribute("NEXT_PAGE", NOTIFICATION_PAGE);
                String postIDString = postsDAO.getPostIDStringByEmail(loginUser.getEmail());
                if (postIDString != null && !postIDString.trim().isEmpty()) {
                    int totalPage = notificationsDAO.getNumberOfNotificationPage(postIDString);
                    if (totalPage > 0) {
                        if (txtPage != null && !txtPage.trim().isEmpty()&&txtPage.matches("\\d+")) {
                            page = Integer.parseInt(txtPage);
                            if (page < DEFAULT_PAGE_NUMBER) {
                                page = DEFAULT_PAGE_NUMBER;
                            }else if(page>totalPage){
                                page=totalPage;
                            }
                        }
                        request.setAttribute("CURR_PAGE", page);
                        request.setAttribute("TOTAL_NOTIFY_PAGE", totalPage);
                        int rs = notificationsDAO.getNotificationByPostID(postIDString, page);
                        if (rs > 0) {
                            List<NotificationsDTO> notificationList = notificationsDAO.getNotificationsList();
                            request.setAttribute("NOTIFICATION_LIST", notificationList);
                        }
                    }
                }
            }
        } catch (NumberFormatException ex) {
            LOGGER.error(ex.getMessage());
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.fatal(ex.getMessage());
        } finally {
            request.getRequestDispatcher(NOTIFICATION_PAGE).forward(request, response);
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
