package domain.response;

import java.util.Date;

/**
 * 实现描述：
 * 
 * @author SW LIU 2017年8月23日 下午4:25:25
 */
public class LoginRes {

    private String username;

    private int userId;

    private String sessionId;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}

