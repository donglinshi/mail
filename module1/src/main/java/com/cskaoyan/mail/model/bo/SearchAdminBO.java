package com.cskaoyan.mail.model.bo;

/**
 * @author 史栋林
 * @date 2020/8/6 11:13
 */
public class SearchAdminBO {
    private String email;

    private String nickname;

    public SearchAdminBO() {
    }

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
}
