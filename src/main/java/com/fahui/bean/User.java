package com.fahui.bean;

import javax.validation.constraints.Pattern;

public class User {

    @Pattern(regexp = "(^[a-zA-Z_-]{4,10}$)",message = "用户格式错误！")
    private String username;
    @Pattern(regexp = "(^\\w{6,16}$)",message = "密码格式错误！")
    private String password;

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

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
