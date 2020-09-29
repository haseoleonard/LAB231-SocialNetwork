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
import nghiadh.NotificationEvent.NotificationEventDAO;
import nghiadh.comments.CommentsDAO;
import nghiadh.notifications.NotificationsDAO;
import nghiadh.posts.PostsDAO;
import nghiadh.posts.PostsDTO;
import nghiadh.users.UsersDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author haseo
 */
@WebServlet(name = "UserAddCommentServlet", urlPatterns = {"/UserAddCommentServlet"})
public class UserAddCommentServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(UserAddCommentServlet.class);
    private static final String LOAD_ARTICLE_CONTROLLER = "ArticleLoadServlet";

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
        String txtPostID = request.getParameter("postID");
        try {
            HttpSession session = request.getSession(false);
            String commentContent = request.getParameter("txtComment");
            commentContent=commentContent.replaceAll("\n","<br/>");
            int postID = Integer.parseInt(txtPostID);
            if (session != null) {
                PostsDAO postsDAO = new PostsDAO();
                PostsDTO post = null;
                //
                if (postsDAO.getRequestedPost(postID)) {
                    post = postsDAO.getRequestPost();
                }
                //
                UsersDTO loginUser = (UsersDTO) session.getAttribute("LOGIN_USER");
                CommentsDAO dao = new CommentsDAO();
                boolean rs = dao.createCommentOnPost(postID, commentContent, loginUser.getEmail());
                if (rs) {
                    if (post != null && !post.getOwnerEmail().equals(loginUser.getEmail())) {
                        int commentID = dao.getRecentCommentID(postID, loginUser.getEmail());
                        if (commentID >= 0) {
                            NotificationEventDAO notificationTypeDAO = new NotificationEventDAO();
                            int eventType = notificationTypeDAO.getEventTypeByName("Comment");
                            if (eventType >= 0) {
                                NotificationsDAO notificationsDAO = new NotificationsDAO();
                                notificationsDAO.addNotification(loginUser.getEmail(), postID, commentID, eventType);
                            }
                        }
                    }
                }
            }
        } catch (NumberFormatException | SQLException ex) {
            LOGGER.error(ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.fatal(ex.getMessage());
        } finally {
            response.sendRedirect(LOAD_ARTICLE_CONTROLLER + "?postID=" + txtPostID);
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
