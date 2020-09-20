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
import java.util.logging.Level;
import java.util.logging.Logger;
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

/**
 *
 * @author haseo
 */
@MultipartConfig
@WebServlet(name = "ArticleCreateServlet", urlPatterns = {"/ArticleCreateServlet"})
public class ArticleCreateServlet extends HttpServlet {
    private static final String CREATE_ARTICLE_PAGE = "createArticlePage.jsp";
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
        try{
            /* TODO output your page here. You may use following sample code. */
            HttpSession session = request.getSession(false);
            UsersDTO loginUser = (UsersDTO) session.getAttribute("LOGIN_USER");
            String title = request.getParameter("txtTitle");
            String description = request.getParameter("txtDescription");
            String content = request.getParameter("txtContent");
            String imgUrl = null;
            Part filePart = request.getPart("uploadImg");
            if(filePart!=null&&filePart.getSize()>0){
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();              
                fileName = System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));
                String filePath = request.getServletContext().getRealPath("/PostsImg/");
                InputStream fileContent = filePart.getInputStream();
//                FileHelpers.writeImgToServerFile(filePath+fileName, fileContent);
//                imgUrl=request.getContextPath()+"/PostsImg/"+fileName;
                FileHelpers.writeImgToServerFile("D:\\PostsImg\\"+fileName, fileContent);
                imgUrl="D:/PostsImg/"+fileName;
//                System.out.println(imgUrl);
            }
            PostsDAO dao = new PostsDAO();
            boolean rs = dao.createNewPost(title, description, content, imgUrl, loginUser.getEmail());
            if(rs){
                request.setAttribute("SUCCESS", "SUCCESS");
            }else{
                request.setAttribute("FAILED", "FAILED");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticleCreateServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ArticleCreateServlet.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            request.getRequestDispatcher(CREATE_ARTICLE_PAGE).forward(request, response);
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
