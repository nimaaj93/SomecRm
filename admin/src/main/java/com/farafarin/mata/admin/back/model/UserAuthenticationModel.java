package com.farafarin.mata.admin.back.model;

/**
 * Created by Asus on 11/2/2017.
 */
public class UserAuthenticationModel {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
