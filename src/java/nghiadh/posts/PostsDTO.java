/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadh.posts;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;

/**
 *
 * @author haseo
 */
public class PostsDTO implements Serializable{
    private int postID;
    private String ownerEmail;
    private String title;
    private String description;
    private String content;
    private String img;
    private Timestamp submitTime;

    public PostsDTO() {
    }

    public PostsDTO(int postID,String ownerEmail, String title, String description, String content, String img,Timestamp submitTime) {
        this.postID = postID;
        this.ownerEmail=ownerEmail;
        this.title = title;
        this.description = description;
        this.content = content;
        this.img = img;
        this.submitTime=submitTime;
    }

    public PostsDTO(int postID,String ownerEmail, String title, String description, String content) {
        this.postID = postID;
        this.ownerEmail=ownerEmail;
        this.title = title;
        this.description = description;
        this.content = content;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSubmitTime() {
//        return submitTime;
        return DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(this.submitTime);
    }

    public void setSubmitTime(Timestamp submitTime) {
        this.submitTime = submitTime;
    }
    
}
