package cn.edu.hunter.sms.web.controller;

import cn.edu.hunter.sms.domain.Clazz;
import cn.edu.hunter.sms.domain.Grade;
import cn.edu.hunter.sms.domain.Page;
import cn.edu.hunter.sms.service.ClazzService;
import cn.edu.hunter.sms.service.GradeService;
import cn.edu.hunter.sms.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @version 1.0.0
 * @description 班级控制类
 * @className ClazzController
 * @anthor Administrator
 * @time 2019/7/12 15:28
 */
@Controller
@RequestMapping("/clazz")
public class ClazzController {

    @Autowired
    private ClazzService clazzService;
    @Autowired
    private GradeService gradeService;

    /**
     * 删除班级信息
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> deleteClazz(@RequestParam(value = "ids[]", required = false, defaultValue = "") Integer ids[]) {
        System.out.println("deleteClazz()方法执行了...");
        HashMap<String, String> data = new HashMap<>();
        if (null == ids || "".equals(ids)) {
            data.put("type", "error");
            data.put("msg", "请选择要删除的数据！");
            return data;
        }
        String str = StringUtil.joinString(Arrays.asList(ids), ",");
        if (0 >= clazzService.deleteClazz(str)) {
            data.put("type", "error");
            data.put("msg", "删除失败！");
            return data;
        }
        data.put("type", "success");
        data.put("msg", "删除成功！");
        return data;
    }

    /**
     * 添加班级信息
     *
     * @param clazz
     * @return
     */
    @RequestMapping(value = "/addClazz", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addClazz(Clazz clazz) {
        System.out.println("addClazz()方法执行了...");
        HashMap<String, String> data = new HashMap<>();
        if (0 >= clazzService.addClazz(clazz)) {
            data.put("type", "error");
            data.put("msg", "添加班级失败！");
            return data;
        }
        data.put("type", "success");
        data.put("smg", "添加班级成功！");
        return data;
    }

    /**
     * 班级页面显示
     *
     * @param model
     * @return ModelAndView
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView list(ModelAndView model) {
        System.out.println("clazzList()方法执行了...");
        model.setViewName("clazz/clazz_list");
        List<Grade> gradeList = gradeService.findAll();
        model.addObject("gradeList", gradeList);
        return model;
    }

    /**
     * 获取班级列表信息
     *
     * @return
     */
    @RequestMapping(value = "/getClazzList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getClazzList(HttpServletRequest request, Page page,
                                            @RequestParam(value = "name", required = false, defaultValue = "") String clazzName) {
        System.out.println("getClazzList()方法执行了...");
        HashMap<String, Object> data = new HashMap<>();
        HashMap<String, Object> queryMap = new HashMap<>();
        queryMap.put("name", "%" + clazzName + "%");
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        data.put("rows", clazzService.findAllClazzList(queryMap));
        data.put("total", clazzService.getClazzCount(queryMap));
        List<Grade> gradeList = gradeService.findAll();
        request.getSession().setAttribute("gradeList", gradeList);
        return data;
    }
}
