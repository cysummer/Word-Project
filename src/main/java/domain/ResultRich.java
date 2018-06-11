package domain;


import java.io.Serializable;

/**
 * Created by tuwl on 2016/10/19. 该类主要规范双方dubbo服务的调用，确保远程服务调用过程的一致性 dubbo服务接口示例： ResultRich<RemoteResultTO>
 * remoteDubbboService(); 使用ResultRich对远程接口进行包装
 */
public class ResultRich<T> implements Serializable {

    private static final long serialVersionUID = -7930454800828564981L;

    /**
     * 标记是否dubbo服务调用成功，这里的约定是如果远程调用服务内部出现异常则success设置为false
     */
    private boolean           success;

    /**
     * 远端调用code状态码
     */
    private Integer           code;
    /**
     * 远程调用的msg信息
     */
    private String            msg;

    /**
     * 服务端调用返回的数据对象,必须实现序列化接口
     */
    private T                 model;

    public ResultRich() {
        this.success = true;
    }

    public ResultRich(boolean success) {
        this(success, null, null, null);
    }

    public ResultRich(T t) {
        this(true, t, null, null);
    }

    public ResultRich(Integer code, String msg) {
        this(false, null, code, msg);
    }

    public ResultRich(boolean success, T t, Integer code, String msg) {
        this.success = success;
        this.model = t;
        this.code = code;
        this.msg = msg;
    }

    public static <T> ResultRich<T> newInstance() {
        return new ResultRich<T>(true);
    }

    public static <T> ResultRich<T> newInstance(T t) {
        return new ResultRich<T>(true, t, null, null);
    }

    public static <T> ResultRich<T> newInstance(ErrorCode errorCode) {
        return new ResultRich<T>(errorCode == ErrorCode.SUCCESS, null, errorCode.getCode(), errorCode.getMessage());
    }

    public void setSuccess(T t) {
        this.success = true;
        this.model = t;
    }

    public void setFail(ErrorCode errorCode) {
        this.success = false;
        this.code = errorCode.getCode();
        this.msg = errorCode.getMessage();
    }

    public void setFail(Integer code, String msg) {
        this.success = false;
        this.code = code;
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getModel() {
        return model;
    }

    public void setModel(T model) {
        this.model = model;
    }
}
