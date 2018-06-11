package domain.request;

import utils.PaginationQueryParam;

import java.util.Date;

public class DocumentQueryParam extends PaginationQueryParam{

    private static final long serialVersionUID = 4855795158184836163L;

    private String     username;
    private Integer        status;
    private String     userId;

    private Date startTime;

    private Date   endTime;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

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
}
