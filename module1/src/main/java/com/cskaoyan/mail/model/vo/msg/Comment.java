package com.cskaoyan.mail.model.vo.msg;

import java.sql.Date;

/**
 * @author 史栋林
 * @date 2020/8/11 15:18
 */
public class Comment {

    private Double score;

    private Integer id;

    private String specName;

    private String comment;

    private Date time;

    private Integer userId;

    public Comment() {
    }

    public Comment(Double score, Integer id, String specName, String comment, Date time, Integer userId) {
        this.score = score;
        this.id = id;
        this.specName = specName;
        this.comment = comment;
        this.time = time;
        this.userId = userId;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
