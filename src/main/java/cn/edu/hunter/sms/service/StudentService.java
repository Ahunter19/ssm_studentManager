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
     * 获取学生列表
     *
     * @return
     */
    List<Student> findAll(Map<String, Object> queryMap);

    /**
     * 获取学生列表页数
     */
    Integer getTotal(Map<String, Object> queryMap);
}
