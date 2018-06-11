package domain.request;

import utils.PaginationQueryParam;

import java.util.Date;

public class UserQueryParam extends PaginationQueryParam{

    private static final long serialVersionUID = 4855795158184836163L;
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
