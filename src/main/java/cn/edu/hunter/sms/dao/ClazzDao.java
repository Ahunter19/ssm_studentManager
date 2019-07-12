package cn.edu.hunter.sms.dao;

import cn.edu.hunter.sms.domain.Clazz;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0.0
 * @description 班级持久层接口
 * @className ClazzDao
 * @anthor Administrator
 * @time 2019/7/12 15:47
 */
@Repository
public interface ClazzDao {

    /**
     * 查询所有的班级信息
     *
     * @return List<Clazz>
     */
    List<Clazz> findAllClazzList(Map<String, Object> queryMap);

    /**
     * 获取班级总页数
     * @param queryMap
     * @return
     */
    Integer getClazzCount(Map<String, Object> queryMap);
}
