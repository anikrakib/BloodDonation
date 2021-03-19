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
    private String bloodGroup;
    private String weight;
    private String gender;
    private String streetAddress;
    private String city;
    private String postalCode;
    private int age;
    private int dateOfMonth;
    private String nameOfMonth;
    private int year;
    private String userProfilePic;

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

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getDateOfMonth() {
        return dateOfMonth;
    }

    public void setDateOfMonth(int dateOfMonth) {
        this.dateOfMonth = dateOfMonth;
    }

    public String getNameOfMonth() {
        return nameOfMonth;
    }

    public void setNameOfMonth(String nameOfMonth) {
        this.nameOfMonth = nameOfMonth;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getUserProfilePic() {
        return userProfilePic;
    }

    public void setUserProfilePic(String userProfilePic) {
        this.userProfilePic = userProfilePic;
    }

    @Override
    public String toString() {
        return "UserDataModel{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", altPhoneNo='" + altPhoneNo + '\'' +
                ", socialMediaLink='" + socialMediaLink + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", weight='" + weight + '\'' +
                ", gender='" + gender + '\'' +
                ", streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", age=" + age +
                ", dateOfMonth=" + dateOfMonth +
                ", nameOfMonth='" + nameOfMonth + '\'' +
                ", year=" + year +
                ", userProfilePic='" + userProfilePic + '\'' +
                '}';
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
        dest.writeString(this.bloodGroup);
        dest.writeString(this.weight);
        dest.writeString(this.gender);
        dest.writeString(this.streetAddress);
        dest.writeString(this.city);
        dest.writeString(this.postalCode);
        dest.writeInt(this.age);
        dest.writeInt(this.dateOfMonth);
        dest.writeString(this.nameOfMonth);
        dest.writeInt(this.year);
        dest.writeString(this.userProfilePic);
    }

    public void readFromParcel(Parcel source) {
        this.userName = source.readString();
        this.email = source.readString();
        this.password = source.readString();
        this.phoneNo = source.readString();
        this.altPhoneNo = source.readString();
        this.socialMediaLink = source.readString();
        this.bloodGroup = source.readString();
        this.weight = source.readString();
        this.gender = source.readString();
        this.streetAddress = source.readString();
        this.city = source.readString();
        this.postalCode = source.readString();
        this.age = source.readInt();
        this.dateOfMonth = source.readInt();
        this.nameOfMonth = source.readString();
        this.year = source.readInt();
        this.userProfilePic = source.readString();
    }

    protected UserDataModel(Parcel in) {
        this.userName = in.readString();
        this.email = in.readString();
        this.password = in.readString();
        this.phoneNo = in.readString();
        this.altPhoneNo = in.readString();
        this.socialMediaLink = in.readString();
        this.bloodGroup = in.readString();
        this.weight = in.readString();
        this.gender = in.readString();
        this.streetAddress = in.readString();
        this.city = in.readString();
        this.postalCode = in.readString();
        this.age = in.readInt();
        this.dateOfMonth = in.readInt();
        this.nameOfMonth = in.readString();
        this.year = in.readInt();
        this.userProfilePic = in.readString();
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
}
