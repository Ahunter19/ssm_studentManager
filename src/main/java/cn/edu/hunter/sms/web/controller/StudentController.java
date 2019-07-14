package cn.edu.hunter.sms.web.controller;

import cn.edu.hunter.sms.domain.Page;
import cn.edu.hunter.sms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @version 1.0.0
 * @description
 * @className StduentController
 * @anthor Administrator
 * @time 2019/7/14 12:57
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * @description: 显示学生信息列表
     * @return: model
     * @author: 陈亮
     * @time: 2019/7/14 12:07
     */
    @RequestMapping("/goStudentView")
    public ModelAndView goStudentView(ModelAndView model) {
        System.out.println("goStudentView()方法执行了...");
        model.setViewName("student/student_list");
        return model;
    }


    /**
     * @description: 获取学生列表
     * @return: data
     * @author: 陈亮
     * @time: 2019/7/14 12:10
     */
    @RequestMapping("/getStudentList")
    @ResponseBody
    public Map<String, Object> getStudentList(Page page,
                                              @RequestParam(value = "name", required = false, defaultValue = "") String studentName,
                                              @RequestParam(value = "clazzId", required = false) Integer clazzId) {
        System.out.println("getStudentList()方法执行了...");
        HashMap<String, Object> data = new HashMap<>();
        HashMap<String, Object> queryMap = new HashMap<>();
        if(null != clazzId){
            queryMap.put("clazzId", clazzId);
        }
        queryMap.put("name", "%" + studentName + "%");
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        data.put("rows", studentService.findAll(queryMap));
        data.put("total", studentService.getTotal(queryMap));
        return data;
    }
}
