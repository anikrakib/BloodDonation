package com.anikrakib.blooddonation.Model;

public class NotificationModel {
    private String bloodGroup;
    private String notificationText;
    private String time;

    public NotificationModel(String bloodGroup, String notificationText, String time) {
        this.bloodGroup = bloodGroup;
        this.notificationText = notificationText;
        this.time = time;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getNotificationText() {
        return notificationText;
    }

    public void setNotificationText(String notificationText) {
        this.notificationText = notificationText;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
