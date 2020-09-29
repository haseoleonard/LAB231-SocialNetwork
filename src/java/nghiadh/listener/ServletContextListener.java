/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadh.listener;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import nghiadh.utils.FileHelpers;
import nghiadh.utils.MailHelpers;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Web application lifecycle listener.
 *
 * @author haseo
 */
public class ServletContextListener implements javax.servlet.ServletContextListener {
    private static final Logger LOGGER = Logger.getLogger(ServletContextListener.class);
    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        ServletContext context = sce.getServletContext();
        //Guest Resource
        List<String> guestAllowList = new ArrayList<>();
        guestAllowList.add("login.jsp");
        guestAllowList.add("LoginServlet");
        guestAllowList.add("GuestDispatchController");
        guestAllowList.add("createAccountPage.jsp");
        guestAllowList.add("CreateAccountServlet");
        context.setAttribute("GUEST_ALLOW_LIST", guestAllowList);
        PropertyConfigurator.configure(context.getRealPath("/WEB-INF/log4j.properties"));
        String hostEmail = context.getInitParameter("HOST_EMAIL");
        String emailPass = context.getInitParameter("HOST_EMAIL_PASSWORD");
        String uploadDir = context.getInitParameter("IMAGE_UPLOAD_DIR");
        try {
            if (hostEmail == null || hostEmail.trim().isEmpty()) {
                throw new Exception("HOST EMAIL MUST NOT BE EMPTY");
            }
            MailHelpers.setMAIL_USER(hostEmail);
            if(emailPass == null||emailPass.trim().isEmpty()){
                throw new Exception("PASSWORD FOR EMAIL CANNOT BE EMPTY");
            }
            MailHelpers.setMAIL_PASSWORD(emailPass);
            if(uploadDir==null||uploadDir.trim().isEmpty()){
                throw new Exception("IMAGE UPLOAD DIRECTORY CANNOT BE EMPTY");
            }
            FileHelpers.setIMAGE_SAVING_FOLDER(uploadDir);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        //Member-only Resource
//        List<String> memberResourceList = new ArrayList<>();
//        memberResourceList.add("DispatchController");
//        memberResourceList.add("LogoutServlet");
//        memberResourceList.add("ArticleSearchingServlet");
//        memberResourceList.add("ArticleListPage.jsp");
//        memberResourceList.add("ArticlePage.jsp");
//        memberResourceList.add("notificationsPage.jsp");
//        context.setAttribute("MEMBER_RESOURCE", memberResourceList);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
