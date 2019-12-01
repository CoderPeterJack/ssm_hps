package com.jp.service.impl;

import com.jp.dao.UserDao;
import com.jp.dto.UserDTO;
import com.jp.model.User;
import com.jp.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: HighConcurrentPraise
 * @description: 用户服务类
 * @author: CoderPengJiang
 * @create: 2019-10-31 22:09
 **/
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    public UserDTO find(Integer id){
       User user=userDao.find(id);
      // System.out.println("user业务层执行成功");
       return converModel2DTO(user);
    }

   private UserDTO converModel2DTO(User user){
       UserDTO userDTO=new UserDTO();
       userDTO.setId(user.getId());
       userDTO.setAccount(user.getAccount());
       userDTO.setName(user.getName());
       return userDTO;
   }
}
