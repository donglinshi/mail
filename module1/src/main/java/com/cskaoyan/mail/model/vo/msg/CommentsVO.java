package com.cskaoyan.mail.model.vo.msg;

import java.util.List;

/**
 * @author 史栋林
 * @date 2020/8/11 14:58
 */
public class CommentsVO {

    private List<CommentList> commentList;

    private Double rate = 20.0;


    public CommentsVO() {
    }

    public CommentsVO(List<CommentList> commentList, Double rate) {
        this.commentList = commentList;
        this.rate = rate;
    }

    public List<CommentList> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentList> commentList) {
        this.commentList = commentList;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
