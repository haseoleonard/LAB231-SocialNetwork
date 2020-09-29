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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import nghiadh.utils.DBHelpers;

/**
 *
 * @author haseo
 */
public class NotificationsDAO implements Serializable{
    private static final double NOTIFICATION_PER_PAGE = 20;
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
    
    public boolean addNotification(String triggeredEmail,int triggeredPostID,int commentID, int eventType) throws SQLException, NamingException{
        try{
            con = DBHelpers.makeConnection();
            if(con!=null){
                String sql = "insert into Notifications(triggerEmail,triggeredPostID,triggeredCommentID,eventType) "
                        + "values (?,?,?,?)";
                psm = con.prepareStatement(sql);
                psm.setString(1, triggeredEmail);
                psm.setInt(2, triggeredPostID);
                psm.setInt(3, commentID);
                psm.setInt(4, eventType);
                int result=psm.executeUpdate();
                if(result>0)return true;
            }
        }finally{
            closeConnection();
        }
        return false;
    }
    
    public boolean removeNotification(String triggeredEmail,int triggeredPostID,int eventType) throws SQLException, NamingException{
        try{
            con = DBHelpers.makeConnection();
            if(con!=null){
                String sql = "delete from Notifications "
                        + "where triggeredPostID=? and eventType=? "
                        + "and triggerEmail=?";
                psm = con.prepareStatement(sql);
                psm.setInt(1, triggeredPostID);
                psm.setInt(2, eventType);
                psm.setString(3, triggeredEmail);
                int result = psm.executeUpdate();
                if(result>0)return true;
            }
        }finally{
            closeConnection();
        }
        return false;
    }
    
    public boolean removeNotification(int commentID) throws SQLException, NamingException{
        try{
            con = DBHelpers.makeConnection();
            if(con!=null){
                String sql = "delete from Notifications "
                        + "where triggeredCommentID=?";
                psm = con.prepareStatement(sql);
                psm.setInt(1, commentID);
                int result = psm.executeUpdate();
                if(result>0)return true;
            }
        }finally{
            closeConnection();
        }
        return false;
    }
    
    public int getNumberOfNotificationPage(String postIDString) throws SQLException, NamingException{
        int totalPost = 0;
        try{
            con=DBHelpers.makeConnection();
            if(con!=null){
                String sql = "select count(notifyReceivedTime) as totalNoti "
                        + "from Notifications "
                        + "where triggeredPostID IN ("+postIDString+")";
                psm = con.prepareStatement(sql);
                rs=psm.executeQuery();
                if(rs.next()){
                    totalPost=rs.getInt("totalNoti");
                }
            }
        }finally{
            closeConnection();
        }
        return (int) Math.ceil(totalPost/NOTIFICATION_PER_PAGE);
    }
    
    private List<NotificationsDTO> notificationsList;

    public List<NotificationsDTO> getNotificationsList() {
        return notificationsList;
    }
    
    public int getNotificationByPostID(String postIDString,int page) throws NamingException, SQLException{
        int totalNotification = 0;
        try{
            con=DBHelpers.makeConnection();
            if(con!=null){
                String sql = "select triggerEmail,triggeredPostID,eventName,notifyReceivedTime "
                        + "from Notifications n,NotificationEvent e "
                        + "where e.eventType=n.eventType "
                        + "and triggeredPostID IN ("+postIDString+") "
                        + "ORDER BY notifyReceivedTime desc "
                        + "OFFSET ? Rows "
                        + "FETCH NEXT ? Rows ONLY";
                psm = con.prepareStatement(sql);
                psm.setInt(1, (page-1)*(int)NOTIFICATION_PER_PAGE);
                psm.setInt(2, (int)NOTIFICATION_PER_PAGE);
                rs = psm.executeQuery();
                while(rs.next()){
                    if(this.notificationsList==null)this.notificationsList=new ArrayList<>();
                    String triggerEmail = rs.getString("triggerEmail");
                    int postID = rs.getInt("triggeredPostID");
                    String eventName = rs.getString("eventName");
                    Timestamp time = rs.getTimestamp("notifyReceivedTime");
                    this.notificationsList.add(new NotificationsDTO(triggerEmail, postID, eventName,time));
                    totalNotification++;
                }
            }
        }finally{
            closeConnection();
        }
        return totalNotification;
    }
}
