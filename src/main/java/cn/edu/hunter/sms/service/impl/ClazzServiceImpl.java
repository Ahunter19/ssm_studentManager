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
     * 获取班级信息列表
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
