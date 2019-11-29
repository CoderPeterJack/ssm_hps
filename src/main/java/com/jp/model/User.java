package com.jp.model;

import java.io.Serializable;

/**
 * @program: HighConcurrentPraise
 * @description: 用户表
 * @author: CoderPengJiang
 * @create: 2019-10-26 21:13
 **/
public class User implements Serializable {
    private Integer id;
    private String name;
    private String account;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", account='" + account + '\'' +
                '}';
    }
}
