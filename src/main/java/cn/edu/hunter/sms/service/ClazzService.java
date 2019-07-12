package cn.edu.hunter.sms.service;

import cn.edu.hunter.sms.domain.Clazz;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @version 1.0.0
 * @description 班级业务层类
 * @className ClazzService
 * @anthor Administrator
 * @time 2019/7/12 15:44
 */
@Service
public interface ClazzService {

    /**
     * 删除班级信息
     * @param ids
     * @return int
     */
    int deleteClazz(String ids);

    /**
     * 添加班级
     */
    int addClazz(Clazz clazz);

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
