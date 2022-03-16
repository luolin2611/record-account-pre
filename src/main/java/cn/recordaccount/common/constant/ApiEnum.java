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
    BILL_QUERY_BILL_INFO("/bill/queryBillInfo", "获取账单信息"),
    BILL_QUERY_MONTH_INCOME_EXPENSE_INFO("/bill/queryMonthIncomeExpenseList", "查询 月支出/月收入 列表"),
    BILL_QUERY_YEAR_BROKE_LINE_LIST("/bill/queryYearBrokeLineList", "查询 查询年账单折线图数据 列表"),
    BILL_QUERY_RECORD_ACCOUNT_INFO_LIST("/bill/billExport", "导出账单的时候，查询记账信息"),


    /**
     * 报表部分
     */
    REPORT_QUERY_REPORT_INFO("/report/queryReportInfo", "获取报表信息"),
    REPORT_QUERY_DETAILS_LIST("/report/queryReportDetailsList", "请求详情列表"),


    ;


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
