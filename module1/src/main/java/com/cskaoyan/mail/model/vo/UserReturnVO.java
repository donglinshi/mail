package com.cskaoyan.mail.model.vo;

/**
 * @author 史栋林
 * @date 2020/8/10 22:47
 */
public class UserReturnVO {

    private String name;

    private String token;

    public UserReturnVO() {
    }

    public UserReturnVO(String name, String token) {
        this.name = name;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
