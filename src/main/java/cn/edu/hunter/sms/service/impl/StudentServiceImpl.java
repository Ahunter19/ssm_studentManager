package cn.edu.hunter.sms.service.impl;

import cn.edu.hunter.sms.dao.StudentDao;
import cn.edu.hunter.sms.domain.Student;
import cn.edu.hunter.sms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0.0
 * @description 学生service层实现类
 * @className StudentServiceImpl
 * @anthor Administrator
 * @time 2019/7/14 12:18
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    /**
     * 获取学生列表信息
     * @param queryMap
     * @return List<Student>
     */
    @Override
    public List<Student> findAll(Map<String, Object> queryMap) {
        return studentDao.findAll(queryMap);
    }

    /**
     * 查询学生信息总页数
     * @param queryMap
     * @return List<Integer>
     */
    @Override
    public Integer getTotal(Map<String, Object> queryMap) {
        return studentDao.getTotal(queryMap);
    }
}
