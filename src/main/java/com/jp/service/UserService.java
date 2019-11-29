package com.jp.service;

import com.jp.dto.UserDTO;

/**
 * @program: HighConcurrentPraise
 * @description: 用户服务接口
 * @author: CoderPengJiang
 * @create: 2019-10-31 22:04
 **/
public interface UserService {
    //通过id查询用户
    UserDTO find(Integer id);
}
