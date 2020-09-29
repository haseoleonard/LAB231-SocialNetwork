/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadh.comments;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;

/**
 *
 * @author haseo
 */
public class CommentsDTO implements Serializable {

    private int commentID;
    private String commenterEmail;
    private String commenterName;
    private String commentContent;
    private Timestamp time;

    public CommentsDTO() {
    }

    public CommentsDTO(int commentID, String commenterEmail, String commenterName, String commentContent,Timestamp time) {
        this.commentID = commentID;
        this.commenterEmail = commenterEmail;
        this.commenterName = commenterName;
        this.commentContent = commentContent;
        this.time=time;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public String getCommenterEmail() {
        return commenterEmail;
    }

    public void setCommenterEmail(String commenterEmail) {
        this.commenterEmail = commenterEmail;
    }

    public String getCommenterName() {
        return commenterName;
    }

    public void setCommenterName(String commenterName) {
        this.commenterName = commenterName;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getTime() {
        return DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(time);
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    
}
