package com.tangerine.ecui.database;

import org.litepal.crud.LitePalSupport;

public class user_profile extends LitePalSupport {
    private int id;
    private String name ;
    private String avatar;
    private String gender ;
    private String address ;
    private int userId;



    public user_profile(String name, String avatar, String gender, String address,int userId) {
        this.name = name;
        this.avatar = avatar;
        this.gender = gender;
        this.address = address;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
