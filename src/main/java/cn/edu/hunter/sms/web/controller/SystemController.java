package cn.edu.hunter.sms.web.controller;

import cn.edu.hunter.sms.utils.CpachaUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

    public Map<String, String> login() {
        Map<String, String> map = new HashMap<>();
        return map;
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
     * 登录
     *
     * @param mv
     * @return
     */
    @RequestMapping(path = "/login", method = RequestMethod.GET)
    public ModelAndView login(ModelAndView mv) {
        mv.setViewName("system/login");
        return mv;
    }


    /**
     * 测试第一个前端控制器
     *
     * @param mv
     * @return
     */
    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView mv) {
        mv.setViewName("hello world");
        mv.addObject("user", "it猎人工作室");
        return mv;
    }
}
