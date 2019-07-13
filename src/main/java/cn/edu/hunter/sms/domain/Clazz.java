package cn.edu.hunter.sms.domain;

import java.util.Objects;

/**
 * @version 1.0.0
 * @description 班级实体类
 * @className Clazz
 * @anthor Administrator
 * @time 2019/7/12 15:25
 */
public class Clazz {
    private Integer id;
    private String name;
    private String remark;

    //与年级属一对一的关系
    private Integer gradeId;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getGradeId() {
        return gradeId;
    }

    public void setGradeId(Integer gradeId) {
        this.gradeId = gradeId;
    }

    @Override
    public String toString() {
        return "Clazz{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", gradeId=" + gradeId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clazz clazz = (Clazz) o;
        return Objects.equals(id, clazz.id) &&
                Objects.equals(name, clazz.name) &&
                Objects.equals(remark, clazz.remark) &&
                Objects.equals(gradeId, clazz.gradeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, remark, gradeId);
    }
}
