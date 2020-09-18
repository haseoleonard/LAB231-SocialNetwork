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

/**
 * Web application lifecycle listener.
 *
 * @author haseo
 */
public class ServletContextListener implements javax.servlet.ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        ServletContext context = sce.getServletContext();
        //Guest Resource
        List<String> guestAllowList = new ArrayList<>();
        guestAllowList.add("login.jsp");
        guestAllowList.add("LoginServlet");
        guestAllowList.add("DispatchController");
        guestAllowList.add("createAccountPage.jsp");
        guestAllowList.add("CreateAccountServlet");
        context.setAttribute("GUEST_ALLOW_LIST", guestAllowList);
        //Member-only Resource
        List<String> memberResourceList = new ArrayList<>();
        memberResourceList.add("DispatchController");
        memberResourceList.add("LogoutServlet");
        memberResourceList.add("ArticleSearchingServlet");
        memberResourceList.add("ArticleListPage.jsp");
        memberResourceList.add("ArticlePage.jsp");
        memberResourceList.add("notificationsPage.jsp");
        context.setAttribute("MEMBER_RESOURCE", memberResourceList);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
