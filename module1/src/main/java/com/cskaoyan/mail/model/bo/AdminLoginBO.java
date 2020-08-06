package com.cskaoyan.mail.model.bo;

/**
 * @author 史栋林
 * @date 2020/8/5 19:54
 */
public class AdminLoginBO {
    private String email;

    private String pwd;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "AdminLoginBO{" +
                "email='" + email + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
