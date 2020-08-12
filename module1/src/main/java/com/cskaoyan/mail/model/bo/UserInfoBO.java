package com.cskaoyan.mail.model.bo;

/**
 * @author 史栋林
 * @date 2020/8/12 19:42
 */
public class UserInfoBO {

    private Integer id;

    private String nickname;

    private String recipient;

    private String address;

    private String phone;

    public UserInfoBO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
