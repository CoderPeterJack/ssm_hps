package com.jp.dao;

import com.jp.model.UserMoodPraiseRel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @program: HighConcurrentPraise
 * @description: 用户说说点赞的关联DAO
 * @author: CoderPengJiang
 * @create: 2019-10-31 21:35
 **/
@Repository
public interface UserMoodPraiseRelDao {
    boolean save(@Param("userMoodPraiseRel") UserMoodPraiseRel userMoodPraiseRel);
}
