package com.lukewaugh.droneguide;

import com.amazonaws.mobileconnectors.cognito.Dataset;

import java.net.PasswordAuthentication;

/**
 * Created by Luke on 20/03/16.
 */
public class CRUD {
    private String username;
    private String password;

    CRUD crud = new CRUD();

    public String getName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String setName(String username) {
        return this.username = username;
    }
    public String setPassword(String password) {
        return this.password = password;
    }


}
