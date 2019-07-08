package cn.edu.hunter.sms.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 系统主页控制器
 *
 * @version 1.0.0
 * @className SystemController
 * @anthor Administrator
 * @data 2019/7/8
 */
@Controller
@RequestMapping("/system")
public class SystemController {

    @RequestMapping(path = "/index",method = RequestMethod.GET)
    public ModelAndView index(ModelAndView mv) {
        mv.setViewName("hello world");
        mv.addObject("user","it猎人工作室");
        return mv;
    }
}
