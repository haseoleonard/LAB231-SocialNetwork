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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import nghiadh.posts.PostsDAO;
import nghiadh.posts.PostsDTO;

/**
 *
 * @author haseo
 */
@WebServlet(name = "ArticleSearchingServlet", urlPatterns = {"/ArticleSearchingServlet"})
public class ArticleSearchingServlet extends HttpServlet {
    private static final int DEFAULT_PAGE_NUMBER = 1;
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
        String searchedContent = request.getParameter("txtContent");
        String hiddenPage = request.getParameter("hiddenPage");
        try {
            if (searchedContent != null&&!searchedContent.trim().isEmpty()) {
                int page = DEFAULT_PAGE_NUMBER;
                if (hiddenPage!=null&&!hiddenPage.trim().isEmpty()) {
                    page = Integer.parseInt(hiddenPage);
                }
                PostsDAO dao = new PostsDAO();
                int rs = dao.searchPostByContent(searchedContent, page);
                int totalPage = dao.getNumberOfPageForPostWithContent(searchedContent);
                if(rs>0){
                    List<PostsDTO> resultList = dao.getResultList();
                    request.setAttribute("RESULT_LIST", resultList);
                    request.setAttribute("NUMBER_OF_PAGE", totalPage);
                }
            }
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(ArticleSearchingServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ArticleSearchingServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            request.getRequestDispatcher(ARTICLE_LIST_PAGE).forward(request, response);
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
