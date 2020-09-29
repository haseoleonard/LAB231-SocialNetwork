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
import nghiadh.posts.PostsDAO;
import org.apache.log4j.Logger;

/**
 *
 * @author haseo
 */
@WebServlet(name = "ArticleDeleteServlet", urlPatterns = {"/ArticleDeleteServlet"})
public class ArticleDeleteServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ArticleDeleteServlet.class);
    private static final String LOAD_ARTICLE_CONTROLLER = "ArtcileLoadServlet";
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
        String txtPostID = request.getParameter("postID");
        String url = "";
        try{
            if(txtPostID!=null&&!txtPostID.trim().isEmpty()){
                url= LOAD_ARTICLE_CONTROLLER+"?postID="+txtPostID;
            }else{
                url = ARTICLE_LIST_PAGE;
            }
            int postID = Integer.parseInt(txtPostID);
            PostsDAO dao = new PostsDAO();
            boolean rs = dao.setPostToDelete(postID);
            if(rs){
                url=ARTICLE_LIST_PAGE;
            }
        } catch (NamingException ex) {
            LOGGER.fatal(ex.getMessage());
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage());
        }finally{
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
