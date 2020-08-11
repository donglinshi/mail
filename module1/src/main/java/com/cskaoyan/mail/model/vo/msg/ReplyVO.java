package com.cskaoyan.mail.model.vo.msg;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * @author 史栋林
 * @date 2020/8/11 11:31
 */
public class ReplyVO {

    private String content;

    private Date time;

    public ReplyVO() {
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
