package cn.edu.hunter.sms.domain;

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

//    //与年级属一对一的关系
//    private Grade grade;

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

//    public Grade getGrade() {
//        return grade;
//    }
//
//    public void setGrade(Grade grade) {
//        this.grade = grade;
//    }

    @Override
    public String toString() {
        return "Clazz{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
