/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadh.NotificationEvent;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import nghiadh.utils.DBHelpers;

/**
 *
 * @author haseo
 */
public class NotificationEventDAO implements Serializable{
    public int getEventTypeByName(String eventName) throws SQLException, NamingException{
        int eventType = -1;
        Connection con=null;
        PreparedStatement psm=null;
        ResultSet rs=null;
        try{
            con = DBHelpers.makeConnection();
            if(con!=null){
                String sql = "select eventType from NotificationEvent where eventName like ?";
                psm = con.prepareStatement(sql);
                psm.setString(1, eventName);
                rs = psm.executeQuery();
                if(rs.next()){
                    eventType=rs.getInt("eventType");
                }
            }
        }finally{
            if(rs!=null)rs.close();
            if(psm!=null)psm.close();
            if(con!=null)con.close();
        }
        return eventType;
    }
}
