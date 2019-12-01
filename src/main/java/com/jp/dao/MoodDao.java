package com.jp.dao;

import com.jp.model.Mood;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: HighConcurrentPraise
 * @description: mood表对应的Dao层
 * @author: CoderPengJiang
 * @create: 2019-10-31 21:25
 **/
@Repository
public interface MoodDao {
    List<Mood> findAll();
    boolean update(@Param("mood")Mood mood);
    Mood findById(Integer id);
}
