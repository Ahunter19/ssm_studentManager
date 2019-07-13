package cn.edu.hunter.sms.web.controller;

import cn.edu.hunter.sms.domain.Clazz;
import cn.edu.hunter.sms.domain.Grade;
import cn.edu.hunter.sms.domain.Page;
import cn.edu.hunter.sms.service.ClazzService;
import cn.edu.hunter.sms.service.GradeService;
import cn.edu.hunter.sms.utils.StringUtil;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

    public HashMap<String, String> data = new HashMap<>();

    /**
     * 修改班级信息
     */
    @RequestMapping(value = "/editClazz", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> editClazz(Clazz clazz) {
        System.out.println("editClazz()方法执行了...");
        if (clazz.getName() == null) {
            data.put("type", "error");
            data.put("msg", "请填写班级名称！");
            return data;
        }

        Clazz beforeClazz = clazzService.findClazzById(clazz.getId());  //根据ID查询对象
        Clazz afterClazz = clazzService.findClazzIfo(clazz);    //根据条件查询对象

        if (beforeClazz.equals(afterClazz)) {    //判断是否修改了
            data.put("type", "error");
            data.put("msg", "数据没有修改！");
            return data;
        } else {
            //修改用户
            if (0 >= clazzService.editClazz(clazz)) {
                data.put("type", "error");
                data.put("msg", "修改失败！");
                return data;
            }
            data.put("type", "success");
            data.put("msg", "修改成功");
            return data;
        }
    }


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

        if (clazz.getName() == null || "".equals(clazz.getName())) {
            data.put("type", "error");
            data.put("msg", "班级名称不能为空！");
            return data;
        }

        if (clazz.getGradeId() == null || "".equals(clazz.getGradeId())) {
            data.put("type", "error");
            data.put("msg", "请选择所属年级！");
            return data;
        }

        //判断是否已经存在年级班级信息
        if (null != clazzService.findClazzIfo(clazz)) {
            data.put("type", "error");
            data.put("msg", "该年级下已经存在本班级，请重新添加！");
            return data;
        }

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
        model.addObject("gradeList", JSONArray.fromObject(gradeList));
        return model;
    }

    /**
     * 获取班级列表信息
     *
     * @return
     */
    @RequestMapping(value = "/getClazzList", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getClazzList(Page page,
                                            @RequestParam(value = "name", required = false, defaultValue = "") String clazzName,
                                            @RequestParam(value = "gradeId", required = false) Integer gradeId) {
        System.out.println("getClazzList()方法执行了...");
        HashMap<String, Object> data = new HashMap<>();
        HashMap<String, Object> queryMap = new HashMap<>();
        queryMap.put("name", "%" + clazzName + "%");
        if (null != gradeId) {
            queryMap.put("gradeId", gradeId);
        }
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        data.put("rows", clazzService.findAllClazzList(queryMap));
        data.put("total", clazzService.getClazzCount(queryMap));
        return data;
    }
}
