package domain.request;

/**
 * 实现描述：
 * 
 * @author SW LIU 2017年8月23日 下午3:19:56
 */
public class LoginReq {

    /**
     * 登陆名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 验证码ID
     */
    private String captchaId;
    /**
     * 验证码
     */
    private String captcha;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCaptchaId() {
        return captchaId;
    }

    public void setCaptchaId(String captchaId) {
        this.captchaId = captchaId;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

}
