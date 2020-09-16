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
    private boolean admin;
    private boolean status;

    public UsersDTO() {
    }

    public UsersDTO(String email, String name, boolean admin, boolean status) {
        this.email = email;
        this.name = name;
        this.admin = admin;
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

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
