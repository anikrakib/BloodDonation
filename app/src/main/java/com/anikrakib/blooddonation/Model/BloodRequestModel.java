package com.anikrakib.blooddonation.Model;

public class BloodRequestModel {
    private String bloodGroup;
    private String relation;
    private String location;
    private String hospitalName;
    private String requiredDate;
    private String postDateAndTime;
    private boolean urgentOrScheduled;
    private String userId;

    public BloodRequestModel() {
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(String requiredDate) {
        this.requiredDate = requiredDate;
    }

    public String getPostDateAndTime() {
        return postDateAndTime;
    }

    public void setPostDateAndTime(String postDateAndTime) {
        this.postDateAndTime = postDateAndTime;
    }

    public boolean isUrgentOrScheduled() {
        return urgentOrScheduled;
    }

    public void setUrgentOrScheduled(boolean urgentOrScheduled) {
        this.urgentOrScheduled = urgentOrScheduled;
    }

    public String  isUserId() {
        return userId;
    }

    public void setUserId(String  userId) {
        this.userId = userId;
    }
}
