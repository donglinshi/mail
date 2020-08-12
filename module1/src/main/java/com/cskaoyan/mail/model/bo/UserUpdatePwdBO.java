package com.cskaoyan.mail.model.bo;

/**
 * @author 史栋林
 * @date 2020/8/12 19:07
 */
public class UserUpdatePwdBO {

    private Integer id;

    private String oldPwd;

    private String newPwd;

    private String confirmPwd;

    public UserUpdatePwdBO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOldPwd() {
        return oldPwd;
    }

    public void setOldPwd(String oldPwd) {
        this.oldPwd = oldPwd;
    }

    public String getNewPwd() {
        return newPwd;
    }

    public void setNewPwd(String newPwd) {
        this.newPwd = newPwd;
    }

    public String getConfirmPwd() {
        return confirmPwd;
    }

    public void setConfirmPwd(String confirmPwd) {
        this.confirmPwd = confirmPwd;
    }
}
