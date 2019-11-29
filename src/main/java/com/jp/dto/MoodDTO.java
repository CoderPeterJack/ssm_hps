package com.jp.dto;

import com.jp.model.Mood;

/**
 * @program: HighConcurrentPraise
 * @description: 说说的DTO
 * @author: CoderPengJiang
 * @create: 2019-10-31 21:56
 **/
public class MoodDTO extends Mood {
    /**
     * 用户名称
     */
    private String userName;
    /**
     * 用户账号
     */
    private String userAccount;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    @Override
    public String toString() {
        return "MoodDTO{" +
                "userName='" + userName + '\'' +
                ", userAccount='" + userAccount + '\'' +
                '}';
    }
}
