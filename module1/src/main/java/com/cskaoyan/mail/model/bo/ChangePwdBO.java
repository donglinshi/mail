package com.cskaoyan.mail.model.bo;

/**
 * @author 史栋林
 * @date 2020/8/6 14:39
 */
public class ChangePwdBO {
    private String adminToken;

    private String oldPwd;

    private String newPwd;

    private String confirmPwd;

    public ChangePwdBO() {
    }

    public String getAdminToken() {
        return adminToken;
    }

    public void setAdminnToken(String adminToken) {
        this.adminToken = adminToken;
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

    @Override
    public String toString() {
        return "ChangePwdBO{" +
                "adminToken='" + adminToken + '\'' +
                ", oldPwd='" + oldPwd + '\'' +
                ", newPwd='" + newPwd + '\'' +
                ", confirmPwd='" + confirmPwd + '\'' +
                '}';
    }
}
