package online.yukun.pojo;

import java.util.List;

// 分页查询的javabean
public class PageBean<T> {
    // 总记录数
    private int totalCount;
    private int currentPage;
    private int pageSize;
    // 当前页的数据
    private List<T> rows;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> row) {
        this.rows = row;
    }
}
