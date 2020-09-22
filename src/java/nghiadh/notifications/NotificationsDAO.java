/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadh.notifications;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import nghiadh.utils.DBHelpers;

/**
 *
 * @author haseo
 */
public class NotificationsDAO implements Serializable{
    private Connection con;
    private PreparedStatement psm;
    private ResultSet rs;

    public NotificationsDAO() {
        con=null;
        psm=null;
        rs=null;
    }
    private void closeConnection() throws SQLException{
        if(rs!=null)rs.close();
        if(psm!=null)psm.close();
        if(con!=null)con.close();
    }
    
    public boolean addNotification(String triggeredEmail,int triggeredPostID, int eventType) throws SQLException, NamingException{
        try{
            con = DBHelpers.makeConnection();
            if(con!=null){
                String sql = "insert into Notifications(triggerEmail,triggeredPostID,eventType) "
                        + "values (?,?,?)";
                psm = con.prepareStatement(sql);
                psm.setString(1, triggeredEmail);
                psm.setInt(2, triggeredPostID);
                psm.setInt(3, eventType);
                int result=psm.executeUpdate();
                if(result>0)return true;
            }
        }finally{
            closeConnection();
        }
        return false;
    }
    
    private List<NotificationsDTO> notificationsList;

    public List<NotificationsDTO> getNotificationsList() {
        return notificationsList;
    }
    
    public int getNotificationByPostID() throws NamingException, SQLException{
        int totalNotification = 0;
        try{
            con=DBHelpers.makeConnection();
            if(con!=null){
                
            }
        }finally{
            closeConnection();
        }
        return totalNotification;
    }
}
