/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadh.users;

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
public class UsersDAO implements Serializable{
    private UsersDTO loginUser;

    public UsersDTO getLoginUser() {
        return loginUser;
    }
    
    public boolean checkLogin(String username,String password) throws SQLException, NamingException{
        Connection con = null; 
        PreparedStatement psm = null;
        ResultSet rs = null;
        try{
            con = DBHelpers.makeConnection();
            if(con!=null){
                String sql = "select email,name,status "
                        + "from Users "
                        + "where email like ? and password like ?";
                psm = con.prepareStatement(sql);
                psm.setString(1, username);
                psm.setString(2, password);
                rs=psm.executeQuery();
                if(rs.next()){
                    if(this.loginUser==null)this.loginUser=new UsersDTO();
                    loginUser.setEmail(rs.getString("email"));
                    loginUser.setName(rs.getNString("name"));
                    loginUser.setStatus(rs.getInt("status"));
                    return true;
                }
            }
        }finally{
            if(rs!=null)rs.close();
            if(psm!=null)psm.close();
            if(con!=null)con.close();
        }
        return false;
    }
    
    public boolean createNewAccount(String email,String password,String name) throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement psm = null;
        try{
            con = DBHelpers.makeConnection();
            if(con!=null){
                String sql = "insert into Users(email,name,password) values(?,?,?)";
                psm = con.prepareStatement(sql);
                psm.setString(1, email);
                psm.setNString(2, name);
                psm.setString(3, password);
//                psm.setBoolean(4, admin);
//                psm.setBoolean(5, status);
                int rs = psm.executeUpdate();
                if(rs>0)return true;
            }
        }finally{
            if(psm!=null)psm.close();
            if(con!=null)con.close();
        }
        return false;
    }
    
//    public boolean updateAdminAccount(String email, boolean admin) throws SQLException, NamingException{
//        Connection con = null;
//        PreparedStatement psm = null;
//        try{
//            con = DBHelpers.makeConnection();
//            if(con!=null){              
//                String sql = "Update Users set admin=? where email like ?";
//                psm = con.prepareStatement(sql);
//                psm.setBoolean(1, admin);
//                psm.setString(2, email);
//                int rs = psm.executeUpdate();
//                if(rs>0)return true;
//            }
//        }finally{
//            if(psm!=null)psm.close();
//            if(con!=null)con.close();
//        }
//        return false;
//    }
//    List<UsersDTO> userList;
//
//    public List<UsersDTO> getUserList() {
//        return userList;
//    }
//    
//    public int loadNewUserList() throws SQLException, NamingException{
//        int total = 0;
//        Connection con = null;
//        PreparedStatement psm = null;
//        ResultSet rs = null;
//        try{
//            con = DBHelpers.makeConnection();
//            if(con!=null){
//                String sql = "select email,name,admin,status "
//                        + "from users "
//                        + "where status=0";
//                psm = con.prepareStatement(sql);
//                rs = psm.executeQuery();
//                while(rs.next()){
//                    if(this.userList==null)this.userList=new ArrayList<>();
//                    String email = rs.getString("email");
//                    String name = rs.getString("name");
////                    boolean admin = rs.getBoolean("admin");
//                    int status = rs.getInt("status");
//                    this.userList.add(new UsersDTO(email, name,status));
//                    total++;
//                }
//            }
//        }finally{
//            if(rs!=null)rs.close();
//            if(psm!=null)psm.close();
//            if(con!=null)con.close();
//        }
//        return total;
//    }
    
//    public int loadNewUserList(String searchName) throws SQLException, NamingException{
//        int total = 0;
//        Connection con = null;
//        PreparedStatement psm = null;
//        ResultSet rs = null;
//        try{
//            con = DBHelpers.makeConnection();
//            if(con!=null){
//                String sql = "select email,name,status "
//                        + "from users "
//                        + "where name like ?"
//                        + " and status=0";
//                psm = con.prepareStatement(sql);
//                psm.setString(1, "%"+searchName+"%");
//                rs = psm.executeQuery();
//                while(rs.next()){
//                    if(this.userList==null)this.userList=new ArrayList<>();
//                    String email = rs.getString("email");
//                    String name = rs.getString("name");
//                    int status = rs.getInt("status");
//                    this.userList.add(new UsersDTO(email, name,status));
//                    total++;
//                }
//            }
//        }finally{
//            if(rs!=null)rs.close();
//            if(psm!=null)psm.close();
//            if(con!=null)con.close();
//        }
//        return total;
//    }
//    
//    public boolean updateStatusAccount(String email, int status) throws SQLException, NamingException{
//        Connection con = null;
//        PreparedStatement psm = null;
//        try{
//            con = DBHelpers.makeConnection();
//            if(con!=null){              
//                String sql = "Update Users set status=? where email like ?";
//                psm = con.prepareStatement(sql);
//                psm.setInt(1, status);
//                psm.setString(2, email);
//                int rs = psm.executeUpdate();
//                if(rs>0)return true;
//            }
//        }finally{
//            if(psm!=null)psm.close();
//            if(con!=null)con.close();
//        }
//        return false;
//    }
}
