/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadh.users;

import java.io.Serializable;

/**
 *
 * @author haseo
 */
public class UsersDTO implements Serializable{
    private String email;
    private String name;
//    private boolean admin;
    private int status;

    public UsersDTO() {
    }
//    public UsersDTO(String email, String name, boolean admin, boolean status) {
    public UsersDTO(String email, String name, int status) {
        this.email = email;
        this.name = name;
//        this.admin = admin;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public boolean isAdmin() {
//        return admin;
//    }
//
//    public void setAdmin(boolean admin) {
//        this.admin = admin;
//    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}
