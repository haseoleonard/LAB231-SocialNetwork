/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadh.notifications;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.DateFormat;

/**
 *
 * @author haseo
 */
public class NotificationsDTO implements Serializable{
    private String triggerEmail;
    private int triggerPostID;
    private String eventName;
    private Timestamp time;

    public NotificationsDTO() {
    }

    public NotificationsDTO(String triggerEmail, int triggerPostID, String eventName, Timestamp time) {
        this.triggerEmail = triggerEmail;
        this.triggerPostID = triggerPostID;
        this.eventName = eventName;
        this.time=time;
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

//    public Timestamp getTime() {
//        return time;
//    }
    public String getTime() {
        return DateFormat.getDateTimeInstance(DateFormat.MEDIUM,DateFormat.SHORT).format(time);
    }    

    public void setTime(Timestamp time) {
        this.time = time;
    }
    
}
