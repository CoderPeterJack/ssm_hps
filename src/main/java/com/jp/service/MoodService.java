package com.jp.service;

import com.jp.dto.MoodDTO;

import java.util.List;

public interface MoodService {
    //查询所有的说说
    List<MoodDTO> findAll();
}
