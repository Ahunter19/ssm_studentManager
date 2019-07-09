package cn.edu.hunter.sms.web.controller;

import cn.edu.hunter.sms.domain.User;
import cn.edu.hunter.sms.service.UserService;
import cn.edu.hunter.sms.utils.CpachaUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    private UserService userService;

    /**
     * @description: 登录表单提交
     * @return: Map<String, String>
     * @author: 陈亮
     * @time: 2019/7/8 16:23
     */
    @RequestMapping(path = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> login(HttpServletRequest request, HttpServletResponse response,
                                     @RequestParam(value = "username", required = true) String username,
                                     @RequestParam(value = "password", required = true) String password) {
        Map<String, String> map = new HashMap<>();
        if (StringUtils.isEmpty(username)) {
            map.put("type", "error");
            map.put("msg", "用户名不能空");
            return map;
        } else if (StringUtils.isEmpty(password)) {
            map.put("type", "error");
            map.put("msg", "密码不能为空");
            return map;
        }
        User user = userService.findByUserName(username);
        System.out.println(user);
        if (user == null) {
            map.put("type", "error");
            map.put("msg", "账号或密码错误");
            return map;
        }
        request.getSession().setAttribute("user", user);
        map.put("type", "success");
        map.put("msg", "登录成功");
        return map;
    }

    /**
     * 登录页面
     *
     * @param mv
     * @return
     */
    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public ModelAndView toLoginPage(ModelAndView mv) {
        mv.setViewName("system/login");
        return mv;
    }

    /**
     * 验证码控制类
     *
     * @param request
     * @param response
     * @param vl
     * @param w
     * @param h
     */
    @RequestMapping(path = "/get_cpacha", method = RequestMethod.GET)
    public void getCpacha(HttpServletRequest request, HttpServletResponse response,
                          @RequestParam(value = "vl", defaultValue = "4", required = false) Integer vl,
                          @RequestParam(value = "w", defaultValue = "50", required = false) Integer w,
                          @RequestParam(value = "h", defaultValue = "33", required = false) Integer h) {
        try {
            System.out.println("获取验证码...");
            CpachaUtil cpachaUtil = new CpachaUtil(vl, w, h);
            String generatorVCode = cpachaUtil.generatorVCode();
            request.getSession().setAttribute("loginCpacha", generatorVCode);
            BufferedImage bufferedImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
            ImageIO.write(bufferedImage, "gif", response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @description: 主页
     * @return: ModelAndView
     * @author: 陈亮
     * @time: 2019/7/8 16:27
     */
    @RequestMapping(path = "/main", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView mv) {
        mv.setViewName("system/main");
        return mv;
    }
}
