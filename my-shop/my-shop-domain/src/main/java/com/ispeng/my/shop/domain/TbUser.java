package com.ispeng.my.shop.domain;

import com.ispeng.my.shop.commons.persistence.BaseEntity;

import java.io.Serializable;
import java.util.Date;

public class TbUser extends BaseEntity {
    private String username;
    private String password;
    private String phone;
    private String email;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
