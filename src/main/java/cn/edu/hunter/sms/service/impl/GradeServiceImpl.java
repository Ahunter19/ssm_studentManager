package cn.edu.hunter.sms.service.impl;

import cn.edu.hunter.sms.dao.GradeDao;
import cn.edu.hunter.sms.domain.Grade;
import cn.edu.hunter.sms.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0.0
 * @description 年级业务层实现类
 * @className GradeServiceImpl
 * @anthor Administrator
 * @time 2019/7/11 15:42
 */

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeDao gradeDao;

    /**
     * 添加年级
     *
     * @param grade
     */
    @Override
    public int addGrade(Grade grade) {
        return gradeDao.addGrade(grade);
    }

    /**
     * 删除年级信息
     * @param ids
     * @return int
     */
    @Override
    public int deleteGrade(String ids) {
        return gradeDao.deleteGrade(ids);
    }

    /**
     * 修改年级信息
     * @param grade
     * @return
     */
    @Override
    public int editGrade(Grade grade) {
        return gradeDao.editGrade(grade);
    }

    /**
     * 根据名称查询年级是否已存在
     *
     * @param name
     * @return
     */
    @Override
    public Grade findGradeByName(String name) {
        return gradeDao.findGradeByName(name);
    }

    /**
     * 查询年级列表
     *
     * @param queryMap
     * @return List<Grade>
     */
    @Override
    public List<Grade> getGradeList(Map<String, Object> queryMap) {
        return gradeDao.getGradeList(queryMap);
    }

    /**
     * 获取年级列表总页数
     *
     * @param queryMap
     * @return Integer
     */
    @Override
    public Integer getTotal(Map<String, Object> queryMap) {
        return gradeDao.getTotal(queryMap);
    }
}
