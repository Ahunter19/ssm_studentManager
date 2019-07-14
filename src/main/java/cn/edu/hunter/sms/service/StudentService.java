package cn.edu.hunter.sms.service;

import cn.edu.hunter.sms.domain.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0.0
 * @description 学生service接口类
 * @anthor Administrator
 * @data: 2019/7/14 12:11
 */
@Service
public interface StudentService {


    /**
     * @description: 添加学生信息
     * @return: int
     * @author: 陈亮
     * @time: 2019/7/14 18:00
     */


    /**
     * @description: 获取学生列表
     * @return: List<Student>
     * @author: 陈亮
     * @time: 2019/7/14 18:00
     */
    List<Student> findAll(Map<String, Object> queryMap);

    /**
     * @description: 获取学生列表页数
     * @return: Integer
     * @author: 陈亮
     * @time: 2019/7/14 17:59
     */
    Integer getTotal(Map<String, Object> queryMap);
}
