/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadh.notifications;

import java.io.Serializable;

/**
 *
 * @author haseo
 */
public class NotificationsDTO implements Serializable{
    private String triggerEmail;
    private int triggerPostID;
    private int eventType;

    public NotificationsDTO() {
    }

    public NotificationsDTO(String triggerEmail, int triggerPostID, int eventType) {
        this.triggerEmail = triggerEmail;
        this.triggerPostID = triggerPostID;
        this.eventType = eventType;
    }

    public String getTriggerEmail() {
        return triggerEmail;
    }

    public void setTriggerEmail(String triggerEmail) {
        this.triggerEmail = triggerEmail;
    }

    public int getTriggerPostID() {
        return triggerPostID;
    }

    public void setTriggerPostID(int triggerPostID) {
        this.triggerPostID = triggerPostID;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }
    
}
