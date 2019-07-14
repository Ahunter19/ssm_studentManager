package cn.edu.hunter.sms.dao;

import cn.edu.hunter.sms.domain.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0.0
 * @description 学生持久层接口
 * @className StudentDao
 * @anthor Administrator
 * @time 2019/7/14 12:18
 */
@Repository
public interface StudentDao {

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
