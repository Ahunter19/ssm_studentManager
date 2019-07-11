package cn.edu.hunter.sms.domain;

/**
 * @version 1.0.0
 * @description 分页实体封装
 * @className Page
 * @anthor Administrator
 * @time 2019/7/9 20:07
 */
public class Page {
    private int page;   //当前页面
    private int rows;   //每页显示条数
    private int offset; //总条数

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getOffset() {
        return (page - 1) * rows;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "Page{" +
                "page=" + page +
                ", rows=" + rows +
                ", offset=" + offset +
                '}';
    }
}
