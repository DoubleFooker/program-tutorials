package com.ognice.domain;

/**
 * Created by huangkaifu on 2017/5/12.
 */
public class User {
    public User(String username, String psw) {
        this.username = username;
        this.psw = psw;
    }

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    private String psw;

}
