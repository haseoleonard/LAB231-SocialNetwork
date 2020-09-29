/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadh.servlets;

import java.io.FileNotFoundException;
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
import nghiadh.comments.CommentsDAO;
import nghiadh.comments.CommentsDTO;
import nghiadh.posts.PostsDAO;
import nghiadh.posts.PostsDTO;
import nghiadh.reactionType.ReactionTypeDAO;
import nghiadh.reactions.ReactionsDAO;
import nghiadh.users.UsersDTO;
import nghiadh.utils.FileHelpers;
import org.apache.log4j.Logger;

/**
 *
 * @author haseo
 */
@WebServlet(name = "ArticleLoadServlet", urlPatterns = {"/ArticleLoadServlet"})
public class ArticleLoadServlet extends HttpServlet {

    private static final String ARTICLE_PAGE = "ArticlePage.jsp";
    private static final Logger LOGGER = Logger.getLogger(ArticleLoadServlet.class);

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
            UsersDTO loginUser = (UsersDTO) session.getAttribute("LOGIN_USER");
            /* TODO output your page here. You may use following sample code. */
            String postID = request.getParameter("postID");
            int iPostID = 0;
            if (postID != null && !postID.trim().isEmpty()) {
                iPostID = Integer.parseInt(postID);
            }
            PostsDAO postsDAO = new PostsDAO();
            CommentsDAO commentDAO = new CommentsDAO();
            ReactionsDAO reactionsDAO = new ReactionsDAO();
            ReactionTypeDAO reactionsTypeDAO = new ReactionTypeDAO();
            
            boolean rs = postsDAO.getRequestedPost(iPostID);
            if (rs) {
                PostsDTO post = postsDAO.getRequestPost();
                request.setAttribute("LOADED_ARTICLE", post);
                int lastReaction = reactionsDAO.checkUserReaction(iPostID, loginUser.getEmail());
                String lastReactionName = reactionsDAO.getLastReactionName();
                request.setAttribute("LAST_REACTION", lastReactionName);
                //
                int reactionType = reactionsTypeDAO.getReactionTypeByName("Like");
                int numberOfLike = reactionsDAO.getNumberOfReaction(iPostID, reactionType);
                request.setAttribute("NUMBER_OF_LIKE", numberOfLike);
                //
                reactionType = reactionsTypeDAO.getReactionTypeByName("Dislike");
                int numberOfDislike = reactionsDAO.getNumberOfReaction(iPostID, reactionType);
                request.setAttribute("NUMBER_OF_DISLIKE", numberOfDislike);
                //
                int totalComment = commentDAO.loadCommentListOnPost(iPostID);
                request.setAttribute("NUMBER_OF_COMMENT", totalComment);
                if (totalComment > 0) {
                    List<CommentsDTO> commentList = commentDAO.getCommentList();
                    request.setAttribute("LOADED_COMMENT", commentList);
                }
                //
                if (post.getImg() != null && !post.getImg().trim().isEmpty()) {
                    String realPath = request.getServletContext().getRealPath("/PostsImg");
                    FileHelpers.copyImgToContextFolder(realPath, post.getImg());
                }
                //
            }
        } catch (FileNotFoundException | NumberFormatException | SQLException ex) {
            LOGGER.error(ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.fatal(ex.getMessage());
        } finally {
            request.getRequestDispatcher(ARTICLE_PAGE).forward(request, response);
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
