package cn.recordaccount.common.constant;

/**
 * @author rollin
 * @date 2021年02月25日10:11:37
 */
public enum ApiEnum {
    /**
     * 用户部分
     */
    USER_LOGIN("/user/login", "用户登录"),
    USER_REGISTER("/user/registerUser", "用户注册"),


    /**
     * 记账部分
     */
    RECORD_ACCOUNT_HOME_INIT_INFO("/home/homeInitInfo", "Home 页面加载获取必要信息"),
    RECORD_ACCOUNT_QUERY_CLASSIFY("/classify/queryClassify", "查询分类"),
    RECORD_ACCOUNT_RECORD_ACCT("/home/addRecordAcct", "记账接口"),


    /**
     * 账单部分
     */
    BILL_QUERY_BILL_INFO("/bill/queryBillInfo", "获取账单信息");


    /**
     * 交易码
     */
    private final String requestMethod;
    /**
     * 描述
     */
    private final String describe;

    ApiEnum(String requestMethod, String describe) {
        this.requestMethod = requestMethod;
        this.describe = describe;
    }

    public String getRequestMethod() {
        return this.requestMethod;
    }

    public String getDescribe() {
        return this.describe;
    }
}
