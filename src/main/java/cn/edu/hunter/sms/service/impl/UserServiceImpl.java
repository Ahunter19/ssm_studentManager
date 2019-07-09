package cn.edu.hunter.sms.service.impl;

import cn.edu.hunter.sms.dao.UserDao;
import cn.edu.hunter.sms.domain.User;
import cn.edu.hunter.sms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version 1.0.0
 * @description 用户业务层实现类
 * @className UserServiceImpl
 * @anthor Administrator
 * @time 2019/7/8 16:31
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 根据用户名查找用户信息
     * @param username
     * @return User对象
     */
    @Override
    public User findByUserName(String username) {
        return userDao.findByUserName(username);
    }

}
