package cn.edu.hunter.sms.web.controller;

import cn.edu.hunter.sms.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

    @RequestMapping(path = "/list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView mv){
        mv.setViewName("user/user_list");
        return mv;
    }
}
