package cn.edu.hunter.sms.service;

import cn.edu.hunter.sms.domain.User;
import org.springframework.stereotype.Service;

/**
 * @version 1.0.0
 * @description 用户业务层接口
 * @className UserService
 * @anthor Administrator
 * @time 2019/7/8 16:28
 */
@Service
public interface UserService {

    /**
     * 根据用户名查找用户信息
     * @param username
     * @return User对象
     */
    User findByUserName(String username);
}
