package utils;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询结果对象
 * <p>
 * 因为分页查询总是会伴随着总记录数、分页的结果列表，所以抽取这2个属性为一个对象，以便于使用。
 *
 * @author liuxf 2015年12月1日 上午10:20:32
 */
public class PaginationQueryResult<T> implements Serializable {

    private static final long serialVersionUID = 6325806629126583297L;

    /**
     * 总记录数(符合查询条件的总记录数)
     */
    private int               totalSize;

    /**
     * 分页后的结果列表
     */
    private List<T>           resultList;

    /**
     * 获取总记录数
     *
     * @return
     */
    public int getTotalSize() {
        return totalSize;
    }

    /**
     * 设置总记录数
     *
     * @param totalSize
     */
    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    /**
     * 获取分页后的结果列表
     *
     * @return
     */
    public List<T> getResultList() {
        return resultList;
    }

    /**
     * 设置分页后的结果列表
     *
     * @param resultList
     */
    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((resultList == null) ? 0 : resultList.hashCode());
        result = prime * result + totalSize;
        return result;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        PaginationQueryResult other = (PaginationQueryResult) obj;
        if (resultList == null) {
            if (other.resultList != null) return false;
        } else if (!resultList.equals(other.resultList)) return false;
        if (totalSize != other.totalSize) return false;
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("totalSize=").append(totalSize);
        sb.append(", resultList=").append(resultList);
        return sb.toString();
    }

}
