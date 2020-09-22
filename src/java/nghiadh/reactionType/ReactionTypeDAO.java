/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadh.reactionType;

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
public class ReactionTypeDAO implements Serializable{
    private Connection con;
    private PreparedStatement psm;
    private ResultSet rs;

    public ReactionTypeDAO() {
        con=null;
        psm=null;
        rs=null;
    }
    
    private void closeConnection() throws SQLException{
        if(rs!=null)rs.close();
        if(psm!=null)psm.close();
        if(con!=null)con.close();
    }
    
    public int getReactionTypeByName(String ReactionName) throws NamingException, SQLException{
        int reactionType = -1;
        try{
            con = DBHelpers.makeConnection();
            if(con!=null){
                String sql = "select reactionType from ReactionType where reactionName like ?";
                psm = con.prepareStatement(sql);
                psm.setString(1, ReactionName);
                rs = psm.executeQuery();
                if(rs.next()){
                    reactionType=rs.getInt("reactionType");
                }
            }
        }finally{
            closeConnection();
        }
        return reactionType;
    }
}
