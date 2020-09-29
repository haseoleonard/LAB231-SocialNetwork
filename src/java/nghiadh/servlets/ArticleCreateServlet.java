/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadh.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import nghiadh.posts.PostsDAO;
import nghiadh.users.UsersDTO;
import nghiadh.utils.FileHelpers;
import org.apache.log4j.Logger;

/**
 *
 * @author haseo
 */
@MultipartConfig
@WebServlet(name = "ArticleCreateServlet", urlPatterns = {"/ArticleCreateServlet"})
public class ArticleCreateServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(ArticleCreateServlet.class);
    private static final String CREATE_ARTICLE_PAGE = "createArticlePage.jsp";
    private static final String LOAD_ARTICLE_CONTROLLER = "DispatchController?btAction=loadArticle&postID=";
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
        int postID=-1;
        try{
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession(false);
            UsersDTO loginUser = (UsersDTO) session.getAttribute("LOGIN_USER");
            String title = request.getParameter("txtTitle");
            String description = request.getParameter("txtDescription");
            String content = request.getParameter("txtContent");
            content=content.replaceAll("\n", "<br/>");
            String imgUrl = null;
            Part filePart = request.getPart("uploadImg");
            if(filePart!=null&&filePart.getSize()>0){
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();              
                fileName = System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));
                InputStream fileContent = filePart.getInputStream();
                FileHelpers.writeImgToServerFile(fileName, fileContent);
                imgUrl=fileName;
            }
            PostsDAO dao = new PostsDAO();
            boolean rs = dao.createNewPost(title, description, content, imgUrl, loginUser.getEmail());
            if(rs){
                postID=dao.getLastestPostIDByEmail(loginUser.getEmail());
                request.setAttribute("SUCCESS", "SUCCESS");
            }
        } catch (SQLException ex) {
            LOGGER.error(ex.getMessage());
        } catch (NamingException ex) {
            LOGGER.fatal(ex.getMessage());
        }finally{
            response.sendRedirect(LOAD_ARTICLE_CONTROLLER+postID);
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
