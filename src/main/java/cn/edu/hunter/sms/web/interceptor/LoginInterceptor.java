package cn.edu.hunter.sms.web.interceptor;

import cn.edu.hunter.sms.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @version 1.0.0
 * @description 登录过滤拦截器
 * @anthor 陈亮
 * @data: 2019/7/8 16:44
 */
public class LoginInterceptor implements HandlerInterceptor {

    //拦截之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        System.out.println("进入拦截器，url = " + url);
        User user = (User) request.getSession().getAttribute("user");
        System.out.println(user);
        if (user == null) {
            //表示未登录或者登录状态失效
            System.out.println("表示未登录或者登录状态失效 ,url= " + url);
            response.sendRedirect(request.getContextPath() + "/system/login");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
