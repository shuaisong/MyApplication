package com.example.lenovo.myapplication.bean;


import java.io.Serializable;

/**
 * Created by lenovo on 2018/9/8.
 * auther:lenovo
 * Date：2018/9/8
 */
public class Login implements Serializable {
    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    private boolean login;
}
