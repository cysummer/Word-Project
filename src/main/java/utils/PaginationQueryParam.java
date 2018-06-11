package utils;

import java.io.Serializable;

/**
 * 分页查询参数DTO.
 * <p>
 * 功能：为分页查询SQL语句提供分页参数的计算，支持Oracle、MySQL数据库.
 * <p>
 * 用法：
 * <p>
 * <ul>
 * <li>自定义的查询参数继承此分页查询参数：class MyQueryParam extends PaginationQueryParam</li>
 * <li>Oracle SQL用法：<br>
 * SELECT * FROM (SELECT A.*, ROWNUM RN FROM (SELECT COLUMN_NAME_1,COLUMN_NAME_2 FROM TABLE_NAME WHERE CONDITION ORDER
 * BY COLUMN_NAME DESC) A WHERE ROWNUM <= #endRowNum ) WHERE RN >= #startRowNum</li>
 * <li>MySQL SQL用法：<br>
 * SELECT COLUMN_NAME_1,COLUMN_NAME_2 FROM TABLE_NAME WHERE CONDITION ORDER BY COLUMN_NAME DESC LIMIT #offset,#limit</li>
 * </ul>
 *
 * @author liuxf 2015年11月19日 下午1:14:00
 */
public class PaginationQueryParam implements Serializable {

    private static final long serialVersionUID = -958088200840759861L;
    /**
     * 页码默认值=1
     */
    public static final int DEFAULT_PAGE_NUM = 1;

    /**
     * 每页查询数量默认值=20
     */
    public static final int DEFAULT_PAGE_SIZE = 20;

    /**
     * 页码,默认值为1
     */
    private int pageNum = DEFAULT_PAGE_NUM;

    /**
     * 每页查询数量,默认值20
     */
    private int pageSize = DEFAULT_PAGE_SIZE;

    private  int  limit;

    private int offset;

    /**
     * 默认构造参数，pageNum=1，pageSize=20
     */
    public PaginationQueryParam() {
    }

    /**
     * 获取页码
     *
     * @return
     */
    public int getPageNum() {
        return pageNum;
    }

    /**
     * 设置页码
     *
     * @param pageNum
     */
    public void setPageNum(int pageNum) {
        if (pageNum <= 0) {
            return;
        }
        this.pageNum = pageNum;
    }

    /**
     * 获取每页查询数量
     *
     * @return
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置每页查询数量
     *
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        if (pageSize <= 0) {
            return;
        }
        this.pageSize = pageSize;
    }

    /**
     * For MySQL, getOffset
     *
     * @return
     */
    public int getOffset() {
        return (pageNum - 1) * pageSize;
    }


    public void setLimit(int limit) {
        this.limit = pageSize;
    }

    public void setOffset(int offset) {
        this.offset = (pageNum - 1) * pageSize;
    }

    /**
     * For MySQL, getLimit
     *
     * @return
     */
    public int getLimit() {
        return pageSize;
    }

    /**
     * For Oracle, getStartRowNum
     *
     * @return
     */
    public int getStartRowNum() {
        return (pageNum - 1) * pageSize + 1;
    }

    /**
     * For Oracle, getEndRowNum
     *
     * @return
     */
    public int getEndRowNum() {
        return pageNum * pageSize;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(100);
        sb.append("pageNum=").append(pageNum).append(", pageSize=").append(pageSize);
        sb.append(", getOffset()=").append(getOffset()).append(", getLimit()=").append(getLimit());
        sb.append(", getStartRowNum()=").append(getStartRowNum()).append(", getEndRowNum()=").append(getEndRowNum());
        return sb.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + pageNum;
        result = prime * result + pageSize;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        PaginationQueryParam other = (PaginationQueryParam) obj;
        if (pageNum != other.pageNum) return false;
        if (pageSize != other.pageSize) return false;
        return true;
    }
}
