/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadh.posts;

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
public class PostsDAO implements Serializable {

    private static final double POST_PER_LOAD = 20;
    private PostsDTO requestPost;

    public PostsDTO getRequestPost() {
        return requestPost;
    }

    public int getLastestPostIDByEmail(String email) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "select Max(postID) as postID from Posts where email like ?";
                psm = con.prepareStatement(sql);
                psm.setString(1, email);
                rs = psm.executeQuery();
                if (rs.next()) {
                    return rs.getInt("postID");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (psm != null) {
                psm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return -1;
    }

    public boolean getRequestedPost(int postID) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "select title,postDescription,postContent,images,email,submitTime "
                        + "from Posts "
                        + "where status=1 and postID=?";
                psm = con.prepareStatement(sql);
                psm.setInt(1, postID);
                rs = psm.executeQuery();
                if (rs.next()) {
                    String title = rs.getNString("title");
                    String description = rs.getNString("postDescription");
                    String content = rs.getNString("postContent");
                    String imgURL = rs.getString("images");
                    String ownerEmail = rs.getString("email");
                    Timestamp time = rs.getTimestamp("submitTime");
                    this.requestPost = new PostsDTO(postID, ownerEmail, title, description, content, imgURL, time);
                    return true;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (psm != null) {
                psm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public int getNumberOfPage() throws SQLException, NamingException {
        int total = 0;
        Connection con = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "select Count(postID) as totalPost from Posts where status=1";
                psm = con.prepareStatement(sql);
                rs = psm.executeQuery();
                if (rs.next()) {
                    total = rs.getInt("totalPost");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (psm != null) {
                psm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return (int) Math.ceil(total / POST_PER_LOAD);
    }

    public int getNumberOfPageForPostWithContent(String searchedContent) throws SQLException, NamingException {
        int total = 0;
        Connection con = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "select Count(postID) as totalPost "
                        + "from Posts "
                        + "where status=1 "
                        + "and (postContent like ? escape '\\' "
                        + "or title like ? escape '\\' "
                        + "or postDescription like ? escape '\\')";
                psm = con.prepareStatement(sql);
                psm.setNString(1, "%" + searchedContent + "%");
                psm.setNString(2, "%" + searchedContent + "%");
                psm.setNString(3, "%" + searchedContent + "%");
                rs = psm.executeQuery();
                if (rs.next()) {
                    total = rs.getInt("totalPost");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (psm != null) {
                psm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return (int) Math.ceil(total / POST_PER_LOAD);
    }

    private List<PostsDTO> resultList;

    public List<PostsDTO> getResultList() {
        return resultList;
    }

    public int searchPostByContent(String searchedContent, int page) throws SQLException, NamingException {
        int result = 0;
        Connection con = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "select postID,title,postDescription,postContent,images,email,submitTime "
                        + "from Posts "
                        + "where status=1 "
                        + "and (postContent like ? escape '\\' "
                        + "or title like ? escape '\\' "
                        + "or postDescription like ? escape '\\') "
                        + "Order by submitTime Desc "
                        + "Offset ? rows "
                        + "FETCH NEXT ? rows only";
                psm = con.prepareStatement(sql);
                psm.setNString(1, "%" + searchedContent + "%");
                psm.setNString(2, "%" + searchedContent + "%");
                psm.setNString(3, "%" + searchedContent + "%");
                psm.setInt(4, (page - 1) * (int) POST_PER_LOAD);
                psm.setInt(5, (int) POST_PER_LOAD);
                rs = psm.executeQuery();
                while (rs.next()) {
                    if (this.resultList == null) {
                        this.resultList = new ArrayList<>();
                    }
                    int postID = rs.getInt("postID");
                    String title = rs.getNString("title");
                    String description = rs.getNString("postDescription");
                    String content = rs.getNString("postContent");
                    String imgURL = rs.getString("images");
                    String ownerEmail = rs.getString("email");
                    Timestamp time = rs.getTimestamp("submitTime");
                    this.resultList.add(new PostsDTO(postID, ownerEmail, title, description, content, imgURL, time));
                    result++;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (psm != null) {
                psm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public String getPostIDStringByEmail(String ownerEmail) throws SQLException, NamingException {
        StringBuilder postIDStringBuilder = new StringBuilder();
        Connection con = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "select postID from Posts where status=1 and email like ?";
                psm = con.prepareStatement(sql);
                psm.setString(1, ownerEmail);
                rs = psm.executeQuery();
                while (rs.next()) {
                    postIDStringBuilder.append(rs.getInt("postID"));
                    postIDStringBuilder.append(",");
                }
                if (postIDStringBuilder.length() >= 1) {
                    postIDStringBuilder.deleteCharAt(postIDStringBuilder.length() - 1);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (psm != null) {
                psm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return postIDStringBuilder.toString();
    }

    public int loadPost(int page) throws NamingException, SQLException {
        int total = 0;
        Connection con = null;
        PreparedStatement psm = null;
        ResultSet rs = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "select postID,title,postDescription,postContent,images,email,submitTime "
                        + "from Posts "
                        + "where status=1"
                        + "Order by submitTime Desc "
                        + "Offset ? rows "
                        + "FETCH NEXT ? rows only";
                psm = con.prepareStatement(sql);
                psm.setInt(1, (page - 1) * (int) POST_PER_LOAD);
                psm.setInt(2, (int) POST_PER_LOAD);
                rs = psm.executeQuery();
                while (rs.next()) {
                    if (this.resultList == null) {
                        this.resultList = new ArrayList<>();
                    }
                    int postID = rs.getInt("postID");
                    String title = rs.getNString("title");
                    String description = rs.getNString("postDescription");
                    String content = rs.getNString("postContent");
                    String imgURL = rs.getString("images");
                    String ownerEmail = rs.getString("email");
                    Timestamp time = rs.getTimestamp("submitTime");
                    this.resultList.add(new PostsDTO(postID, ownerEmail, title, description, content, imgURL, time));
                    total++;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (psm != null) {
                psm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return total;
    }

    public boolean createNewPost(String title, String description, String content, String imgURL, String ownerEmail) throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement psm = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "insert into Posts(title,postDescription,postContent,images,email) values(?,?,?,?,?)";
                psm = con.prepareStatement(sql);
                psm.setNString(1, title);
                psm.setNString(2, description);
                psm.setNString(3, content);
                psm.setString(4, imgURL);
                psm.setString(5, ownerEmail);
                int rs = psm.executeUpdate();
                if (rs > 0) {
                    return true;
                }
            }
        } finally {
            if (psm != null) {
                psm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean setPostToDelete(int postID) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement psm = null;
        try {
            con = DBHelpers.makeConnection();
            if (con != null) {
                String sql = "Update Posts Set status=2 where postID=?";
                psm = con.prepareStatement(sql);
                psm.setInt(1, postID);
                int rs = psm.executeUpdate();
                if (rs > 0) {
                    return true;
                }
            }
        } finally {
            if (psm != null) {
                psm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
