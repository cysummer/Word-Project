package domain.request;

import utils.PaginationQueryParam;

import java.util.Date;

/**
 * LogQueryParam
 * Create by cy on 2018/5/9
 **/
public class LogQueryParam extends PaginationQueryParam {

    /**
     * 创建开始时间
     */
    private Date startTime;

    /**
     * 创建结束时间
     */
    private Date   endTime;

    private String username;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
