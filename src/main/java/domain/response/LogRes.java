package domain.response;

import java.util.Date;

/**
 * LogRes
 * Create by cy on 2018/5/9
 **/
public class LogRes {

    private Date createTime;
    private String username;
    private String documentName;
    private int status;

    private Integer readNum;
    private Integer downNum;
    private Integer uploadNum;

    public Integer getReadNum() {
        return readNum;
    }

    public void setReadNum(Integer readNum) {
        this.readNum = readNum;
    }

    public Integer getDownNum() {
        return downNum;
    }

    public void setDownNum(Integer downNum) {
        this.downNum = downNum;
    }

    public Integer getUploadNum() {
        return uploadNum;
    }

    public void setUploadNum(Integer uploadNum) {
        this.uploadNum = uploadNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
