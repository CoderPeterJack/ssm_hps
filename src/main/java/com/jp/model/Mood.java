package com.jp.model;

import java.io.Serializable;
import java.util.Date;


/**
 * @program: HighConcurrentPraise
 * @description: 说说
 * @author: CoderPengJiang
 * @create: 2019-10-26 21:17
 **/
public class Mood implements Serializable {
    private Integer id;
    private String content;
    private Integer praiseNum;
    private Integer userId;
    private Date publishTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }


    @Override
    public String toString() {
        return "Mood{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", praiseNum=" + praiseNum +
                ", userId=" + userId +
                ", publishTime=" + publishTime +
                '}';
    }
}
