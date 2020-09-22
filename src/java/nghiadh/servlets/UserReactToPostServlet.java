/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadh.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import nghiadh.NotificationEvent.NotificationEventDAO;
import nghiadh.notifications.NotificationsDAO;
import nghiadh.reactionType.ReactionTypeDAO;
import nghiadh.reactions.ReactionsDAO;
import nghiadh.users.UsersDTO;

/**
 *
 * @author haseo
 */
@WebServlet(name = "UserReactToPostServlet", urlPatterns = {"/UserReactToPostServlet"})
public class UserReactToPostServlet extends HttpServlet {

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
                UsersDTO loginUsers = (UsersDTO) session.getAttribute("LOGIN_USER");
                if (button != null) {
                    boolean addNotification=false;
                    ReactionTypeDAO reactionTypeDAO = new ReactionTypeDAO();
                    ReactionsDAO reactionsDAO = new ReactionsDAO();
                    int reactionType = reactionTypeDAO.getReactionTypeByName(button);
                    int lastReaction = reactionsDAO.checkUserReaction(postID,loginUsers.getEmail());
                    if (reactionType >= 0) {
                        if(lastReaction==-1){
                            reactionsDAO.addReactionToPost(postID, loginUsers.getEmail(), reactionType);
                            addNotification=true;
                        }else if(lastReaction==reactionType){
                            reactionsDAO.removeReaction(postID, loginUsers.getEmail());
                        }else{
                            reactionsDAO.updateReaction(postID, loginUsers.getEmail(), reactionType);
                            addNotification=true;
                        }
                        if(addNotification){
                            NotificationEventDAO notificationTypeDAO = new NotificationEventDAO();
                            NotificationsDAO notificationsDAO = new NotificationsDAO();
                            int eventType = notificationTypeDAO.getEventTypeByName(button);
                            if(eventType>=0){
                                notificationsDAO.addNotification(loginUsers.getEmail(), postID, eventType);
                            }
                        }
                    }
                }
            }
        }catch(NumberFormatException ex){
        }catch (NamingException ex) {
            Logger.getLogger(UserReactToPostServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(UserReactToPostServlet.class.getName()).log(Level.SEVERE, null, ex);
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
