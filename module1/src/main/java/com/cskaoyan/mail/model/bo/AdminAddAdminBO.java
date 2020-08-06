package com.cskaoyan.mail.model.bo;

/**
 * @author 史栋林
 * @date 2020/8/5 23:00
 */
public class AdminAddAdminBO {

    private String email;

    private String nickname;

    private String pwd;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "AdminAddAdminBO{" +
                "email='" + email + '\'' +
                ", nickname='" + nickname + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
