package com.cskaoyan.mail.model.vo.msg;

import java.sql.Date;

/**
 * @author 史栋林
 * @date 2020/8/11 11:36
 */
public class AskVO {

    private Integer id;

    private Integer userId;

    private String content;

    private Date time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
