package cn.recordaccount.common.constant;

/**
 * 状态返回
 * @author yuanmin
 */
public enum ResStatusEnum {

    /**
     * 请求成功，0开头
     */
    SUCCESS("0000", "请求成功"),

    /**
     * 请求异常，9开头
     */
    SERVER_ERROR("9999", "服务器异常，请稍后再试"),
    JWT_TIMEOUT("9001", "jwt标识过期"),
    JWT_SIGN_EXCEPTION("9002", "jwt签名异常"),
    JWT_CHECK_EXCEPTION("9003", "jwt验证异常"),
    JWT_TOKEN_IS_NULL("9004", "jwt token 为空"),


    /**
     * 通用，1开头
     */
    INCORRECT_SUBMISSION_INFORMATION("1000", "提交信息有误"),


    ;

    ResStatusEnum(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    /**
     * 返回码
     */
    private String code;
    /**
     * 返回信息
     */
    private String msg;

    public String getCode() {
        return code;
    }

    void setCode(String code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    void setMsg(String msg) {
        this.msg = msg;
    }
}
