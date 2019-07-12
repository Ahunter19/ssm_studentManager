package cn.edu.hunter.sms.utils;

import java.util.List;

/**
 * @version 1.0.0
 * @description 实用工具类
 * @className StringUtil
 * @anthor Administrator
 * @time 2019/7/12 14:54
 */
public class StringUtil {

    /**
     * 根据集合及分隔符返回字符串
     * @param list
     * @param spilt
     * @return String
     */
    public static String joinString(List<Integer> list, String spilt) {
        String str = "";
        for (Integer i : list) {
            str += i + spilt;
        }
        if (!"".equals(str)) {
            str = str.substring(0, str.length() - spilt.length());
        }
        return str;
    }
}
