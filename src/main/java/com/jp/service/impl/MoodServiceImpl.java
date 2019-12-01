package com.jp.service.impl;

import com.jp.dao.MoodDao;
import com.jp.dao.UserDao;
import com.jp.dao.UserMoodPraiseRelDao;
import com.jp.dto.MoodDTO;
import com.jp.model.Mood;
import com.jp.model.User;
import com.jp.model.UserMoodPraiseRel;
import com.jp.mq.MoodProducer;
import com.jp.service.MoodService;
import com.jp.service.UserService;
import com.sun.media.sound.ModelDestination;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.jms.Destination;
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

    //redis缓存点赞,高效访问redis数据的方案
    @Resource
    private RedisTemplate redisTemplate;

    //消息队列
    @Resource
    private MoodProducer moodProducer;
    //队列
    private static Destination destination = new ActiveMQQueue("jp.queue.high.concurrency.praise");

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

    //传统点赞
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

    //redis缓存点赞
    //key命名规范：项目名称+模板名称+具体内容
    private static final String PRAISE_HASH_KEY = "ssm_hps.mood.id.list.key";

    public boolean praiseMoodForRedis(Integer userId, Integer moodId) {
        //修改为异步处理方式
        MoodDTO moodDTO=new MoodDTO();
        moodDTO.setUserId(userId);
        moodDTO.setId(moodId);
        //发送消息
        moodProducer.sendMessage(destination,moodDTO);
     //  //1.存放到hashset中
     //  redisTemplate.opsForSet().add(PRAISE_HASH_KEY , moodId);
     //  //2.存放到set中
     //  redisTemplate.opsForSet().add(moodId,userId);
        return false;
    }

    @Resource
    private UserService userService;

    public List<MoodDTO> findAllForRedis() {
        List<Mood> moodList = moodDao.findAll();
        if(CollectionUtils.isEmpty(moodList)){
            return Collections.EMPTY_LIST;
        }
        List<MoodDTO> moodDTOList = new ArrayList<MoodDTO>();
        for(Mood mood: moodList){
            MoodDTO moodDTO = new MoodDTO();
            moodDTO.setId(mood.getId());
            moodDTO.setUserId(mood.getUserId());
            //right = 总点赞数量 ： 数据库的点赞数量 + redis的点赞数量
            moodDTO.setPraiseNum(mood.getPraiseNum() + redisTemplate.opsForSet().size(mood.getId()).intValue());
            moodDTO.setPublishTime(mood.getPublishTime());
            moodDTO.setContent(mood.getContent());
            //通过userID查询用户
            User user =  userService.find(mood.getUserId());
            //用户名
            moodDTO.setUserName(user.getName());
            //账户
            moodDTO.setUserAccount(user.getAccount());
            moodDTOList.add(moodDTO);
        }
        return moodDTOList;
    }



}
