/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadh.comments;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import nghiadh.utils.DBHelpers;

/**
 *
 * @author haseo
 */
public class CommentsDAO implements Serializable{
    private Connection con;
    private PreparedStatement psm;
    private ResultSet rs;

    public CommentsDAO() {
        con=null;
        psm=null;
        rs=null;
    }
    
    private void closeConnection() throws SQLException{
        if(this.rs!=null)this.rs.close();
        if(this.psm!=null)this.psm.close();
        if(this.con!=null)this.con.close();
    }
    
    public boolean createCommentOnPost(int postID,String comment,String commenterEmail) throws SQLException, NamingException{
        try{
            this.con = DBHelpers.makeConnection();
            if(this.con!=null){
                String sql = "insert into Comments(postID,commentEmail,commentContent) values(?,?,?)";
                this.psm = con.prepareStatement(sql);
                psm.setInt(1,postID);
                psm.setString(2, commenterEmail);
                psm.setNString(3, comment);
                int result = psm.executeUpdate();
                if(result>0)return true;
            }
        }finally{
            closeConnection();
        }
        return false;
    }
    private List<CommentsDTO> commentList;

    public List<CommentsDTO> getCommentList() {
        return commentList;
    }
    
    public int loadCommentListOnPost(int postID) throws SQLException, NamingException{
        int total = 0;
        try{
            con=DBHelpers.makeConnection();
            if(con!=null){
                String sql = "select commentID,commentEmail,commentContent,Users.name as commenterName "
                        + "from Comments,Users where postID=? and commentEmail like Users.email "
                        + "Order by commentTime Desc";
                psm = con.prepareStatement(sql);
                psm.setInt(1, postID);
                rs = psm.executeQuery();
                while(rs.next()){
                    if(this.commentList==null)this.commentList=new ArrayList<>();
                    int commentID = rs.getInt("commentID");
                    String commenterEmail = rs.getString("commentEmail");
                    String commenterName = rs.getNString("commenterName");
                    String commentContent = rs.getNString("commentContent");
                    this.commentList.add(new CommentsDTO(commentID, commenterEmail,commenterName, commentContent));
                    total++;
                }
            }
        }finally{
            closeConnection();
        }
        return total;
    }
    
    public boolean deleteComment(int commentID) throws SQLException, NamingException{
        try{
            con =DBHelpers.makeConnection();
            if(con!=null){
                String sql = "delete from Comments where commentID=?";
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
}
