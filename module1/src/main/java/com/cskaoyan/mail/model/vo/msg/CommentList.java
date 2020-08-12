package com.cskaoyan.mail.model.vo.msg;

/**
 * @author 史栋林
 * @date 2020/8/12 16:38
 */
public class CommentList {

    private UserNickname user;

    private Double score;

    private Integer id;

    private String specName;

    private String comment;

    private String time;

    private Integer userId;

    public CommentList() {
    }

    public CommentList(UserNickname user, Double score, Integer id, String specName, String comment, String time, Integer userId) {
        this.user = user;
        this.score = score;
        this.id = id;
        this.specName = specName;
        this.comment = comment;
        this.time = time;
        this.userId = userId;
    }

    public UserNickname getUser() {
        return user;
    }

    public void setUser(UserNickname user) {
        this.user = user;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
