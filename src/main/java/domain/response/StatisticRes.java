package domain.response;

import java.util.List;

/**
 * StatisticRes
 * Create by cy on 2018/5/10
 **/
public class StatisticRes {

    private List<String> date;
    private List<Integer> downNum;
    private List<Integer> readNum;
    private List<Integer> uploadNum;

    public List<String> getDate() {
        return date;
    }

    public void setDate(List<String> date) {
        this.date = date;
    }

    public List<Integer> getDownNum() {
        return downNum;
    }

    public void setDownNum(List<Integer> downNum) {
        this.downNum = downNum;
    }

    public List<Integer> getReadNum() {
        return readNum;
    }

    public void setReadNum(List<Integer> readNum) {
        this.readNum = readNum;
    }

    public List<Integer> getUploadNum() {
        return uploadNum;
    }

    public void setUploadNum(List<Integer> uploadNum) {
        this.uploadNum = uploadNum;
    }
}
