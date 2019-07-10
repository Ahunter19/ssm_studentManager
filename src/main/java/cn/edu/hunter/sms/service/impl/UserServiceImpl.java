package cn.edu.hunter.sms.service.impl;

import cn.edu.hunter.sms.dao.UserDao;
import cn.edu.hunter.sms.domain.User;
import cn.edu.hunter.sms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
     * @description: 修改用户信息
     * @return: List<User>
     * @author: 陈亮
     * @time: 2019/7/10 18:58
     */
    @Override
    public Integer editUser(User user) {
        return userDao.editUser(user);
    }

    /**
     * 根据用户名查找用户信息
     *
     * @param username
     * @return User对象
     */
    @Override
    public User findByUserName(String username) {
        return userDao.findByUserName(username);
    }

    /**
     * 添加用户
     *
     * @param user
     */
    @Override
    public Integer addUser(User user) {
        return userDao.addUser(user);
    }

    /**
     * @description: 获取用户列表
     * @return: List<User>
     * @author: 陈亮
     * @time: 2019/7/9 20:14
     */
    @Override
    public List<User> findUserList(Map<String, Object> queryMap) {
        return userDao.findUserList(queryMap);
    }

    /**
     * @description: 获取用户列表总页数
     * @return: Integer
     * @author: 陈亮
     * @time: 2019/7/9 20:11
     */
    @Override
    public Integer getTotal(Map<String, Object> queryMap) {
        return userDao.getTotal(queryMap);
    }

}
