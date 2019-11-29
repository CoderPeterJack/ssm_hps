package com.jp.dao;

import com.jp.model.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户dao
 */
@Repository
public interface UserDao {
    User find(Integer id);
}
