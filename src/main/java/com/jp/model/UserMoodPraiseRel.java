package com.jp.model;

import java.io.Serializable;

/**
 * @program: HighConcurrentPraise
 * @description: 说说点赞关系表
 * @author: CoderPengJiang
 * @create: 2019-10-26 21:24
 **/
public class UserMoodPraiseRel implements Serializable {
    private Integer id;
    private Integer userId;
    private Integer moodId;

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

    public Integer getMoodId() {
        return moodId;
    }

    public void setMoodId(Integer moodId) {
        this.moodId = moodId;
    }

    @Override
    public String toString() {
        return "UserMoodPraiseRel{" +
                "id=" + id +
                ", userId=" + userId +
                ", moodId=" + moodId +
                '}';
    }
}
