package cn.edu.hunter.sms.service.impl;

import cn.edu.hunter.sms.dao.ClazzDao;
import cn.edu.hunter.sms.domain.Clazz;
import cn.edu.hunter.sms.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0.0
 * @description 班级业务层实现类
 * @className ClazzServiceImpl
 * @anthor Administrator
 * @time 2019/7/12 15:47
 */
@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzDao clazzDao;

    /**
     * 根据班级名称和年级ID查询是否存在该班级
     * @param clazz
     * @return Clazz对象
     */
    @Override
    public Clazz findClazzIfo(Clazz clazz) {
        return clazzDao.findClazzIfo(clazz);
    }

    @Override
    public Clazz findClazzById(Integer id) {
        return clazzDao.findClazzById(id);
    }

    /**
     * 修改班级信息
     *
     * @param clazz
     * @return int
     */
    @Override
    public int editClazz(Clazz clazz) {
        return clazzDao.editClazz(clazz);
    }

    /**
     * 删除、批量删除班级信息
     *
     * @param ids
     * @return
     */
    @Override
    public int deleteClazz(String ids) {
        return clazzDao.deleteClazz(ids);
    }

    /**
     * 添加班级信息
     *
     * @param clazz
     * @return
     */
    @Override
    public int addClazz(Clazz clazz) {
        return clazzDao.addClazz(clazz);
    }

    /**
     * 获取班级信息列表
     *
     * @param queryMap
     * @return
     */
    @Override
    public List<Clazz> findAllClazzList(Map<String, Object> queryMap) {
        return clazzDao.findAllClazzList(queryMap);
    }

    /**
     * 获取班级总页数
     *
     * @param queryMap
     * @return Integer
     */
    @Override
    public Integer getClazzCount(Map<String, Object> queryMap) {
        return clazzDao.getClazzCount(queryMap);
    }
}
