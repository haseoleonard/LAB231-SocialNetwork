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
import nghiadh.notifications.NotificationsDAO;
import nghiadh.posts.PostsDAO;
import nghiadh.posts.PostsDTO;
import nghiadh.reactionType.ReactionTypeDAO;
import nghiadh.reactions.ReactionsDAO;
import nghiadh.users.UsersDTO;
import org.apache.log4j.Logger;

/**
 *
 * @author haseo
 */
@WebServlet(name = "UserReactToPostServlet", urlPatterns = {"/UserReactToPostServlet"})
public class UserReactToPostServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(UserReactToPostServlet.class);
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
            String button = request.getParameter("btAction");
            HttpSession session = request.getSession(false);
            int postID = Integer.parseInt(txtPostID);
            if (session != null) {
                UsersDTO loginUser = (UsersDTO) session.getAttribute("LOGIN_USER");
                if (button != null) {
                    PostsDAO postsDAO = new PostsDAO();
                    PostsDTO post = null;
                    //
                    if (postsDAO.getRequestedPost(postID)) {
                        post = postsDAO.getRequestPost();
                    }
                    //
                    ReactionTypeDAO reactionTypeDAO = new ReactionTypeDAO();
                    ReactionsDAO reactionsDAO = new ReactionsDAO();
                    //                    
                    int reactionType = reactionTypeDAO.getReactionTypeByName(button);
                    int lastReaction = reactionsDAO.checkUserReaction(postID, loginUser.getEmail());
                    String lastReactionName = reactionsDAO.getLastReactionName();
                    if (reactionType >= 0) {
                        //
                        NotificationEventDAO notificationTypeDAO = new NotificationEventDAO();
                        NotificationsDAO notificationsDAO = new NotificationsDAO();
                        int eventType;
                        //
                        if (lastReaction == -1) {
                            eventType = notificationTypeDAO.getEventTypeByName(button);
                            if (reactionsDAO.addReactionToPost(postID, loginUser.getEmail(), reactionType) && eventType >= 0) {
                                if (post != null && !post.getOwnerEmail().equals(loginUser.getEmail())) {
                                    notificationsDAO.addNotification(loginUser.getEmail(), postID, eventType);
                                }
                            }
                        } else if (lastReaction == reactionType) {
                            eventType = notificationTypeDAO.getEventTypeByName(lastReactionName);
                            if (reactionsDAO.removeReaction(postID, loginUser.getEmail())) {
                                if (post != null && !post.getOwnerEmail().equals(loginUser.getEmail())) {
                                    notificationsDAO.removeNotification(loginUser.getEmail(), postID, eventType);
                                }
                            }
                        } else {
                            if (reactionsDAO.updateReaction(postID, loginUser.getEmail(), reactionType)) {
                                if (post != null && !post.getOwnerEmail().equals(loginUser.getEmail())) {
                                    eventType = notificationTypeDAO.getEventTypeByName(lastReactionName);
                                    if (notificationsDAO.removeNotification(loginUser.getEmail(), postID, eventType)) {
                                        eventType = notificationTypeDAO.getEventTypeByName(button);
                                        notificationsDAO.addNotification(loginUser.getEmail(), postID, eventType);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (NumberFormatException ex) {
            LOGGER.error(ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.fatal(ex.getMessage());
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage());
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
