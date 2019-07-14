package cn.edu.hunter.sms.domain;

import org.springframework.stereotype.Component;

/**
 * @version 1.0.0
 * @description 学生实体类
 * @className Student
 * @anthor Administrator
 * @time 2019/7/14 12:12
 */
@Component
public class Student {
    private Integer id;
    private String name;    //姓名
    private String sex;     //性别
    private String tel;     //电话
    private String qq;      //QQ
    private Integer clazzId;   //班级ID

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public Integer getClazzId() {
        return clazzId;
    }

    public void setClazzId(Integer clazzId) {
        this.clazzId = clazzId;
    }
}
