package com.anikrakib.blooddonation.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class UserDataModel implements Parcelable {
    private String userName;
    private String email;
    private String password;
    private String phoneNo;
    private String altPhoneNo;
    private String socialMediaLink;

    public UserDataModel() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAltPhoneNo() {
        return altPhoneNo;
    }

    public void setAltPhoneNo(String altPhoneNo) {
        this.altPhoneNo = altPhoneNo;
    }

    public String getSocialMediaLink() {
        return socialMediaLink;
    }

    public void setSocialMediaLink(String socialMediaLink) {
        this.socialMediaLink = socialMediaLink;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userName);
        dest.writeString(this.email);
        dest.writeString(this.password);
        dest.writeString(this.phoneNo);
        dest.writeString(this.altPhoneNo);
        dest.writeString(this.socialMediaLink);
    }

    public void readFromParcel(Parcel source) {
        this.userName = source.readString();
        this.email = source.readString();
        this.password = source.readString();
        this.phoneNo = source.readString();
        this.altPhoneNo = source.readString();
        this.socialMediaLink = source.readString();
    }

    protected UserDataModel(Parcel in) {
        this.userName = in.readString();
        this.email = in.readString();
        this.password = in.readString();
        this.phoneNo = in.readString();
        this.altPhoneNo = in.readString();
        this.socialMediaLink = in.readString();
    }

    public static final Creator<UserDataModel> CREATOR = new Creator<UserDataModel>() {
        @Override
        public UserDataModel createFromParcel(Parcel source) {
            return new UserDataModel(source);
        }

        @Override
        public UserDataModel[] newArray(int size) {
            return new UserDataModel[size];
        }
    };

    @Override
    public String toString() {
        return "UserDataModel{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", altPhoneNo='" + altPhoneNo + '\'' +
                ", socialMediaLink='" + socialMediaLink + '\'' +
                '}';
    }
}
