/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadh.posts;

import java.io.Serializable;

/**
 *
 * @author haseo
 */
public class PostError implements Serializable{
    private String titleLengthErr;
    private String descriptionLengthErr;
    private String contentLengthErr;
    private String fileTypeIncorrectErr;

    public String getTitleLengthErr() {
        return titleLengthErr;
    }

    public void setTitleLengthErr(String titleLengthErr) {
        this.titleLengthErr = titleLengthErr;
    }

    public String getDescriptionLengthErr() {
        return descriptionLengthErr;
    }

    public void setDescriptionLengthErr(String descriptionLengthErr) {
        this.descriptionLengthErr = descriptionLengthErr;
    }

    public String getContentLengthErr() {
        return contentLengthErr;
    }

    public void setContentLengthErr(String contentLengthErr) {
        this.contentLengthErr = contentLengthErr;
    }

    public String getFileTypeIncorrectErr() {
        return fileTypeIncorrectErr;
    }

    public void setFileTypeIncorrectErr(String fileTypeIncorrectErr) {
        this.fileTypeIncorrectErr = fileTypeIncorrectErr;
    }
    
}
