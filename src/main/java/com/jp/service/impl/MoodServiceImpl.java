package com.jp.service.impl;

import com.jp.dao.MoodDao;
import com.jp.dao.UserDao;
import com.jp.dao.UserMoodPraiseRelDao;
import com.jp.dto.MoodDTO;
import com.jp.model.Mood;
import com.jp.model.User;
import com.jp.model.UserMoodPraiseRel;
import com.jp.service.MoodService;
import com.sun.media.sound.ModelDestination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @program: HighConcurrentPraise
 * @description: 说说服务类
 * @author: CoderPengJiang
 * @create: 2019-10-31 22:19
 **/
@Service
public class MoodServiceImpl implements MoodService {
    @Resource
    private MoodDao moodDao;
    @Resource
    private UserDao userDao;
    @Resource
    private UserMoodPraiseRelDao userMoodPraiseRelDao;

    public List<MoodDTO> findAll(){
        //查询所有的说说
        List<Mood> moodList=moodDao.findAll();
        //转换为DTO对象
        //System.out.println("mood业务层执行成功");
        return converMode12DTO(moodList);
    }

    private List<MoodDTO> converMode12DTO(List<Mood> moodList){
        if (CollectionUtils.isEmpty(moodList)) return Collections.EMPTY_LIST;
        List<MoodDTO> moodDTOList=new ArrayList<MoodDTO>();
        for (Mood mood:moodList) {
            MoodDTO moodDTO=new MoodDTO();
            moodDTO.setId(mood.getId());
            moodDTO.setContent(mood.getContent());
            moodDTO.setPraiseNum(mood.getPraiseNum());
            moodDTO.setPublishTime(mood.getPublishTime());
            moodDTO.setUserId(mood.getUserId());
            moodDTOList.add(moodDTO);
            //设置用户信息
            User user=userDao.find(mood.getUserId());
            moodDTO.setUserName(user.getName());
            moodDTO.setUserAccount(user.getAccount());
        }
        return moodDTOList;
    }

    public boolean praiseMood(Integer userId,Integer moodId){
        //保持关联关系
        UserMoodPraiseRel userMoodPraiseRel=new UserMoodPraiseRel();
        userMoodPraiseRel.setUserId(userId);
        userMoodPraiseRel.setMoodId(moodId);
        userMoodPraiseRelDao.save(userMoodPraiseRel);
        //更新说说的点赞数量
        Mood mood=this.findById(moodId);
        mood.setPraiseNum(mood.getPraiseNum()+1);
        this.update(mood);
        return Boolean.TRUE;
    }

    public boolean update(Mood mood){
        return moodDao.update(mood);
    }

    public Mood findById(Integer id){
        return moodDao.findById(id);
    }
}
