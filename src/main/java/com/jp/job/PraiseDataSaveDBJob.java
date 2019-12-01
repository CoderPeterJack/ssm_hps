package com.jp.job;

import com.jp.model.Mood;
import com.jp.model.UserMoodPraiseRel;
import com.jp.service.MoodService;
import com.jp.service.UserMoodPraiseRelService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @program: ssm_hps
 * @description: 定时器
 * @author: CoderPengJiang
 * @create: 2019-12-01 15:12
 **/
public class PraiseDataSaveDBJob {

    @Resource
    private RedisTemplate redisTemplate;
    private static final String PRAISE_HASH_KEY = "ssm_hps.mood.id.list.key";
    @Resource
    private UserMoodPraiseRelService userMoodPraiseRelService;
    @Resource
    private MoodService moodService;

    //每10秒执行一次，真实项目当中，我们可以把定时器的执行计划时间设置长一点
    //比如说每天晚上凌晨2点跑一次。
    @Scheduled(cron = "*/10 * *  * * * ")
    public void savePraiseDataToDB2(){
        //获取所有被点赞的说说id
        Set<Integer> moods = redisTemplate.opsForSet().members(PRAISE_HASH_KEY);
        if(CollectionUtils.isEmpty(moods)){
            return;
        }
        for(Integer moodId: moods){
            if(redisTemplate.opsForSet().members(moodId) == null){
                continue;
            }else {
                //通过说说id获取所有点赞的用户id列表
                Set<Integer> userIds = redisTemplate.opsForSet().members(moodId);
                if(CollectionUtils.isEmpty(userIds)){
                    continue;
                }else{
                    for(Integer userId:userIds){
                        UserMoodPraiseRel userMoodPraiseRel = new UserMoodPraiseRel();
                        userMoodPraiseRel.setMoodId(moodId);
                        userMoodPraiseRel.setUserId(userId);
                        //保存说说与用户关联关系
                        userMoodPraiseRelService.save(userMoodPraiseRel);
                    }
                    Mood mood = moodService.findById(moodId);
                    //更新说说点赞数量
                    //说说的总点赞数量 = redis 点赞数量 + 数据库的点赞数量
                    mood.setPraiseNum(mood.getPraiseNum() + redisTemplate.opsForSet().size(moodId).intValue());
                    moodService.update(mood);
                    //清除缓存数据
                    redisTemplate.delete(moodId);
                }
            }
        }
        //清除缓存数据
        redisTemplate.delete(PRAISE_HASH_KEY);

    }
}
