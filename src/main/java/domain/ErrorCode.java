package domain;

public enum ErrorCode {

    /**
	 * 
	 */
    SUCCESS(0, "ok"),
    /**
     * 
     */
    SYSTEM_ERROR(500, "系统异常"),

    LABEL_NAME_REPETITION(600,"标签名重复"),

    FILE_EMPTY(601,"文件为空"),

    /**
     * 参数缺失
     */
    MISSING_PARAMETER(400, "Missing parameter:[{%s}]"),
    /**
     * 无效参数
     */
    INVALID_PARAMETER(400, "Invalid parameter:[{%s}]"),
    /**
     * 参数超长
     */
    PARAMETER_TOO_LONG(400, "Parameter:[{%s}] too long"),
    /**
	 * 
	 */
    LOGIN_EXPIRED(401, "尚未登陆，或登陆超时"),
    /**
     * 
     */
    NO_AUTHORITY(403, "没有权限"),
    /**
     * 
     */
    SEND_CAPTCHA_FAIL(441, "发送验证码失败"),
    /**
     * 
     */
    SEND_CAPTCHA_FREQUENT(442, "发送验证码频繁"),
    /**
     * 
     */
    CAPTCHA_WRONG(443, "验证码不正确"),
    /**
     * 
     */
    LOGIN_NAME_PASSWORD_WRONG(444, "登陆名或密码错误"),
    /**
     * 
     */
    NO_REGISTER(445, "用户尚未注册"),
    /**
     * 
     */
    USER_EXIST(446, "用户已存在"),
    /**
     * 
     */
    USER_NOT_EXIST(447, "用户不存在"),
    /**
     * 
     */
    USER_INVALID(448, "用户不可用"),

    /**
     * 角色不存在
     */
    ROLE_NOT_EXIST(449, "角色不存在"),
    /**
     * 
     */
    ADD_REMIITTER_RISK_NO_EXIST(461, "添加汇款人风险错误，用户不存在"),
    /**
     * 
     */
    ADD_RECIPIENT_RISK_NO_EXIST(462, "添加收款人风险错误，用户不存在"),
    /**
     * 
     */
    AUDIT_KYC_ALREADY_FAILED(463, "kyc已经失败");

    private int    code;
    private String message;

    private ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
