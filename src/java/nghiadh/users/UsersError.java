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
public class UsersError implements Serializable{
    private String updatedUsername;
    private String emailLengthErr;
    private String emailNotValidErr;
    private String passwordLengthErr;
    private String confirmNotMatchedErr;
    private String nameLengthNotMatchErr;
    private String emailExistedErr;

    public String getUpdatedUsername() {
        return updatedUsername;
    }

    public void setUpdatedUsername(String updatedUsername) {
        this.updatedUsername = updatedUsername;
    }
    
    public String getEmailLengthErr() {
        return emailLengthErr;
    }

    public void setEmailLengthErr(String emailLengthErr) {
        this.emailLengthErr = emailLengthErr;
    }

    public String getEmailNotValidErr() {
        return emailNotValidErr;
    }

    public void setEmailNotValidErr(String emailNotValidErr) {
        this.emailNotValidErr = emailNotValidErr;
    }

    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    public String getConfirmNotMatchedErr() {
        return confirmNotMatchedErr;
    }

    public void setConfirmNotMatchedErr(String confirmNotMatchedErr) {
        this.confirmNotMatchedErr = confirmNotMatchedErr;
    }

    public String getNameLengthNotMatchErr() {
        return nameLengthNotMatchErr;
    }

    public void setNameLengthNotMatchErr(String nameLengthNotMatchErr) {
        this.nameLengthNotMatchErr = nameLengthNotMatchErr;
    }

    public String getEmailExistedErr() {
        return emailExistedErr;
    }

    public void setEmailExistedErr(String emailExistedErr) {
        this.emailExistedErr = emailExistedErr;
    }   
    
}
