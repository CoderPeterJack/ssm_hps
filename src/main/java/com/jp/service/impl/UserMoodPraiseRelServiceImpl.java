package com.jp.service.impl;

import com.jp.dao.UserMoodPraiseRelDao;
import com.jp.model.UserMoodPraiseRel;
import com.jp.service.UserMoodPraiseRelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: HighConcurrentPraise
 * @description: 用户说说点赞关联服务类
 * @author: CoderPengJiang
 * @create: 2019-10-31 22:51
 **/
@Service
public class UserMoodPraiseRelServiceImpl implements UserMoodPraiseRelService {
    @Resource
    private UserMoodPraiseRelDao userMoodPraiseRelDao;

    public boolean save(UserMoodPraiseRel userMoodPraiseRel){
        return userMoodPraiseRelDao.save(userMoodPraiseRel);
    }
}
