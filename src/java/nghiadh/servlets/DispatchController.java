/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadh.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author haseo
 */
@MultipartConfig
@WebServlet(name = "DispatchController", urlPatterns = {"/DispatchController"})
public class DispatchController extends HttpServlet {
    private static final String LOGIN_PAGE = "login.jsp";
    private static final String LOGOUT_CONTROLLER = "LogoutServlet";
    private static final String SEARCH_ARTICLE_CONTROLLER = "ArticleSearchingServlet";
    private static final String CREATE_ARTICLE_PAGE="createArticlePage.jsp";
    private static final String CREATE_ARTICLE_CONTROLLER = "ArticleCreateServlet";
    private static final String LOAD_ARTICLE_CONTROLLER = "ArticleLoadServlet";
    private static final String DELETE_ARTICLE_CONTROLLER = "ArticleDeleteServlet";
    private static final String ADD_COMMENT_CONTROLLER = "UserAddCommentServlet";
    private static final String DELETE_COMMENT_CONTROLLER = "UserDeleteCommentServlet";
    private static final String USER_REACT_CONTROLLER = "UserReactToPostServlet";
    private static final String LOAD_NOTIFICATION_CONTROLLER ="NotificationLoadingServlet";
    private static final String USER_VERIFICATION_CONTROLLER = "UserVerificationServlet";
    private static final String RESEND_VERIFICATION_CONTROLLER = "UserVerifyCodeResendServlet";
    
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
        String url = LOGIN_PAGE;
        try{
            String button = request.getParameter("btAction");
            
            if(button==null){
            }else if(button.equalsIgnoreCase("logout")){
                url=LOGOUT_CONTROLLER;
            }else if(button.equals("Search Article")){
                url=SEARCH_ARTICLE_CONTROLLER;
            }else if(button.equals("Create New Article")){
                url=CREATE_ARTICLE_PAGE;
            }else if(button.equals("Create Article")){
                url=CREATE_ARTICLE_CONTROLLER;
            }else if(button.equals("loadArticle")){
                url=LOAD_ARTICLE_CONTROLLER;
            }else if(button.equals("Delete Article")){
                url=DELETE_ARTICLE_CONTROLLER;
            }else if(button.equals("Comment")){
                url=ADD_COMMENT_CONTROLLER;
            }else if(button.equals("Delete Comment")){
                url=DELETE_COMMENT_CONTROLLER;
            }else if(button.equals("Like")||button.equals("Dislike")){
                url=USER_REACT_CONTROLLER;
            }else if(button.equals("loadNotification")){
                url=LOAD_NOTIFICATION_CONTROLLER;
            }else if(button.equals("Verify")){
                url=USER_VERIFICATION_CONTROLLER;
            }else if(button.equals("resendAuth")){
                url=RESEND_VERIFICATION_CONTROLLER;
            }
        }finally{
            request.getRequestDispatcher(url).forward(request, response);
            if(out!=null)out.close();
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
