package com.jp.controller;

import com.jp.dto.MoodDTO;
import com.jp.dto.UserDTO;
import com.jp.model.User;
import com.jp.service.MoodService;
import com.jp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: HighConcurrentPraise
 * @description: 用户控制层
 * @author: CoderPengJiang
 * @create: 2019-10-31 22:55
 **/
@Controller
@RequestMapping("/user")
public class UserController {
   //@Resource
   //private UserService userService;
   //@RequestMapping("sayHello")
   //public String hello1(){
   //    return "hello";
   //}
    //测试
   @Resource
   private UserService userService;

    @RequestMapping("/find")
    public String find(Model model) {
        User user = userService.find(1);
        model.addAttribute("users", user);
        return "user";
    }
}
