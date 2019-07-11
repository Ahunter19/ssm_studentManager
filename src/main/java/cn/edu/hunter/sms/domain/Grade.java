package cn.edu.hunter.sms.domain;

import org.springframework.stereotype.Component;

/**
 * @version 1.0.0
 * @description 年级实体类
 * @className Grade
 * @anthor Administrator
 * @time 2019/7/11 15:01
 */
@Component
public class Grade {
    private Integer id;
    private String name;    //年级名称
    private String remark;  //年级备注

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

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
