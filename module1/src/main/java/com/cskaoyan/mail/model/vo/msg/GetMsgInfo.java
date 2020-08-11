package com.cskaoyan.mail.model.vo.msg;

import java.sql.Date;

/**
 * @author 史栋林
 * @date 2020/8/11 11:26
 *用户获取问答信息
 */
public class GetMsgInfo {

    private Integer id;

    private String content;

    private String asker;

    private Date time;

    private ReplyVO reply;


    public GetMsgInfo() {
    }

    public GetMsgInfo(Integer id, String content, String asker, Date time, ReplyVO reply) {
        this.id = id;
        this.content = content;
        this.asker = asker;
        this.time = time;
        this.reply = reply;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAsker() {
        return asker;
    }

    public void setAsker(String asker) {
        this.asker = asker;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public ReplyVO getReply() {
        return reply;
    }

    public void setReply(ReplyVO reply) {
        this.reply = reply;
    }
}
