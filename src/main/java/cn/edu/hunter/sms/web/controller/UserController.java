package cn.edu.hunter.sms.web.controller;

import cn.edu.hunter.sms.domain.Page;
import cn.edu.hunter.sms.domain.User;
import cn.edu.hunter.sms.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0.0
 * @description 用户（管理员）控制器
 * @className UserController
 * @anthor Administrator
 * @time 2019/7/9 19:17
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    public UserService userService;

    /**
     * @description: 修改用户信息
     * @return: Map<String, Object>
     * @author: 陈亮
     * @time: 2019/7/10 18:52
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> editUser(User user) {
        System.out.println("editUser()方式执行了...");
        Map<String, String> ret = new HashMap<>();
        if (user == null) {
            ret.put("type", "error");
            ret.put("msg", "数据绑定出错，请联系开发作者！");
            return ret;
        }
        if (StringUtils.isEmpty(user.getUsername())) {
            ret.put("type", "error");
            ret.put("msg", "用户名不能为空");
            return ret;
        } else if (StringUtils.isEmpty(user.getPassword())) {
            ret.put("type", "error");
            ret.put("msg", "密码不能为空");
            return ret;
        }
        User existUser = userService.findByUserName(user.getUsername());
        if (existUser != null) {
            if (user.getId() != existUser.getId()){
                ret.put("type", "error");
                ret.put("msg", "该用户名已经存在！");
                return ret;
            }
        }
        if (userService.editUser(user) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "修改失败！");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "修改成功！");
        return ret;
    }


    /**
     * @description: 获取用户列表
     * @return: Map<String, String>
     * @author: 陈亮
     * @time: 2019/7/9 20:12
     */
    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getList(Page page, @RequestParam(value = "username", required = false, defaultValue = "") String username) {
        System.out.println("getList()方法执行了...");
        Map<String, Object> ret = new HashMap<>();
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("username", "%" + username + "%");
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows", userService.findUserList(queryMap));
        ret.put("total", userService.getTotal(queryMap));
        return ret;
    }

    /**
     * @description: 添加用户
     * @return: Map<String, String>
     * @author: 陈亮
     * @time: 2019/7/9 19:59
     */
    @RequestMapping(path = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addUser(User user) {
        System.out.println("addUser()方式执行了...");
        Map<String, String> ret = new HashMap<>();
        if (user == null) {
            ret.put("type", "error");
            ret.put("msg", "数据绑定出错，请联系开发作者！");
            return ret;
        }
        if (StringUtils.isEmpty(user.getUsername())) {
            ret.put("type", "error");
            ret.put("msg", "用户名不能为空");
            return ret;
        } else if (StringUtils.isEmpty(user.getPassword())) {
            ret.put("type", "error");
            ret.put("msg", "密码不能为空");
            return ret;
        }

        User existUser = userService.findByUserName(user.getUsername());
        if (existUser != null) {
            ret.put("type", "error");
            ret.put("msg", "该用户名已经存在！");
            return ret;
        }

        if (userService.addUser(user) <= 0) {
            ret.put("type", "error");
            ret.put("msg", "添加失败！");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "添加成功！");
        return ret;
    }

    /**
     * @description: 获取用户列表界面
     * @return: ModelAndView
     * @author: 陈亮
     * @time: 2019/7/9 20:05
     */
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public ModelAndView list(ModelAndView mv) {
        System.out.println("list()方法执行了...");
        mv.setViewName("user/user_list");
        return mv;
    }


}
