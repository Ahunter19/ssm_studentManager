package cn.edu.hunter.sms.dao;

import cn.edu.hunter.sms.domain.Grade;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @description 年级持久层类
 * @anthor Administrator
 * @data: 2019/7/11 15:42
 * @version 1.0.0
 */
@Repository
public interface GradeDao {

    /**
     * 修改年级信息
     * @param grade
     * @return
     */
    int editGrade(Grade grade);

    /**
     * 删除年级信息
     * @param ids
     * @return int
     */
    int deleteGrade(String ids);

    /**
     * 添加年级
     * @param grade
     */
    int addGrade(Grade grade);

    /**
     * 根据名称查询年级是否已存在
     * @return Grade对象
     */
    Grade findGradeByName(String name);

    /**
     * 获取年级信息列表
     * @param queryMap
     * @return List<Grade>
     */
    List<Grade> getGradeList(Map<String,Object> queryMap);

    /**
     * 查询年级列表总页数
     * @param queryMap
     * @return Integer
     */
    Integer getTotal(Map<String, Object> queryMap);
}
