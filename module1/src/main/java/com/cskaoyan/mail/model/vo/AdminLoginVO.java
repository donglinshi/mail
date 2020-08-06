package com.cskaoyan.mail.model.vo;

/**
 * @author 史栋林
 * @date 2020/8/5 21:10
 */
public class AdminLoginVO {

    private String token;

    private String name;

    public AdminLoginVO(String token, String name) {
        this.token = token;
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
