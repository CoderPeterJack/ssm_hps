package com.jp.service;

import com.jp.model.UserMoodPraiseRel;

/**
 * @program: HighConcurrentPraise
 * @description: 用户说说点赞关联接口
 * @author: CoderPengJiang
 * @create: 2019-10-31 22:50
 **/
public interface UserMoodPraiseRelService {
    boolean save(UserMoodPraiseRel userMoodPraiseRel);
}
