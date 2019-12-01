package com.jp.service;

import com.jp.dto.MoodDTO;
import com.jp.model.Mood;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MoodService {
    //查询所有的说说
    List<MoodDTO> findAll();
    //传统点赞
    boolean praiseMood(Integer userId,Integer moodId);
    boolean update(@Param("mood") Mood mood);
    Mood findById(Integer id);
}
