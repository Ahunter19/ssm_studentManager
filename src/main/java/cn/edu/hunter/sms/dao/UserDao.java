package cn.edu.hunter.sms.dao;

import cn.edu.hunter.sms.domain.User;
import org.springframework.stereotype.Repository;

/**
 * @version 1.0.0
 * @description 用户持久层接口
 * @className UserDao
 * @anthor Administrator
 * @time 2019/7/8 16:27
 */
@Repository
public interface UserDao {

    /**
     * 根据用户名查找用户信息
     * @param username
     * @return User对象
     */
    User findByUserName(String username);
}
