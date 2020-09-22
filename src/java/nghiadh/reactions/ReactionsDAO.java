/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadh.reactions;

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
public class ReactionsDAO implements Serializable{
    private static final int REACTION_TYPE_LIKE=0;
    private static final int REACTION_TYPE_DISLIKE=1;
    
    private Connection con;
    private PreparedStatement psm = null;
    private ResultSet rs = null;

    public ReactionsDAO() {
        con = null;
        psm = null;
        rs = null;
    }
    
    private void closeConnection() throws SQLException{
        if(rs!=null)rs.close();
        if(psm!=null)psm.close();
        if(con!=null)con.close();
    }
    
    public int getNumberOfReaction(int postID,int ReactionType) throws SQLException, NamingException{
        int totalLike = 0;
        try{
            con = DBHelpers.makeConnection();
            if(con!=null){
                String sql = "select count(reactEmail) as totalReaction "
                        + "from Reactions "
                        + "where postID=? and reactionType=?";
                psm = con.prepareStatement(sql);
                psm.setInt(1, postID);
                psm.setInt(2, ReactionType);
                rs = psm.executeQuery();
                if(rs.next()){
                    totalLike=rs.getInt("totalReaction");
                }
            }
        }finally{
            closeConnection();
        }
        return totalLike;
    }
    
    public int checkUserReaction(int postID, String reactEmail) throws SQLException, NamingException{
        int reactionType = -1;
        try{
            con = DBHelpers.makeConnection();
            if(con!=null){
                String sql = "select reactionType from Reactions where postID=? and reactEmail like ?";
                psm = con.prepareStatement(sql);
                psm.setInt(1, postID);
                psm.setString(2, reactEmail);
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
    
    public boolean addReactionToPost(int postID,String reactEmail, int reactionType) throws SQLException, NamingException{
        try{
            con = DBHelpers.makeConnection();
            if(con!=null){
                String sql = "insert into Reactions(postID,reactEmail,reactionType) values (?,?,?)";
                psm = con.prepareStatement(sql);
                psm.setInt(1, postID);
                psm.setString(2, reactEmail);
                psm.setInt(3, reactionType);
                int rs = psm.executeUpdate();
                if(rs>0)return true;
            }
        }finally{
            closeConnection();
        }
        return false;
    }
    
    public boolean updateReaction(int postID,String reactEmail,int reactionType) throws NamingException, SQLException{
        try{
            con = DBHelpers.makeConnection();
            if(con!=null){
                String sql = "Update Reactions set reactionType=? "
                        + "where postID=? and reactEmail like ?";
                psm = con.prepareStatement(sql);
                psm.setInt(1, reactionType);
                psm.setInt(2, postID);
                psm.setString(3, reactEmail);                
                int rs = psm.executeUpdate();
                if(rs>0)return true;
            }
        }finally{
            closeConnection();
        }
        return false;
    }
    
    public boolean removeReaction(int postID,String reactEmail) throws NamingException, SQLException{
        try{
            con = DBHelpers.makeConnection();
            if(con!=null){
                String sql = "delete from Reactions where postID=? and reactEmail like ?";
                psm = con.prepareStatement(sql);
                psm.setInt(1, postID);
                psm.setString(2, reactEmail);                
                int rs = psm.executeUpdate();
                if(rs>0)return true;
            }
        }finally{
            closeConnection();
        }
        return false;
    }
}
