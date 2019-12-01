package com.jp.mq;

import com.jp.dto.MoodDTO;
import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Destination;

/**
 * @program: ssm_hps
 * @description:生产者jmsTemplate
 * @author: CoderPengJiang
 * @create: 2019-12-01 16:08
 **/
@Component
public class MoodProducer {
    @Resource
    private JmsTemplate jmsTemplate;

    private Logger log = Logger.getLogger(this.getClass());

    public void sendMessage(Destination destination, final MoodDTO mood) {
        log.info("生产者--->>>用户id：" + mood.getUserId() + " 给说说id：" + mood.getId() + " 点赞");
        //mood实体需要实现Serializable序列化接口
        jmsTemplate.convertAndSend(destination, mood);
    }
}
