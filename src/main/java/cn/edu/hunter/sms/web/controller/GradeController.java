package cn.edu.hunter.sms.web.controller;

import cn.edu.hunter.sms.domain.Grade;
import cn.edu.hunter.sms.domain.Page;
import cn.edu.hunter.sms.service.GradeService;
import cn.edu.hunter.sms.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0.0
 * @description 年级控制类
 * @className GradeController
 * @anthor Administrator
 * @time 2019/7/11 14:31
 */
@Controller
@RequestMapping("/grade")
public class GradeController {

    @Autowired
    private GradeService gradeService;

    /**
     * 删除年级信息
     *
     * @param ids
     * @return data
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteGrade(@RequestParam(value = "ids[]", required = false, defaultValue = "") Integer[] ids) {
        System.out.println("deleteGrade()方法执行了...");
        HashMap<String, Object> data = new HashMap<>();
        if (ids == null || ids.length == 0) {
            data.put("type", "error");
            data.put("msg", "请选择要删除的数据！");
            return data;
        }
        try {
            if (0 >= gradeService.deleteGrade(StringUtil.joinString(Arrays.asList(ids), ","))) {
                data.put("type", "error");
                data.put("msg", "删除失败！");
                return data;
            }
        } catch (Exception e) {
            data.put("type", "error");
            data.put("msg", "该年级下存在班级信息，无法删除！");
            return data;
        }
        data.put("type", "success");
        data.put("msg", "删除成功！");
        return data;
    }

    /**
     * 修改年级
     *
     * @param grade
     * @return data
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> editGrade(Grade grade) {
        System.out.println("editGrade()方法执行了...");
        Map<String, String> data = new HashMap<>();
        if (StringUtils.isEmpty(grade.getName())) {
            data.put("type", "error");
            data.put("msg", "年级不能为空！");
            return data;
        }
        if (null != gradeService.findGradeByName(grade.getName())) {
            data.put("type", "error");
            data.put("msg", "输入的年级已存在，请重新输入！");
            return data;
        }
        if (0 >= gradeService.editGrade(grade)) {
            data.put("type", "error");
            data.put("msg", "修改失败！");
            return data;
        }
        data.put("type", "success");
        data.put("msg", "修改成功！");
        return data;
    }

    /**
     * 添加年级
     *
     * @return Map<String, String>
     */
    @RequestMapping(value = "/addGrade", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addGrade(Grade grade) {
        System.out.println("addGrade()方法执行了...");
        HashMap<String, String> data = new HashMap<>();
        System.out.println(grade);
        if (StringUtils.isEmpty(grade.getName())) {
            data.put("type", "error");
            data.put("msg", "年级不能为null");
            return data;
        }
        //查询年级是否重复
        if (gradeService.findGradeByName(grade.getName()) != null) {
            //重复 返回添加失败
            data.put("type", "error");
            data.put("msg", "年级重复，请重新输入！");
            return data;
        }
        //没重复 返回添加成功
        if (gradeService.addGrade(grade) <= 0) {
            data.put("type", "error");
            data.put("msg", "添加失败！");
        }
        data.put("type", "success");
        data.put("msg", "添加成功！");
        return data;

    }

    /**
     * 获取年级列表
     *
     * @param page
     * @param gradeName
     * @return
     */
    @RequestMapping(value = "/getGradeList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getGradeList(Page page, @RequestParam(value = "name", defaultValue = "", required = false) String gradeName) {
        System.out.println("getGradeList()方法执行了...");
        Map<String, Object> ret = new HashMap<>();
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("name", "%" + gradeName + "%");
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows", gradeService.getGradeList(queryMap));
        ret.put("total", gradeService.getTotal(queryMap));
        return ret;
    }

    /**
     * 跳转年级列表页面
     *
     * @param mv
     * @return ModelAndView
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(ModelAndView mv) {
        System.out.println("list()方法执行了...");
        mv.setViewName("grade/grade_list");
        return mv;
    }
}
