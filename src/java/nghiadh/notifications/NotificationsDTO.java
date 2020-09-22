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
    private String eventName;

    public NotificationsDTO() {
    }

    public NotificationsDTO(String triggerEmail, int triggerPostID, String eventName) {
        this.triggerEmail = triggerEmail;
        this.triggerPostID = triggerPostID;
        this.eventName = eventName;
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

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    
}
