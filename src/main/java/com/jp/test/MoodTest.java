//package com.jp.test;
//
//import com.jp.dto.MoodDTO;
//import com.jp.model.Mood;
//import com.jp.service.MoodService;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @program: ssm_hps
// * @description: mood测试
// * @author: CoderPengJiang
// * @create: 2019-11-29 21:02
// **/
//public class MoodTest {
//    //获取容器
//    public static void main(String[] args) {
//        ApplicationContext con = new ClassPathXmlApplicationContext("applicationContext.xml");
//        MoodService as = (MoodService) con.getBean("moodService");
//        List<MoodDTO> listMoodDTO=as.findAll();
//        for(MoodDTO moodDTO:listMoodDTO){
//            System.out.println(moodDTO);
//        }
//    }
//
//
//
//}
