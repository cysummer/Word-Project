package domain;

import java.io.Serializable;

public class ServerResponseEnvelope<T> implements  Serializable {

    private static final long serialVersionUID = 3886783185348718622L;

    /**
     * 泛型，业务处理成功结果.
     */
    private T                 successResult;

    /**
     * 失败原因，如果失败则有值，否则为null
     */
    private String            failReasonStr;

    /**
     * 是否成功.
     *
     * @return true=成功，false=失败
     */
    public boolean isSuccess() {
        return failReasonStr == null;
    }





    public T getSuccessResult() {
        return successResult;
    }

    public void setSuccessResult(T successResult) {
        this.successResult = successResult;
    }

    public String getFailReasonStr() {
        return failReasonStr;
    }

    public void setFailReasonStr(String failReasonStr) {
        this.failReasonStr = failReasonStr;
    }


    public static <T> ServerResponseEnvelope<T> newInstance() {
        return new ServerResponseEnvelope<T>();
    }

    public static <T> ServerResponseEnvelope<T> newInstance(T t) {
        ServerResponseEnvelope<T> serverResponseEnvelope = newInstance();
        serverResponseEnvelope.setSuccessResult(t);
        return serverResponseEnvelope;
    }

    public static <T> ServerResponseEnvelope<T> newInstance(String failMesg) {
        ServerResponseEnvelope<T> serverResponseEnvelope = newInstance();
        serverResponseEnvelope.setFailReasonStr(failMesg);
        return serverResponseEnvelope;
    }
}
