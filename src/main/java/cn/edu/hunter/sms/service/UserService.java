package cn.edu.hunter.sms.service;

import cn.edu.hunter.sms.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
     * @description: 删除/批量删除用户
     * @return: Integer
     * @author: 陈亮
     * @time: 2019/7/11 14:17
     */
    Integer deleteUser(String ids);

    /**
     * @description: 修改用户信息
     * @return: List<User>
     * @author: 陈亮
     * @time: 2019/7/10 18:58
     */
    Integer editUser(User user);

    /**
     * 根据用户名查找用户信息
     * @param username
     * @return User对象
     */
    User findByUserName(String username);

    /**
     * 添加用户
     * @param user
     */
    Integer addUser(User user);

    /**
     * @description: 获取用户列表
     * @return: List<User>
     * @author: 陈亮
     * @time: 2019/7/9 20:12
     */
    List<User> findUserList(Map<String,Object> queryMap);

    /**
     * 查询用户列表总页数
     * @param queryMap
     * @return Integer
     */
    Integer getTotal(Map<String, Object> queryMap);
}
